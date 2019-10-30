package org.aero.huddle.ModelElements.Block;

import java.util.Collection;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;
import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.magicdraw.uml.Finder.ByQualifiedNameFinder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;

public class Unit extends CommonElement {

	public Unit(String name, String EAID) {
		super(name, EAID);
	}

	@Override
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {
		ElementsFactory f = project.getElementsFactory();
		if (!SessionManager.getInstance().isSessionCreated(project)) {
			SessionManager.getInstance().createSession(project, "Create Quantity Kind Element");
		}
		Element sysmlElement = f.createInstanceSpecificationInstance();
		((NamedElement)sysmlElement).setName(name);
		
		//Get QUDV Profile and get DerivedQuantityKind
		Element unit = Finder.byQualifiedName().find(project, "SysML::Libraries::UnitAndQuantityKind");
		TypedElement ownerTyped = (TypedElement)sysmlElement;
		ownerTyped.setType((Type)unit);

		//Get Stereotype Unit from MD Customization for SysML
		Profile sysmlProfile = StereotypesHelper.getProfile(project, "SysML");
		Stereotype unitStereotype = StereotypesHelper.getStereotype(project, SysMLProfile.VALUETYPE_UNIT_PROPERTY, sysmlProfile);
		StereotypesHelper.addStereotype(sysmlElement, unitStereotype);

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
		org.w3c.dom.Element data = createBaseXML(element, xmlDoc);
		
		//org.w3c.dom.Element attributes = getAttributes(data.getChildNodes());
		
		// Create type field for Sysml model element types
		org.w3c.dom.Element type = xmlDoc.createElement("type");
		type.appendChild(xmlDoc.createTextNode(XmlTagConstants.UNIT));
		data.appendChild(type);
		
		org.w3c.dom.Element root = (org.w3c.dom.Element) xmlDoc.getFirstChild();
		root.appendChild(data);
	}
}
