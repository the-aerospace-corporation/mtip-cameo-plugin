package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Interaction extends CommonElement {

	public Interaction(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INTERACTION;
		this.xmlConstant = XmlTagConstants.INTERACTION;
		this.sysmlElement = f.createInteractionInstance();
	}
}
