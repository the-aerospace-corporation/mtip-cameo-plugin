/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.paths.SeqBaseMessageView;
import com.nomagic.magicdraw.uml.symbols.shapes.CombinedFragmentView;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.MessageKindEnum;

@SuppressWarnings("deprecation")
public class SequenceDiagram  extends AbstractDiagram {
	// Used on Export
	private HashMap<String, org.w3c.dom.Element> messageTags = new HashMap<String, org.w3c.dom.Element>();
	private HashMap<String, Rectangle> messageBounds = new HashMap<String, Rectangle>();
	private HashMap<String, Rectangle> combinedFragmentBounds = new HashMap<String, Rectangle>();
	
	// Used on Import
	private HashMap<String, PresentationElement> presentationElementsById = new HashMap<String, PresentationElement>();
	
	public SequenceDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SEQUENCEDIAGRAM;
		 this.allowableElements = SysmlConstants.SEQ_TYPES;
	}
	
	
	@Override
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) throws ReadOnlyElementException {
		if (!diagram.isEditable()) {
			Logger.log(String.format("Diagram %s with id %s is not editable.", diagram.getHumanName(), diagram.getID()));
			return;
		}
		
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);		
		removeAutopopulatedPathElements(presentationDiagram);
		
		Message previousMessage = null;
		List<Element> createLater = new ArrayList<Element> ();
		
		for (Element relationship : relationships) {
			if (relationship instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint) {
				createLater.add(relationship);
				continue;
			}
			
			if (relationship instanceof Message) {
				previousMessage = addMessageToDiagram(presentationDiagram, (Message) relationship, previousMessage);
				continue;
			}
			
			addRelationshipToDiagram(presentationDiagram, relationship);
		}
		
		for (Element relationship : createLater) {
			if (relationship instanceof com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint) {
				createDurationConstraintView(relationship);
			}
		}
	}
	
	protected void addRelationshipToDiagram(DiagramPresentationElement presentationDiagram, Element relationship) throws ReadOnlyElementException {
		PresentationElement supplierPE = getSupplierPE(presentationDiagram, relationship);
		PresentationElement clientPE = getClientPE(presentationDiagram, relationship);
		
		if (supplierPE == null || clientPE == null) {
			return;
		}
		
		PathElement pathElement = PresentationElementsManager.getInstance().createPathElement(relationship, clientPE, supplierPE);
		presentationElementsById.put(relationship.getID(), pathElement);
	}
	
	protected Message addMessageToDiagram(DiagramPresentationElement presentationDiagram, Message message, Message previousMessage) throws ReadOnlyElementException {		
		PathElement pathElement = createMessagePathElement(presentationDiagram, message, previousMessage);
		
		if (pathElement == null) {
			return previousMessage;
		}

		presentationElementsById.put(message.getID(), pathElement);
		return message;
	}
	
	@CheckForNull
	protected PathElement createMessagePathElement(DiagramPresentationElement presentationDiagram, Message message, Message previousMessage) throws ReadOnlyElementException {
		PresentationElement supplierPE = getSupplierPE(presentationDiagram, message);
		PresentationElement clientPE = getClientPE(presentationDiagram, message);
		
		if (message.getMessageKind() == MessageKindEnum.LOST && clientPE != null) {
			 PresentationElementsManager.getInstance().createLostMessage(message, (ShapeElement) clientPE, previousMessage, 0);
		}
		
		if (message.getMessageKind() == MessageKindEnum.FOUND && supplierPE != null) {
			PresentationElementsManager.getInstance().createFoundMessage(message, (ShapeElement) supplierPE, previousMessage, 0);
		}
		
		if (supplierPE == null 
				|| clientPE == null 
				|| !(supplierPE instanceof ShapeElement) 
				|| !(clientPE instanceof ShapeElement)) {
			return null;
		}
		
		return PresentationElementsManager.getInstance().createSequenceMessage(message, message.getMessageSort(), (ShapeElement)clientPE, (ShapeElement)supplierPE, false, 0, previousMessage, 25);
	}
	
	private void removeAutopopulatedPathElements(DiagramPresentationElement presentationDiagram) throws ReadOnlyElementException {
		List<PresentationElement> presentationElements = presentationDiagram.getPresentationElements();
		
		for(PresentationElement presentationElement : presentationElements) {
			if(!(presentationElement instanceof PathElement)) {
				continue;
			}
			
			PresentationElementsManager.getInstance().deletePresentationElement(presentationElement);
		}
	}
	
	private void createDurationConstraintView(Element relationship) throws ReadOnlyElementException {
		com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint dc = (com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationConstraint)relationship;
		
		if (dc.getConstrainedElement().size() < 2) {
			Logger.log(String.format("Duration Constraint no placed on diagram. Requires 2 constrained elements."));
			return;
		}
		
		PresentationElement pe1 = presentationElementsById.get(dc.getConstrainedElement().get(0).getID());
		PresentationElement pe2 = presentationElementsById.get(dc.getConstrainedElement().get(1).getID());
		
		if (pe1 == null || pe2 == null) {
			Logger.log(String.format("Path elements for duration constraint %s not created successfully.", relationship.getID()));
			return;
		}
		
		if (!(pe1 instanceof PathElement) || !(pe2 instanceof PathElement)) {
			Logger.log(String.format("Duration Constraint not added to diagram. Constrained elements not path elements for %s.", relationship.getID()));
			return;
		}
		
		PresentationElementsManager.getInstance().createDurationConstraint(dc, (PathElement)pe1, (PathElement)pe2);
	}
	
	@CheckForNull
	protected PresentationElement getSupplierPE(DiagramPresentationElement presentationDiagram, Element relationship) {
		Element supplier = ModelHelper.getSupplierElement(relationship);
		
		if (supplier == null) {
			return null;
		}
		
		return presentationDiagram.findPresentationElementForPathConnecting(supplier, null);
	}
	
	@CheckForNull
	protected PresentationElement getClientPE(DiagramPresentationElement presentationDiagram, Element relationship) {
		Element client = ModelHelper.getClientElement(relationship);
		
		if (client == null) {
			return null;
		}
		
		return presentationDiagram.findPresentationElementForPathConnecting(client, null);
	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.SEQUENCEDIAGRAM;
	}
	
	@Override
	protected void writeDiagramElement(org.w3c.dom.Element elementListTag, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		super.writeDiagramElement(elementListTag, presentationElement, parentPresentationElement);
		
		if(presentationElement instanceof CombinedFragmentView) {
			Element cfElement = presentationElement.getElement();
			combinedFragmentBounds.put(cfElement.getID(), presentationElement.getBounds());
		}
	}
	
	@Override
	protected void writeRelationshipMetadata(org.w3c.dom.Element diagramElementTag, PresentationElement presentationElement) {
		org.w3c.dom.Element relDataTag = XmlWriter.createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		if (presentationElement instanceof SeqBaseMessageView) {
			writeMessageNumber(relDataTag, presentationElement);
		}
		
		Rectangle bounds = presentationElement.getBounds();
		
		org.w3c.dom.Element topTag = XmlWriter.createTag(XmlTagConstants.TOP, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(-bounds.y));
		org.w3c.dom.Element bottomTag = XmlWriter.createTag(XmlTagConstants.BOTTOM, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(-bounds.y - bounds.height));
		org.w3c.dom.Element leftTag = XmlWriter.createTag(XmlTagConstants.LEFT, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(bounds.x));
		org.w3c.dom.Element rightTag = XmlWriter.createTag(XmlTagConstants.RIGHT, XmlTagConstants.ATTRIBUTE_TYPE_INT, String.valueOf(bounds.x + bounds.width));
	
		XmlWriter.add(relDataTag, topTag);
		XmlWriter.add(relDataTag, bottomTag);
		XmlWriter.add(relDataTag, leftTag);
		XmlWriter.add(relDataTag, rightTag);
		
		XmlWriter.add(diagramElementTag, relDataTag);
	}
	
	protected void writeMessageNumber(org.w3c.dom.Element relDataTag, PresentationElement presentationElement) {
		Iterator<String> iterator = ((SeqBaseMessageView)presentationElement).getNumber().iterator();
		
		if (!iterator.hasNext()) {
			return;
		}
		
		org.w3c.dom.Element messageNumberTag = XmlWriter.createMtipStringAttribute(XmlTagConstants.MESSAGE_NUMBER, iterator.next());
		XmlWriter.add(relDataTag, messageNumberTag);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		
		writeCoveredMessages();
		
		return data;
	}
	
	protected void writeCoveredMessages() {
		for (Map.Entry<String, Rectangle> entry : combinedFragmentBounds.entrySet()) {
		    String cfID = entry.getKey();
		    Rectangle cfBounds = entry.getValue();
		    
		    for (Map.Entry<String, Rectangle> entry2 : messageBounds.entrySet()) {
			    String messageID = entry2.getKey();
			    Rectangle messageBounds = entry2.getValue();
			    
			    if(!cfBounds.contains(messageBounds)) {
			    	continue;
			    }
			    
		    	org.w3c.dom.Element messageTag = messageTags.get(messageID);
		    	org.w3c.dom.Element relationshipMetadataTag = XmlWriter.createTag(XmlTagConstants.RELATIONSHIP_METADATA);
		    	org.w3c.dom.Element coveredByTag = XmlWriter.createTag(XmlTagConstants.COVERED_BY);
		    	XmlWriter.setText(coveredByTag, cfID);
		    	
		    	XmlWriter.add(relationshipMetadataTag, coveredByTag);
		    	XmlWriter.add(messageTag, relationshipMetadataTag);
		    }
		}
	}
}
