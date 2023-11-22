package org.aero.mtip.dodaf.div;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DIV2 extends AbstractDiagram {

	public DIV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.DIV2;
		this.xmlConstant = XmlTagConstants.DIV2;
		this.cameoDiagramConstant = "DIV-2 Logical Data Model";
		this.allowableElements = DoDAFConstants.DIV2_TYPES;
	}

	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}

	@Override
	public String getDiagramType() {
		return xmlConstant;
	}

}
