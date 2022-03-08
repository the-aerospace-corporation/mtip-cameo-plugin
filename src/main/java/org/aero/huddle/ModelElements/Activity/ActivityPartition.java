/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActivityPartition extends ActivityNode {

	public ActivityPartition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.ACTIVITYPARTITION;
		this.xmlConstant = XmlTagConstants.ACTIVITYPARTITION;
		this.sysmlElement = f.createActivityPartitionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition ap = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition)sysmlElement;
		if(owner instanceof Activity) {
			ap.setInActivity((com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity)owner);
		}
		
		return sysmlElement;
	}
}
