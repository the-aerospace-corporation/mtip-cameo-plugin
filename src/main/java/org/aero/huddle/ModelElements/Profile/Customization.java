package org.aero.huddle.ModelElements.Profile;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Customization extends CommonElement {
	private final String ALLOWEDRELATIONSHIPS = "allowedRelationships";
	private final String DISALLOWEDRELATIONSHIPS = "disallowedRelationships";
	private final String TYPESFORTARGET = "typesForTarget";
	private final String TYPESFORSOURCE = "typesForSource";
	private final String CUSTOMIZATIONTARGET = "customizationTarget";
	private final String CUSTOMIZATIONTYPE = "customizationType";
	private final String STEREOTYPEDRELATIONSHIP = "stereotyped relationship";
	private final String METARELATIONSHIP = "metarelationship";
	
	private final String UML_PROFILE = "UML";
//	private final HashMap<String, String> umlStereotypeMap = new HashMap<String, String> ();
	
	public Customization(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.CUSTOMIZATION;
//		this.seedUMLStereotypeMap();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Customization Element");
		}
		Element customizationTarget = null;
		NamedElement client = (NamedElement)project.getElementByID(xmlElement.getAttribute("client"));
		NamedElement supplier = (NamedElement)project.getElementByID(xmlElement.getAttribute("supplier"));
		
		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
		Element customization = createClassWithStereotype(project, this.name, customizationStereotype, owner);
		
		String profileName = xmlElement.getRelationshipStereotypeProfile();
		String relationshipStereotype = xmlElement.getRelationshipStereotype();
		
		String customizationType = xmlElement.getAttribute(CUSTOMIZATIONTYPE);
		if(customizationType.contentEquals(STEREOTYPEDRELATIONSHIP)) {
			customizationTarget = (Element) project.getElementByID(xmlElement.getAttribute("customizationTarget"));
			StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, CUSTOMIZATIONTARGET, customizationTarget);
		}
		
		if(customizationType.contentEquals(METARELATIONSHIP)) {
			// Composition and Aggregate UML types are Associations with different member end types (composite and aggregation)
			if(relationshipStereotype.contentEquals("Composition")) {
				relationshipStereotype = "Association";
			}
			if(relationshipStereotype.contentEquals("Aggregate")) {
				relationshipStereotype = "Association";
			}
			
			if(profileName.contentEquals(UML_PROFILE)) {
				customizationTarget = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::" + relationshipStereotype);
				StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, CUSTOMIZATIONTARGET, customizationTarget);
			}
		} else {
			customizationTarget = (Element) project.getElementByID(xmlElement.getAttribute("customizationTarget"));
			StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, CUSTOMIZATIONTARGET, customizationTarget);
		}
		
		String clientID = xmlElement.getAttribute("client");
		String supplierID = xmlElement.getAttribute("supplier");
		
		StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, TYPESFORSOURCE, project.getElementByID(supplierID));
		StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, TYPESFORTARGET, project.getElementByID(clientID));
		
		//Finish eliciting name from customization target, supplier, client
		String name = "";
		if(customizationTarget != null && supplier != null && client != null) {
			name = ((NamedElement)customizationTarget).getName() + "_" + supplier.getName() + "_" + client.getName();
		}
//		} else if (supplier != null) {
//			name = ((NamedElement)customizationTarget).getName() + "_" + supplier.getName();
//		} else if (client != null) {
//			name = ((NamedElement)customizationTarget).getName() + "_" + client.getName();
//		} else {
//			name = ((NamedElement)customizationTarget).getName();
//		}
		
		name.replace("___",  "_").replace("__", "_");
		((NamedElement)customization).setName(name);
		
		
		SessionManager.getInstance().closeSession(project);
		return customization;
		//Cameo Format below
//		if (!SessionManager.getInstance().isSessionCreated(project)) {
//			SessionManager.getInstance().createSession(project, "Create Customization Element");
//		}
//		
//		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
//		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
//		Element customization = createClassWithStereotype(project, this.name, customizationStereotype, owner);
//		
//		List<String> fields = Arrays.asList(TYPESFORTARGET, TYPESFORSOURCE, ALLOWEDRELATIONSHIPS, DISALLOWEDRELATIONSHIPS, CUSTOMIZATIONTARGET);
//		if(xmlElement != null) {
//			for(String field: fields) {
//				String elementID = xmlElement.getAttribute(field);
//				if(elementID != null && !elementID.isEmpty()) {
//					StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, field, project.getElementByID(elementID));
//				}
//			}
//		}
	}
	
	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
		
		// Find the elements from the following customization property fields and store their Cameo ID in the xml under the appropriate attribute field
		List<String> fields = Arrays.asList(TYPESFORTARGET, TYPESFORSOURCE, ALLOWEDRELATIONSHIPS, DISALLOWEDRELATIONSHIPS, CUSTOMIZATIONTARGET);
		
		Element customizationTarget = (Element) StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, CUSTOMIZATIONTARGET);
		org.w3c.dom.Element customizationTargetTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_STEREOTYPE);
		customizationTargetTag.appendChild(xmlDoc.createTextNode(customizationTarget.getLocalID()));
		attributes.appendChild(customizationTargetTag);
		
		Element client = (Element) StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, TYPESFORTARGET);
		org.w3c.dom.Element clientTag = xmlDoc.createElement(XmlTagConstants.CLIENT_ID);
		clientTag.appendChild(xmlDoc.createTextNode(client.getLocalID()));
		attributes.appendChild(clientTag);
		
		Element supplier = (Element) StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, TYPESFORSOURCE);
		org.w3c.dom.Element supplierTag = xmlDoc.createElement(XmlTagConstants.SUPPLIER_ID);
		clientTag.appendChild(xmlDoc.createTextNode(supplier.getLocalID()));
		attributes.appendChild(supplierTag);
		
//		for(String field : fields) {
//			List<Element> propList = StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, field);
//			Iterator<Element> iter = propList.iterator();
//			while(iter.hasNext()) {
//				Element elem = iter.next();
//				
//				org.w3c.dom.Element fieldTag = xmlDoc.createElement(field);
//				fieldTag.appendChild(xmlDoc.createTextNode(elem.getLocalID()));
//				attributes.appendChild(fieldTag);
//			}
//		}
//		
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(this.xmlConstant));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
