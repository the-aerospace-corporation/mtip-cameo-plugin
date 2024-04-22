/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.Collection;
import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.Importer;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

public class State extends CommonElement {
	public State(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STATE;
		this.xmlConstant = XmlTagConstants.STATE;
		this.element = f.createStateInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		
		if(xmlElement != null) {
			if(xmlElement.isSubmachine()) {
				state.setSubmachine((StateMachine) project.getElementByID(xmlElement.getNewSubmachine()));
			}
		}		
		
		return element;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	@Override
	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;

		if(xmlElement.hasAttribute(XmlTagConstants.DO_ACTIVITY)) {
			CameoUtils.logGui("Creating do activity for State.");
			String doActivityId = xmlElement.getAttribute(XmlTagConstants.DO_ACTIVITY);
			Behavior doActivity = (Behavior) Importer.getInstance().buildElement(parsedXML, parsedXML.get(doActivityId));
			state.setDoActivity(doActivity);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.ENTRY)) {
			CameoUtils.logGui("Creating entry for State.");
			String entryId = xmlElement.getAttribute(XmlTagConstants.ENTRY);
			Behavior entry = (Behavior)Importer.getInstance().buildElement(parsedXML, parsedXML.get(entryId));
			state.setEntry(entry);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.EXIT)) {
			CameoUtils.logGui("Creating exit for State.");
			String exitId = xmlElement.getAttribute(XmlTagConstants.EXIT);
			Behavior exit = (Behavior)Importer.getInstance().buildElement(parsedXML, parsedXML.get(exitId));
			state.setExit(exit);
		}
	}
	
	public void setOwner(Project project, Element owner) {
		if (owner == null) {
			Logger.log(String.format("Owner for state %s is null.", EAID));
			return;
		}
		
		if (owner instanceof Region) {
			element.setOwner(owner);
			return;
		}

		if(owner instanceof StateMachine && setOwnerStateMachine(owner)) {
			return;
		} 
		
		if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State
				&& setOwnerState(owner)) {
			return;
		} 

		owner = CameoUtils.findNearestRegion(project, owner);
		
		if(owner == null) {
			Logger.log(String.format("No valid parent found for state %s with id %s. State not imported.", name, element));
			element.dispose();
			return;
		}
		
		element.setOwner(owner);
	}
	
	boolean setOwnerState(Element owner) {
		Region existingRegion = PseudoState.findExistingRegion(owner);
		
		if(existingRegion == null) {
			Logger.log(String.format("Creating new region for %s as child of %s for allowable owneship.", name, owner.getHumanName()));
			Region newRegion = f.createRegionInstance();
			newRegion.setOwner(owner);
			
			element.setOwner(newRegion);
			return true;
		}
		
		element.setOwner(existingRegion);
		return true;
	}
	
	boolean setOwnerStateMachine(Element owner) {
		Collection<Region> regions = ((StateMachine) owner).getRegion();
		
		if(regions == null) {
			return false;
		}
		
		Region region = regions.iterator().next();
		
		if (region == null) {
			return false;
		}
		
		element.setOwner(region);
		
		return true;
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeSubmachine(relationships, element);
		writeDoActivity(relationships, element);
		writeEntry(relationships, element);
		writeExit(relationships, element);
		
		return data;
	}
	
	@Override
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner();
		
		if(owner == null) {
			Logger.log(String.format("No parent found for state %s with id %s", element.getHumanName(), element.getID()));
			return;
		}
		
		org.w3c.dom.Element hasParentTag = XmlWriter.createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
		XmlWriter.add(relationships, hasParentTag);
	}
	
	protected void writeSubmachine(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		
		if(!state.isSubmachineState()) {
			return;
		}
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine submachine = state.getSubmachine();
		
		if (submachine == null) {
			return;
		}
		
		org.w3c.dom.Element submachineTag = XmlWriter.createMtipRelationship(submachine, XmlTagConstants.SUBMACHINE);
		XmlWriter.add(relationships, submachineTag);
	}
	
	protected void writeDoActivity(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		Behavior doActivity = state.getDoActivity();
		
		if (doActivity == null) {
			return;
		}
		
		org.w3c.dom.Element doActivityTag = XmlWriter.createMtipRelationship(doActivity, XmlTagConstants.DO_ACTIVITY);
		XmlWriter.add(relationships, doActivityTag);
	}
	
	protected void writeEntry(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		Behavior entry = state.getEntry();
		
		if (entry == null) {
			return;
		}
		
		org.w3c.dom.Element entryTag = XmlWriter.createMtipRelationship(entry, XmlTagConstants.ENTRY);
		XmlWriter.add(relationships, entryTag);
	}
	
	protected void writeExit(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		Behavior exit = state.getExit();
		
		if (exit == null) {
			return;
		}
		
		org.w3c.dom.Element exitTag = XmlWriter.createMtipRelationship(exit, XmlTagConstants.EXIT);
		XmlWriter.add(relationships, exitTag);
	}
}
