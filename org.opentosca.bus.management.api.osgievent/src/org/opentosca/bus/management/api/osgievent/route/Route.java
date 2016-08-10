package org.opentosca.bus.management.api.osgievent.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.opentosca.bus.management.api.osgievent.Activator;
import org.opentosca.bus.management.header.MBHeader;

/**
 * Route of the Management Bus-OSGiEvent-API.<br>
 * <br>
 * 
 * Copyright 2013 IAAS University of Stuttgart <br>
 * <br>
 * 
 * Incoming events are given here from the EventHandler to be routed to the
 * Management Bus for further processing. The response message is given back to the
 * EventHandler.
 * 
 * 
 * 
 * @author Michael Zimmermann - zimmerml@studi.informatik.uni-stuttgart.de
 * 
 */
public class Route extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		// Management Bus Endpoints
		final String MANAGEMENT_BUS_IA = "bean:org.opentosca.bus.management.service.IManagementBusService?method=invokeIA";
		final String MANAGEMENT_BUS_PLAN = "bean:org.opentosca.bus.management.service.IManagementBusService?method=invokePlan";
		
		this.from("direct:invoke").to("stream:out").process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				
				exchange.getIn().setHeader(MBHeader.APIID_STRING.toString(), Activator.apiID);
				
				String messageID = exchange.getIn().getHeader("MessageID", String.class);
				if (messageID != null) {
					exchange.getIn().setMessageId(messageID);
					exchange.getIn().removeHeader("MessageID");
					exchange.getIn().setHeader(MBHeader.SYNCINVOCATION_BOOLEAN.toString(), "false");
				} else {
					exchange.getIn().setHeader(MBHeader.SYNCINVOCATION_BOOLEAN.toString(), "true");
				}
				
			}
		}).to("stream:out").choice().when(this.header("OPERATION").isEqualTo("invokeIA")).wireTap(MANAGEMENT_BUS_IA).end().when(this.header("OPERATION").isEqualTo("invokePlan")).wireTap(MANAGEMENT_BUS_PLAN).end();
		
	}
}
