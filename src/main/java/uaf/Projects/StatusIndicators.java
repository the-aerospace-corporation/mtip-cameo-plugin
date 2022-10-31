package uaf.Projects;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import uaf.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

public class StatusIndicators extends Enumeration{
	public StatusIndicators(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.STATUS_INDICATORS;
		this.xmlConstant = XmlTagConstants.STATUS_INDICATORS;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.STATUS_INDICATORS, creationProfile);
	}
}