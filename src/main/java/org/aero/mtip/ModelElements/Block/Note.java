/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Note extends CommonElement {
	public Note(String name, String ea_id) {
		super(name, ea_id);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		return null;
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		return null;
	}
}
