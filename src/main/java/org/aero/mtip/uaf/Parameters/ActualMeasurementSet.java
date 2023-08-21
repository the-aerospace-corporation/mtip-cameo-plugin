package org.aero.mtip.uaf.Parameters;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ActualMeasurementSet extends InstanceSpecification {

	public ActualMeasurementSet(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.ACTUAL_MEASUREMENT_SET;
		this.xmlConstant = XmlTagConstants.ACTUAL_MEASUREMENT_SET;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACTUAL_MEASUREMENT_SET_STEREOTYPE);
		
		return sysmlElement;
	}

}
