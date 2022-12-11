package uaf.Projects;

import org.aero.mtip.ModelElements.Block.Enumeration;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

import uaf.UAFConstants;

public class ActualMilestoneKind extends Enumeration{
	public ActualMilestoneKind(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.sysmlConstant = UAFConstants.ACTUAL_MILESTONE_KIND;
		this.xmlConstant = XmlTagConstants.ACTUAL_MILESTONE_KIND;
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), UAFConstants.UAF_PROFILE_NAME); 
		this.creationStereotype = StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), UAFConstants.ACTUAL_MILESTONE_KIND, creationProfile);
	}
}