package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourcesProcessFlow extends ActivityDiagram {
	
	public ResourcesProcessFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCES_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.RESOURCES_PROCESS_FLOW;
		this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_PROCESS_FLOW;
	}
}
