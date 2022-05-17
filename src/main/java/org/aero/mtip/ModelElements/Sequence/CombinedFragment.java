/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Sequence;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperatorKind;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperatorKindEnum;

public class CombinedFragment extends CommonElement {
	public static final String newInteractionOperand = "newInteractionOperand";
	public CombinedFragment(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.COMBINEDFRAGMENT;
		this.xmlConstant = XmlTagConstants.COMBINEDFRAGMENT;
		this.sysmlElement = f.createCombinedFragmentInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment combinedFragment = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)sysmlElement;
		
		// Add ioKind
		String ioKind = xmlElement.getInteractionOperatorKind();
		if(ioKind != null && !ioKind.isEmpty()) {
			InteractionOperatorKind kindEnum = InteractionOperatorKindEnum.get(ioKind);
					combinedFragment.setInteractionOperator(kindEnum);
		} else {
			// Can add default ioKind here i.e. InteractionOperatorKindEnum.LOOP
		}
		// Add interaction operands
		if(xmlElement.hasAttribute(CombinedFragment.newInteractionOperand)) {
			List<String> interactionOperands = xmlElement.getNewInteractionOperands();
			for(String interactionOperand : interactionOperands) {
				BaseElement element = project.getElementByID(interactionOperand);
				if(element instanceof InteractionOperand) {
					InteractionOperand cameoInteractionOperand = (InteractionOperand)element;
					combinedFragment.getOperand().add(cameoInteractionOperand);
				}
			}
		}
		return (Element)combinedFragment;
	}
	
//	@Override
//	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
//		if(modelElement.hasInteractionOperands()) {
//			List<String> interactionOperands = modelElement.getInteractionOperands();
//			for(String interactionOperand : interactionOperands) {
//				Element newInteractionOperand = ImportXmlSysml.buildElement(project, parsedXML, modelElement, interactionOperand);
//				modelElement.addAttribute(CombinedFragment.newInteractionOperand, newInteractionOperand.getID());
//			}
//		}
//	}
	
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment combinedFragment = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)element;
		InteractionOperatorKind ioKind = combinedFragment.getInteractionOperator();
		
		org.w3c.dom.Element ioKindTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERATOR_KIND, ioKind.toString());
		attributes.appendChild(ioKindTag);
		CameoUtils.logGUI("Interaction Operator Kind:" + ioKind.toString());
		
		List<InteractionOperand> interactionOperands = combinedFragment.getOperand();
		for(InteractionOperand interactionOperand : interactionOperands) {
			org.w3c.dom.Element interactionOperandTag = createRel(xmlDoc, interactionOperand, XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERAND);
			relationships.appendChild(interactionOperandTag);
			CameoUtils.logGUI("Interaction operands: " + ((NamedElement)interactionOperand).getName());
		}
		
		return data;
	}
}
