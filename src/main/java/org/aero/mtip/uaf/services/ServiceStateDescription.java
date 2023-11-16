package org.aero.mtip.uaf.services;

import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFProfile;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class ServiceStateDescription extends StateMachine {

	public ServiceStateDescription(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.SERVICE_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.SERVICE_STATE_DESCRIPTION;
		this.creationProfile = UAFProfile.UAF_PROFILE;
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.SERVICE_STATE_DESCRIPTION, creationProfile);
	}
}
