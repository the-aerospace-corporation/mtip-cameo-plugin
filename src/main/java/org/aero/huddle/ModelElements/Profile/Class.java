package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.mdusecases.UseCase;
import com.nomagic.uml2.impl.ElementsFactory;

public class Class extends CommonElement {
	public Class(String name, String EAID)  {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		
		Element sysmlElement = null;

		if(xmlElement.hasStereotypes()) {
			Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
			Stereotype blockStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.BLOCK_STEREOTYPE, sysmlProfile);
			// Remove this check after the demo
			if(owner instanceof UseCase) {
				owner = project.getPrimaryModel();
			}
			sysmlElement = createClassWithStereotype(project, name, blockStereotype, owner);
		} else {
			sysmlElement = f.createClassInstance();
			((NamedElement)sysmlElement).setName(name);
			
			if(owner != null) {
				if(owner instanceof UseCase) {
					sysmlElement.setOwner(project.getPrimaryModel());
				} else {
					sysmlElement.setOwner(owner);
				}
			} else {
				sysmlElement.setOwner(project.getPrimaryModel());
			}
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.CLASS));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}
}