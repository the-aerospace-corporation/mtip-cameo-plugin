package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class DataStoreNode extends ActivityNode {

	public DataStoreNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DATASTORENODE;
		this.xmlConstant = XmlTagConstants.DATASTORENODE;
		this.sysmlElement = f.createDataStoreNodeInstance();
	}
}
