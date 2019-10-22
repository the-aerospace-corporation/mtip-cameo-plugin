package org.aero.huddle.ModelElements.Block;

import org.aero.huddle.util.XMLItem;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class ProxyPort extends Port {

	public ProxyPort(String name, String EAID) {
		super(name, EAID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		Element port = super.createElement(project, owner, xmlElement);
		
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML"); 
		Stereotype fullPortStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.PROXYPORT_STEREOTYPE, sysmlProfile);
		
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Full Port Relationship");
		}
		StereotypesHelper.addStereotype(port, fullPortStereotype);
		SessionManager.getInstance().closeSession(project);
		return port;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

}
