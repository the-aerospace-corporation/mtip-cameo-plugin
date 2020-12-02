package org.aero.huddle.ModelElements.UseCase;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class ExtensionPoint extends CommonElement {

	public ExtensionPoint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.EXTENSIONPOINT;
		this.xmlConstant = XmlTagConstants.EXTENSIONPOINT;
		this.sysmlElement = f.createExtensionPointInstance();
	}
}
