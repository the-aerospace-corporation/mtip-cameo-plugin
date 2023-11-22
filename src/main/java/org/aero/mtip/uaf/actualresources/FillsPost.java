package org.aero.mtip.uaf.actualresources;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class FillsPost extends Abstraction {

	public FillsPost(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.FILLS_POST;
		this.metamodelConstant = UAFConstants.FILLS_POST;
	}
}
