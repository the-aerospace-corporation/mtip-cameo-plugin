package uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ObjectFlow;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class OperationalObjectFlow extends ObjectFlow {

	public OperationalObjectFlow(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_OBJECT_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_OBJECT_FLOW;
		this.sysmlElement = f.createObjectFlowInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_OBJECT_FLOW_STEREOTYPE);
		
		return sysmlElement;
	}
}
