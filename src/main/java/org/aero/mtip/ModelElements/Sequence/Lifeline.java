/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectableElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.InteractionFragment;

public class Lifeline extends CommonElement {

	public Lifeline(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.LIFELINE;
		this.xmlConstant = XmlTagConstants.LIFELINE;
		this.element = f.createLifelineInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
//		List<String> oldCoveredByIDs = xmlElement.getCoveredBy();
//		for(String oldID : oldCoveredByIDs) {
			com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)element;
//			lifeline.
////		}
		return element;
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		List<String> coveredBy = modelElement.getCoveredBy();
		
		for(String coveredByID : coveredBy) {
			XMLItem dependentElementXML = parsedXML.get(coveredByID);
			Element newElement = null;
			if(dependentElementXML.getCategory().contentEquals(SysmlConstants.RELATIONSHIP)) {
				newElement = ImportXmlSysml.buildRelationship(project, parsedXML, dependentElementXML, coveredByID);
			} else {
				newElement = ImportXmlSysml.buildElement(project, parsedXML, dependentElementXML, coveredByID);
			}
			if(newElement != null) {
				modelElement.setCoveredByID(coveredByID, newElement.getID());
			}
		}
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline) element;
		java.util.Collection<InteractionFragment> interactionFragments = lifeline.getCoveredBy();
		for(InteractionFragment interactionFragment : interactionFragments) {
			org.w3c.dom.Element interactionFragmentTag = createRel(xmlDoc, interactionFragment, XmlTagConstants.ATTRIBUTE_NAME_COVERED_BY);
			relationships.appendChild(interactionFragmentTag);
			CameoUtils.logGUI("Lifeline covered by interaction fragment: " + ((NamedElement)interactionFragment).getName());
		}
		
		ConnectableElement represents = lifeline.getRepresents();
			
		if(represents != null) {
			org.w3c.dom.Element representsTag = createRel(xmlDoc, represents, XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
			relationships.appendChild(representsTag);
			CameoUtils.logGUI("Lifeline represents element: " + ((NamedElement)represents).getName());
			
			Type typedBy = represents.getType();
			if(typedBy != null) {
				org.w3c.dom.Element typedByTag = createRel(xmlDoc, typedBy, XmlTagConstants.ATTRIBUTE_NAME_TYPED_BY);
				relationships.appendChild(typedByTag);
				CameoUtils.logGUI("Lifeline typed by element: " + ((NamedElement)typedBy).getName());
			}
		}
		
		
		return data;
	}
}
