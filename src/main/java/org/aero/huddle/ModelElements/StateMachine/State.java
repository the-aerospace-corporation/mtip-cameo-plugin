/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;
import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

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
		this.sysmlElement = f.createStateInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)sysmlElement;
		
		if(xmlElement != null) {
			if(xmlElement.isSubmachine()) {
				state.setSubmachine((StateMachine) project.getElementByID(xmlElement.getNewSubmachine()));
			}
		}		
		
		return sysmlElement;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		
	}
	
	@Override
	public void addDependentElements(Map<String, XMLItem> parsedXML, XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)sysmlElement;

		if(xmlElement.hasAttribute(XmlTagConstants.DO_ACTIVITY)) {
			CameoUtils.logGUI("Creating do activity for State.");
			String doActivityId = xmlElement.getAttribute(XmlTagConstants.DO_ACTIVITY);
			Behavior doActivity = (Behavior) ImportXmlSysml.getOrBuildElement(project, parsedXML, doActivityId);
			state.setDoActivity(doActivity);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.ENTRY)) {
			CameoUtils.logGUI("Creating entry for State.");
			String entryId = xmlElement.getAttribute(XmlTagConstants.ENTRY);
			Behavior entry = (Behavior)ImportXmlSysml.getOrBuildElement(project, parsedXML, entryId);
			state.setEntry(entry);
		}
		
		if(xmlElement.hasAttribute(XmlTagConstants.EXIT)) {
			CameoUtils.logGUI("Creating exit for State.");
			String exitId = xmlElement.getAttribute(XmlTagConstants.EXIT);
			Behavior exit = (Behavior)ImportXmlSysml.getOrBuildElement(project, parsedXML, exitId);
			state.setExit(exit);
		}
	}
	
	public void setOwner(Project project, Element owner) {
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
				if(owner == null) {
					String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
					CameoUtils.logGUI(logMessage);
					ImportLog.log(logMessage);
					sysmlElement.dispose();
				}
				sysmlElement.setOwner(owner);
			}
		} else {
			String logMessage = "Invalid parent. No parent provided and primary model invalid parent for " + name + " with id " + EAID + ". Element could not be placed in model.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			sysmlElement.dispose();
		}
	}

	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		if(state.isSubmachineState()) {
			org.w3c.dom.Element submachine = createStringAttribute(xmlDoc, XmlTagConstants.SUBMACHINE, state.getSubmachine().getLocalID());
			attributes.appendChild(submachine);
		}
		
		Behavior doActivity = state.getDoActivity();
		Behavior entry = state.getEntry();
		Behavior exit = state.getExit();
		
		if(doActivity != null) {
			org.w3c.dom.Element doActivityTag = createRel(xmlDoc, doActivity, XmlTagConstants.DO_ACTIVITY);
			relationships.appendChild(doActivityTag);
		}
		
		if(entry != null) {
			org.w3c.dom.Element entryTag = createRel(xmlDoc, entry, XmlTagConstants.ENTRY);
			relationships.appendChild(entryTag);
		}
		
		if(exit != null) {
			org.w3c.dom.Element exitTag = createRel(xmlDoc, exit, XmlTagConstants.EXIT);
			relationships.appendChild(exitTag);
		}
		
		return data;
	}
	
	@Override
	protected org.w3c.dom.Element createRelationships(Document xmlDoc, Element element) {
		org.w3c.dom.Element relationships = xmlDoc.createElement(XmlTagConstants.RELATIONSHIPS);
		relationships.setAttribute(XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		if(element.getOwner().getOwner() != null) {
			org.w3c.dom.Element hasParent = createRel(xmlDoc, element.getOwner().getOwner(), XmlTagConstants.HAS_PARENT);
			relationships.appendChild(hasParent);
		}
		
		return relationships;
	}
}
