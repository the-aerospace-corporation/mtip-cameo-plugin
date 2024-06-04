package org.aero.mtip.uaf.Resources;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.uaf.UAFElement;

public class ResourceArtifact extends CommonElement implements UAFElement {
	
	public ResourceArtifact(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.CLASS_WITH_STEREOTYPE;
		this.metamodelConstant = UAFConstants.RESOURCE_ARTIFACT;
		this.xmlConstant = XmlTagConstants.RESOURCE_ARTIFACT;
	}
}
