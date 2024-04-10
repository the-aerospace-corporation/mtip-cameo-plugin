/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonRelationship;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

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
		this.sysmlConstant = SysmlConstants.CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.CONTROLFLOW;
		this.element = f.createControlFlowInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project,owner, client, supplier, xmlElement);
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow cf = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow)element;
		
		if(xmlElement.hasAttribute(XmlTagConstants.GUARD)) {
			ValueSpecification guard = cf.getGuard();
			
			if(guard != null) {
				guard.dispose();
			}
			
			LiteralString specification = f.createLiteralStringInstance();
			specification.setValue(xmlElement.getAttribute(XmlTagConstants.GUARD));			
			cf.setGuard(specification);
		}
		
		return cf;
	}
	
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeGuard(attributes, element);
		
		return data;
	}
	
	public void writeGuard(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow cf = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ControlFlow)element;
		ValueSpecification vs = cf.getGuard();
		
		if(vs == null) {
			return;
		}
		
		org.w3c.dom.Element guardTag = XmlWriter.createAttributeFromValueSpecification(vs, XmlTagConstants.GUARD);
		
		if(guardTag == null) {
			ExportLog.log(String.format("Failed to create xml attribute for guard of control flow with id %s", element.getID()));
			return;	
		}
		
		XmlWriter.add(attributes, guardTag);
	}
	
	@Override
	public void setOwner(Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(project, supplier);
		}
		element.setOwner(owner);
	}
	
	@Override
	public void setSupplier() {
		ActivityEdge activityEdge = (ActivityEdge)element;
		activityEdge.setSource((ActivityNode) supplier);
	}
	
	public void setClient() {
		ActivityEdge activityEdge = (ActivityEdge)element;
		activityEdge.setTarget((ActivityNode) client);
	}
	
	@Override
	public void getSupplier(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.supplier = activityEdge.getSource();
	}
	
	@Override
	public void getClient(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.client = activityEdge.getTarget();
	}
}
