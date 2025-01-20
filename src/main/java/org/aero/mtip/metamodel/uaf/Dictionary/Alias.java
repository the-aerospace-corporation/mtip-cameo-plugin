package org.aero.mtip.metamodel.uaf.Dictionary;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Comment;

public class Alias extends Comment {

	public Alias(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ALIAS;
		this.xmlConstant = XmlTagConstants.ALIAS;
	}
}
