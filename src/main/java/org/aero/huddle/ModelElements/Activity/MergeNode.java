package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class MergeNode extends ActivityNode {

	public MergeNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.MERGENODE;
		this.xmlConstant = XmlTagConstants.MERGENODE;
		this.sysmlElement = f.createMergeNodeInstance();
	}
}
