/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class StateInvariant extends CommonElement {

	public StateInvariant(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.STATEINVARIANT;
		this.xmlConstant = XmlTagConstants.STATEINVARIANT;
		this.sysmlElement = f.createStateInvariantInstance();
	}
	
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant stateInvariant = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.StateInvariant)element;
		Constraint constraint = stateInvariant.getInvariant();
		if(constraint != null) {
			ValueSpecification valueSpecification = constraint.getSpecification();
			if(valueSpecification != null) {
				if(valueSpecification instanceof ElementValue) {
					ElementValue elementValue = (ElementValue) valueSpecification;
					// invariant is the state element
					Element invariant = elementValue.getElement();
					if(invariant != null) {
						org.w3c.dom.Element invariantTag = createRel(xmlDoc, invariant, XmlTagConstants.INVARIANT_TAG);
						relationships.appendChild(invariantTag);
					} else {
						ExportLog.log("Invariant element value is null.");
					}
				} else {
					ExportLog.log("Value specification of constraint with id " + constraint.getLocalID() + " of state invariant with id " + this.EAID + " is not instanceof ElementValue.");
				}
			} else {
				ExportLog.log("No specification for constraint with id " + constraint.getLocalID() + " of state invariant with id " + this.EAID);
			}
		} else {
			ExportLog.log("No constraint for state invariant with id: " + this.EAID);
		}
		
		return data;
		
	}
}
