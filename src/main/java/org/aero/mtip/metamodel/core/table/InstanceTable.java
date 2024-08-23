/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.metamodel.core.table;

import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class InstanceTable extends AbstractTable {
	public InstanceTable(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = SysmlConstants.INSTANCE_TABLE;
		this.xmlConstant = XmlTagConstants.INSTANCE_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_INSTANCE_TABLE;
	}
}
