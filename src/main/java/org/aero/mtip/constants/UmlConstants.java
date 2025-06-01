package org.aero.mtip.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UmlConstants {
  public static final String METAMODEL_PREFIX = "uml";

  // Element Constants
  public static final String ARTIFACT = "Artifact";

  public static final String[] UML_ELEMENTS_VALUES = {ARTIFACT};
  public static final String[] UML_RELATIONSHIPS_VALUES = {};
  public static final String[] UML_DIAGRAMS_VALUES = {};

  public static Set<String> UML_ELEMENTS = new HashSet<String>(Arrays.asList(UML_ELEMENTS_VALUES));
  public static Set<String> UML_RELATIONSHIPS =
      new HashSet<String>(Arrays.asList(UML_RELATIONSHIPS_VALUES));
  public static Set<String> UML_DIAGRAMS = new HashSet<String>(Arrays.asList(UML_DIAGRAMS_VALUES));
}
