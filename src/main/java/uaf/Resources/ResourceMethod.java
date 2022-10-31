package uaf.Resources;

import org.aero.mtip.ModelElements.Block.Operation;
import uaf.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ResourceMethod extends Operation {

	public ResourceMethod(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_METHOD;
		this.xmlConstant = XmlTagConstants.RESOURCE_METHOD;
		this.sysmlElement = f.createOperationInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_METHOD_STEREOTYPE);
		
		return sysmlElement;
	}
}
