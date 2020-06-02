package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class Model extends CommonElement {
	
	public Model(String name, String EAID)  {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.MODEL;
		this.xmlConstant = XmlTagConstants.MODEL;
		this.sysmlElement = f.createModelInstance();
	}
}
