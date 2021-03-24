package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.CameoUtils;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public abstract class ActivityNode extends CommonElement {

	public ActivityNode(String name, String EAID) {
		super(name, EAID);
	}
	
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(project, owner);
		}
		sysmlElement.setOwner(owner);
	}
}
