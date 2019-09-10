package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class ValueType extends CommonElement{
	public ValueType(String name, String EAID)  {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner) {
		ElementsFactory f = project.getElementsFactory();
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype valueTypeStereotype = StereotypesHelper.getStereotype(project, "Block", sysmlProfile);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create ValueType Element");
		}
		Element sysmlElement = f.createDataTypeInstance();
		((NamedElement)sysmlElement).setName(name);
		StereotypesHelper.addStereotype(sysmlElement, valueTypeStereotype);
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
}