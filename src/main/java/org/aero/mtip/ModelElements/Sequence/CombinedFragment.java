/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Sequence;

import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperand;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperatorKind;
import com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.InteractionOperatorKindEnum;

public class CombinedFragment extends CommonElement {
	public static final String newInteractionOperand = "newInteractionOperand";
	public CombinedFragment(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.COMBINED_FRAGMENT;
		this.xmlConstant = XmlTagConstants.COMBINED_FRAGMENT;
		this.element = f.createCombinedFragmentInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment combinedFragment = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)element;
		
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
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeOperatorKind(attributes, element);
		writeInteractionOperand(relationships, element);
		
		return data;
	}
	
	private void writeOperatorKind(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment combinedFragment = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)element;
		InteractionOperatorKind ioKind = combinedFragment.getInteractionOperator();
		
		if (ioKind == null) {
			return;
		}
		
		org.w3c.dom.Element ioKindTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERATOR_KIND, ioKind.toString());
		XmlWriter.add(attributes, ioKindTag);
	}
	
	private void writeInteractionOperand(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment combinedFragment = (com.nomagic.uml2.ext.magicdraw.interactions.mdfragments.CombinedFragment)element;
		List<InteractionOperand> interactionOperands = combinedFragment.getOperand();
		
		for(InteractionOperand interactionOperand : interactionOperands) {
			org.w3c.dom.Element interactionOperandTag = XmlWriter.createMtipRelationship(interactionOperand, XmlTagConstants.ATTRIBUTE_NAME_INTERACTION_OPERAND);
			XmlWriter.add(relationships, interactionOperandTag);
		}
	}
}
