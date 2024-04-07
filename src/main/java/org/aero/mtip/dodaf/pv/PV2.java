package org.aero.mtip.dodaf.pv;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PV2 extends AbstractDiagram {

	public PV2(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.PV2;
		this.xmlConstant = XmlTagConstants.PV2;
		this.cameoDiagramConstant = "";
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
