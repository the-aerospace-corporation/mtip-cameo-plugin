package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.UseCase.UseCaseDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OV4 extends UseCaseDiagram {

	public OV4(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.OV4;
		this.xmlConstant = XmlTagConstants.OV4;
		this.cameoDiagramConstant = "OV-4 Organizational Relationships Chart";
		this.allowableElements = DoDAFConstants.OV4_TYPES;
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
