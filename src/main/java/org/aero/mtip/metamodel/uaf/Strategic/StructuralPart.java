package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class StructuralPart extends Property {

	public StructuralPart(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.STRUCTURAL_PART;
		this.xmlConstant = XmlTagConstants.STRUCTURAL_PART;
	}
}
