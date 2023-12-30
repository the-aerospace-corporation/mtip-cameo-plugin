/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class StateInvariant extends CommonElement {

	public StateInvariant(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STATE_INVARIANT;
		this.xmlConstant = XmlTagConstants.STATEINVARIANT;
		this.element = f.createStateInvariantInstance();
	}
	
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeInvariant(relationships, element);
		
		return data;
	}
	
	private void writeInvariant(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant stateInvariant = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant)element;
		Constraint constraint = stateInvariant.getInvariant();
		
		if(constraint == null) {
			return;
		}
		
		ValueSpecification valueSpecification = constraint.getSpecification();
		
		if(valueSpecification == null) {
			return;
		}
		
		if(!(valueSpecification instanceof ElementValue)) {
			return;
		}
		
		ElementValue elementValue = (ElementValue) valueSpecification;
		Element invariant = elementValue.getElement();
		
		if(invariant == null) {
			return;
		}
		
		org.w3c.dom.Element invariantTag = XmlWriter.createMtipRelationship(invariant, XmlTagConstants.INVARIANT_TAG);
		XmlWriter.add(relationships, invariantTag);
	}
}
