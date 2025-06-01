/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.activity;

import java.util.Arrays;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonRelationship;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class ObjectFlow extends CommonRelationship {

	public ObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.OBJECT_FLOW;
		this.xmlConstant = XmlTagConstants.OBJECTFLOW;
		this.element = f.createObjectFlowInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project,owner, client, supplier, xmlElement);
		
		if (xmlElement.hasAttribute(XmlTagConstants.GUARD)) {
			setGuard(xmlElement);
		}
		
		return element;
	}
	
	private void setGuard(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow of = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow)element;
		ValueSpecification guard = of.getGuard();
		
		if(guard != null) {
			ModelHelper.dispose(Arrays.asList(guard));
		}
		
		LiteralString specification = f.createLiteralStringInstance();		
		specification.setValue(xmlElement.getAttribute(XmlTagConstants.GUARD));
		of.setGuard(specification);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeGuard(attributes, element);
		
		return data;
	}
	
	private void writeGuard(org.w3c.dom.Element attributes, Element elemet) {
		com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow of = (com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ObjectFlow)element;
		
		ValueSpecification vs = of.getGuard();
		
		if(vs == null) {
			return;
		}
		
		org.w3c.dom.Element guardTag = XmlWriter.createAttributeFromValueSpecification(vs, XmlTagConstants.GUARD);
		
		if(guardTag == null) {
			return;
		}
		
		XmlWriter.add(attributes, guardTag);
	}
	
	@Override
	public void setOwner(Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(supplier);
		}

		element.setOwner(owner);
	}
	
	@Override
	public void setSupplier() {
		ActivityEdge activityEdge = (ActivityEdge)element;
		activityEdge.setSource((ActivityNode) supplier);
	}
	
	@Override
	public void setClient() {
		ActivityEdge activityEdge = (ActivityEdge)element;
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
