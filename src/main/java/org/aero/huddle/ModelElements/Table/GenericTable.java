package org.aero.huddle.ModelElements.Table;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class GenericTable extends AbstractTable {
	public GenericTable(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = SysmlConstants.GENERIC_TABLE;
		this.xmlConstant = XmlTagConstants.GENERIC_TABLE;
	}
}
