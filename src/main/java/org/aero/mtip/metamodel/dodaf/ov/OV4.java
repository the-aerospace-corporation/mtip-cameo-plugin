package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.usecase.UseCaseDiagram;

public class OV4 extends UseCaseDiagram {

	public OV4(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV4;
		this.xmlConstant = XmlTagConstants.OV4;
		this.cameoDiagramConstant = "OV-4 Organizational Relationships Chart";
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
