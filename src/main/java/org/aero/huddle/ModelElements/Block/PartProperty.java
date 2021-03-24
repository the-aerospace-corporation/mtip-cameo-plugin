package org.aero.huddle.ModelElements.Block;

import java.util.Map;

import org.aero.huddle.ModelElements.CommonElement;
import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.CameoUtils;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XMLItem;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class PartProperty extends CommonElement {
	public PartProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.PARTPROPERTY;
		this.xmlConstant = XmlTagConstants.PARTPROPERTY;
		this.sysmlElement = f.createPropertyInstance();
	}
	
	public Element createElement(Project project, Element owner, XMLItem xmlElement) {	
		Element partProperty = super.createElement(project, owner, xmlElement);
		
		Profile mdCustomizationProfile = StereotypesHelper.getProfile(project, "MD Customization for SysML"); 
		Stereotype partPropertyStereotype = StereotypesHelper.getStereotype(project, "PartProperty", mdCustomizationProfile);
		StereotypesHelper.addStereotype(partProperty, partPropertyStereotype);
		
		if(xmlElement.hasAttribute(XmlTagConstants.CLASSIFIER_TYPE)) {
			Type classifierType = (Type) project.getElementByID(xmlElement.getAttribute(XmlTagConstants.CLASSIFIER_TYPE));
			((TypedElement)partProperty).setType(classifierType);
		}
		return partProperty;
	}
	
	@Override
	public void createDependentElements(Project project, Map<String, XMLItem> parsedXML, XMLItem modelElement) {
		CameoUtils.logGUI("\t...Creating dependent elements for PartProperty with id: " + modelElement.getEAID());
		
		if(modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
			Element type = ImportXmlSysml.getOrBuildElement(project, parsedXML, classifierID);
			modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, type.getLocalID());
		}		
	}
}