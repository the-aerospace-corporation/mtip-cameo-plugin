package uaf.Strategic;

import org.aero.mtip.ModelElements.Block.InstanceSpecification;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class Achiever extends InstanceSpecification {

	public Achiever(String name, String EAID) {
		super(name, EAID);
		this.sysmlConstant = UAFConstants.ACHIEVER;
		this.xmlConstant = XmlTagConstants.ACHIEVER;
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ACHIEVER_STEREOTYPE);
		
		return sysmlElement;
	}

}
