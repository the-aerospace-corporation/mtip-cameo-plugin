package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Domain extends CommonElement {

	public Domain(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

}
