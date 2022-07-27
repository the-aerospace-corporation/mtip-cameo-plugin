package uaf.Operational;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class OperationalExchangeKind extends Enumeration{
	public OperationalExchangeKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.OPERATIONAL_EXCHANGE_KIND;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_EXCHANGE_KIND;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.OPERATIONAL_EXCHANGE_KIND, creationProfile);
	}
}
