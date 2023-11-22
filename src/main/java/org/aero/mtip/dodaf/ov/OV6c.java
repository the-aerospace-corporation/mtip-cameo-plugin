package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.Sequence.SequenceDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OV6c extends SequenceDiagram {

	public OV6c(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV6C;
		this.xmlConstant = XmlTagConstants.OV6C;
		this.cameoDiagramConstant = "OV-6c Operational Event-Trace Description";
		this.allowableElements = DoDAFConstants.OV6C_TYPES;
	}
	
	@Override
	public String getSysmlConstant() {
		return cameoDiagramConstant;
	}
	
	@Override
	public String getDiagramType() {
		return this.xmlConstant;
	}

}
