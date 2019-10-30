package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLHelper;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.VisibilityKindEnum;
import com.nomagic.uml2.ext.magicdraw.compositestructures.mdports.Port;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class FlowPort extends CommonElement {

	public FlowPort(String name, String EAID) {
		super(name, EAID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Class Element");
		}
		Port port = (Port) f.createPortInstance(); 
		port.setOwner(owner);                                               
		port.setVisibility(VisibilityKindEnum.PRIVATE);                         
   
		Stereotype flowPortStereotype = SysMLProfile.getInstance(port).getFlowPort();   
		StereotypesHelper.addStereotype(port, flowPortStereotype);   
		
		//Flow Direction enumeration missing from SysMLUtilities class
//		for (SysMLUtilities.FlowDirection c : SysMLUtilities.FlowDirection.values())
//		    System.out.println(c);
//		SysMLUtilities.setDirectionForFlowPort(port,  SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		
		SysMLHelper.setDirectionFlowPort(port, SysMLProfile.FLOWDIRECTION_IN_LITERAL);
		SessionManager.getInstance().closeSession(project);
		return port;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.FLOWPORT));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
