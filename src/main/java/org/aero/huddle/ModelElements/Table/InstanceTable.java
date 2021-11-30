package org.aero.huddle.ModelElements.Table;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InstanceTable extends AbstractTable {
	public InstanceTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.INSTANCE_TABLE;
		this.xmlConstant = XmlTagConstants.INSTANCE_TABLE;
		this.cameoConstant = SysmlConstants.CAMEO_INSTANCE_TABLE;
	}
}
