/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

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
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document root) {
		return null;
	}
}
