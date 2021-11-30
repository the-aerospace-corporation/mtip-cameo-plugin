package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InformationItem extends CommonElement {

	public InformationItem(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INFORMATIONITEM;
		this.xmlConstant = XmlTagConstants.INFORMATIONITEM;
		this.sysmlElement = f.createInformationItemInstance();
	}
	
}
