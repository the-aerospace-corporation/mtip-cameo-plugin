/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;

public class DecisionNode extends ActivityNode {

	public DecisionNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.DECISIONNODE;
		this.xmlConstant = XmlTagConstants.DECISIONNODE;
		this.sysmlElement = f.createDecisionNodeInstance();
	}
	
	@Override
	public void addDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		// Create decision input to be assigned to decision node, should it exist
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT)) {
			Element decisionInput = ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT)), modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT));
			if(decisionInput instanceof Behavior) {
				com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode decisionNode = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode)sysmlElement;
				decisionNode.setDecisionInput((Behavior)decisionInput);
			}
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
				
		com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode decisionNode = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode)element;
		Behavior decisionInput = decisionNode.getDecisionInput();
		
		if(decisionInput != null) {
			org.w3c.dom.Element decisionInputTag = createRel(xmlDoc, decisionInput, XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT);
//			org.w3c.dom.Element operationTag = createRel(xmlDoc, operation, XmlTagConstants.DECISION_INPUT);
			relationships.appendChild(decisionInputTag);
		}
		return data;
	}
}
