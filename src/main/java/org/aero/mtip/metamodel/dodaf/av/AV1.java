package org.aero.mtip.metamodel.dodaf.av;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class AV1 extends AbstractDiagram {

	public AV1(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.AV1;
		this.xmlConstant = XmlTagConstants.AV1;
		this.cameoDiagramConstant = "AV-1 Overview and Summary Information";
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
