/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.util;

import java.awt.event.ActionEvent;
import java.util.List;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

@SuppressWarnings("serial")
public class InspectDiagramElementAction extends MDAction {
	DiagramPresentationElement diagramPresentationElement;
	PresentationElement[] selectedPresentationElements;
	PresentationElement requestorPresentationElement;
	
	public InspectDiagramElementAction(String id, String name, DiagramPresentationElement diagramPresentationElement, PresentationElement[] selectedPresentationElements, PresentationElement requestorPresentationElement)	{
		super(id, name, null, null);
		this.diagramPresentationElement = diagramPresentationElement;
		this.selectedPresentationElements = selectedPresentationElements;
		this.requestorPresentationElement = requestorPresentationElement;
	}
	
	public void actionPerformed(ActionEvent e) {
		Element diagramElement = diagramPresentationElement.getElement();
		CameoUtils.logGUI("Diagram element has id:" + diagramElement.getLocalID());
		CameoUtils.logGUI(Integer.toString(selectedPresentationElements.length) + " elements selected on diagram.");
		
		int allElementCount = findNestedPresentationElements(diagramPresentationElement.getPresentationElements());
		CameoUtils.logGUI(Integer.toString(allElementCount) + " total elements on diagram found via api.");
		
		for (int i = 0; i < selectedPresentationElements.length; i++) {
			PresentationElement displayedPresentationElement = selectedPresentationElements[i];
			Element displayedElement = displayedPresentationElement.getElement();
			if(displayedElement != null) {
				CameoUtils.logGUI("Displayed Element has id " + displayedElement.getLocalID() + " and displays as an object of type " + displayedPresentationElement.getClass().toString());
				CameoUtils.logGUI("......with size: " + displayedPresentationElement.getBounds().toString());
			} else {
				CameoUtils.logGUI("Presentation Element with id " + displayedPresentationElement.getID() + " has no element and is an object of type " + displayedPresentationElement.getClass().toString());
				CameoUtils.logGUI("......with size: " + displayedPresentationElement.getBounds().toString());
			}
			
		}
	}
	
	public int findNestedPresentationElements(List<PresentationElement> presentationElements) {
		int count = 0;
		for(int i = 0; i < presentationElements.size(); i++) {
			PresentationElement presentationElement = presentationElements.get(i);
			CameoUtils.logGUI("Presentation Element from api is an object of type " + presentationElement.getClass().toString() + " with size" + presentationElement.getBounds().toString());
			List<PresentationElement> nestedPresentationElements = presentationElement.getPresentationElements();
			if(!nestedPresentationElements.isEmpty()) {
				count += findNestedPresentationElements(nestedPresentationElements);
			} else {
				count += 1;
			}
		}
		return count;
	}
}