package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class DataElement extends CommonElement implements UAFElement {
	
	public DataElement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.DATA_ELEMENT;
		this.xmlConstant = XmlTagConstants.DATA_ELEMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.DATA_ELEMENT, creationProfile);
	}
}
