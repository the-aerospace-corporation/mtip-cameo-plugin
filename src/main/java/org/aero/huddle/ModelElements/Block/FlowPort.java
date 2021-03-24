package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.sysml.util.SysMLHelper;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.VisibilityKindEnum;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class FlowPort extends CommonElement {

	public FlowPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.FLOWPORT;
		this.xmlConstant = XmlTagConstants.FLOWPORT;
		this.sysmlElement = f.createPortInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Port port = (Port) super.createElement(project, owner, xmlElement);                                            
		port.setVisibility(VisibilityKindEnum.PRIVATE);                         
   
		Stereotype flowPortStereotype = SysMLProfile.getInstance(port).getFlowPort();   
		StereotypesHelper.addStereotype(port, flowPortStereotype);   
		
		//Flow Direction enumeration missing from SysMLUtilities class
//		for (SysMLUtilities.FlowDirection c : SysMLUtilities.FlowDirection.values())
//		    System.out.println(c);
//		SysMLUtilities.setDirectionForFlowPort(port,  SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		
		SysMLHelper.setDirectionFlowPort(port, SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		return port;
	}
}
