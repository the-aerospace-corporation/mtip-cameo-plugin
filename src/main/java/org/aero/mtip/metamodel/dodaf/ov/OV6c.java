package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.SequenceDiagram;

public class OV6c extends SequenceDiagram {

	public OV6c(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV6C;
		this.xmlConstant = XmlTagConstants.OV6C;
		this.cameoDiagramConstant = "OV-6c Operational Event-Trace Description";
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
