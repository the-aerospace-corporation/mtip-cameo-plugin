/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.HashMap;
import java.util.Map;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class ActivityNode extends CommonElement {

	public ActivityNode(String name, String EAID) {
		super(name, EAID);
	}
	
	@Override
	public void createDependentElements(Project project, HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {
		super.createDependentElements(project, parsedXML, modelElement);

		if(modelElement.hasAttribute(XmlTagConstants.ATTRIBUTE_NAME_ACTIVITY)) {
			String activityId = modelElement.getAttribute(XmlTagConstants.ATTRIBUTE_NAME_ACTIVITY);
			ImportXmlSysml.buildElement(project, parsedXML, parsedXML.get(activityId), activityId);
		}
	}

	@Override
	public void setOwner(Project project, Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(project, owner);
		}
		sysmlElement.setOwner(owner);
	}
}
