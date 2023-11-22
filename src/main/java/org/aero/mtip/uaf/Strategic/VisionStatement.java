package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class VisionStatement extends Comment {

	public VisionStatement(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.VISION_STATEMENT;
		this.xmlConstant = XmlTagConstants.VISION_STATEMENT;
	}
}
