/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;

import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

//public abstract class ModelDiagram extends CommonElement {
//	protected String name;
//	protected String EAID;
//	
//	public ModelDiagram(String name, String EAID) {
//		super(name,EAID);
//	}
//	
//	public abstract Element createElement(Project project, Element owner);
//	public abstract void addElements(Project project, Diagram diagram, List<Element> elements);
//	public abstract void linkElements(Project project, Diagram diagram, Element supplierElement, Element clientElement);
//
//}


public interface ModelDiagram  {
	public boolean addElements(Project project, Diagram diagram, HashMap<Element, Rectangle> elements, XMLItem xmlElement);
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships);
}
