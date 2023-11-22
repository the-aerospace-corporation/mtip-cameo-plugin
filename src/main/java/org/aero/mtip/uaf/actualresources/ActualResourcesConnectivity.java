package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ActualResourcesConnectivity extends BlockDefinitionDiagram {

	public ActualResourcesConnectivity(String name, String EAID) {
		 super(name, EAID);
		 this.metamodelConstant = UAFConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.ACTUAL_RESOURCES_CONNECTIVITY_DIAGRAM;
		 this.cameoDiagramConstant = CameoDiagramConstants.ACTUAL_RESOURCES_CONNECTIVITY;
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
