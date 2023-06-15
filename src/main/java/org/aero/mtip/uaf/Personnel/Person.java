package org.aero.mtip.uaf.Personnel;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFElement;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class Person extends CommonElement implements UAFElement{
	public Person(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.PERSON;
		this.xmlConstant = XmlTagConstants.PERSON;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME);
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.PERSON, creationProfile);
	}

}
