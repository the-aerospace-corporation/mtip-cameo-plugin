package uaf.Resources;

import org.aero.mtip.ModelElements.Dependency;
import uaf.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class VersionSuccession extends Dependency {

	public VersionSuccession(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.VERSION_SUCCESSION;
		this.xmlConstant = XmlTagConstants.VERSION_SUCCESSION;
		this.sysmlElement = f.createDependencyInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.VERSION_SUCCESSION_STEREOTYPE);
		
		return sysmlElement;
	}
}
