package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.core.CommonElement;

public class GeoPoliticalExtentType extends CommonElement {
  
  public GeoPoliticalExtentType(String name, String EAID) {
    super(name, EAID);
    this.creationType = XmlTagConstants.ELEMENTS_FACTORY;
    this.metamodelConstant = UAFConstants.GEO_POLITICAL_EXTENT_TYPE;
    this.xmlConstant = XmlTagConstants.GEO_POLITICAL_EXTENT_TYPE;
    this.element = f.createDataTypeInstance();

  }
}
