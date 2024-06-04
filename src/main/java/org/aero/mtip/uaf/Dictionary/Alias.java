package org.aero.mtip.uaf.Dictionary;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Alias extends Comment {

	public Alias(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.ALIAS;
		this.xmlConstant = XmlTagConstants.ALIAS;
	}
}
