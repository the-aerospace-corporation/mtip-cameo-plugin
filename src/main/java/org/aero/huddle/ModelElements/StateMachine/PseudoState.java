package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKind;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.impl.ElementsFactory;

public abstract class PseudoState extends CommonElement {
	protected PseudostateKind psKind;
	protected String xmlTag;
	
	public PseudoState(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		
		sysmlElement = f.createPseudostateInstance();
		Pseudostate pseudoState = (Pseudostate)sysmlElement;
		pseudoState.setKind(this.psKind);
		pseudoState.setName(this.name);
		
		if(owner != null) {
			//if owner is not a region, create a region and set that region as owned by state machine
			if(owner instanceof Region) {
				pseudoState.setOwner(owner);
			} else if(owner instanceof StateMachine) {
				Collection<Region> regions = ((StateMachine) owner).getRegion();
				if(regions != null) {
					Region region = regions.iterator().next();
					pseudoState.setOwner(region);
				} else {
					CameoUtils.logGUI("CREATE REGION HERE!!!!!!!!!!!!!");
					//create region
				}
			} else if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {
				Region existingRegion = findExistingRegion(owner);
				if(existingRegion != null) {
					CameoUtils.logGUI("Setting owner of " + name + " as existing Region.");
					pseudoState.setOwner(existingRegion);
				} else {
					CameoUtils.logGUI("Creating new region for " + name + " as child of " + owner.getHumanName());
					Region newRegion = f.createRegionInstance();
					newRegion.setOwner(owner);
					pseudoState.setOwner(newRegion);
				}
			} else {
				owner = CameoUtils.findNearestRegion(project, owner);
				if(owner == null) {
					String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
					CameoUtils.logGUI(logMessage);
					ImportLog.log(logMessage);
					sysmlElement.dispose();
					return null;
				}
				pseudoState.setOwner(owner);
			}
		} else {
			String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			sysmlElement.dispose();
			return null;
		}
		
		SessionManager.getInstance().closeSession(project);
		return pseudoState;
	}
	
	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(this.xmlTag));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);		
	}
	
	public static Region findExistingRegion(Element owner) {
		Collection<Element> children = owner.getOwnedElement();
		for(Element childElement : children) {
			if(childElement instanceof Region) {
				return (Region) childElement;
			}
		}
		return null;
	}
}
