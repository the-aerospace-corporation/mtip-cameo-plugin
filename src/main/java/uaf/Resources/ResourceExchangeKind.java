package uaf.Resources;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class ResourceExchangeKind extends Enumeration{
	public ResourceExchangeKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.RESOURCE_EXCHANGE_KIND;
		this.xmlConstant = XmlTagConstants.RESOURCE_EXCHANGE_KIND;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.RESOURCE_EXCHANGE_KIND, creationProfile);
	}
}