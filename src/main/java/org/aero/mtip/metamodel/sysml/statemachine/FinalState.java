/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */
package org.aero.mtip.metamodel.sysml.statemachine;

import java.util.Collection;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;

public class FinalState extends CommonElement {
	public FinalState(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.FINAL_STATE;
		this.xmlConstant = XmlTagConstants.FINAL_STATE;
		this.element = f.createFinalStateInstance();
	}
	
	//NOTE: Must be added to Region under state Machine
	@Override
	public void setOwner(Element owner) {
		if (owner == null) {
			return;
		}
		
		if (owner instanceof Region) {
			element.setOwner(owner);
			return;
		}
		
		if (owner instanceof StateMachine) {
			Collection<Region> regions = ((StateMachine) owner).getRegion();
			if(regions != null) {
				Region region = regions.iterator().next();
				
				if (region != null) {
					element.setOwner(region);
					return;
				}
			}
		}
		
		owner = CameoUtils.findNearestRegion(project, owner);
		
		if (owner == null) {
			Logger.log(String.format("Invalid parent. Parent must be region for %s of type %s with id %s.", name, element.getHumanType(), MtipUtils.getId(element)));
			return;
		}
		
		element.setOwner(owner);
	}
	
	@Override
	protected void writeParent(org.w3c.dom.Element relationships) {
		Element owner = element.getOwner();
		
		if(owner == null) {
			Logger.log(String.format("No parent found for final state %s with id %s", element.getHumanName(), MtipUtils.getId(element)));
			return;
		}
		
		org.w3c.dom.Element hasParentTag = XmlWriter.createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
		XmlWriter.add(relationships, hasParentTag);
	}
}