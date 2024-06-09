package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class CapabilityProperty extends Property {

	public CapabilityProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = UAFConstants.CAPABILITY_PROPERTY;
		this.xmlConstant = XmlTagConstants.CAPABILITY_PROPERTY;
		this.element = f.createPropertyInstance();
	}
}
