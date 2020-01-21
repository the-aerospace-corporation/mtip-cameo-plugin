package org.aero.huddle.ModelElements.Profile;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Customization extends CommonElement {

	public Customization(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Customization Element");
		}
		
		Profile umlStandardprofile = StereotypesHelper.getProfile(project,  "MagicDraw Profile");
		Stereotype customizationStereotype = StereotypesHelper.getStereotype(project, SysmlConstants.CUSTOMIZATION, umlStandardprofile);
		Element customization = createClassWithStereotype(project, this.name, customizationStereotype, owner);
		
		if(xmlElement != null) {
			String applyToSource = xmlElement.getAttribute("applyToSource");
			String applyToTarget = xmlElement.getAttribute("applyToTarget");
			String allowedRelationships = xmlElement.getAttribute("allowedRelationships");
			String disallowedRelationships = xmlElement.getAttribute("disallowedRelationships");
			
			if(!StringUtils.isBlank(applyToSource)) {
				StereotypesHelper.setStereotypePropertyValue(customization, customizationStereotype, "applyToSource", project.getElementByID(applyToSource));
			}
			if(!StringUtils.isBlank(applyToTarget)) {
				
			}
			if(!StringUtils.isBlank(allowedRelationships)) {
				
			}
			if(!StringUtils.isBlank(disallowedRelationships)) {
				
			}
		}
		
		
		SessionManager.getInstance().closeSession(project);
		return customization;
	}

	@Override
	public void writeToXML(Element element, Project project, Document xmlDoc) {
		// TODO Auto-generated method stub
		
	}

}
