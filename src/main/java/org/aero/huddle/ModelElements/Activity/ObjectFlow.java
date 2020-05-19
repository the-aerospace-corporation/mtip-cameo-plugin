package org.aero.huddle.ModelElements.Activity;

import org.aero.huddle.ModelElements.CommonRelationship;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.ImportLog;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.activities.mdbasicactivities.ActivityEdge;
import com.nomagic.uml2.ext.magicdraw.activities.mdfundamentalactivities.ActivityNode;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;

public class ObjectFlow extends CommonRelationship {

	public ObjectFlow(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, Element client, Element supplier, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Object Flow Relationship");
		}
		
		Element objectFlow = project.getElementsFactory().createObjectFlowInstance();
		try {
			if(supplier == null || client == null) {
				String logMessage = "Invalid client/supplier object flow " + name + " with id " + EAID + ". Supplier or client does not exist or was not created.";
				CameoUtils.logGUI(logMessage);
				ImportLog.log(logMessage);
				objectFlow.dispose();
				return null;
			}
			ActivityEdge activityEdge = (ActivityEdge)objectFlow;
			activityEdge.setSource((ActivityNode) supplier);
			activityEdge.setTarget((ActivityNode) client);
		} catch(ClassCastException cce) {
			String logMessage = "Invalid client/supplier object flow " + name + " with id " + EAID + ". Supplier or client does not exist or was not created.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			objectFlow.dispose();
			return null;
		}
		((NamedElement)objectFlow).setName(name);
		try {
			if(owner instanceof Activity) {
				objectFlow.setOwner(owner);
			} else {
				objectFlow.setOwner(CameoUtils.findNearestActivity(project, owner));
			}
			
			SessionManager.getInstance().closeSession(project);
			return objectFlow;
		} catch(IllegalArgumentException iae) {
			String logMessage = "Invalid parent. Parent invalid for element " + name + " with id " + EAID + ". Element could not be placed in model.";
			CameoUtils.logGUI(logMessage);
			ImportLog.log(logMessage);
			objectFlow.dispose();
		}
		return null;	
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.OBJECTFLOW));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
		
	}

}
