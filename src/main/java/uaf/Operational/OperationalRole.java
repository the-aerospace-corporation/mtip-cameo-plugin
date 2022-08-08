package uaf.Operational;

import org.aero.mtip.ModelElements.Sequence.Property;
<<<<<<< HEAD
=======
import org.aero.mtip.util.SysmlConstants;
>>>>>>> 2d7e9fc (Added support for exporting capability views from UAF/DoDAF models.)
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class OperationalRole extends Property {

	public OperationalRole(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_ROLE;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ROLE;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_ROLE_STEREOTYPE);
		
		return sysmlElement;
	}
}
