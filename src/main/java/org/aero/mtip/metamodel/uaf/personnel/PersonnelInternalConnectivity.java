package org.aero.mtip.metamodel.uaf.personnel;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.internalblock.InternalBlockDiagram;

public class PersonnelInternalConnectivity extends InternalBlockDiagram {

  public PersonnelInternalConnectivity(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.PERSONNEL_INTERNAL_CONNECTIVITY_DIAGRAM;
    this.xmlConstant = XmlTagConstants.PERSONNEL_INTERNAL_CONNECTIVITY;
    this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_INTERNAL_CONNECTIVITY;
  }
}
