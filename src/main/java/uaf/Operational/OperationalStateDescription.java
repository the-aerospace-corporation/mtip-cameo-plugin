package uaf.Operational;

import org.aero.mtip.ModelElements.StateMachine.StateMachine;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class OperationalStateDescription extends StateMachine {

	public OperationalStateDescription(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_STATE_DESCRIPTION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_STATE_DESCRIPTION;
		this.sysmlElement = f.createStateMachineInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_STATE_DESCRIPTION_STEREOTYPE);
		
		return sysmlElement;
	}
}
