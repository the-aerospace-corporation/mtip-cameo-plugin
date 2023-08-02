package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ActualResourcesConnectivity extends BlockDefinitionDiagram {

	public ActualResourcesConnectivity(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = UAFConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = "Actual Resources Connectivity";
		 this.allowableElements = UAFConstants.AR_CN_TYPES;
	}
	
	@Override
	public  String getSysmlConstant() {
		return this.cameoDiagramConstant;
	}
	
	@Override
	public  String getDiagramType() {
		return this.xmlConstant;
	}
}
