/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Activity;

import java.util.Collections;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XmlTagConstants;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActivityPartition extends CommonElement {

	public ActivityPartition(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.metamodelConstant = SysmlConstants.ACTIVITY_PARTITION;
		this.xmlConstant = XmlTagConstants.ACTIVITY_PARTITION;
		this.element = f.createActivityPartitionInstance();
	}
	
	@Override
	public void setOwner(Element owner) {
	    if(!(owner instanceof Activity)) {
	        owner = CameoUtils.findNearestActivity(owner);
	    }
	        
	    if (owner == null) {
	      ModelHelper.dispose(Collections.singletonList(element));
	      return;
	    }
	    
	    element.setOwner(owner);
	    com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition ap = (com.nomagic.uml2.ext.magicdraw.activities.mdintermediateactivities.ActivityPartition)element;
	    ap.setInActivity((com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity)owner);
	}
}
