package org.aero.mtip.uaf.Metadata;

import org.aero.mtip.ModelElements.Comment;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class ArchitectureMetadata extends Comment {

	public ArchitectureMetadata(String name, String EAID) {
		super(name, EAID);
		this.metamodelConstant = UAFConstants.INFORMATION;
		this.xmlConstant = XmlTagConstants.INFORMATION;
	}
}