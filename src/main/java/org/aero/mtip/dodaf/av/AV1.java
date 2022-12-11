package org.aero.mtip.dodaf.av;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class AV1 extends AbstractDiagram {

	public AV1(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.AV1;
		this.xmlConstant = XmlTagConstants.AV1;
		this.cameoDiagramConstant = "AV-1 Overview and Summary Information";
		this.allowableElements = DoDAFConstants.AV1_TYPES;
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
