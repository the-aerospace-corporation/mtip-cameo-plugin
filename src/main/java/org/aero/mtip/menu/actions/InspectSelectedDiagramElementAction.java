/*
 * The Aerospace Corporation Huddle_Cameo Copyright 2022 The Aerospace Corporation
 * 
 * This product includes software developed at The Aerospace Corporation
 * (http://www.aerospace.org/).
 */

package org.aero.mtip.menu.actions;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.MtipUtils;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ImageView;
import com.nomagic.magicdraw.uml.symbols.shapes.StereotypeIconView;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

@SuppressWarnings("serial")
public class InspectSelectedDiagramElementAction extends MDAction {
  DiagramPresentationElement diagramPresentationElement;
  PresentationElement[] selectedPresentationElements;
  PresentationElement requestorPresentationElement;
  
  HashSet<String> loggedPresentationElements = new HashSet<String>();

  public InspectSelectedDiagramElementAction(String id, String name,
      DiagramPresentationElement diagramPresentationElement,
      PresentationElement[] selectedPresentationElements,
      PresentationElement requestorPresentationElement) {
    super(id, name, null, null);
    this.diagramPresentationElement = diagramPresentationElement;
    this.selectedPresentationElements = selectedPresentationElements;
    this.requestorPresentationElement = requestorPresentationElement;
  }

  public void actionPerformed(ActionEvent e) {
    CameoUtils.logGui(
        Integer.toString(selectedPresentationElements.length) + " elements selected on diagram.\n");

    for (int i = 0; i < selectedPresentationElements.length; i++) {
      CameoUtils.logGui("Selected Presentation Element");
      logPresentationElementDetails(selectedPresentationElements[i]);
      
      CameoUtils.logGui("Recursive Presentation Elements from non-diagram parent.");
      logChildRecursive(selectedPresentationElements[i]);
    }
  }

  private void logPresentationElementDetails(PresentationElement presentationElement) {
    if (loggedPresentationElements.contains(presentationElement.getID())) {
      return;
    }
    
    loggedPresentationElements.add(presentationElement.getID());
    
    Element diagramElement = presentationElement.getElement();
    
    if (diagramElement == null) {
      logPresentationElementDetailsNoElement(presentationElement);
      return;
    }
    
    CameoUtils.logGui(String.format("Selected presentation element:\n "
        + "PresentationElement of type : %s\n"
        + "PresentationElement size: %s\n"
        + "PresetnationElement preferred dimension: %s\n"
        + "Element type: %s\n" 
        + "Element id: %s\n",
        presentationElement.getClass().toString(),
        presentationElement.getBounds().toString(),
        presentationElement.getPreferredDimension().toString(),
        diagramElement.getHumanType(),
        MtipUtils.getId(diagramElement)));
  }
  
  private void logPresentationElementDetailsNoElement(PresentationElement presentationElement) {    
    CameoUtils.logGui(String.format("Selected presentation element:\n "
        + "PresentationElement of type : %s"
        + "PresentationElement bounds: %s"
        + "PresetnationElement preferred dimension: %s\n",
        presentationElement.getClass().toString(),
        presentationElement.getBounds().toString(),
        presentationElement.getPreferredDimension().toString()));
  }
  
  private void logChildRecursive(PresentationElement presentationElement) {
    for (PresentationElement childPresentationElement : presentationElement.getPresentationElements()) {
      CameoUtils.logGui(String.format("...with child presentation element of type: %s", childPresentationElement.getClass().toString()));
      
      logChildRecursive(childPresentationElement);
      
      if (childPresentationElement instanceof StereotypeIconView) {
        StereotypeIconView siv = (StereotypeIconView)childPresentationElement;
        CameoUtils.logGui(String.format("StereotypeIconView bounds: %s", siv.getBounds().toString()));
      }
      
      if (childPresentationElement instanceof ImageView) {
        ImageView imageView = (ImageView)childPresentationElement;
        CameoUtils.logGui(String.format("ImageView bounds: %s", imageView.getBounds().toString()));
      }
    }
  }
  
//  ResizableIcon icon = ElementImageHelper.getIconFromCustomImageProperty(presentationElement.getElement());
//  
//  if (icon != null) {
//    CameoUtils.logGui(String.format("Icon height: %d, width: %d", icon.getIconHeight(), icon.getIconWidth()));
//  }
}
