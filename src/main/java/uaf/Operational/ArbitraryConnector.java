package uaf.Operational;

import org.aero.mtip.ModelElements.Dependency;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

import uaf.UAFProfile;

public class ArbitraryConnector extends Dependency {

	public ArbitraryConnector(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.ARBITRARY_CONNECTOR;
		this.xmlConstant = XmlTagConstants.ARBITRARY_CONNECTOR;
		this.sysmlElement = f.createDependencyInstance();
	}
	
	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.ARBITRARY_CONNECTOR_STEREOTYPE);
		
		return sysmlElement;
	}
}
