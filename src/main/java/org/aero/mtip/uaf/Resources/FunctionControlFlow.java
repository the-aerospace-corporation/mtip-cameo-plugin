package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.Activity.ControlFlow;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class FunctionControlFlow extends ControlFlow{
	
	public FunctionControlFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.FUNCTION_CONTROL_FLOW;
		this.xmlConstant = XmlTagConstants.FUNCTION_CONTROL_FLOW;
		this.sysmlElement = f.createControlFlowInstance();
	}
	
	@Override
	
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.FUNCTION_CONTROL_FLOW_STEREOTYPE);
		
		return sysmlElement;
	}
}
