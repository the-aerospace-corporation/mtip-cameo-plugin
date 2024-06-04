package org.aero.mtip.uaf.Dictionary;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Definition extends Comment {

	public Definition(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.DEFINITION;
		this.xmlConstant = XmlTagConstants.DEFINITION;
	}
}
