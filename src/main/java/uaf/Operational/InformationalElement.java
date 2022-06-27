package uaf.Operational;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

import uaf.UAFElement;

public class InformationalElement extends CommonElement implements UAFElement {
	
	public InformationalElement(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.INFORMATIONAL_ELEMENT;
		this.xmlConstant = XmlTagConstants.INFORMATIONAL_ELEMENT;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.INFORMATIONAL_ELEMENT, creationProfile);
	}
}