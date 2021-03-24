package org.aero.huddle.ModelElements.Sequence;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class StateInvariant extends CommonElement {

	public StateInvariant(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STATEINVARIANT;
		this.xmlConstant = XmlTagConstants.STATEINVARIANT;
		this.sysmlElement = f.createStateInvariantInstance();
	}
}
