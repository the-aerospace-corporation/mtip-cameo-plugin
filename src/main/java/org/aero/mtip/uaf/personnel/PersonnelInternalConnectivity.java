package org.aero.mtip.uaf.personnel;

import org.aero.mtip.ModelElements.InternalBlock.InternalBlockDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class PersonnelInternalConnectivity extends InternalBlockDiagram {

  public PersonnelInternalConnectivity(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.PERSONNEL_INTERNAL_CONNECTIVITY_DIAGRAM;
    this.xmlConstant = XmlTagConstants.PERSONNEL_INTERNAL_CONNECTIVITY;
    this.cameoDiagramConstant = CameoDiagramConstants.PERSONNEL_INTERNAL_CONNECTIVITY;
  }
}
