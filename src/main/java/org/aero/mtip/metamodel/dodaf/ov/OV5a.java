package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.AbstractDiagram;

public class OV5a extends AbstractDiagram {

	public OV5a(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV5A;
		this.xmlConstant = XmlTagConstants.OV5A;
		this.cameoDiagramConstant = "OV-5a Operational Activity Decomposition Tree";
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
