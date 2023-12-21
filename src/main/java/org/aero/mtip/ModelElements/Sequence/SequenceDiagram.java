/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.CheckForNull;

import org.aero.mtip.ModelElements.AbstractDiagram;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

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

@SuppressWarnings("deprecation")
public class SequenceDiagram  extends AbstractDiagram {
	private HashMap<String, org.w3c.dom.Element> messageTags = new HashMap<String, org.w3c.dom.Element>();
	private HashMap<String, Rectangle> messageBounds = new HashMap<String, Rectangle>();
	private HashMap<String, Rectangle> combinedFragmentBounds = new HashMap<String, Rectangle>();
	
	public SequenceDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SEQUENCEDIAGRAM;
		 this.allowableElements = SysmlConstants.SEQ_TYPES;
	}
	
	
	@Override
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) throws ReadOnlyElementException {
		if (!diagram.isEditable()) {
			ImportLog.log(String.format("Diagram %s with id %s is not read only.", diagram.getHumanName(), diagram.getID()));
			return;
		}
		
		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);		
		removeAutopopulatedPathElements(presentationDiagram);
		
		Message previousMessage = null;
		for (Element relationship : relationships) {
			Element client = ModelHelper.getClientElement(relationship);
			Element supplier = ModelHelper.getSupplierElement(relationship);
			
			PresentationElement clientPE = presentationDiagram.findPresentationElementForPathConnecting(client, null);
			
			if (clientPE == null) {
				ImportLog.log(String.format("Client presentation element for relationship with id %s was not created.", relationship.getID()));
				continue;
			}
			
			PresentationElement supplierPE = presentationDiagram.findPresentationElementForPathConnecting(supplier, null);
			
			if (supplierPE == null) {
				ImportLog.log(String.format("Supplier presentation element for relationship with id %s was not created.", relationship.getID()));
				continue;
			}
			
			if(!(relationship instanceof Message)) {
				PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
				continue;
			}
			
			if (!(supplierPE instanceof ShapeElement) || !(clientPE instanceof ShapeElement)) {
				ImportLog.log(String.format("Supplier and client could not be cast to shape elements to create message for message with id %s.", relationship.getID()));
				continue;
			}
			
			Message message = (Message)relationship;
			PresentationElementsManager.getInstance().createSequenceMessage(message, message.getMessageSort(), (ShapeElement)supplierPE, (ShapeElement)clientPE, false, 0, previousMessage, 25);
			 
			previousMessage = (Message)relationship;
		}
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
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.SEQUENCEDIAGRAM;
	}
	
	@Override
	protected org.w3c.dom.Element createDiagramElementTag(Document xmlDoc, PresentationElement presentationElement, PresentationElement parentPresentationElement) {
		org.w3c.dom.Element elementTag = super.createDiagramElementTag(xmlDoc, presentationElement, parentPresentationElement);
		
		if(presentationElement instanceof CombinedFragmentView) {
			Element cfElement = presentationElement.getElement();
			this.combinedFragmentBounds.put(cfElement.getID(), presentationElement.getBounds());
		}
		return elementTag;
	}
	
	@Override @CheckForNull
	protected org.w3c.dom.Element createDiagramRelationshipTag(Document xmlDoc, PresentationElement presentationElement) {
		Element relationship = presentationElement.getElement();
		org.w3c.dom.Element relationshipTag = super.createDiagramRelationshipTag(xmlDoc, presentationElement);
		
		if(relationshipTag == null) {
			ExportLog.log("Failed to create diagram connectors xml block.");
			return null;
		}
		
		org.w3c.dom.Element relDataTag = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
		relDataTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		relationshipTag.appendChild(relDataTag);
		
		if(!(relationship instanceof Message)) {
			return relationshipTag;
		}
		
		SeqBaseMessageView messageView = (SeqBaseMessageView) Project.getProject(relationship).getSymbolElementMap().getPresentationElement(relationship);
		
		if (messageView == null) {
			return relationshipTag;
		}
		
		String number = messageView.getNumber().iterator().next();
		
		org.w3c.dom.Element messageNumberTag = xmlDoc.createElement(XmlTagConstants.MESSAGE_NUMBER);
    	messageNumberTag.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
    	messageNumberTag.appendChild(xmlDoc.createTextNode(number));
    	
    	relDataTag.appendChild(messageNumberTag);
    	
		return relationshipTag;
	}
	
	protected void writeCoveredMessages(Document xmlDoc) {
		for (Map.Entry<String, Rectangle> entry : this.combinedFragmentBounds.entrySet()) {
		    String cfID = entry.getKey();
		    Rectangle cfBounds = entry.getValue();
		    
		    for (Map.Entry<String, Rectangle> entry2 : this.messageBounds.entrySet()) {
			    String messageID = entry2.getKey();
			    Rectangle messageBounds = entry2.getValue();
			    
			    if(cfBounds.contains(messageBounds)) {
			    	CameoUtils.logGUI(messageID + " is covered by " + cfID + ". Adding data to relationship metadata.");
			    	org.w3c.dom.Element messageTag = this.messageTags.get(messageID);
			    	org.w3c.dom.Element relationshipMetadata = xmlDoc.createElement(XmlTagConstants.RELATIONSHIP_METADATA);
			    	org.w3c.dom.Element coveredBy = xmlDoc.createElement(XmlTagConstants.COVERED_BY);
			    	coveredBy.appendChild(xmlDoc.createTextNode(cfID));
			    	
			    	relationshipMetadata.appendChild(coveredBy);
			    	messageTag.appendChild(relationshipMetadata);
			    } else {
			    	CameoUtils.logGUI(messageID + " " + messageBounds.toString() + " is not covered by " + cfID + " " + cfBounds.toString());
			    }
		    }
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		writeCoveredMessages(xmlDoc);
		return data;
	}
}
