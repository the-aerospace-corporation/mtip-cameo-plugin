package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV2 extends ClassDiagram {

	public CV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV2;
		this.xmlConstant = XmlTagConstants.CV2;
		this.cameoDiagramConstant = "CV-2 Capability Taxonomy";
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
