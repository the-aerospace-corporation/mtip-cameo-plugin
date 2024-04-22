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
		CameoUtils.logGui("Diagram element has id:" + diagramElement.getID());
		CameoUtils.logGui(Integer.toString(selectedPresentationElements.length) + " elements selected on diagram.");
		
		int allElementCount = findPresentationElements(diagramPresentationElement.getPresentationElements());
		CameoUtils.logGui(Integer.toString(allElementCount) + " total elements on diagram found via api.");
		
		for (int i = 0; i < selectedPresentationElements.length; i++) {
			PresentationElement displayedPresentationElement = selectedPresentationElements[i];
			Element displayedElement = displayedPresentationElement.getElement();
			if(displayedElement != null) {
				CameoUtils.logGui("Displayed Element has id " + displayedElement.getID() + " and displays as an object of type " + displayedPresentationElement.getClass().toString());
				CameoUtils.logGui("......with size: " + displayedPresentationElement.getBounds().toString());
			} else {
				CameoUtils.logGui("Presentation Element with id " + displayedPresentationElement.getID() + " has no element and is an object of type " + displayedPresentationElement.getClass().toString());
				CameoUtils.logGui("......with size: " + displayedPresentationElement.getBounds().toString());
			}
			
		}
	}
	
	public int findPresentationElements(List<PresentationElement> presentationElements) {
		int count = 0;
		for(int i = 0; i < presentationElements.size(); i++) {
			PresentationElement presentationElement = presentationElements.get(i);
			CameoUtils.logGui(String.format("Presentation Element child of main diagram is of type %s with size %s",
						presentationElement.getClass().toString(), presentationElement.getBounds().toString()));
			count += 1;
		}
		
		return count;
	}
}