package org.aero.mtip.uaf.Standards;

import org.aero.mtip.ModelElements.Sequence.Property;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XMLItem;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ProtocolLayer extends Property{
	public ProtocolLayer(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = UAFConstants.PROTOCOL_LAYER;
		this.xmlConstant = XmlTagConstants.PROTOCOL_LAYER;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		super.createElement(project, owner, xmlElement);
		StereotypesHelper.addStereotype(sysmlElement, UAFProfile.PROTOCOL_LAYER_STEREOTYPE);
		
		return sysmlElement;
	}
}
