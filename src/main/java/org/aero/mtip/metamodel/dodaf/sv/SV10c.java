package org.aero.mtip.metamodel.dodaf.sv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class SV10c extends BlockDefinitionDiagram {

	public SV10c(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.SV10C;
		this.xmlConstant = XmlTagConstants.SV10C;
		this.cameoDiagramConstant = "SV-10c Systems Event-Trace Description";
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
