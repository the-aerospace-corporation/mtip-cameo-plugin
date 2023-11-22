package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class DataElement extends CommonElement implements UAFElement {
	
	public DataElement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.DATA_ELEMENT;
		this.xmlConstant = XmlTagConstants.DATA_ELEMENT;
	}
}
