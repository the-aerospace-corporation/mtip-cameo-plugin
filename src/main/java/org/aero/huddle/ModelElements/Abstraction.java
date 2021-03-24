package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Abstraction extends CommonDirectedRelationship {

	public Abstraction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.ABSTRACTION;
		this.sysmlConstant = SysmlConstants.ABSTRACTION;
		this.sysmlElement = f.createAbstractionInstance();
	}
}
