package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Property extends CommonElement {
	public Property(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner) {		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Property Element");
		}
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property prop = project.getElementsFactory().createPropertyInstance();
		prop.setName(name);
		
		//Add verification that property is allowed to be a child of owner
		if (owner != null) {
			prop.setOwner(owner);
		} else {
			prop.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return prop;
	}
}