/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;

public class DecisionNode extends ActivityNode {

	public DecisionNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.DECISION_NODE;
		this.xmlConstant = XmlTagConstants.DECISION_NODE;
		this.element = f.createDecisionNodeInstance();
	}
	
	@Override
	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		// Create decision input to be assigned to decision node, should it exist
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT)) {
			Element decisionInput = ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT)));
			
			
			if(decisionInput instanceof Behavior) {
				com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode decisionNode = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode)element;
				decisionNode.setDecisionInput((Behavior)decisionInput);
			}
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
				
		writeDecisionInput(relationships, element);
		
		return data;
	}
	
	public void writeDecisionInput(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode decisionNode = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.DecisionNode)element;
		Behavior decisionInput = decisionNode.getDecisionInput();
		
		if(decisionInput == null) {
			return;
		}
		
		org.w3c.dom.Element decisionInputTag = XmlWriter.createMtipRelationship(decisionInput, XmlTagConstants.ATTRIBUTE_NAME_DECISION_INPUT);
		XmlWriter.add(relationships, decisionInputTag);
	}
}
