package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
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
		
		if(xmlElement != null) {
			if(xmlElement.isSubmachine()) {
				com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)sysmlElement;
				state.setSubmachine((StateMachine) project.getElementByID(xmlElement.getNewSubmachine()));
			}
		}		

		return sysmlElement;
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
		
		com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State state = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)element;
		if(state.isSubmachineState()) {
			org.w3c.dom.Element submachine = xmlDoc.createElement("submachine");
			submachine.appendChild(xmlDoc.createTextNode(state.getSubmachine().getLocalID()));
			attributes.appendChild(submachine);
		}
		
		return data;
	}
}
