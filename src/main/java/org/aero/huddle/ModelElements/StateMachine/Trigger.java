package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Trigger extends CommonElement {

	public Trigger(String name, String EAID) {
		super(name, EAID);
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
