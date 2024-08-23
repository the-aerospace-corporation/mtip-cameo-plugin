package org.aero.mtip.metamodel.dodaf.div;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class DIV3 extends AbstractDiagram {

	public DIV3(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.DIV3;
		this.xmlConstant = XmlTagConstants.DIV3;
		this.cameoDiagramConstant = "DIV-3 Physical Data Model";
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
