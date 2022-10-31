package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OV5a extends AbstractDiagram {

	public OV5a(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.OV5A;
		this.xmlConstant = XmlTagConstants.OV5A;
		this.cameoDiagramConstant = "OV-5a Operational Activity Decomposition Tree";
		this.allowableElements = DoDAFConstants.OV5A_TYPES;
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
