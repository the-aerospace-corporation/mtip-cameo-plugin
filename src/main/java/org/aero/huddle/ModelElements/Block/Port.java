package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.impl.ElementsFactory;

public class Port extends CommonElement {
	public Port(String name, String EAID)  {
		super(name, EAID);
	}
	
	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Port Element");
		}
		Element sysmlElement = f.createPortInstance();
		((NamedElement)sysmlElement).setName(name);
		
		if (owner != null) {
			Element newOwner = null;
			String ownerType = owner.getHumanType();
			if(ownerType.equals("Port")) {
				// Check if Type property is set if it is, get the block already typing the port and set as owner of new port
				if(isTyped(owner)) {
					TypedElement ownerTyped = (TypedElement)owner;
					Type type = ownerTyped.getType();
					newOwner = (Element)type;
					sysmlElement.setOwner(newOwner);
				} else {
					newOwner = createNestedPorts(project, owner);
					TypedElement ownerTyped = (TypedElement)owner;
					ownerTyped.setType((Type)newOwner);
					sysmlElement.setOwner(newOwner);
				}
			} else {
				sysmlElement.setOwner(owner);
			}
		} else {
			sysmlElement.setOwner(project.getPrimaryModel());
		}
		
		SessionManager.getInstance().closeSession(project);
		return sysmlElement;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);

		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.PORT));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}
}
