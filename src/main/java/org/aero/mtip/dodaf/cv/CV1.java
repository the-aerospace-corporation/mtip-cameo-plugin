package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV1 extends AbstractDiagram {

	public CV1(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.CV1;
		this.xmlConstant = XmlTagConstants.CV1;
		this.cameoDiagramConstant = "CV-1 Vision";
		this.allowableElements = DoDAFConstants.CV1_TYPES;
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
