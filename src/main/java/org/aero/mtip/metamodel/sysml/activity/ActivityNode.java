/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.metamodel.sysml.activity;

import java.util.HashMap;
import org.aero.mtip.XML.XmlWriter;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.metamodel.core.CommonElement;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class ActivityNode extends CommonElement {

	public ActivityNode(String name, String EAID) {
		super(name, EAID);
	}

	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		if(xmlElement != null) {
			if(xmlElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION)) {
//				com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode activityNode = (com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode) element;
				// Add In Interruptible Region here if possible - may need to nest in diagram to achieve this.
			}
		}
		return element;
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		super.createDependentElements(parsedXML, modelElement);

		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_ACTIVITY)) {
			String activityId = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_ACTIVITY);
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(activityId));
		}
		
		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION)) {
			String iarID = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION);
			Importer.getInstance().buildElement(parsedXML, parsedXML.get(iarID));
		}
	}

	@Override
	public void setOwner(Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(owner);
		}
		
		element.setOwner(owner);
	}
	
	@Override
	public org.w3c.dom.Element writeToXML(Element element) {
		org.w3c.dom.Element data = super.writeToXML(element);
		org.w3c.dom.Element relationships = getRelationships(data.getChildNodes());
		
		writeActivity(relationships, element);
		writeInterruptibleActivityRegion(relationships, element);
		
		return data;
	}
	
	public void writeActivity(org.w3c.dom.Element relationships, Element element) {
	  if (!(element instanceof com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode)) {
        return;         // ActivityParameterNodes are ActivityNodes but cannot be cast.
      }
	  
	  com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode activityNode = (com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode)element;
      Activity activity = activityNode.getActivity();
      
      if (activity == null) {
        return;
      }
      
      org.w3c.dom.Element activityTag = XmlWriter.createMtipRelationship(activity, XmlTagConstants.ATTRIBUTE_NAME_ACTIVITY);
      XmlWriter.add(relationships, activityTag);
	}
	
	public void writeInterruptibleActivityRegion(org.w3c.dom.Element relationships, Element element) {
		if (!(element instanceof com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode)) {
		  return;         // ActivityParameterNodes are ActivityNodes but cannot be cast.
		}
	  
	    com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode activityNode = (com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode)element;
		java.util.Collection<com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.InterruptibleActivityRegion> iars = activityNode.getInInterruptibleRegion();
		
		for(com.nomagic.uml2.ext.magicdraw.activities.mdcompleteactivities.InterruptibleActivityRegion iar : iars) {
			org.w3c.dom.Element iarTag = XmlWriter.createMtipRelationship(iar, XmlTagConstants.ATTRIBUTE_NAME_INTERRUPTIBLE_ACTIVITY_REGION);
			XmlWriter.add(relationships, iarTag);
		}
	}
}
