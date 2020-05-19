package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class JoinNode extends CommonElement {

	public JoinNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.JOINNODE;
		this.xmlConstant = XmlTagConstants.JOINNODE;
		this.sysmlElement = f.createJoinNodeInstance();
	}
}
