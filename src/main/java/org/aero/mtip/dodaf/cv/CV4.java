package org.aero.mtip.dodaf.cv;

import org.aero.mtip.ModelElements.Profile.ClassDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CV4 extends ClassDiagram {

	public CV4(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV4;
		this.xmlConstant = XmlTagConstants.CV4;
		this.cameoDiagramConstant = "CV-4 Capability Dependencies";
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