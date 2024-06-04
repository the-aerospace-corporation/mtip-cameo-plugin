package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.Table.AbstractTable;
import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class OV3 extends AbstractTable {

	public OV3(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV3;
		this.xmlConstant = XmlTagConstants.OV3;
		//this.cameoDiagramConstant = "OV-3 Operational Resource Flow Description";
		//this.allowableElements = DoDAFConstants.OV3_TYPES;
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
