package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OV2 extends ActivityDiagram {

	public OV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV2;
		this.xmlConstant = XmlTagConstants.OV2;
		this.cameoDiagramConstant = "OV-2 Operational Resource Flow Description";
		this.allowableElements = DoDAFConstants.OV2_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}
