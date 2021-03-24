package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Actor extends CommonElement {

	public Actor(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTOR;
		this.xmlConstant = XmlTagConstants.ACTOR;
		this.sysmlElement = f.createActorInstance();
	}
}
