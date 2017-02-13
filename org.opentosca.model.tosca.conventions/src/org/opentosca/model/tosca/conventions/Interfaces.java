package org.opentosca.model.tosca.conventions;

/**
 * This class holds the names of the well-known interfaces and their operations.
 * 
 * 
 * @author Kalman Kepes - kalman.kepes@iaas.uni-stuttgart.de
 *
 */
public class Interfaces {

	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_CLOUDPROVIDER = "CloudProviderInterface";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_CLOUDPROVIDER_CREATEVM = "createVM";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_CLOUDPROVIDER_TERMINATEVM = "terminateVM";

	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM = "OperatingSystemInterface";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_WAITFORAVAIL = "waitForAvailability";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_RUNSCRIPT = "runScript";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_TRANSFERFILE = "transferFile";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_INSTALLPACKAGE = "installPackage";
	
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_DOCKERENGINE = "InterfaceDockerEngine";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_DOCKERENGINE_STARTCONTAINER = "startContainer";
	
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_DOCKERCONTAINER = "ContainerManagementInterface";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_DOCKERCONTAINER_RUNSCRIPT = "runScript";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_DOCKERCONTAINER_TRANSFERFILE = "transferFile";

	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_PARAMETER_PACKAGENAMES = "PackageNames";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_PARAMETER_SCRIPT = "Script";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_PARAMETER_TARGETABSOLUTPATH = "TargetAbsolutePath";
	public static final String OPENTOSCA_DECLARATIVE_INTERFACE_OPERATINGSYSTEM_PARAMETER_SOURCEURLORLOCALPATH= "SourceURLorLocalPath";
}
