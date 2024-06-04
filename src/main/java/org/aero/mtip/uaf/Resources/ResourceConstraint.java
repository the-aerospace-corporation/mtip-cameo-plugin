package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Profile.Constraint;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ResourceConstraint extends Constraint{
	public ResourceConstraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.RESOURCE_CONSTRAINT;
		this.xmlConstant = XmlTagConstants.RESOURCE_CONSTRAINT;
	}
}
