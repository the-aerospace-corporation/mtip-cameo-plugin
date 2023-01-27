package org.aero.mtip.uaf.Strategic;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.uaf.UAFConstants;
<<<<<<< HEAD:src/main/java/uaf/Strategic/CapabilityProperty.java
=======
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.SysmlConstants;
>>>>>>> 4562eba (Fixed errors in package naming conventions.):src/main/java/org/aero/mtip/uaf/Strategic/CapabilityProperty.java
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

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
