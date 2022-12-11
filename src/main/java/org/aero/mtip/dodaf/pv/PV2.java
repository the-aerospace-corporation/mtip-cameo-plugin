package org.aero.mtip.dodaf.pv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class PV2 extends AbstractDiagram {

	public PV2(String name, String EAID) {
		super(name, EAID);		
		this.sysmlConstant = DoDAFConstants.PV2;
		this.xmlConstant = XmlTagConstants.PV2;
		this.cameoDiagramConstant = "";
		this.allowableElements = DoDAFConstants.PV2_TYPES;
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
