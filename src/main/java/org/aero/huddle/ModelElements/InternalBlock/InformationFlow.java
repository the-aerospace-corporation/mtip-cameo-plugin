package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class InformationFlow extends CommonRelationship {

	public InformationFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INFORMATIONFLOW;
		this.xmlConstant = XmlTagConstants.INFORMATIONFLOW;
		this.sysmlElement = f.createInformationFlowInstance();
	}
}
