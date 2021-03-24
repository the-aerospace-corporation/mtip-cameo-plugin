package org.aero.huddle.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKind;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

public abstract class PseudoState extends CommonElement {
	protected PseudostateKind psKind;
	
	public PseudoState(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlElement = f.createPseudostateInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		Pseudostate pseudoState = (Pseudostate)sysmlElement;
		pseudoState.setKind(this.psKind);
		
		return pseudoState;
	}
	
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
			} else if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {
				Region existingRegion = findExistingRegion(owner);
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
