package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.impl.ElementsFactory;

public class State extends CommonElement {
	public State(String name, String EAID) {
		super(name, EAID);
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create State Element");
		}
		Element sysmlElement = f.createStateInstance();
		((NamedElement)sysmlElement).setName(name);
		
		
		Region region = null;
		Collection<Region> regions = null;
		if(owner != null) {
			//if owner is not a region, create a region and set that region as owned by state machine
			if(owner instanceof Region) {
				sysmlElement.setOwner(owner);
			} else if(owner instanceof StateMachine) {
				regions = ((StateMachine) owner).getRegion();
				if(regions != null) {
					region = regions.iterator().next();
					sysmlElement.setOwner(region);
				} else {
					CameoUtils.logGUI("Error in Cameo processes in auto-region creation.");
				}
			} else if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {
				Region existingRegion = PseudoState.findExistingRegion(owner);
				if(existingRegion != null) {
					CameoUtils.logGUI("Setting owner of " + name + " as existing Region.");
					sysmlElement.setOwner(existingRegion);
				} else {
					CameoUtils.logGUI("Creating new region for " + name + " as child of " + owner.getHumanName());
					Region newRegion = f.createRegionInstance();
					newRegion.setOwner(owner);
					sysmlElement.setOwner(newRegion);
				}
			} else {
				owner = CameoUtils.findNearestRegion(project, owner);
				sysmlElement.setOwner(owner);
			}
		} else {
			CameoUtils.logGUI("No region to add InitialPseudoState " + name + " to.");
		}
		if(xmlElement != null) {
			if(xmlElement.isSubmachine()) {
				com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)sysmlElement;
				state.setSubmachine((StateMachine) project.getElementByID(xmlElement.getNewSubmachine()));
			}
		}		
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.STATE));
		data.appendChild(type);
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		if(state.isSubmachineState()) {
			org.w3c.dom.Element submachine = xmlDoc.createElement("submachine");
			submachine.appendChild(xmlDoc.createTextNode(state.getSubmachine().getLocalID()));
			attributes.appendChild(submachine);
		}
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}
}
