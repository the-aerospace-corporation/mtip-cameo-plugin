package org.aero.huddle.ModelElements.Block;

import java.util.List;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.ModelDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLConstants;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.magicdraw.uml.symbols.shapes.ShapeElement;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Namespace;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.impl.ElementsFactory;

public class BlockDefinitionDiagram  extends CommonElement implements ModelDiagram{

	public BlockDefinitionDiagram(String name, String EAID) {
		 super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Block Diagram Element");
		}

		Diagram sysmlElement = null;
		try {
			// a class diagram is created and added to a parent model element

			sysmlElement = ModelElementsManager.getInstance().createDiagram(SysMLConstants.SYSML_BLOCK_DEFINITION_DIAGRAM,
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

		DiagramPresentationElement presentationDiagram;
		try {
			// a class diagram is created and added to a parent model element
			presentationDiagram = project.getDiagram(diagram);

			// open a diagram
			project.getDiagram(diagram).open();

			for (Element element : elements) {
				ShapeElement shape = PresentationElementsManager.getInstance().createShapeElement(element,
						presentationDiagram, true);

			}
		} catch (ReadOnlyElementException e) {
		}

		SessionManager.getInstance().closeSession(project);
		Application.getInstance().getProject().getDiagram(diagram).layout(true,
				new com.nomagic.magicdraw.uml.symbols.layout.ClassDiagramLayouter());

	}

	public void linkElements(Project project, Diagram diagram, Element supplierElement, Element clientElement) {
		CameoUtils.logGUI("LINKELEMENTS of supplier: " + supplierElement.toString() + "client: " + clientElement.toString());
		DiagramPresentationElement presentationDiagram = null;
		presentationDiagram = project.getDiagram(diagram);

		if (presentationDiagram.isElementInDiagram(clientElement)
				&& presentationDiagram.isElementInDiagram(supplierElement)) {

			//verify
			Package parent = project.getPrimaryModel();
			SessionManager.getInstance().createSession(project, "Link diagram elements");
			ElementsFactory f = project.getElementsFactory();

			// add Dependency links
			com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency link = f.createDependencyInstance();

			PresentationElement clientPE = presentationDiagram.findPresentationElementForPathConnecting(clientElement, null);

			PresentationElement supplierPE = presentationDiagram.findPresentationElementForPathConnecting(supplierElement, null);

			if ((clientPE != null) && (supplierPE != null)) {
				try {
					CameoUtils.logGUI("Going to add path element: ");

					PathElement path = PresentationElementsManager.getInstance().createPathElement(link, clientPE,
							supplierPE);
				} catch (ReadOnlyElementException e) {
// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		SessionManager.getInstance().closeSession(project);
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);

		// get attributes part
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element displayID = xmlDoc.createElement("display_as");
		displayID.appendChild(xmlDoc.createTextNode("Diagram"));
		attributes.appendChild(displayID);

		// get relationships part
		// for all elements in diagram, write to relationships section
		org.w3c.dom.Element relation = getType(data.getChildNodes(), "relationships");

		Diagram diagram = (Diagram) element;
		DiagramPresentationElement presentationDiagram;

		presentationDiagram = project.getDiagram(diagram);
		List presentationElements = presentationDiagram.getPresentationElements();

		for (int i = presentationElements.size() - 1; i >= 0; --i) {
			PresentationElement presentationElement = (PresentationElement) presentationElements.get(i);
			// element represented in the diagram (can be null for views that do not  represent concrete element)

			Element curElement = presentationElement.getElement();

			if (curElement != null) {
				String type = curElement.getHumanType();

				if ((!type.toLowerCase().equals("association")) && (!type.toLowerCase().equals("diagram"))) { 
					// For now, skip associations in the diagram. They are added via relationships.
					String curID = curElement.getID();
					org.w3c.dom.Element elementTag = xmlDoc.createElement("element");
					elementTag.appendChild(xmlDoc.createTextNode(curID));
					elementTag.setAttribute("type", "sysml." + type);
					relation.appendChild(elementTag);
				}
			}
		}

		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.BLOCKDEFINITIONDIAGRAM));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
