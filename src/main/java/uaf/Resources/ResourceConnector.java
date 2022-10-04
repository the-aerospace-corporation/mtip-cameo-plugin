package uaf.Resources;

import org.aero.mtip.ModelElements.InternalBlock.Connector;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ResourceConnector extends Connector {

	public ResourceConnector(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.RESOURCE_CONNECTOR;
		this.xmlConstant = XmlTagConstants.RESOURCE_CONNECTOR;
		this.sysmlElement = f.createConnectorInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner,client, supplier, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.RESOURCE_CONNECTOR_STEREOTYPE);
		
		return sysmlElement;
	}
}
