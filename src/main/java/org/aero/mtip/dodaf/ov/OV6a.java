package org.aero.mtip.dodaf.ov;

import org.aero.mtip.ModelElements.Table.AbstractTable;
import org.aero.mtip.dodaf.DoDAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OV6a extends AbstractTable {

	public OV6a(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = DoDAFConstants.OV6A;
		this.xmlConstant = XmlTagConstants.OV6A;
//		this.cameoDiagramConstant = "OV-1 High-Level Operational Concept Graphic";
//		this.allowableElements = DoDAFConstants.OV6A_TYPES;
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
