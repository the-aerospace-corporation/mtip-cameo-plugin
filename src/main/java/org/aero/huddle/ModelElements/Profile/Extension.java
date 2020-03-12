package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.AggregationKindEnum;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Extension extends CommonRelationship {

	public Extension(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Extension Relationship");
		}
		
		//Supplier and Client are seemingly flipped in XML
		com.nomagic.uml2.ext.magicdraw.mdprofiles.Extension extension = project.getElementsFactory().createExtensionInstance();
		// Directionality is reversed from expected. Export reverses as well to be 'correct' way in XML and import into EA. 
		ModelHelper.setClientElement(extension, client);
		ModelHelper.setSupplierElement(extension, supplier);
		extension.setName(name);
		extension.setOwner(owner);
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property firstMemberEnd = ModelHelper.getFirstMemberEnd(extension);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property secondMemberEnd = ModelHelper.getSecondMemberEnd(extension);
		firstMemberEnd.setAggregation(AggregationKindEnum.NONE);
		ModelHelper.setNavigable(firstMemberEnd, true);
		firstMemberEnd.setOwner(client);
		
		
		secondMemberEnd.setAggregation(AggregationKindEnum.COMPOSITE);
		ModelHelper.setNavigable(secondMemberEnd, true);
		secondMemberEnd.setOwner(extension);
		
		
		
		SessionManager.getInstance().closeSession(project);
		return extension;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.EXTENSION));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
