/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class RequiredInterface extends CommonElement {

	public RequiredInterface(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		return null;
	}
}
