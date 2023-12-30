/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActivityPartition extends CommonElement {

	public ActivityPartition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITY_PARTITION;
		this.xmlConstant = XmlTagConstants.ACTIVITY_PARTITION;
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
