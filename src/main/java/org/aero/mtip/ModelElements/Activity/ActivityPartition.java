/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;


import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActivityPartition extends CommonElement {

	public ActivityPartition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.ACTIVITYPARTITION;
		this.xmlConstant = XmlTagConstants.ACTIVITYPARTITION;
		this.element = f.createActivityPartitionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition ap = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition)element;
		if(owner instanceof Activity) {
			ap.setInActivity((com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity)owner);
		}
		
		return element;
	}
}
