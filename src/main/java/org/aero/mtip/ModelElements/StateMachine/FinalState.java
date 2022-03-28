/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

public class FinalState extends CommonElement {
	public FinalState(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FINALSTATE;
		this.xmlConstant = XmlTagConstants.FINALSTATE;
		this.sysmlElement = f.createFinalStateInstance();
	}
	
	//NOTE: Must be added to Region under state Machine
	@Override
	public void setOwner(Project project, Element owner) {
		if(owner != null) {
			//if owner is not a region, create a region and set that region as owned by state machine
			if(owner instanceof Region) {
				sysmlElement.setOwner(owner);
			} else if(owner instanceof StateMachine) {
				Collection<Region> regions = ((StateMachine) owner).getRegion();
				if(regions != null) {
					Region region = regions.iterator().next();
					sysmlElement.setOwner(region);
				} else {
					CameoUtils.logGUI("CREATE REGION HERE!!!!!!!!!!!!!");
					//create region
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