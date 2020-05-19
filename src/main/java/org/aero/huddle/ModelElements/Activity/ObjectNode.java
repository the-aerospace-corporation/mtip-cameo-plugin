package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class ObjectNode extends CommonElement {

	public ObjectNode(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
//		this.sysmlConstant = SysmlConstants.OBJECTNODE;
//		this.xmlConstant = XmlTagConstants.ACTION;
		this.sysmlElement = f.createCallBehaviorActionInstance();
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Object Node Element");
		}
		//Figure out how or if Cameo allows Object Nodes to be created
		Element sysmlElement = f.createCallBehaviorActionInstance();
		((NamedElement)sysmlElement).setName(name);
		if(owner != null) {
			sysmlElement.setOwner(owner);
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

}
