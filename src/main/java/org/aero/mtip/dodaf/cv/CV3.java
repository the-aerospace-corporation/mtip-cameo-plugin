package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV3 extends ClassDiagram {

	public CV3(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV3;
		this.xmlConstant = XmlTagConstants.CV3;
		this.cameoDiagramConstant = "CV-3";
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
