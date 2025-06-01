package org.aero.mtip.metamodel.uaf.actualresources;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.general.Abstraction;

public class FillsPost extends Abstraction {

	public FillsPost(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.FILLS_POST;
		this.metamodelConstant = UAFConstants.FILLS_POST;
	}
}
