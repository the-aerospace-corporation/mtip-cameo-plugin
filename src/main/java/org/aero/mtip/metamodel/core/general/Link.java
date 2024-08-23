/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.core.general;

import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.PathElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class Link extends CommonElement {
	public Link(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.metamodelConstant = SysmlConstants.LINK;
		this.xmlConstant = XmlTagConstants.LINK;
		this.element = f.createInstanceSpecificationInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		
		return null;
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc, PresentationElement presentationElement) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeSupplier(relationships, presentationElement);
		writeClient(relationships, presentationElement);
		
		return data;
	}
	
	public void writeSupplier(org.w3c.dom.Element relationships, PresentationElement presentationElement) {
		PathElement pathElement = (PathElement)presentationElement;
		Element supplier = pathElement.getSupplier().getElement();
		
		if(supplier == null) {
			Logger.log("No supplier found for link.");
			return;
		}
		
		org.w3c.dom.Element supplierTag = XmlWriter.createMtipRelationship(supplier, XmlTagConstants.SUPPLIER);
		XmlWriter.add(relationships, supplierTag);
	}
	
	public void writeClient(org.w3c.dom.Element relationships, PresentationElement presentationElement) {
		PathElement pathElement = (PathElement)presentationElement;
		Element client = pathElement.getClient().getElement();
			
			
		if(client == null) {
			Logger.log("No client found for link.");
			return;
		}
		
		org.w3c.dom.Element clientTag = XmlWriter.createMtipRelationship(client, XmlTagConstants.CLIENT);
		XmlWriter.add(relationships, clientTag);
	}
}
