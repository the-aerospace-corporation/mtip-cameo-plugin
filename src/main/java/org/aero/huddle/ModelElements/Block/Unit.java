package org.aero.huddle.ModelElements.Block;

import java.util.ArrayList;

import org.aero.huddle.XML.Import.ImportXmlSysml;
import org.aero.huddle.util.SysmlConstants;
import org.aero.huddle.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class Unit extends InstanceSpecification {

	public Unit(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.sysmlConstant = SysmlConstants.UNIT;
		this.xmlConstant = XmlTagConstants.UNIT;
		this.sysmlElement = f.createInstanceSpecificationInstance();
		this.creationProfile = StereotypesHelper.getProfile(ImportXmlSysml.getProject(), SysmlConstants.MD_CUSTOMIZATION_PROFILE_NAME);
		this.initialStereotypes = new ArrayList<Stereotype> ();
		this.initialStereotypes.add(StereotypesHelper.getStereotype(ImportXmlSysml.getProject(), SysMLProfile.VALUETYPE_UNIT_PROPERTY, this.creationProfile));
	}
	
	@Override
	protected void setClassifier() {
		this.classifier = (com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element)Finder.byQualifiedName().find(ImportXmlSysml.getProject(), "SysML::Libraries::UnitAndQuantityKind::Unit");
	}
}
