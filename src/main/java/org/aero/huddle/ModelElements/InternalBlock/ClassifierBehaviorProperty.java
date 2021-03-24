package org.aero.huddle.ModelElements.InternalBlock;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ClassifierBehaviorProperty extends CommonElement {

	public ClassifierBehaviorProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.CLASSIFIERBEHAVIORPROPERTY;
		this.sysmlConstant = SysmlConstants.CLASSIFIERBEHAVIORPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype classifierBehaviorPropertyStereotype = StereotypesHelper.getStereotype(project, "ClassifierBehaviorProperty", sysmlProfile);
		StereotypesHelper.addStereotype(sysmlElement, classifierBehaviorPropertyStereotype);

		return sysmlElement;
	}
}
