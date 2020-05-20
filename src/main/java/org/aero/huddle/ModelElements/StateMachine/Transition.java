package org.aero.huddle.ModelElements.StateMachine;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.FunctionBehavior;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Vertex;
import com.nomagic.uml2.impl.ElementsFactory;

public class Transition extends CommonRelationship {
	public Transition(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Transition Relationship");
		}
		ElementsFactory ef = project.getElementsFactory();
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Transition transition = ef.createTransitionInstance();
		
		if(xmlElement.hasGuard()) {
			Constraint constraint = ef.createConstraintInstance();
			LiteralString specification = ef.createLiteralStringInstance();
			specification.setValue(xmlElement.getGuard());
			constraint.setSpecification(specification);			
			transition.setGuard(constraint);
		}
		
		if(xmlElement.hasEffect()) {
			FunctionBehavior functionBehavior = ef.createFunctionBehaviorInstance();
			functionBehavior.getBody().add(xmlElement.getAttribute("effect"));
			transition.setEffect(functionBehavior);
		}
		try {
			transition.setSource((Vertex) supplier);
			transition.setTarget((Vertex) client);
		} catch(ClassCastException cce) {
			String logMessage = "Invalid supplier or client. Supplier and client must be sub-classes of Vertex. Transition " + name + " with id " + EAID + " not created";
			ImportLog.log(logMessage);
			transition.dispose();
			return null;
		}
		
		((NamedElement)transition).setName(name);
		transition.setOwner(owner);
		
		SessionManager.getInstance().closeSession(project);
		return transition;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.TRANSITION));
		data.appendChild(type);
		
		
//		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);	
	}
}
