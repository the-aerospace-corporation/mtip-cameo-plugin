package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Generalization extends CommonDirectedRelationship {
	public Generalization(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.GENERALIZATION;
		this.xmlConstant = XmlTagConstants.GENERALIZATION;
		this.sysmlElement = f.createGeneralizationInstance();
	}
}
