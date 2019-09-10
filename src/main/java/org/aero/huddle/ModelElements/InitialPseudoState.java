package org.aero.huddle.ModelElements;

import java.util.Collection;

import org.aero.huddle.util.CameoUtils;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.StateMachine;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Pseudostate;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKind;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.PseudostateKindEnum;
import com.nomagic.uml2.ext.magicdraw.statemachines.mdbehaviorstatemachines.Region;
import com.nomagic.uml2.impl.ElementsFactory;

public class InitialPseudoState extends CommonElement {
	public InitialPseudoState(String name, String EAID) {
		super(name, EAID);
	}
	
	//NOTE: Must be added to Region under state Machine
	public Element createElement(Project project, Element owner) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		Element sysmlElement = null;
		Pseudostate pseudoState = f.createPseudostateInstance();
		PseudostateKind psKind = PseudostateKindEnum.getByName("initial");
		pseudoState.setKind(psKind);
		pseudoState.setName(name);
		
		if(owner != null) {
			//if owner is not a region, create a region and set that region as owned by state machine
			if(owner instanceof Region) {
				pseudoState.setOwner(owner);
			} else if (owner instanceof StateMachine) {
				StateMachine sm = (StateMachine)owner;
				Collection<Region> regions = sm.getRegion();
				if(regions != null) {
					Region region = regions.iterator().next();
					pseudoState.setOwner(region);
				}
			}
		} else {
			CameoUtils.logGUI("No region to add InitialPseudoState " + name + " to.");
		}
		
		sysmlElement = (Element)pseudoState;
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}
}
