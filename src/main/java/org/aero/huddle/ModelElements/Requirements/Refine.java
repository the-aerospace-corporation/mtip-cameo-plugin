package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Refine extends CommonRelationship {

	public Refine(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		Profile sysml = StereotypesHelper.getProfile(project, "SysML");
		Stereotype refineStereotype = StereotypesHelper.getStereotype(project,  "Refine", sysml);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Refine Relationship");
		}
		
		Element refine = project.getElementsFactory().createAbstractionInstance();
		StereotypesHelper.addStereotype(refine, refineStereotype);
		
		ModelHelper.setClientElement(refine, client);
		ModelHelper.setSupplierElement(refine, supplier);
		((NamedElement)refine).setName(name);
		refine.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return refine;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.REFINE));
		data.appendChild(type);
		
		
//		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);	
	}

}
