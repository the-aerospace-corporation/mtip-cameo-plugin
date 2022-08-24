package uaf.Resources;

import org.aero.mtip.ModelElements.Block.Operation;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.Parameter;
import uaf.UAFProfile;

public class ResourceParameter extends Parameter {

	public ResourceParameter(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_PARAMETER;
		this.xmlConstant = XmlTagConstants.RESOURCE_PARAMETER;
		this.sysmlElement = f.createParameterInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_PARAMETER_STEREOTYPE);
		
		return sysmlElement;
	}
}