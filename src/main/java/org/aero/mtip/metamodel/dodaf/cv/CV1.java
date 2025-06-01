package org.aero.mtip.metamodel.dodaf.cv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class CV1 extends AbstractDiagram {

	public CV1(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.CV1;
		this.xmlConstant = XmlTagConstants.CV1;
		this.cameoDiagramConstant = "CV-1 Vision";
	}
	
	@Override
	public String getCameoDiagramConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}
}
