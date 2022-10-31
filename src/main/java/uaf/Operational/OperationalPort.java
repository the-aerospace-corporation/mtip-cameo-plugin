package uaf.Operational;

import org.aero.mtip.ModelElements.Block.Port;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class OperationalPort extends Port {

	public OperationalPort(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_PORT;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PORT;
		this.sysmlElement = f.createPortInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_PORT_STEREOTYPE);
		
		return sysmlElement;
	}
}
