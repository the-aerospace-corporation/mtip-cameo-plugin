package org.aero.mtip.uaf.Standards;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFElement;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class ProtocolStack extends CommonElement implements UAFElement{
	public ProtocolStack(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.PROTOCOL_STACK;
		this.xmlConstant = XmlTagConstants.PROTOCOL_STACK;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME);
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.PROTOCOL_STACK, creationProfile);
	}

}
