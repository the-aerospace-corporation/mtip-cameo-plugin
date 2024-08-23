/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.metamodel.sysml.sequence;

import java.util.Collection;
import java.util.HashMap;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.metamodel.core.CommonRelationship;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageKind;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageOccurrenceSpecification;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageSort;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageSortEnum;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.OccurrenceSpecification;

public class Message extends CommonRelationship {
	public Message(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
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
		
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureCameoId = Importer.idConversion(xmlElement.getAttribute(XmlTagConstants.SIGNATURE_TAG));
			NamedElement signature = (NamedElement) project.getElementByID(signatureCameoId);
			message.setSignature(signature);
		}
		
		return element;
	}
	
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureID = modelElement.getAttribute(XmlTagConstants.SIGNATURE_TAG);
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(signatureID));
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeMessageKind(attributes, element);
		writeMessageSort(attributes, element);
		writeSignature(relationships, element);
		
//		message.getArgument();
//		message.getConnector();
//		message.getGuard();
//		message.getInteraction();
//		message.getTarget();

		return data;
	}
	
	protected void writeMessageKind(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		MessageKind mk = message.getMessageKind();
		
		org.w3c.dom.Element mkTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_KIND, mk.toString());
		XmlWriter.add(attributes, mkTag);
	}
	
	protected void writeMessageSort(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		MessageSort ms = message.getMessageSort();
		
		org.w3c.dom.Element msTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_SORT, ms.toString());
		XmlWriter.add(attributes, msTag);
	}
	
	protected void writeSignature(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		Element signature = message.getSignature();
		
		if(signature == null || !(signature instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal)) {
			return;
		}
		
		org.w3c.dom.Element signalTag = XmlWriter.createMtipRelationship(signature, XmlTagConstants.SIGNATURE_TAG);
		XmlWriter.add(relationships, signalTag);
	}
	
	@Override
	public void setSupplier() {
		if (supplier == null) {
			Logger.log(String.format("Supplier null for message with import id %s", EAID));
			return;
		}
		
		if (!(supplier instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)) {
			Logger.log(String.format("Unable to set supplier for message as it is not a lifeline but %s.", supplier.getHumanType()));
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)supplier;
		
		Element interaction = element.getOwner();
		
		if (interaction == null) {
			Logger.log("Interaction owner could not be found to create client for message.");
		}
		
		MessageOccurrenceSpecification os = f.createMessageOccurrenceSpecificationInstance();
		os.setOwner(interaction);
		os.getCovered().add(lifeline);
		
		lifeline.getCoveredBy().add(os);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		os.setMessage(message);
		message.setSendEvent(os);
	}
	
	@Override
	public void setClient() {
		if (client == null) {
			Logger.log(String.format("Client null for message with import id %s", EAID));
			return;
		}
		
		if (!(client instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)) {
			Logger.log("Unable to set client for message as it is not a lifeline.");
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)client;
		
		Element interaction = element.getOwner();
		
		if (interaction == null) {
			Logger.log("Interaction owner could not be found to create client for message.");
		}
		
		MessageOccurrenceSpecification os = f.createMessageOccurrenceSpecificationInstance();
		os.setOwner(interaction);
		os.getCovered().add(lifeline);
		
		lifeline.getCoveredBy().add(os);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		
		os.setMessage(message);
		message.setReceiveEvent(os);
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
