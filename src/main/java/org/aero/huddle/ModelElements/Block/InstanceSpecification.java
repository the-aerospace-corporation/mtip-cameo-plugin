package org.aero.huddle.ModelElements.Block;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Classifier;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class InstanceSpecification extends CommonElement {

	public InstanceSpecification(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.INSTANCESPECIFICATION;
		this.xmlConstant = XmlTagConstants.INSTANCESPECIFICATION;
		this.sysmlElement = f.createInstanceSpecificationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element instance = super.createElement(project, owner, xmlElement);
		
		if(xmlElement.hasAttribute(XmlTagConstants.CLASSIFIED_BY)) {
			Classifier classifier = (Classifier) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIED_BY));
			ModelHelper.setClassifierForInstanceSpecification(classifier, (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification) instance, true);
		}
		
		SessionManager.getInstance().closeSession(project);
		return instance;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.INSTANCESPECIFICATION));
		data.appendChild(type);
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification is = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceSpecification)element;
		List<Classifier> classifiers = is.getClassifier();
		
		for(Classifier classifier : classifiers) {
			org.w3c.dom.Element classifierTag = xmlDoc.createElement("classifiedBy");
			classifierTag.appendChild(xmlDoc.createTextNode(classifier.getLocalID()));
			relationships.appendChild(classifierTag);
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}

}
