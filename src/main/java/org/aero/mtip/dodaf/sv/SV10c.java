package org.aero.mtip.dodaf.sv;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SV10c extends BlockDefinitionDiagram {

	public SV10c(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.SV10C;
		this.xmlConstant = XmlTagConstants.SV10C;
		this.cameoDiagramConstant = "SV-10c Systems Event-Trace Description";
		this.allowableElements = DoDAFConstants.SV10C_TYPES;
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
