package org.aero.mtip.metamodel.uaf.Resources;

import org.aero.mtip.constants.CameoDiagramConstants;
import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.block.BlockDefinitionDiagram;

public class ResourcesTaxonomy extends BlockDefinitionDiagram {
  public ResourcesTaxonomy(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.RESOURCES_TAXONOMY_DIAGRAM;
    this.xmlConstant = XmlTagConstants.RESOURCES_TAXONOMY_DIAGRAM;
    this.cameoDiagramConstant = CameoDiagramConstants.RESOURCES_TAXONOMY;
  }
}
