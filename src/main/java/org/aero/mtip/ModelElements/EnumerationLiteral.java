/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

public class EnumerationLiteral extends CommonElement {

	public EnumerationLiteral(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ENUMERATION_LITERAL;
		this.xmlConstant = XmlTagConstants.ENUMERATION_LITERAL;
		this.element = f.createEnumerationLiteralInstance();
	}

}
