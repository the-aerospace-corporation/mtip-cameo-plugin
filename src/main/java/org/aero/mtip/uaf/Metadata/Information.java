package org.aero.mtip.uaf.Metadata;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Information extends Comment {

	public Information(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.INFORMATION;
		this.xmlConstant = XmlTagConstants.INFORMATION;
	}
}