package org.aero.mtip.uaf.actualresources;

import java.util.Arrays;

import org.aero.mtip.ModelElements.CommonDirectedRelationship;
import org.aero.mtip.uaf.UAFConstants;
import org.aero.mtip.uaf.UAFProfile;
import org.aero.mtip.util.XmlTagConstants;

public class OwnsProcess extends CommonDirectedRelationship {

	public OwnsProcess(String name, String EAID) {
		super(name, EAID);
		this.creationType = XmlTagConstants.ELEMENTSFACTORY;
		this.xmlConstant = XmlTagConstants.OWNS_PROCESS;
		this.sysmlConstant = UAFConstants.OWNS_PROCESS;
		this.sysmlElement = f.createAbstractionInstance();
		this.initialStereotypes = Arrays.asList(UAFProfile.OWNS_PROCESS_STEREOTYPE);
	}
}
