/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Table;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class RequirementTable extends AbstractTable {

	public RequirementTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.REQUIREMENT_TABLE;
		this.xmlConstant = XmlTagConstants.REQUIREMENT_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_REQUIREMENT_TABLE;
	}
}
