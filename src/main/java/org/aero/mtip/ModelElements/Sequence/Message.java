/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.util.Collection;
import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

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
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.MESSAGE;
		this.xmlConstant = XmlTagConstants.MESSAGE;
		this.sysmlElement = f.createMessageInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)sysmlElement;
		
		String messageSort = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_SORT);
		MessageSort messageSortEnum = MessageSortEnum.getByName(messageSort);
		message.setMessageSort(messageSortEnum);
		
		if(xmlElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureCameoId = ImportXmlSysml.idConversion(xmlElement.getAttribute(XmlTagConstants.SIGNATURE_TAG));
			NamedElement signature = (NamedElement) project.getElementByID(signatureCameoId);
			message.setSignature(signature);
		}
		
		return sysmlElement;
	}
	
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		if(modelElement.hasAttribute(XmlTagConstants.SIGNATURE_TAG)) {
			String signatureID = modelElement.getAttribute(XmlTagConstants.SIGNATURE_TAG);
			ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(signatureID));
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
		
		MessageKind mk = message.getMessageKind();
		org.w3c.dom.Element mkTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_KIND, mk.toString());
		
		MessageSort ms = message.getMessageSort();
		org.w3c.dom.Element msTag = createStringAttribute(xmlDoc, XmlTagConstants.ATTRIBUTE_NAME_MESSAGE_SORT, ms.toString());

		Element signature = message.getSignature();
		if(signature != null && signature instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdcommunications.Signal) {
			org.w3c.dom.Element signalTag = createRel(xmlDoc, signature, XmlTagConstants.SIGNATURE_TAG);
			relationships.appendChild(signalTag);
		}
		
		message.getTarget();
		
		attributes.appendChild(mkTag);
		attributes.appendChild(msTag);
		return data;
	}
	
	@Override
	public void setSupplier() {
		if (!(supplier instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)
				|| !(client instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)) {
			ImportLog.log("Unable to set supplier for message as it is not a lifeline.");
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)supplier;
		
		Element interaction = sysmlElement.getOwner();
		
		if (interaction == null) {
			ImportLog.log("Interaction owner could not be found to create client for message.");
		}
		
		MessageOccurrenceSpecification os = f.createMessageOccurrenceSpecificationInstance();
		os.setOwner(interaction);
		os.getCovered().add(lifeline);
		
		lifeline.getCoveredBy().add(os);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)sysmlElement;
		os.setMessage(message);
		message.setSendEvent(os);
	}
	
	@Override
	public void setClient() {
		if (!(client instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)
				|| !(supplier instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)) {
			ImportLog.log("Unable to set client for message as it is not a lifeline.");
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)client;
		
		Element interaction = sysmlElement.getOwner();
		
		if (interaction == null) {
			ImportLog.log("Interaction owner could not be found to create client for message.");
		}
		
		MessageOccurrenceSpecification os = f.createMessageOccurrenceSpecificationInstance();
		os.setOwner(interaction);
		os.getCovered().add(lifeline);
		
		lifeline.getCoveredBy().add(os);
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)sysmlElement;
		
		os.setMessage(message);
		message.setReceiveEvent(os);
	}
	
	@Override
	public void getSupplier(Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		if(message.getSendEvent() instanceof OccurrenceSpecification) {
			OccurrenceSpecification sendEvent = (OccurrenceSpecification) message.getSendEvent();
			Collection<com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline> covered = sendEvent.getCovered();
			this.supplier = covered.iterator().next();
		} else {
			this.supplier = null;
		}
		
	}
	
	@Override
	public void getClient(Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message message = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message)element;
		if(message.getReceiveEvent() instanceof OccurrenceSpecification) {
			OccurrenceSpecification receiveEvent = (OccurrenceSpecification) message.getReceiveEvent();
			Collection<com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline> covered = receiveEvent.getCovered();
			this.client = covered.iterator().next();
		} else {
			this.client = null;
		}
		
	}
}
