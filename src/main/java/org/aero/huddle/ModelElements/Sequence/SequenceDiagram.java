package org.aero.huddle.ModelElements.Sequence;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import org.aero.huddle.ModelElements.AbstractDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Message;

public class SequenceDiagram  extends AbstractDiagram{

	public SequenceDiagram(String name, String EAID) {
		 super(name, EAID);
		 this.sysmlConstant = SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
		 this.xmlConstant = XmlTagConstants.SEQUENCEDIAGRAM;
	}
	
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships) {
		try {
			DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
//			Application.getInstance().getProject().getDiagram(diagram).layout(true, new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());
//			project.getDiagram(diagram).open();
			List<PresentationElement> presentationElements = presentationDiagram.getPresentationElements();
			for(PresentationElement presentationElement : presentationElements) {
				if(presentationElement instanceof PathElement) {
					PresentationElementsManager.getInstance().deletePresentationElement(presentationElement);
				}
			}
			Message previousMessage = null;
			for (Element relationship : relationships) {
				Element client = ModelHelper.getClientElement(relationship);
				Element supplier = ModelHelper.getSupplierElement(relationship);
				PresentationElement clientPE = presentationDiagram.findPresentationElementForPathConnecting(client, null);
				PresentationElement supplierPE = presentationDiagram.findPresentationElementForPathConnecting(supplier, null);
				
				if(relationship instanceof Message) {
					Message message = (Message)relationship;
					ShapeElement clientSE = shapeElements.get(client.getLocalID());
					ShapeElement supplierSE = shapeElements.get(client.getLocalID());
					PresentationElementsManager.getInstance().createSequenceMessage(message, ((Message)message).getMessageSort(), supplierSE, clientSE, false, 0, previousMessage, 25);
					previousMessage = message;
				} else {
					PresentationElementsManager.getInstance().createPathElement(relationship, clientPE ,supplierPE);
				}
				
//				ShapeElement shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true, point);
				
				CameoUtils.logGUI("Placing relationship " + relationship.getHumanName() + " on to diagram.");				
			}
		} catch (ReadOnlyElementException e) {
			CameoUtils.logGUI("Diagram " + diagram.getHumanName() + " is ready only. No relationships will be added.");
		}
//		project.getDiagram(diagram).close();
	}
	
//	@Override
//	protected void writeElements(Document xmlDoc, Project project, org.w3c.dom.Element elementListTag, org.w3c.dom.Element relationshipListTag, Element element) {
//		Diagram diagram = (Diagram) element;
//		DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);
//		List<PresentationElement> presentationElements = presentationDiagram.getPresentationElements();
//		presentationDiagram.open();
//			
//		for(PresentationElement presentationElement : presentationElements) {
//			if(presentationElement instanceof PathElement) {
//				org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
//				if(relationshipTag != null) {
//					relationshipListTag.appendChild(relationshipTag);
//				}
//			} else {
//				org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement);
//				if(elementTag != null) {
//					elementListTag.appendChild(elementTag);
//				}
//			}
//			List<PresentationElement> subPresentationElements = presentationElement.getPresentationElements();
//			for(PresentationElement subPresentationElement : subPresentationElements) {
//				List<PresentationElement> subSubPresentationElements = subPresentationElement.getPresentationElements();
//				if(subPresentationElement != null) {
////					CameoUtils.logGUI("Sub presentation Element found for presentation element with id: " + presentationElement.getElement().getLocalID() + " and name" + presentationElement.getElement().getHumanName());
//					if(presentationElement instanceof PathElement) {
//						org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
//						if(relationshipTag != null) {
//							relationshipListTag.appendChild(relationshipTag);
//						}
//					} else {
//						org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement);
//						if(elementTag != null) {
//							elementListTag.appendChild(elementTag);
//						}
//					}
//				}
//				for(PresentationElement subSubPresentationElement : subSubPresentationElements) {
//					if(subSubPresentationElement != null && subPresentationElement.getElement() != null) {
//						if(presentationElement instanceof PathElement) {
//							org.w3c.dom.Element relationshipTag = createDiagramRelationshipTag(xmlDoc, presentationElement);
//							if(relationshipTag != null) {
//								relationshipListTag.appendChild(relationshipTag);
//							}
//						} else {
//							org.w3c.dom.Element elementTag = createDiagramElementTag(xmlDoc, presentationElement);
//							if(elementTag != null) {
//								elementListTag.appendChild(elementTag);
//							}
//						}
////						CameoUtils.logGUI("Sub-Sub presentation Element found for presentation element with id: " + subPresentationElement.getElement().getLocalID() + " and name" + subPresentationElement.getElement().getHumanName());
//					}
//				}
//			}
//			presentationDiagram.close();
//		}
//	}
	
	@Override
	public String getSysmlConstant() {
		return SysMLConstants.SYSML_SEQUENCE_DIAGRAM;
	}
	
	@Override
	public String getDiagramType() {
		return XmlTagConstants.SEQUENCEDIAGRAM;
	}
}
