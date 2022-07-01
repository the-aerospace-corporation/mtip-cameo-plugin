package uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.util.SysmlConstants;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class OperationalSignalProperty extends Property {

	public OperationalSignalProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_SIGNAL_PROPERTY;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_SIGNAL_PROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_SIGNAL_PROPERTY_STEREOTYPE);
		
		return sysmlElement;
	}
}
