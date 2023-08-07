package org.aero.mtip.dodaf.div;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class DIV3 extends AbstractDiagram {

	public DIV3(String name, String EAID) {
		super(name, EAID);		
		this.sysmlConstant = DoDAFConstants.DIV3;
		this.xmlConstant = XmlTagConstants.DIV3;
		this.cameoDiagramConstant = "DIV-3 Physical Data Model";
		this.allowableElements = DoDAFConstants.DIV3_TYPES;
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
