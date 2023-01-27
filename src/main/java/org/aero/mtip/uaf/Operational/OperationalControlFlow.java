package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ControlFlow;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class OperationalControlFlow extends ControlFlow {

	public OperationalControlFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_CONTROL_FLOW;
		this.sysmlElement = f.createControlFlowInstance();
	}
	
	@Override
	
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_CONTROL_FLOW_STEREOTYPE);
		
		return sysmlElement;
	}
}
