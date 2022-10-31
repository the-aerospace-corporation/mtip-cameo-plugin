package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OV1 extends AbstractDiagram {

	public OV1(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.OV1;
		this.xmlConstant = XmlTagConstants.OV1;
		this.cameoDiagramConstant = "OV-1 High-Level Operational Concept Graphic";
		this.allowableElements = DoDAFConstants.OV1_TYPES;
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
