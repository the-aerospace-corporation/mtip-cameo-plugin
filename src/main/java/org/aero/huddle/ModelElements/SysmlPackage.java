package org.aero.huddle.ModelElements;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class SysmlPackage extends CommonElement{
	public SysmlPackage(String name, String EAID)  {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Package Element");
		}
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package sysmlPackage = f.createPackageInstance();
		((NamedElement)sysmlPackage).setName(name);
		if(owner != null) {
			sysmlPackage.setOwner(owner);
		} else {
			sysmlPackage.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlPackage;
	}
}
