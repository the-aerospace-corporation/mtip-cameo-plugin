package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

public class VisionStatement extends Comment {

	public VisionStatement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.VISION_STATEMENT;
		this.xmlConstant = XmlTagConstants.VISION_STATEMENT;
		this.sysmlElement = f.createCommentInstance();
		this.creationStereotype = UAFProfile.VISION_STATEMENT_STEREOTYPE;
	}
}
