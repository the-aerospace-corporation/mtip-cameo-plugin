package uaf.Strategic;

import org.aero.mtip.ModelElements.Abstraction;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class MapsToCapability extends Abstraction{
	public MapsToCapability(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.MAPS_TO_CAPABILITY;
		this.sysmlConstant = UAFConstants.MAPS_TO_CAPABILITY;
		this.sysmlElement = f.createAbstractionInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.MAPS_TO_CAPABILITY_STEREOTYPE);
		
		return sysmlElement;
	}

}
