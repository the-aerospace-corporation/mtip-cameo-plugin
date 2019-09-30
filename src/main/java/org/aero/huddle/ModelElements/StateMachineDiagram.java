package org.aero.huddle.ModelElements;

import java.util.List;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
//import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.impl.ElementsFactory;

public class StateMachineDiagram  extends CommonElement implements ModelDiagram{

	public StateMachineDiagram(String name, String EAID) {
		 super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Block Diagram Element");
		}

		Diagram sysmlElement = null;
		try {
			// a class diagram is created and added to a parent model element

			sysmlElement = ModelElementsManager.getInstance().createDiagram(DiagramTypeConstants.UML_STATECHART_DIAGRAM,
					(Namespace) owner);

		} catch (ReadOnlyElementException e) {
		}

		((NamedElement) sysmlElement).setName(name);

		if (owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}

		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}

	@Override
	public void addElements(Project project, Diagram diagram, List<Element> elements) {
		Package parent = project.getPrimaryModel();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create a diagram");
		}

		try {
			// a class diagram is created and added to a parent model element
			DiagramPresentationElement presentationDiagram = project.getDiagram(diagram);

			// open a diagram
			project.getDiagram(diagram).open();

			for (Element element : elements) {

				ShapeElement shape = PresentationElementsManager.getInstance().createShapeElement(element,
						presentationDiagram);
			}

		} catch (ReadOnlyElementException e) {
		}

		SessionManager.getInstance().closeSession(project);
		Application.getInstance().getProject().getDiagram(diagram).layout(true,
				new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());

	}

	public void linkElements(Project project, Diagram diagram, Element supplierElement, Element clientElement) {
		Package parent = project.getPrimaryModel();
		SessionManager.getInstance().createSession(project, "Create a diagram");
		ElementsFactory f = project.getElementsFactory();
		// add Dependency links
		com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency link = f.createDependencyInstance();
//PresentationElement clientPE = ...;
//PresentationElement supplierPE = ...;
//SessionManager.getInstance().createSession("Test");
		try {
			PathElement path = PresentationElementsManager.getInstance().createPathElement(link,
					(PresentationElement) clientElement, (PresentationElement) supplierElement);
		} catch (ReadOnlyElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionManager.getInstance().closeSession(project);
	}
}
