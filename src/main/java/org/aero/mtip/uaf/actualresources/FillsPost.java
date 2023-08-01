package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XmlTagConstants;

public class FillsPost extends CommonDirectedRelationship {

	public FillsPost(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.FILLS_POST;
		this.sysmlConstant = UAFConstants.FILLS_POST;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.FILLS_POST_STEREOTYPE);
	}
}
