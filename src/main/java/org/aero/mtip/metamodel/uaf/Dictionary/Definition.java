package org.aero.mtip.metamodel.uaf.Dictionary;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Comment;

public class Definition extends Comment {

	public Definition(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.DEFINITION;
		this.xmlConstant = XmlTagConstants.DEFINITION;
	}
}
