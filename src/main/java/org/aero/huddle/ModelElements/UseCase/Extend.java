package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.CommonDirectedRelationship;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Extend extends CommonDirectedRelationship {

	public Extend(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.EXTEND;
		this.xmlConstant = XmlTagConstants.EXTEND;
		this.sysmlElement = f.createExtendInstance();
	}
}
