package uaf.Strategic;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

import uaf.UAFConstants;
import uaf.UAFElement;

public class Capability extends CommonElement implements UAFElement {
	
	public Capability(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.CAPABILITY;
		this.xmlConstant = XmlTagConstants.CAPABILITY;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.CAPABILITY, creationProfile);
	}
}
