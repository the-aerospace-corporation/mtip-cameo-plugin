package org.aero.huddle.ModelElements;

import java.util.Collection;

import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

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
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
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

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.STATE));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}
}
