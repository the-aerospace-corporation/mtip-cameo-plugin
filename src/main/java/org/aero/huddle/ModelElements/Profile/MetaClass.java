package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class MetaClass extends CommonElement {

	public MetaClass(String name, String EAID) {
		super(name, EAID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Profile Element");
		}
		
		Element metaclass = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::" + this.name);
		CameoUtils.logGUI("Creating metaclass with name: " + this.name);
		CameoUtils.logGUI(metaclass.getLocalID());
		SessionManager.getInstance().closeSession(project);
		return metaclass;
	}

}
