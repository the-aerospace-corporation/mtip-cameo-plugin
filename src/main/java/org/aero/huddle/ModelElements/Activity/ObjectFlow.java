package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.Activity;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ObjectFlow extends CommonRelationship {

	public ObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.OBJECTFLOW;
		this.xmlConstant = XmlTagConstants.OBJECTFLOW;
		this.sysmlElement = f.createObjectFlowInstance();
	}
	
	@Override
	public void setOwner(Project project, Element owner) {
		if(!(owner instanceof Activity)) {
			owner = CameoUtils.findNearestActivity(project, supplier);
		}
		sysmlElement.setOwner(owner);
	}
	
	@Override
	public void setSupplier() {
		ActivityEdge activityEdge = (ActivityEdge)sysmlElement;
		activityEdge.setSource((ActivityNode) supplier);
	}
	
	@Override
	public void setClient() {
		ActivityEdge activityEdge = (ActivityEdge)sysmlElement;
		activityEdge.setTarget((ActivityNode) client);
	}
	
	
	@Override
	public void getSupplier(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.supplier = activityEdge.getSource();
	}
	
	@Override
	public void getClient(Element element) {
		ActivityEdge activityEdge = (ActivityEdge)element;
		this.client = activityEdge.getTarget();
	}
}
