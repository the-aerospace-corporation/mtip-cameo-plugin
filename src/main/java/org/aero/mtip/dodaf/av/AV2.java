package org.aero.mtip.dodaf.av;

import org.aero.mtip.ModelElements.Table.AbstractTable;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class AV2 extends AbstractTable {

	public AV2(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.AV2;
		this.xmlConstant = XmlTagConstants.AV2;
//		this.cameoDiagramConstant = "AV-1 Overview and Summary Information";
//		this.allowableElements = DoDAFConstants.AV2_TYPES;
	}
	
//	@Override
//	public String getSysmlConstant() {
//		return cameoDiagramConstant;
//	}
//	
//	@Override
//	public String getDiagramType() {
//		return this.xmlConstant;
//	}
}
