package org.aero.mtip.metamodel.dodaf.ov;

import org.aero.mtip.constants.DoDAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.table.AbstractTable;

public class OV6a extends AbstractTable {

	public OV6a(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = DoDAFConstants.OV6A;
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
