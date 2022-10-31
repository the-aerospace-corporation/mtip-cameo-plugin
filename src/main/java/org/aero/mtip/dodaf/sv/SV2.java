package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class SV2 extends ClassDiagram {

	public SV2(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV2;
		this.xmlConstant = XmlTagConstants.SV2;
		this.cameoDiagramConstant = "SV-2 Systems Communications Description";
		this.allowableElements = DoDAFConstants.SV2_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		//Going to need to find what Cameo calls DoDAF Diagrams
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}
