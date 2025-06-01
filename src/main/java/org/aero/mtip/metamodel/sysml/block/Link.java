/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.block;

import org.aero.mtip.metamodel.core.CommonRelationship;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Link extends CommonRelationship {
	public Link(String name, String EAID) {
		super(name, EAID);
	}
	/**
		CURRENTLY NOT SUPPORTED - MAY BE DEPRECATED
	*/
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		return null;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		return null;
	}
}
