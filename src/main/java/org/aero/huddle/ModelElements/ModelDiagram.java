package org.aero.huddle.ModelElements;

import java.util.List;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.impl.ElementsFactory;

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
	public void addElements(Project project, Diagram diagram, List<Element> elements);
	public void linkElements(Project project, Diagram diagram, Element supplierElement, Element clientElement);
}
