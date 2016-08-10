package org.opentosca.planbuilder.prephase.plugin.scriptiaonlinux.handler;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.opentosca.planbuilder.model.tosca.AbstractArtifactReference;
import org.opentosca.planbuilder.model.tosca.AbstractDeploymentArtifact;
import org.opentosca.planbuilder.model.tosca.AbstractImplementationArtifact;
import org.opentosca.planbuilder.model.tosca.AbstractNodeTemplate;
import org.opentosca.model.tosca.conventions.Properties;
import org.opentosca.planbuilder.plugins.context.TemplatePlanContext;
import org.opentosca.planbuilder.plugins.context.TemplatePlanContext.Variable;
import org.opentosca.planbuilder.provphase.plugin.invoker.Plugin;
import org.opentosca.planbuilder.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * This class contains logic to upload files to a linux machine. Those files
 * must be available trough a openTOSCA Container
 * </p>
 * Copyright 2013 IAAS University of Stuttgart <br>
 * <br>
 *
 * @author Kalman Kepes - kalman.kepes@iaas.uni-stuttgart.de
 *
 */
public class Handler {

	private final static Logger LOG = LoggerFactory.getLogger(Handler.class);

	private Plugin invokerPlugin = new Plugin();

	private ResourceHandler res;

	/**
	 * Constructor
	 */
	public Handler() {
		try {
			this.res = new ResourceHandler();
		} catch (ParserConfigurationException e) {
			Handler.LOG.error("Couldn't initialize internal ResourceHandler", e);
		}
	}

	/**
	 * Adds necessary BPEL logic trough the given context that can upload the
	 * given DA unto the given InfrastructureNode
	 *
	 * @param context
	 *            a TemplateContext
	 * @param da
	 *            the DeploymentArtifact to deploy
	 * @param nodeTemplate
	 *            the NodeTemplate which is used as InfrastructureNode
	 * @return true iff adding logic was successful
	 */
	public boolean handle(TemplatePlanContext context, AbstractDeploymentArtifact da,
			AbstractNodeTemplate nodeTemplate) {
		List<AbstractArtifactReference> refs = da.getArtifactRef().getArtifactReferences();
		return this.handle(context, refs, da.getName(), nodeTemplate);
	}

	/**
	 * Adds necessary BPEL logic through the given context that can upload the
	 * given IA unto the given InfrastructureNode
	 *
	 * @param context
	 *            a TemplateContext
	 * @param ia
	 *            the ImplementationArtifact to deploy
	 * @param nodeTemplate
	 *            the NodeTemplate which is used as InfrastructureNode
	 * @return true iff adding logic was successful
	 */
	public boolean handle(TemplatePlanContext context, AbstractImplementationArtifact ia,
			AbstractNodeTemplate nodeTemplate) {
		// fetch references
		List<AbstractArtifactReference> refs = ia.getArtifactRef().getArtifactReferences();
		return this.handle(context, refs, ia.getArtifactType().getLocalPart() + "_" + ia.getOperationName() + "_IA",
				nodeTemplate);

	}

	/**
	 * Adds necessary BPEL logic through the given Context, to deploy the given
	 * ArtifactReferences unto the specified InfrastructureNode
	 *
	 * @param context
	 *            a TemplateContext
	 * @param refs
	 *            the ArtifactReferences to deploy
	 * @param artifactName
	 *            the name of the artifact, where the references originate from
	 * @param nodeTemplate
	 *            a NodeTemplate which is a InfrastructureNode to deploy the
	 *            AbstractReferences on
	 * @return true iff adding the logic was successful
	 */
	private boolean handle(TemplatePlanContext templateContext, List<AbstractArtifactReference> refs,
			String artifactName, AbstractNodeTemplate nodeTemplate) {
		
		LOG.debug("Handling DA upload with");
		String refsString = "";
		for(AbstractArtifactReference ref : refs){
			refsString += ref.getReference() + ", ";
		}
		LOG.debug("Refs:" + refsString.substring(0, refsString.lastIndexOf(",")));
		LOG.debug("ArtifactName: " + artifactName);
		LOG.debug("NodeTemplate: " + nodeTemplate.getId() + "(Type: " + nodeTemplate.getType().getId().toString() + ")");
				
		// fetch server ip of the vm this artefact will be deployed on
		
		Variable serverIpPropWrapper = null;
		for (String serverIpName : org.opentosca.model.tosca.conventions.Utils.getSupportedVirtualMachineIPPropertyNames()) {
			serverIpPropWrapper = templateContext.getPropertyVariable(nodeTemplate,serverIpName);
			if(serverIpPropWrapper != null){
				break;
			}
//			if (serverIpPropWrapper == null) {
//				serverIpPropWrapper = templateContext.getPropertyVariable(serverIpName, true);
//				if (serverIpPropWrapper == null) {
//					serverIpPropWrapper = templateContext.getPropertyVariable(serverIpName, false);
//				}else {
//					break;
//				}
//			} else {
//				break;
//			}
		}

		if (serverIpPropWrapper == null) {
			Handler.LOG.warn("No Infrastructure Node available with ServerIp property");
			return false;
		}

		// find sshUser and sshKey
		Variable sshUserVariable = null;
		for (String vmLoginName : org.opentosca.model.tosca.conventions.Utils.getSupportedVirtualMachineLoginUserNamePropertyNames()) {
			sshUserVariable = templateContext.getPropertyVariable(nodeTemplate,vmLoginName);
			if(sshUserVariable != null){
				break;
			}
//			if (sshUserVariable == null) {
//				sshUserVariable = templateContext.getPropertyVariable(vmLoginName, true);
//				if (sshUserVariable == null) {
//					sshUserVariable = templateContext.getPropertyVariable(vmLoginName, false);
//				}else {
//					break;
//				}
//			} else {
//				break;
//			}
		}

		// if the variable is null now -> the property isn't set properly
		if (sshUserVariable == null) {
			return false;
		} else {
			if (Utils.isVariableValueEmpty(sshUserVariable, templateContext)) {
				// the property isn't set in the topology template -> we set it
				// null here so it will be handled as an external parameter
				sshUserVariable = null;
			}
		}

		Variable sshKeyVariable = null;
		for (String vmLoginPassword : org.opentosca.model.tosca.conventions.Utils.getSupportedVirtualMachineLoginPasswordPropertyNames()) {
			sshKeyVariable = templateContext.getPropertyVariable(nodeTemplate, vmLoginPassword);
			if(sshKeyVariable != null){
				break;
			}
//			if (sshKeyVariable == null) {
//				sshKeyVariable = templateContext.getPropertyVariable(vmLoginPassword, true);
//				if (sshKeyVariable == null) {
//					sshKeyVariable = templateContext.getPropertyVariable(vmLoginPassword, false);
//				}else {
//					break;
//				}
//			} else {
//				break;
//			}
		}
		// if variable null now -> the property isn't set according to schema
		if (sshKeyVariable == null) {
			return false;
		} else {
			if (Utils.isVariableValueEmpty(sshKeyVariable, templateContext)) {
				// see sshUserVariable..
				sshKeyVariable = null;
			}
		}

		// add sshUser and sshKey to the input message of the build plan, if
		// needed
		if (sshUserVariable == null) {
			String cleanName = serverIpPropWrapper.getName().substring(serverIpPropWrapper.getName().lastIndexOf("_")+1);
			switch (cleanName) {
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_SERVERIP:
				LOG.debug("Adding sshUser field to plan input");
				templateContext.addStringValueToPlanRequest("sshUser");
				break;
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_VMIP:
				LOG.debug("Adding sshUser field to plan input");
				templateContext.addStringValueToPlanRequest(Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_VMLOGINNAME);
				break;
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_RASPBIANIP:
				templateContext.addStringValueToPlanRequest(Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_RASPBIANUSER);
				break;
			default:
				return false;

			}

		}

		if (sshKeyVariable == null) {
			LOG.debug("Adding sshKey field to plan input");
			String cleanName = serverIpPropWrapper.getName().substring(serverIpPropWrapper.getName().lastIndexOf("_")+1);
			switch (cleanName) {
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_SERVERIP:
				LOG.debug("Adding sshKey field to plan input");
				templateContext.addStringValueToPlanRequest("sshKey");
				break;
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_VMIP:
				templateContext
						.addStringValueToPlanRequest(Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_VMLOGINPASSWORD);
				break;
			case Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_RASPBIANIP:
				templateContext.addStringValueToPlanRequest(Properties.OPENTOSCA_DECLARATIVE_PROPERTYNAME_RASPBIANPASSWD);
				break;
			default:
				return false;

			}
		}

		// find the ubuntu node and its nodeTemplateId
		String templateId = nodeTemplate.getId();

		if (templateId.equals("")) {
			Handler.LOG.warn("Couldn't determine NodeTemplateId of Ubuntu Node");
			return false;
		}

		// adds field into plan input message to give the plan it's own address
		// for the invoker PortType (callback etc.). This is needed as WSO2 BPS
		// 2.x can't give that at runtime (bug)
		LOG.debug("Adding plan callback address field to plan input");
		templateContext.addStringValueToPlanRequest("planCallbackAddress_invoker");

		// add csarEntryPoint to plan input message
		LOG.debug("Adding csarEntryPoint field to plan input");
		templateContext.addStringValueToPlanRequest("csarEntrypoint");

		LOG.debug("Handling DA references:");
		for (AbstractArtifactReference ref : refs) {
			// upload da ref and unzip it
			this.invokerPlugin.handleArtifactReferenceUpload(ref, templateContext, serverIpPropWrapper, sshUserVariable,
					sshKeyVariable, templateId);
		}

		return true;
	}

}
