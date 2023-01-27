package org.aero.mtip.uaf.Operational;

import org.aero.mtip.ModelElements.Activity.Activity;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class StandardOperationalActivity extends Activity {

	public StandardOperationalActivity(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.STANDARD_OPERATIONAL_ACTIVITY;
		this.xmlConstant = XmlTagConstants.STANDARD_OPERATIONAL_ACTIVITY;
		this.sysmlElement = f.createActivityInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.STANDARD_OPERATIONAL_ACTIVITY_STEREOTYPE);
		
		return sysmlElement;
	}
}
