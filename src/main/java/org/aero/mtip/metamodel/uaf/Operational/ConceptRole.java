package org.aero.mtip.metamodel.uaf.Operational;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.sequence.Property;

public class ConceptRole extends Property {

	public ConceptRole(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.CONCEPT_ROLE;
		this.xmlConstant = XmlTagConstants.CONCEPT_ROLE;
	}
}
