/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import java.util.Arrays;

import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class ProxyPort extends Port {

	public ProxyPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PROXY_PORT;
		this.xmlConstant = XmlTagConstants.PROXY_PORT;
		this.element = f.createPortInstance();
		this.initialStereotypes = Arrays.asList(SysML.getProxyPortStereotype());
	}
}
