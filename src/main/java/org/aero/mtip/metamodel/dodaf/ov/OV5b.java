package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class OV5b extends ActivityDiagram {

	public OV5b(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV5B;
		this.xmlConstant = XmlTagConstants.OV5B;
		this.cameoDiagramConstant = "OV-5b Operational Activity Model";
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
