package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.activity.ActivityDiagram;

public class OV2 extends ActivityDiagram {

	public OV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV2;
		this.xmlConstant = XmlTagConstants.OV2;
		this.cameoDiagramConstant = "OV-2 Operational Resource Flow Description";
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
