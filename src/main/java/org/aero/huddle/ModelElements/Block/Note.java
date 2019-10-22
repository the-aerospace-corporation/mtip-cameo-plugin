package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

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
	public void writeToXML(Element element, Project project, Document root) {
		// TODO Auto-generated method stub
		
	}
}
