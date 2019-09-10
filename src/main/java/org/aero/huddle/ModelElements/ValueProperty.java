package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;

public class ValueProperty extends CommonElement {
	public ValueProperty(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner) {
		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "MD Customization for SysML");
		Stereotype valPropStereotype = StereotypesHelper.getStereotype(project,  "ValueProperty", mdCustomSysml);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Value Property Element");
		}
		
		Property valProp = project.getElementsFactory().createPropertyInstance();
		valProp.setName(name);
		
		StereotypesHelper.addStereotype(valProp, valPropStereotype);
		
		//Add verification that value property can be a child of owner
		if (owner != null) {
			valProp.setOwner(owner);
		} else {
			valProp.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return valProp;
	}
}
