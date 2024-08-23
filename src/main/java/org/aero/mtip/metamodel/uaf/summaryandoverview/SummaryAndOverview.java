package org.aero.mtip.metamodel.uaf.summaryandoverview;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class SummaryAndOverview extends BlockDefinitionDiagram {
  public SummaryAndOverview(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.SUMMARY_AND_OVERVIEW_DIAGRAM;
    this.xmlConstant = XmlTagConstants.SUMMARY_AND_OVERVIEW_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.SUMMARY_AND_OVERVIEW;
  }
}
