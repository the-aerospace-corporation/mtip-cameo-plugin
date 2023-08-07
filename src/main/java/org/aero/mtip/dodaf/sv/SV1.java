package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SV1 extends ClassDiagram {

	public SV1(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV1;
		this.xmlConstant = XmlTagConstants.SV1;
		this.cameoDiagramConstant = "SV-1 Systems Interface Description";
		this.allowableElements = DoDAFConstants.SV1_TYPES;
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
