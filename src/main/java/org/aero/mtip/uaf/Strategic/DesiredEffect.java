package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class DesiredEffect extends Dependency {

	public DesiredEffect(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.DESIRED_EFFECT;
		this.xmlConstant = XmlTagConstants.DESIRED_EFFECT;
		this.sysmlElement = f.createDependencyInstance();
	}
	
	//TODO IMPLEMENT THE IMPORT createElement addclient and supplier
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.DESIRED_EFFECT_STEREOTYPE);
		
		return sysmlElement;
	}
}
