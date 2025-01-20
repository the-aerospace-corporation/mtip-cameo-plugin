/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.profile;

import java.util.HashMap;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

@Deprecated
public class RelationshipConstraint extends CommonElement {
	public final static String CUSTOMIZATION_TARGET_XML_ID = "customizationTargetID";
	
	private final static String CUSTOMIZATIONTARGET = "customizationTarget";
	private final String ALLOWEDRELATIONSHIPS = "allowedRelationships";
	private final String DISALLOWEDRELATIONSHIPS = "disallowedRelationships";
	private final String TYPESFORTARGET = "typesForTarget";
	private final String TYPESFORSOURCE = "typesForSource";
	private final String CUSTOMIZATIONTYPE = "customizationType";
	private final String STEREOTYPEDRELATIONSHIP = "stereotyped relationship";
	private final String METARELATIONSHIP = "metarelationship";
	private final String IS_INCLUSIVE = "isInclusiveOfBaseClass";
	
	private final String UML_PROFILE = "UML";
//	private final HashMap<String, String> umlStereotypeMap = new HashMap<String, String> ();
	
	public static Element CHECK_RELATIONSHIPS_DIRECTION = null;
	
	public RelationshipConstraint(String name, String EAID) {
		super(name, EAID);
		this.xmlConstant = XmlTagConstants.CUSTOMIZATION;
//		this.seedUMLStereotypeMap();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element customizationTarget = null;
		
		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
		Element customization = createClassWithStereotype(project, this.name, customizationStereotype, owner);
		
		String profileName = xmlElement.getRelationshipStereotypeProfile();
		String relationshipStereotype = xmlElement.getRelationshipStereotype();
		
		String customizationType = xmlElement.getAttribute(CUSTOMIZATIONTYPE);

		NamedElement client = (NamedElement)xmlElement.getClientElement();
		NamedElement supplier = (NamedElement)xmlElement.getSupplierElement();
		
//		if(customizationType.contentEquals(METARELATIONSHIP)) {
//			// Composition and Aggregate UML types are Associations with different member end types (composite and aggregation)
//			CameoUtils.logGui("\t...metarelationship steretoype: " + relationshipStereotype);
//			if(relationshipStereotype.contentEquals("Composition")) {
//				CameoUtils.logGui("\t...customization target composition.");
//				if(Importer.CREATE_VALIDATION_ON_IMPORT) {
//					ValidationRuleGenerator.createCompositionRule(project, Importer.MODEL_VALIDATION_PACKAGE, supplier, client);
//				}
//				relationshipStereotype = "Association";
//			}
//			if(relationshipStereotype.contentEquals("Aggregate")) {
//				relationshipStereotype = "Association";
//				if(Importer.CREATE_VALIDATION_ON_IMPORT) {
//					ValidationRuleGenerator.createAggregationRule(project, Importer.MODEL_VALIDATION_PACKAGE, supplier, client);
//				}
//			}
//			
//			if(profileName.contentEquals(UML_PROFILE)) {
//				customizationTarget = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::" + relationshipStereotype);
//				StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, CUSTOMIZATIONTARGET, customizationTarget);
//			}
//		} else {
//			CameoUtils.logGui("Setting customization target as : " + xmlElement.getAttribute(CUSTOMIZATIONTARGET) + " with xml id " + xmlElement.getAttribute(CUSTOMIZATION_TARGET_XML_ID));
//			customizationTarget = (Element) project.getElementByID(xmlElement.getAttribute(CUSTOMIZATIONTARGET));
//			StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, CUSTOMIZATIONTARGET, customizationTarget);
//		}
		
		
		if(customizationTarget != null) {
			CameoUtils.logGui("\t...Setting customization target to " + customizationTarget.getHumanName());
		}
		if(client != null) {
			CameoUtils.logGui("\t...Adding client " + client.getName() + " to typesForSource field");
			StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, TYPESFORTARGET, client);
		}
		if(supplier != null) {
			CameoUtils.logGui("\t...Adding supplier " + supplier.getName() + " to typesForTargetField");
			StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, TYPESFORSOURCE, supplier);
		}
		
		//Finish eliciting name from customization target, supplier, client
		String name = "";

		if(customizationTarget != null && supplier != null && client != null) {
			name = ((NamedElement)customizationTarget).getName() + "_" + ((NamedElement)supplier).getName() + "_" + ((NamedElement)client).getName();
		} else if (customizationTarget != null && supplier != null) {
			name = ((NamedElement)customizationTarget).getName() + "_" + supplier.getName();
		} else if (customizationTarget != null && client != null) {
			name = ((NamedElement)customizationTarget).getName() + "_" + client.getName();
		} else if(customizationTarget != null) {
			name = ((NamedElement)customizationTarget).getName();
		} else {
			Logger.log("No customization target for customization with id: " + EAID);
		}
		
		name.replace("___",  "_").replace("__", "_");
		((NamedElement)customization).setName(name);
				
		return customization;
	}
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGui("\t...Creating dependent elements for customization with id: " + modelElement.getImportId());
		if(modelElement.hasClient()) {
			String clientID = modelElement.getClient();
			if(parsedXML.containsKey(clientID)) {
				CameoUtils.logGui("\t...Finding or creating client for customization with client id: " + clientID);
				Element client = Importer.getInstance().buildElement(parsedXML, parsedXML.get(clientID));
				modelElement.setClientElement(client);
			} else {
				CameoUtils.logGui("\t...Client element not in XML. Missing element with id: " + clientID);
			}
		} else {
			CameoUtils.logGui("\t...No client found for customization with id: " + modelElement.getImportId());
		}
		if(modelElement.hasSupplier()) {
			String supplierID = modelElement.getSupplier();
			if(parsedXML.containsKey(supplierID)) {
				CameoUtils.logGui("\t...Finding or creating supplier for customization with supplier id: " + supplierID);
				Element supplier = Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getSupplier()));
				modelElement.setSupplierElement(supplier);
			} else {
				CameoUtils.logGui("\t...Supplier element not in XML. Missing element with id: " + supplierID);
			}
		} else {
			CameoUtils.logGui("\t...No supplier found for customization with id: " + modelElement.getImportId());
		}

		if(modelElement.getAttribute(CUSTOMIZATIONTYPE).contentEquals(STEREOTYPEDRELATIONSHIP)) {
			CameoUtils.logGui("\t...Finding or creating customizaxtion target for customization with cusomization id: " + modelElement.getAttribute(CUSTOMIZATION_TARGET_XML_ID));
			String customizationTargetID = modelElement.getAttribute(CUSTOMIZATION_TARGET_XML_ID);
			if(customizationTargetID != null && !customizationTargetID.isEmpty()) {
				if(parsedXML.containsKey(customizationTargetID)) {
					Element stereotype = Importer.getInstance().buildElement(parsedXML, parsedXML.get(customizationTargetID));
					modelElement.addAttribute(CUSTOMIZATIONTARGET, MtipUtils.getId(stereotype));
				}
			}
		}
		
		String isInclusive = modelElement.getAttribute("isInclusiveOfBaseClass");
		if(isInclusive.contentEquals("true")) {
			//Build two more customizations here to restrict the relationships allowed.
		}
	}
	
//	@Override
//	public org.w3c.dom.Element writeToXML(Element element) {
//		org.w3c.dom.Element data = super.writeToXML(element);
//		
//		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
//		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
//		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
//		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, "Customization", umlStandardprofile);
//		
//		// Find the elements from the following customization property fields and store their Cameo ID in the xml under the appropriate attribute field
//		List<String> fields = Arrays.asList(TYPESFORTARGET, TYPESFORSOURCE, ALLOWEDRELATIONSHIPS, DISALLOWEDRELATIONSHIPS, CUSTOMIZATIONTARGET);
//		
//		String stereotypeName = "";
//		
//		org.w3c.dom.Element customizationTargetTag = null;
//		List<Element> customizationTargets = StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, CUSTOMIZATIONTARGET);
//		if(customizationTargets != null && !customizationTargets.isEmpty()) {
//			Element customizationTarget = customizationTargets.get(0);
//			if(customizationTarget != null) {
//				customizationTargetTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_STEREOTYPE);
//				customizationTargetTag.appendChild(xmlDoc.createTextNode(((NamedElement)customizationTarget).getName()));
//				// Possible to replace with checks for Metaclasses and stereotypes - Metaclasses are in UML profile and stereotypes in user-defined profiles
//				if(customizationTarget.getID().startsWith("_9")) {
//					customizationTargetTag.setAttribute(XmlTagConstants.PROFILE_TAG, "UML");
//					stereotypeName = METARELATIONSHIP;
//				} else {
//					stereotypeName = STEREOTYPEDRELATIONSHIP;
//					Stereotype targetStereotype = (Stereotype)customizationTarget;
//					Profile targetProfile = targetStereotype.getProfile();
//					customizationTargetTag.setAttribute(XmlTagConstants.PROFILE_TAG, ((NamedElement)targetProfile).getName());
//				}
//				customizationTargetTag.setAttribute(XmlTagConstants.ID_TAG, customizationTarget.getID());
//				attributes.appendChild(customizationTargetTag);
//			} else {
//				//Export Log
//				return null;
//			}
//		}
//		
//		//Set stereotype field this stereotype corresponds to the metarelationship/stereotyped relationship in EA
//		org.w3c.dom.Element xmlStereotype = xmlDoc.createElement("stereotype");
//		xmlStereotype.appendChild(xmlDoc.createTextNode(stereotypeName));
//		attributes.appendChild(xmlStereotype);
//		
//		//Set customizationType for Cameo import purposes
//		org.w3c.dom.Element xmlCustomization = xmlDoc.createElement("customizationType");
//		xmlCustomization.appendChild(xmlDoc.createTextNode(stereotypeName));
//		attributes.appendChild(xmlCustomization);
//		
//		//Set client
//		List<Element> clients = StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, TYPESFORTARGET);
//		if(clients != null && !clients.isEmpty()) {
//			Element client = clients.get(0);
//			org.w3c.dom.Element clientTag = xmlDoc.createElement(XmlTagConstants.CLIENT_ID);
//			clientTag.appendChild(xmlDoc.createTextNode(client.getID()));
//			relationships.appendChild(clientTag);
//		}
//		
//		//Set supplier
//		List<Element> suppliers = StereotypesHelper.getStereotypePropertyValue(element, customizationStereotype, TYPESFORSOURCE);
//		if(suppliers != null && !suppliers.isEmpty()) {
//			Element supplier = suppliers.get(0);
//			org.w3c.dom.Element supplierTag = xmlDoc.createElement(XmlTagConstants.SUPPLIER);
//			supplierTag.appendChild(xmlDoc.createTextNode(supplier.getID()));
//			relationships.appendChild(supplierTag);
//		}
//		
//		//Add is inclusive attribute.
//		org.w3c.dom.Element inclusiveTag = xmlDoc.createElement(IS_INCLUSIVE);
//		inclusiveTag.appendChild(xmlDoc.createTextNode("false"));
//		attributes.appendChild(inclusiveTag);
//		
//		// Create type field for Sysml model element types
//		org.w3c.dom.Element type = xmlDoc.createElement("type");
//		type.appendChild(xmlDoc.createTextNode(this.xmlConstant));
//		data.appendChild(type);
//		
//		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
//		root.appendChild(data);
//		return data;
//	}
	
//	@Override
//	public org.w3c.dom.Element createBaseXML(Element element, Document xmlDoc) {
//		org.w3c.dom.Element data = xmlDoc.createElement("data");
//		
//		//Add attributes
//		org.w3c.dom.Element attributes = xmlDoc.createElement("attributes");
//		
//		//Add Name
//		if(!name.equals("") && !name.equals(null)) {
//			org.w3c.dom.Element name = xmlDoc.createElement("name");
//			name.appendChild(xmlDoc.createTextNode(this.name));
//			attributes.appendChild(name);
//		} else {
//			org.w3c.dom.Element name = xmlDoc.createElement("name");
//			attributes.appendChild(name);
//		}
//		data.appendChild(attributes);
//		
//		//Add ID
//		org.w3c.dom.Element id = xmlDoc.createElement("id");
//		org.w3c.dom.Element cameoID = xmlDoc.createElement("cameo");
//		cameoID.appendChild(xmlDoc.createTextNode(element.getID()));
//		id.appendChild(cameoID);
//		data.appendChild(id);
//		
//		//Add parent relationship
//		org.w3c.dom.Element relationship = xmlDoc.createElement("relationships");
//		
//		if(element.getOwner() != null) {
//			org.w3c.dom.Element hasParent = xmlDoc.createElement("hasParent");
//			Element parent = null;
//			parent = element.getOwner();
//			hasParent.appendChild(xmlDoc.createTextNode(parent.getID()));
//			relationship.appendChild(hasParent);
//		}
//		data.appendChild(relationship);
//		
//		return data;
//	}
}
