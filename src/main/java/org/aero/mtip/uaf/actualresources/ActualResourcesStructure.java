package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ActualResourcesStructure extends BlockDefinitionDiagram {
	
	public ActualResourcesStructure(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCES_STRUCTURE_DIAGRAM;
		 this.allowableElements = UAFConstants.AR_SR_TYPES;
	}
}
