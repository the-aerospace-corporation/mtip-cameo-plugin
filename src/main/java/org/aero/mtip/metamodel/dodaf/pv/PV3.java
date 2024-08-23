package org.aero.mtip.metamodel.dodaf.pv;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.matrix.AbstractMatrix;

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
