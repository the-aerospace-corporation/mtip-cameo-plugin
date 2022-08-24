package uaf.Resources;

import org.aero.mtip.ModelElements.Sequence.Message;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ResourceMessage extends Message{
	public ResourceMessage(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_MESSAGE;
		this.xmlConstant = XmlTagConstants.RESOURCE_MESSAGE;
		this.sysmlElement = f.createMessageInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier,XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_MESSAGE_STEREOTYPE);
		
		return sysmlElement;
	}
}
