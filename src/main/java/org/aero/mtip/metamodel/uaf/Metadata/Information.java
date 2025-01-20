package org.aero.mtip.metamodel.uaf.Metadata;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Comment;

public class Information extends Comment {

	public Information(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.INFORMATION;
		this.xmlConstant = XmlTagConstants.INFORMATION;
	}
}