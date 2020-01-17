package org.aero.huddle.ModelElements;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.ModelElements.ModelDiagram;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
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

public abstract class  AbstractDiagram  extends CommonElement implements ModelDiagram{
    public static Map<String,String> diagramToType;
    static {
    	
// ALL POSSIBLE DIAGRAM TYPES
//        Class Diagram
//        Use Case Diagram
//        Sequence Diagram
//        Activity Diagram
//        State Machine Diagram
//        Component Diagram
//        Object Diagram
//        Package Diagram
//        Deployment Diagram
//        Communication Diagram
//        Protocol State Machine Diagram
//        Composite Structure Diagram
//        Interaction Overview Diagram
//        Profile Diagram
//        Any Diagram
//        Static Diagram
//        Behavior Diagram
//        Interaction Diagram
//        Free Form Diagram
//        Networking Diagram
//        Requirement Diagram
//        Simulation Configuration Diagram
//        SysML Activity Diagram
//        SysML Block Definition Diagram
//        SysML Internal Block Diagram
//        SysML Package Diagram
//        SysML Parametric Diagram
//        SysML Sequence Diagram
//        SysML State Machine Diagram
//        SysML Use Case Diagram
//        Time Diagram
//        User Interface Modeling Diagram
//        Views and Viewpoints Diagram
//        Content Diagram
//        Dependency Matrix
//        Derive Requirement Matrix
//        Refine Requirement Matrix
//        Satisfy Requirement Matrix
//        SysML Allocation Matrix
//        Verify Requirement Matrix
//        Generic Table
//        Instance Table
//        Glossary Table
//        Activity Decomposition Map
//        Instance Map
//        Relation Map Diagram
//        Requirement Containment Map
//        Requirement Derivation Map
//        Structure Decomposition Map
//        Requirement Table
//        ProfileUpgradeStereotypesOrTagsMappingTable
//        ProfileUpgradeStereotypesOrTagsClearingTable
//        Metric Table
//        Blackbox ICD Table
//        Whitebox ICD Table
    	
        Map<String, String> aMap = new HashMap<String,String>();
      
//    	public static final String BDD = "BlockDefinitionDiagram";
//    	public static final String IBD = "InternalBlockDiagram";
//    	public static final String PKG = "PackageDiagram";
//    	public static final String REQ = "RequirementsDiagram";
//    	public static final String PAR = "ParametricDiagram";
//    	public static final String STM = "StateMachineDiagram";
//    	public static final String ACT = "ActivityDiagram";
//    	public static final String SEQ = "SequenceDiagram";
//    	public static final String UC = "UseCaseDiagram";
//    	public static final String PROFILEDIAGRAM = "ProfileDiagram";
//    	public static final String CLASSDIAGRAM = "ClassDiagram";

        aMap.put("Class Diagram", SysmlConstants.CLASSDIAGRAM);
        aMap.put("Use Case Diagram",SysmlConstants.UC );
        aMap.put("Sequence Diagram",SysmlConstants.SEQ );
        aMap.put("Activity Diagram", SysmlConstants.ACT);
        aMap.put("State Machine Diagram", SysmlConstants.STM);
//        aMap.put("Component Diagram", );
//        aMap.put("Object Diagram", );
        aMap.put("Package Diagram", SysmlConstants.PKG);
//        aMap.put("Deployment Diagram", );
//        aMap.put("Communication Diagram", );
//        aMap.put("Protocol State Machine Diagram", );
//        aMap.put("Composite Structure Diagram", );
//        aMap.put("Interaction Overview Diagram", );
        aMap.put("Profile Diagram", SysmlConstants.PROFILEDIAGRAM);
//        aMap.put("Any Diagram", );
//        aMap.put("Static Diagram", );
//        aMap.put("Behavior Diagram", );
//        aMap.put("Interaction Diagram", );
//        aMap.put("Free Form Diagram", );
//        aMap.put("Networking Diagram", );
        aMap.put("Requirement Diagram",SysmlConstants.REQ );
//        aMap.put("Simulation Configuration Diagram", );
        
        aMap.put("SysML Activity Diagram", SysmlConstants.ACT );
        aMap.put("SysML Block Definition Diagram", SysmlConstants.BDD );
        aMap.put("SysML Internal Block Diagram", SysmlConstants.IBD );
        aMap.put("SysML Package Diagram", SysmlConstants.PKG );
        aMap.put("SysML Parametric Diagram", SysmlConstants.PAR);
        aMap.put("SysML Sequence Diagram", SysmlConstants.SEQ);
        aMap.put("SysML State Machine Diagram", SysmlConstants.STM);
        aMap.put("SysML Use Case Diagram", SysmlConstants.UC);
        
//        aMap.put("Time Diagram", );
//        aMap.put("User Interface Modeling Diagram", );
//        aMap.put("Views and Viewpoints Diagram", );
//        aMap.put("Content Diagram", );
//        aMap.put("Dependency Matrix", );
//        aMap.put("Derive Requirement Matrix", );
//        aMap.put("Refine Requirement Matrix", );
//        aMap.put("Satisfy Requirement Matrix", );
//        aMap.put("SysML Allocation Matrix", );
//        aMap.put("Verify Requirement Matrix", );
//        aMap.put("Generic Table", );
//        aMap.put("Instance Table", );
//        aMap.put("Glossary Table", );
//        aMap.put("Activity Decomposition Map", );
//        aMap.put("Instance Map", );
//        aMap.put("Relation Map Diagram", );
//        aMap.put("Requirement Containment Map", );
//        aMap.put("Requirement Derivation Map", );
//        aMap.put("Structure Decomposition Map", );
//        aMap.put("Requirement Table", );
//        aMap.put("ProfileUpgradeStereotypesOrTagsMappingTable", );
//        aMap.put("ProfileUpgradeStereotypesOrTagsClearingTable", );
//        aMap.put("Metric Table", );
//        aMap.put("Blackbox ICD Table", );
//        aMap.put("Whitebox ICD Table", );
        
           
        diagramToType = Collections.unmodifiableMap(aMap);
    }
    
	public AbstractDiagram(String name, String EAID) {
		 super(name, EAID);
	}
	
	//Abstract methods
	public abstract String getSysmlConstant();
	public abstract String getDiagramType();

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Diagram Element");
		}

		Diagram sysmlElement = null;
		try {
			// a class diagram is created and added to a parent model element

			sysmlElement = ModelElementsManager.getInstance().createDiagram(getSysmlConstant(),
					(Namespace) owner);
			
		//	diagramToType.put(sysmlElement, getSysmlConstant());

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
				ShapeElement shape = PresentationElementsManager.getInstance().createShapeElement(element, presentationDiagram, true);
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
		            
				if (   (!type.toLowerCase().equals("association")) 
				    && (!type.toLowerCase().equals("diagram")) 
				    && (!type.toLowerCase().equals("transition"))
					&& (!type.toLowerCase().equals("controlflow")) 
					&& (!type.toLowerCase().equals("usage")) 
					//&& (!type.toLowerCase().equals("generalization")) 
					//&& (!type.toLowerCase().equals("instance specification")) 
					&& (!type.toLowerCase().equals("interface realization")) 
					) { 
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
		type.appendChild(xmlDoc.createTextNode(getDiagramType()));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
