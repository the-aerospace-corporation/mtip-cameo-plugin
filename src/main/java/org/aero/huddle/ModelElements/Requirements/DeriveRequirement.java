package org.aero.huddle.ModelElements.Requirements;

import org.aero.huddle.ModelElements.CommonRelationship;
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
import com.nomagic.magicdraw.sysml.util.SysMLProfile;

public class DeriveRequirement extends CommonRelationship {

	public DeriveRequirement(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier) {
		Profile sysml = StereotypesHelper.getProfile(project, "SysML");
		Stereotype deriveRequirementStereotype = StereotypesHelper.getStereotype(project,  SysMLProfile.DERIVEREQT_STEREOTYPE, sysml);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Derive Requirement Relationship");
		}
		
		Element DeriveRequirement = project.getElementsFactory().createAbstractionInstance();
		StereotypesHelper.addStereotype(DeriveRequirement, deriveRequirementStereotype);
		
		ModelHelper.setClientElement(DeriveRequirement, client);
		ModelHelper.setSupplierElement(DeriveRequirement, supplier);
		((NamedElement)DeriveRequirement).setName(name);
		DeriveRequirement.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return DeriveRequirement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.DERIVEREQUIREMENT));
		data.appendChild(type);
		
		
//		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);	
		
	}

}
