package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class StructuralPart extends Property {

	public StructuralPart(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STRUCTURAL_PART;
		this.xmlConstant = XmlTagConstants.STRUCTURAL_PART;
	}
}
