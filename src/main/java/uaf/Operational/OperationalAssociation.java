package uaf.Operational;

import org.aero.mtip.ModelElements.Association;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFConstants;
import uaf.UAFProfile;

public class OperationalAssociation extends Association {

	public OperationalAssociation(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.OPERATIONAL_ASSOCIATION;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_ASSOCIATION;
		this.sysmlElement = f.createAssociationInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, client, supplier, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.OPERATIONAL_ASSOCIATION_STEREOTYPE);
		
		return sysmlElement;
	}
}
