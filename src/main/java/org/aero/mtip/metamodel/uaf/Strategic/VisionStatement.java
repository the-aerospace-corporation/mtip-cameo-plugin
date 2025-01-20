package org.aero.mtip.metamodel.uaf.Strategic;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Comment;

public class VisionStatement extends Comment {

	public VisionStatement(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.VISION_STATEMENT;
		this.xmlConstant = XmlTagConstants.VISION_STATEMENT;
	}
}
