/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.Block;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.profiles.SysML;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLHelper;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.VisibilityKindEnum;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;

public class FlowPort extends CommonElement {

	public FlowPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FLOW_PORT;
		this.xmlConstant = XmlTagConstants.FLOW_PORT;
		this.element = f.createPortInstance();
		this.creationStereotype = SysML.getFlowPortStereotype();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Port port = (Port) super.createElement(project, owner, xmlElement);                                            
		port.setVisibility(VisibilityKindEnum.PRIVATE);                         
		
		//Flow Direction enumeration missing from SysMLUtilities class
//		for (SysMLUtilities.FlowDirection c : SysMLUtilities.FlowDirection.values())
//		    System.out.println(c);
//		SysMLUtilities.setDirectionForFlowPort(port,  SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		
		SysMLHelper.setDirectionFlowPort(port, SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		return port;
	}
}
