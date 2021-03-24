package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class InterfaceRealization extends CommonRelationship {

	public InterfaceRealization(String name, String EAID) {
		super(name, EAID);
	}
	
	@Override 
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		return null;
	}
}
