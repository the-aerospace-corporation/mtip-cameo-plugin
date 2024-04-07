/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.ModelElements.StateMachine;

import java.util.Collection;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.XMLItem;

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
		this.element = f.createPseudostateInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		Pseudostate pseudoState = (Pseudostate)element;
		pseudoState.setKind(this.psKind);
		
		return pseudoState;
	}
	
	@Override
	public void setOwner(Element owner) {
		if (owner == null) {
			return;
		}
		
		if(owner instanceof Region) {
			element.setOwner(owner);
			return;
		} 

		if(owner instanceof StateMachine) {
			Collection<Region> regions = ((StateMachine) owner).getRegion();
			if(regions != null) {
				Region region = regions.iterator().next();
				
				if (region != null) {
					element.setOwner(region);
					return;
				}
			}
		}
		
		if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {
			Region existingRegion = findExistingRegion(owner);
			
			if(existingRegion != null) {
				element.setOwner(existingRegion);
				return;
			}
		}

		owner = CameoUtils.findNearestRegion(project, owner);
		
		if (owner == null) {
			ImportLog.log(String.format("Invalid parent. Parent must be region for %s of type %s with id %s.", name, element.getHumanType(), element.getID()));
			return;
		}
		
		element.setOwner(owner);
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
	
	@Override
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner().getOwner();
		
		if(owner == null) {
			ExportLog.log(String.format("No parent found for final state %s with id %s", element.getHumanName(), element.getID()));
			return;
		}
		
		org.w3c.dom.Element hasParentTag = XmlWriter.createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
		XmlWriter.add(relationships, hasParentTag);
	}
}
