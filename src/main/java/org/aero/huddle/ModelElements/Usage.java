package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Usage extends CommonDirectedRelationship {

	public Usage(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.USAGE;
		this.xmlConstant = XmlTagConstants.USAGE;
		this.sysmlElement = f.createUsageInstance();
	}
}
