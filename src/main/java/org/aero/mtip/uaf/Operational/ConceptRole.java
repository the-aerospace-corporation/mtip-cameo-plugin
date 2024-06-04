package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ConceptRole extends Property {

	public ConceptRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONCEPT_ROLE;
		this.xmlConstant = XmlTagConstants.CONCEPT_ROLE;
	}
}
