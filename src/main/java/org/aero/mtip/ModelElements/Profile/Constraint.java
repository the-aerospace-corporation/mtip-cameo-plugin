/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Profile;

import java.util.HashMap;
import java.util.List;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;

public class Constraint extends CommonElement {
	public static final String VALUE_SPECIFICATION = "valueSpecification";
	public static final String CONSTRAINED_ELEMENT = "constrainedElement";
	
	public Constraint(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.CONSTRAINT;
		this.xmlConstant = XmlTagConstants.CONSTRAINT;
		this.element = f.createConstraintInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
		
		ValueSpecification oe = (ValueSpecification) project.getElementByID(xmlElement.getNewValueSpecification());
		constraint.setSpecification(oe);

		for(String constrainedElement : xmlElement.getNewConstrainedElements()) {
			Element constrainedCameoElement = (Element) project.getElementByID(constrainedElement);
			constraint.getConstrainedElement().add(constrainedCameoElement);
		}
		
		return constraint;
	}
	
	public Element createElement(Project project, Element owner) {
		super.createElement(project, owner, null);
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
				
		return constraint;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		for(String constrainedElement : modelElement.getConstrainedElements()) {
			if(constrainedElement.contentEquals("_9_0_62a020a_1105704885343_144138_7929")) {
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Class");
				
                if (constrainedCameoElement == null) {
                  continue;
                }
				
				modelElement.addNewConstrainedElement(MtipUtils.getId(constrainedCameoElement));
			} else if(constrainedElement.contentEquals("_9_0_62a020a_1105704885473_18793_7971")) { 
				Element constrainedCameoElement = Finder.byQualifiedName().find(project, "UML Standard Profile::UML2 Metamodel::Association");
				
                if (constrainedCameoElement == null) {
                  continue;
                }
				
				modelElement.addNewConstrainedElement(MtipUtils.getId(constrainedCameoElement));
			} else {
				Element constrainedCameoElement = Importer.getInstance().buildElement(parsedXML, parsedXML.get(constrainedElement));
				
				if (constrainedCameoElement == null) {
				  continue;
				}
				
				modelElement.addNewConstrainedElement(MtipUtils.getId(constrainedCameoElement));
			}
			
		}
		if(modelElement.hasValueSpecification()) {
			Element valueSpecification = Importer.getInstance().buildElement(parsedXML, parsedXML.get(modelElement.getValueSpecification()));
			
            if (valueSpecification == null) {
              return;
            }
			
			modelElement.setNewValueSpecification(MtipUtils.getId(valueSpecification));
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		writeSpecification(attributes, element);
		writeConstrainedElements(relationships, element);
		
		return data;
	}
	
	private void writeSpecification(org.w3c.dom.Element attributes, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
		ValueSpecification vs = constraint.getSpecification();
		
		if (vs == null) {
			return;
		}
		
		org.w3c.dom.Element valueSpecTag = XmlWriter.createAttributeFromValueSpecification(vs, Constraint.VALUE_SPECIFICATION);
		
		if (valueSpecTag == null) {
			return;
		}
		
		XmlWriter.add(attributes, valueSpecTag);
	}
	
	protected void writeConstrainedElements(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint constraint = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Constraint)element;
		List<Element> constrainedElements = constraint.getConstrainedElement();
		
		for(Element constrainedElement : constrainedElements) {
			org.w3c.dom.Element constrainedElementTag = XmlWriter.createMtipRelationship(constrainedElement, Constraint.CONSTRAINED_ELEMENT);
			XmlWriter.add(relationships, constrainedElementTag);
		}
	}
}
