/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Table;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class MetricTable extends AbstractTable {

	public MetricTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.METRIC_TABLE;
		this.xmlConstant = XmlTagConstants.METRIC_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_METRIC_TABLE;
	}

}
