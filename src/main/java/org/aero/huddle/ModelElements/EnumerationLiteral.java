package org.aero.huddle.ModelElements;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

public class EnumerationLiteral extends CommonElement {

	public EnumerationLiteral(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ENUMERATIONLITERAL;
		this.xmlConstant = XmlTagConstants.ENUMERATIONLITERAL;
		this.sysmlElement = f.createEnumerationLiteralInstance();
	}

}
