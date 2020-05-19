package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ConnectionPointReference extends CommonElement {

	public ConnectionPointReference(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CONNECTIONPOINTREFERENCE;
		this.xmlConstant = XmlTagConstants.CONNECTIONPOINTREFERENCE;
		this.sysmlElement = f.createConnectionPointReferenceInstance();
	}
}
