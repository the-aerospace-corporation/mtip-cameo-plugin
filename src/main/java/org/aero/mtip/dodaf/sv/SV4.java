package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SV4 extends ClassDiagram {

	public SV4(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV4;
		this.xmlConstant = XmlTagConstants.SV4;
		this.cameoDiagramConstant = "SV-4 Systems Functionality Description";
		this.allowableElements = DoDAFConstants.SV4_TYPES;
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
