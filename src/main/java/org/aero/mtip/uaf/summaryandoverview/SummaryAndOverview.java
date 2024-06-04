package org.aero.mtip.uaf.summaryandoverview;

import org.aero.mtip.ModelElements.Block.BlockDefinitionDiagram;
import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;

public class SummaryAndOverview extends BlockDefinitionDiagram {
  public SummaryAndOverview(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.SUMMARY_AND_OVERVIEW_DIAGRAM;
    this.xmlConstant = XmlTagConstants.SUMMARY_AND_OVERVIEW_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.SUMMARY_AND_OVERVIEW;
  }
}
