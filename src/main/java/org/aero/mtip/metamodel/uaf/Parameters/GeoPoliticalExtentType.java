package org.aero.mtip.metamodel.uaf.Parameters;

import org.aero.mtip.constants.UAFConstants;
import org.aero.mtip.constants.XmlTagConstants;
import org.aero.mtip.metamodel.sysml.profile.DataType;

public class GeoPoliticalExtentType extends DataType {
  
  public GeoPoliticalExtentType(String name, String EAID) {
    super(name, EAID);
    this.metamodelConstant = UAFConstants.GEO_POLITICAL_EXTENT_TYPE;
    this.xmlConstant = XmlTagConstants.GEO_POLITICAL_EXTENT_TYPE;
  }
}
