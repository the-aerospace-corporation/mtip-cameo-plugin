/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class ObjectFlow extends CommonRelationship {

	public ObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OBJECTFLOW;
		this.xmlConstant = XmlTagConstants.OBJECTFLOW;
		this.sysmlElement = f.createObjectFlowInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project,owner, client, supplier, xmlElement);
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow of = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow)sysmlElement;
		
		//if(xmlElement.hasGuard()) {
		if (xmlElement.hasAttribute(XmlTagConstants.GUARD)) {
			ValueSpecification guard = of.getGuard();
			if(guard != null) {
				guard.dispose();
			}
			LiteralString specification = f.createLiteralStringInstance();
			//specification.setValue(xmlElement.getGuard());			
			specification.setValue(xmlElement.getAttribute(XmlTagConstants.GUARD));
			of.setGuard(specification);
		} else {
			CameoUtils.logGUI("Object flow "+ xmlElement.getEAID() + "has no guard.");
		}
		
		return of;
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow of = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow)element;
		ValueSpecification vs = of.getGuard();
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
	public void setClient() {
		ActivityEdge activityEdge = (ActivityEdge)sysmlElement;
		activityEdge.setTarget((ActivityNode) client);
	}
	
	/*
	 * 
	 */
	
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
	
	
	//
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
