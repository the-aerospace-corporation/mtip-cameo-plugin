package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class BindingConnector extends Connector {

	public BindingConnector(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.BINDINGCONNECTOR;
		this.sysmlConstant = SysmlConstants.BINDINGCONNECTOR;
		this.sysmlElement= f.createConnectorInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype bindingConnectorStereotype = StereotypesHelper.getStereotype(project, "BindingConnector", sysmlProfile);
		StereotypesHelper.addStereotype(sysmlElement, bindingConnectorStereotype);
		
		return sysmlElement;
	}
}
