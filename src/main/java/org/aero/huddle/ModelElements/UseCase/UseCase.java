package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class UseCase extends CommonElement {

	public UseCase(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.USECASE;
		this.xmlConstant = XmlTagConstants.USECASE;
		this.sysmlElement = f.createUseCaseInstance();
	}
}
