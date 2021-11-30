package org.aero.huddle.ModelElements.Table;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class MetricTable extends AbstractTable {

	public MetricTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.METRIC_TABLE;
		this.xmlConstant = XmlTagConstants.METRIC_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_METRIC_TABLE;
	}

}
