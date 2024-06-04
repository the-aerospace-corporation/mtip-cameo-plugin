package org.aero.mtip.uaf.Metadata;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class Metadata extends Comment {

	public Metadata(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.METADATA;
		this.xmlConstant = XmlTagConstants.METADATA;
	}
}