package org.aero.huddle.ModelElements;

import java.awt.Rectangle;
import java.util.List;

import org.aero.huddle.util.XMLItem;

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
	public boolean addElements(Project project, Diagram diagram, List<Element> elements, List<Rectangle> elementsLocations, XMLItem xmlElement);
	public void addRelationships(Project project, Diagram diagram, List<Element> relationships);
}
