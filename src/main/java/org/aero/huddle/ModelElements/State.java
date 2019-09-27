package org.aero.huddle.ModelElements;

import java.util.Collection;

import org.aero.huddle.util.CameoUtils;

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
	
	public Element createElement(Project project, Element owner) {
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
			if(owner instanceof StateMachine ) {
				StateMachine sm = (StateMachine)owner;
				regions = sm.getRegion();
				if(regions != null) {
					region = regions.iterator().next();
				} else {
					region = f.createRegionInstance();
					region.setOwner(sm);
				}
				sysmlElement.setOwner(region);
			} else if (owner instanceof com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State) {	
				com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State cameoState = (com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.State)owner;
				regions = cameoState.getRegion();
				if(regions != null) {
					if(regions.iterator().hasNext()) {
						region = regions.iterator().next();
					} else {
						region = f.createRegionInstance();
						region.setOwner(cameoState);
					}
				} else {
					region = f.createRegionInstance();
					region.setOwner(cameoState);
				}
				
				sysmlElement.setOwner(region);
			} else {
				CameoUtils.logGUI("Owner not  " + name + " is null.");
			}
		} else {
			CameoUtils.logGUI("Owner for " + name + " is null.");
		}
			
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
}
