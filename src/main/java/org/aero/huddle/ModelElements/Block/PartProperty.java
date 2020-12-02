package org.aero.huddle.ModelElements.Block;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class PartProperty extends CommonElement {
	public PartProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PARTPROPERTY;
		this.xmlConstant = XmlTagConstants.PARTPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {	
		Element partProperty = super.createElement(project, owner, xmlElement);
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "PartProperty", mdCustomizationProfile);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Property Element");
		}
		
		Type classifierType = (Type) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIER_TYPE));
		((TypedElement)partProperty).setType(classifierType);
		
//		Property property = (Property)partProperty;
//		String associationCameoID = xmlElement.getAttribute(XmlTagConstants.ASSOCIATION_PART_PROPERTY_ID);
//		CameoUtils.logGUI("Setting Association of PartProperty to element with id: " + associationCameoID);
//		Association association = (Association) project.getElementByID(associationCameoID);
//		property.setAssociation(association);
		
		StereotypesHelper.addStereotype(partProperty, partPropertyStereotype);
		
		SessionManager.getInstance().closeSession(project);
		return partProperty;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for PartProperty with id: " + modelElement.getEAID());
//		String associationID = modelElement.getAttribute(XmlTagConstants.ASSOCIATION_TAG);
//		Element association = ImportXmlSysml.getOrBuildRelationship(project, parsedXML, associationID);
//		modelElement.addAttribute(XmlTagConstants.ASSOCIATION_PART_PROPERTY_ID, association.getLocalID());
		
		String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
		Element type = ImportXmlSysml.getOrBuildElement(project, parsedXML, classifierID);
		modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, type.getLocalID());
		
	}
	
	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.PARTPROPERTY));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}