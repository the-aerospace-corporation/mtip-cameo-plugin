package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OV5b extends ActivityDiagram {

	public OV5b(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV5B;
		this.xmlConstant = XmlTagConstants.OV5B;
		this.cameoDiagramConstant = "OV-5b Operational Activity Model";
		this.allowableElements = DoDAFConstants.OV5B_TYPES;
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
