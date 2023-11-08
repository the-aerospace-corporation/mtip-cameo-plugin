/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class ControlFlow extends CommonRelationship {

	public ControlFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.CONTROLFLOW;
		this.xmlConstant = XmlTagConstants.CONTROLFLOW;
		this.sysmlElement = f.createControlFlowInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project,owner, client, supplier, xmlElement);
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow cf = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow)sysmlElement;
		
		if(xmlElement.hasAttribute(XmlTagConstants.GUARD)) {
			CameoUtils.logGUI("Creating guard for control flow with id " + xmlElement.getEAID() + " and guard value " + xmlElement.getAttribute(XmlTagConstants.GUARD));
			ValueSpecification guard = cf.getGuard();
			if(guard != null) {
				guard.dispose();
			}
			LiteralString specification = f.createLiteralStringInstance();
			specification.setValue(xmlElement.getAttribute(XmlTagConstants.GUARD));			
			cf.setGuard(specification);
		} else {
			CameoUtils.logGUI("Control flow " + xmlElement.getEAID() + " has no guard.");
		}
		
		return cf;
	}
	
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow cf = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow)element;
		ValueSpecification vs = cf.getGuard();
		if(vs != null) {
			org.w3c.dom.Element attribute = createAttributefromValueSpecification(vs, XmlTagConstants.GUARD, xmlDoc);
			if(attribute != null) {
				attributes.appendChild(attribute);
			}
		}
		
		return data;
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(project, supplier);
		}
		sysmlElement.setOwner(owner);
	}
	
	@Override
	public void setSupplier() {
		ActivityEdge activityEdge = (ActivityEdge)sysmlElement;
		activityEdge.setSource((ActivityNode) supplier);
	}
	
	@Override
	public void setSupplier(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.supplier = activityEdge.getSource();
	}
	
	@Override
	public void setClient(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.client = activityEdge.getTarget();
	}
	
	@Override
	public void setClient() {
		ActivityEdge activityEdge = (ActivityEdge)sysmlElement;
		activityEdge.setTarget((ActivityNode) client);
	}
	
	@Override
	public Element getSupplier(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		return activityEdge.getSource();
	}
	
	@Override
	public Element getClient(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		return activityEdge.getTarget();
	}
}
