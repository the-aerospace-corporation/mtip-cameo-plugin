package org.aero.mtip.metamodel.uml;

import org.aero.mtip.constants.UmlConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class Artifact extends CommonElement {
  public Artifact(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
    this.metamodelConstant = UmlConstants.ARTIFACT;
    this.xmlConstant = XmlTagConstants.ARTIFACT;
    this.element = f.createArtifactInstance();
  }
}
