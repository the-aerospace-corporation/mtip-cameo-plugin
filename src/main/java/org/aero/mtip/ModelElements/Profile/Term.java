/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.profiles.MagicDraw;
import org.aero.mtip.util.XmlTagConstants;

public class Term extends CommonElement {
	public Term(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = SysmlConstants.TERM;
		this.xmlConstant = XmlTagConstants.TERM;
		this.creationStereotype = MagicDraw.getTermStereotype();
	}
}
