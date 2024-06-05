/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Sequence;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
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
		
		setRepresents(xmlElement);
		setCoveredBy(xmlElement);
		
		return element;
	}
	
	private void setRepresents(XMLItem xmlElement) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)element;
		String importedRepresentsID = xmlElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		
		String createdRepresentsID = Importer.idConversion(importedRepresentsID);
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
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline)element;
		Collection<InteractionFragment> coveredByElements = lifeline.getCoveredBy();
		
		List<String> importedCoveredByIDs = xmlElement.getCoveredBy();
		for (String importedCoveredByID : importedCoveredByIDs) {

			String createdCoveredById = Importer.idConversion(importedCoveredByID);
			
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
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		for(String coveredByID : modelElement.getCoveredBy()) {
			XMLItem dependentElementXML = parsedXML.get(coveredByID);
			
			if (dependentElementXML == null) {
				continue;
			}
			
			Element newElement = Importer.getInstance().buildEntity(parsedXML, dependentElementXML);
			
			if(newElement == null) {
				continue;
			}
			
			modelElement.setCoveredByID(coveredByID, MtipUtils.getId(newElement));
		}
		
		String representsID = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		XMLItem representsXML = parsedXML.get(representsID);
		
		if (representsXML == null) {
			Logger.log(String.format("Missing XML for dependent element with id %s", representsID));
			return;
		}
		
		Importer.getInstance().buildElement(parsedXML, representsXML);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeRepresents(relationships, element);
		writeCoveredBy(relationships, element);		
		
		return data;
	}
	
	private void writeRepresents(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline) element;
		ConnectableElement represents = lifeline.getRepresents();
		
		if(represents == null) {
			return;
		}
		
		org.w3c.dom.Element representsTag = XmlWriter.createMtipRelationship(represents, XmlTagConstants.ATTRIBUTE_NAME_REPRESENTS);
		XmlWriter.add(relationships, representsTag);
		
		// Lifeline is not instanceof TypedElement so CommonElement writeTypedBy will not write type.
		Type typedBy = represents.getType();
		
		if(typedBy == null) {
			return;
		}
		
		org.w3c.dom.Element typedByTag = XmlWriter.createMtipRelationship(typedBy, XmlTagConstants.ATTRIBUTE_NAME_TYPED_BY);
		XmlWriter.add(relationships, typedByTag);
	}
	
	private void writeCoveredBy(org.w3c.dom.Element relationships, Element element) {
		com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline lifeline = (com.nomagic.uml2.ext.magicdraw.interactions.mdbasicinteractions.Lifeline) element;
		java.util.Collection<InteractionFragment> interactionFragments = lifeline.getCoveredBy();
		
		for(InteractionFragment interactionFragment : interactionFragments) {
			if (!MtipUtils.isSupported(interactionFragment)) {
				continue;
			}
			
			org.w3c.dom.Element interactionFragmentTag = XmlWriter.createMtipRelationship(interactionFragment, XmlTagConstants.ATTRIBUTE_NAME_COVERED_BY);
			XmlWriter.add(relationships, interactionFragmentTag);
		}
	}
}
