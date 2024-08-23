package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class ResourcesProcessFlow extends ActivityDiagram {
	
	public ResourcesProcessFlow(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.RESOURCES_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.RESOURCES_PROCESS_FLOW;
		this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_PROCESS_FLOW;
	}
}
