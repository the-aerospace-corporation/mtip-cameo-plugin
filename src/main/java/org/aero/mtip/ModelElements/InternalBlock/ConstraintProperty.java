/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.ModelElements.InternalBlock;

import java.util.HashMap;
import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.SysmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.io.Importer;
import org.aero.mtip.profiles.MDCustomizationForSysML;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.MtipUtils;
import org.aero.mtip.util.XMLItem;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class ConstraintProperty extends CommonElement {

	public ConstraintProperty(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
		this.xmlConstant = XmlTagConstants.CONSTRAINT_PROPERTY;
		this.metamodelConstant = SysmlConstants.CONSTRAINT_PROPERTY;
		this.element = f.createPropertyInstance();
		this.creationStereotype = MDCustomizationForSysML.getConstraintPropertyStereotype();
	}
	
	@Override
	public void createDependentElements(HashMap<String, XMLItem> parsedXML, XMLItem modelElement) {		
		if(!modelElement.hasAttribute(XmlTagConstants.TYPED_BY)) {
			return;
		}
		
		String classifierID = modelElement.getAttribute(XmlTagConstants.TYPED_BY);
		try {
			Element type = Importer.getInstance().buildElement(parsedXML, parsedXML.get(classifierID));
			modelElement.addAttribute(XmlTagConstants.CLASSIFIER_TYPE, MtipUtils.getId(type));
		} catch (NullPointerException npe) {
			Logger.log(String.format("Failed to create/get typed by element for element with id %s", EAID));
		}
	}
}
