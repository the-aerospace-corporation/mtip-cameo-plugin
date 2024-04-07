/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.StateMachine;

import java.util.Collection;
import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdbasicbehaviors.Behavior;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

public class State extends CommonElement {
	public State(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.STATE;
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
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	@Override
	public void createReferencedElements(HashMap<String, XMLItem> parsedXML, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;

		if(xmlElement.hasAttribute(XmlTagConstants.DO_ACTIVITY)) {
			CameoUtils.logGUI("Creating do activity for State.");
			String doActivityId = xmlElement.getAttribute(XmlTagConstants.DO_ACTIVITY);
			Behavior doActivity = (Behavior) ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(doActivityId));
			state.setDoActivity(doActivity);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.ENTRY)) {
			CameoUtils.logGUI("Creating entry for State.");
			String entryId = xmlElement.getAttribute(XmlTagConstants.ENTRY);
			Behavior entry = (Behavior)ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(entryId));
			state.setEntry(entry);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.EXIT)) {
			CameoUtils.logGUI("Creating exit for State.");
			String exitId = xmlElement.getAttribute(XmlTagConstants.EXIT);
			Behavior exit = (Behavior)ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(exitId));
			state.setExit(exit);
		}
	}
	
	public void setOwner(Project project, Element owner) {
		Region region = null;
		Collection<Region> regions = null;
		if(owner != null) {
			//if owner is not a region, create a region and set that region as owned by state machine
			if(owner instanceof Region) {
				element.setOwner(owner);
			} else if(owner instanceof StateMachine) {
				regions = ((StateMachine) owner).getRegion();
				if(regions != null) {
					region = regions.iterator().next();
					element.setOwner(region);
				} else {
					CameoUtils.logGUI("Error in Cameo processes in auto-region creation.");
				}
			} else if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {
				Region existingRegion = PseudoState.findExistingRegion(owner);
				if(existingRegion != null) {
					CameoUtils.logGUI("Setting owner of " + name + " as existing Region.");
					element.setOwner(existingRegion);
				} else {
					CameoUtils.logGUI("Creating new region for " + name + " as child of " + owner.getHumanName());
					Region newRegion = f.createRegionInstance();
					newRegion.setOwner(owner);
					element.setOwner(newRegion);
				}
			} else {
				owner = CameoUtils.findNearestRegion(project, owner);
				if(owner == null) {
					String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
					CameoUtils.logGUI(logMessage);
					ImportLog.log(logMessage);
					element.dispose();
				}
				element.setOwner(owner);
			}
		} else {
			String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			element.dispose();
		}
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
		Element owner = element.getOwner().getOwner();
		
		if(owner == null) {
			ExportLog.log(String.format("No parent found for state %s with id %s", element.getHumanName(), element.getID()));
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
