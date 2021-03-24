package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ValueProperty extends CommonElement {
	public ValueProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.VALUEPROPERTY;
		this.xmlConstant = XmlTagConstants.VALUEPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		
		Profile mdCustomSysml = StereotypesHelper.getProfile(project, "MD Customization for SysML");
		Stereotype valPropStereotype = StereotypesHelper.getStereotype(project,  "ValueProperty", mdCustomSysml);
		StereotypesHelper.addStereotype(sysmlElement, valPropStereotype);
		
		return sysmlElement;
	}
}
