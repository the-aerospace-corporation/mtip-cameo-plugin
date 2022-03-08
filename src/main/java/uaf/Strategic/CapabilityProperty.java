package uaf.Strategic;

import org.aero.huddle.ModelElements.Sequence.Property;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.UAFConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class CapabilityProperty extends Property {

	public CapabilityProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.CAPABILITY_PROPERTY;
		this.xmlConstant = XmlTagConstants.CAPABILITY_PROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.CAPABILITY_PROPERTY_STEREOTYPE);
		
		return sysmlElement;
	}
}
