package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class ConstraintBlock extends CommonElement {

	public ConstraintBlock(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype constraintBlockStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.CONSTRAINTBLOCK_STEREOTYPE, sysmlProfile);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		Element sysmlElement = f.createClassInstance();
		((NamedElement)sysmlElement).setName(name);
		
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		StereotypesHelper.addStereotype(sysmlElement, constraintBlockStereotype);
		SessionManager.getInstance().closeSession(project);
		
		return sysmlElement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

}