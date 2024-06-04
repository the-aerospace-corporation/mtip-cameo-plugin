package org.aero.mtip.dodaf.pv;

import org.aero.mtip.ModelElements.Matrix.AbstractMatrix;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PV3 extends AbstractMatrix {

	public PV3(String name, String EAID) {
		super(name, EAID);		
		this.metamodelConstant = DoDAFConstants.PV3;
		this.xmlConstant = XmlTagConstants.PV3;
		this.cameoDiagramConstant = "";
	}

//	@Override
//	public String getSysmlConstant() {
//		return cameoDiagramConstant;
//	}
//
//	@Override
//	public String getDiagramType() {
//		return xmlConstant;
//	}
}
