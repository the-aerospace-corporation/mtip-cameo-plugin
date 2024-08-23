package org.aero.mtip.metamodel.dodaf.div;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class DIV2 extends AbstractDiagram {

	public DIV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.DIV2;
		this.xmlConstant = XmlTagConstants.DIV2;
		this.cameoDiagramConstant = "DIV-2 Logical Data Model";
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
