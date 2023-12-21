/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.ExportLog;
import org.aero.mtip.util.ImportLog;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdinternalstructures.ConnectableElement;
import com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.InteractionFragment;

public class Lifeline extends CommonElement {

	public Lifeline(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.LIFELINE;
		this.xmlConstant = XmlTagConstants.LIFELINE;
		this.sysmlElement = f.createLifelineInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		setRepresents(xmlElement);
		setCoveredBy(xmlElement);
		
		return sysmlElement;
	}
	
	private void setRepresents(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)sysmlElement;
		String importedRepresentsID = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		
		String createdRepresentsID = ImportXmlSysml.idConversion(importedRepresentsID);
		if (createdRepresentsID == null || createdRepresentsID.trim().isEmpty()) {
			return;
		}
		
		Element representsElement = (Element) project.getElementByID(createdRepresentsID);
		
		if (representsElement == null || !(representsElement instanceof ConnectableElement)) {
			return;
		}
		
		lifeline.setRepresents((ConnectableElement) representsElement);
	}
	
	private void setCoveredBy(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)sysmlElement;
		Collection<InteractionFragment> coveredByElements = lifeline.getCoveredBy();
		
		List<String> importedCoveredByIDs = xmlElement.getCoveredBy();
		for (String importedCoveredByID : importedCoveredByIDs) {

			String createdCoveredById = ImportXmlSysml.idConversion(importedCoveredByID);
			
			if (createdCoveredById == null 
					|| createdCoveredById.trim().isEmpty()
					|| coveredByElements == null) {
				
				continue;
			}
			
			Element coveredByElement = (Element) project.getElementByID(createdCoveredById);
			
			if (!(coveredByElement instanceof InteractionFragment)) {
				continue;
			}
			
			coveredByElements.add((InteractionFragment)coveredByElement);
		}
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		List<String> coveredBy = modelElement.getCoveredBy();
		for(String coveredByID : coveredBy) {
			XMLItem dependentElementXML = parsedXML.get(coveredByID);
			Element newElement = null;
			if(dependentElementXML.getCategory().contentEquals(SysmlConstants.RELATIONSHIP)) {
				newElement = ImportXmlSysml.buildRelationship(project, parsedXML, dependentElementXML);
			} else {
				newElement = ImportXmlSysml.buildElement(project, parsedXML, dependentElementXML);
			}
			if(newElement != null) {
				modelElement.setCoveredByID(coveredByID, newElement.getID());
			}
		}
		
		String representsID = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		XMLItem representsXML = parsedXML.get(representsID);
		
		if (representsXML == null) {
			ImportLog.log(String.format("Missing XML for dependent element with id %s", representsID));
			return;
		}
		
		ImportXmlSysml.buildElement(project, parsedXML, representsXML);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = super.writeToXML(element, project, xmlDoc);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		if (!(element instanceof com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)) {
			ExportLog.log(String.format("Error writing %s with id %s to XML. Exported as lifeline but not instance of lifeline.", element.getHumanName(), element.getID()));
			return null;
		}
		
		writeRepresents(element, xmlDoc, relationships);
		writeCoveredBy(element, xmlDoc, relationships);		
		
		return data;
	}
	
	private void writeRepresents(Element element, Document xmlDoc, org.w3c.dom.Element relationships) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline) element;
		ConnectableElement represents = lifeline.getRepresents();
		
		if(represents == null) {
			return;
		}
		
		org.w3c.dom.Element representsTag = createRel(xmlDoc, represents, XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		relationships.appendChild(representsTag);
		
		Type typedBy = represents.getType();
		
		if(typedBy == null) {
			return;
		}
		
		org.w3c.dom.Element typedByTag = createRel(xmlDoc, typedBy, XmlTagConstants.ATTRIBUTE_NAME_TYPED_BY);
		relationships.appendChild(typedByTag);
	}
	
	private void writeCoveredBy(Element element, Document xmlDoc, org.w3c.dom.Element relationships) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline) element;
		java.util.Collection<InteractionFragment> interactionFragments = lifeline.getCoveredBy();
		
		for(InteractionFragment interactionFragment : interactionFragments) {
			org.w3c.dom.Element interactionFragmentTag = createRel(xmlDoc, interactionFragment, XmlTagConstants.ATTRIBUTE_NAME_COVERED_BY);
			relationships.appendChild(interactionFragmentTag);
		}
	}
}
