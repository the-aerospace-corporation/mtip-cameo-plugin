package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV5 extends ClassDiagram {

	public CV5(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV5;
		this.xmlConstant = XmlTagConstants.CV5;
		this.cameoDiagramConstant = "DODAF2_CV-5";
	}
	
	@Override
	public String getCameoDiagramConstant() {
		//Going to need to find what Cameo calls DoDAF Diagrams
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}