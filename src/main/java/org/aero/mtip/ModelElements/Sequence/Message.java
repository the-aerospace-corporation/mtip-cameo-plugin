/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.Sequence;

import java.util.Collection;
import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageEnd;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageSort;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageSortEnum;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.OccurrenceSpecification;

public class Message extends CommonRelationship {
	public Message(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.MESSAGE;
		this.xmlConstant = XmlTagConstants.MESSAGE;
		this.element = f.createMessageInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		String messageSort = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_SORT);
		MessageSort messageSortEnum = MessageSortEnum.getByName(messageSort);
		message.setMessageSort(messageSortEnum);
//		String messageKind = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_KIND);
//		MessageKind messageKindEnum = MessageKindEnum.getByName(messageKind);
//		message.setMessageKind(messageKindEnum);
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_RECEIVE_EVENT)) {
			MessageEnd receiveEvent = (MessageEnd) project.getElementByID(ImportXmlSysml.completeXML.get(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_RECEIVE_EVENT)).getCameoID());
			message.setReceiveEvent(receiveEvent);
		}
		if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_SEND_EVENT)) {
			MessageEnd sendEvent = (MessageEnd) project.getElementByID(ImportXmlSysml.completeXML.get(xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_SEND_EVENT)).getCameoID());
			message.setSendEvent(sendEvent);
		}
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureCameoId = ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.SIGNATURE_TAG));
			NamedElement signature = (NamedElement) project.getElementByID(signatureCameoId);
			message.setSignature(signature);
		}
		
		return element;
	}
	
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_RECEIVE_EVENT)) {
			String receiveEvent = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_RECEIVE_EVENT);
			ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(receiveEvent), receiveEvent);
		}
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_SEND_EVENT)) {
			String sendEvent = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_SEND_EVENT);
			ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(sendEvent), sendEvent);
		}
		if(modelElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureID = modelElement.getAttribute(XmlTagConstants.SIGNATURE_TAG);
			ImportXmlSysml.getOrBuildElement(project, parsedXML, signatureID);
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		message.getArgument();
		message.getConnector();
		message.getGuard();
		message.getInteraction();
//		MessageKind mk = message.getMessageKind();
//		org.w3c.dom.Element mkTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_KIND, mk.toString());
		MessageSort ms = message.getMessageSort();
		org.w3c.dom.Element msTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_SORT, ms.toString());
		message.getReceiveEvent();
//		MessageEnd receiveEvent = message.getReceiveEvent();
//		MessageEnd sendEvent = message.getSendEvent();
//		if(receiveEvent != null) {
//			org.w3c.dom.Element reTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_RECEIVE_EVENT, receiveEvent.getID());
//			attributes.appendChild(reTag);
//		}
//		message.getReplyMessage();
//
//		if(sendEvent != null) {
//			org.w3c.dom.Element seTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_SEND_EVENT, sendEvent.getID());
//			attributes.appendChild(seTag);
//		}
		Element signature = message.getSignature();
		if(signature != null && signature instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal) {
			org.w3c.dom.Element signalTag = createRel(xmlDoc, signature, XmlTagConstants.SIGNATURE_TAG);
			relationships.appendChild(signalTag);
		}
		message.getTarget();
		
//		attributes.appendChild(mkTag);
		attributes.appendChild(msTag);
		return data;
	}
	
	@Override
	public void setSupplier() {
		// Supplier set at runtime from xmlElement in Message.createElement()
		// Refactor as member to be used here.
	}
	
	@Override
	public void setClient() {
		// Client set at runtime from xmlElement in Message.createElement()
		// Refactor as member to be used here.
	}
	
	@Override
	public Element getSupplier(Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		if(message.getSendEvent() instanceof OccurrenceSpecification) {
			OccurrenceSpecification sendEvent = (OccurrenceSpecification) message.getSendEvent();
			Collection<com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline> covered = sendEvent.getCovered();
			return covered.iterator().next();
		} 
		return null;
	}
	
	@Override
	public Element getClient(Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		if(message.getReceiveEvent() instanceof OccurrenceSpecification) {
			OccurrenceSpecification sendEvent = (OccurrenceSpecification) message.getReceiveEvent();
			Collection<com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline> covered = sendEvent.getCovered();
			return covered.iterator().next();
		} 
		return null;
	}
}
