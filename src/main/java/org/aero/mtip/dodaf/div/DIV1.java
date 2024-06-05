package org.aero.mtip.dodaf.div;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DIV1 extends AbstractDiagram {

	public DIV1(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.DIV1;
		this.xmlConstant = XmlTagConstants.DIV1;
		this.cameoDiagramConstant = "DIV-1 Conceptual Data Model";
	}

	@Override
	public String getCameoDiagramConstant() {
		return cameoDiagramConstant;
	}

	@Override
	public String getDiagramType() {
		return xmlConstant;
	}

}
