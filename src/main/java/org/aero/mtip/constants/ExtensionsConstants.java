package org.aero.mtip.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtensionsConstants {
  public static final String TERM = "Term";
  
  public static final String[] EXTENSION_ELEMENTS_VALUES = {TERM};
  public static final String[] EXTENSION_RELATIONSHIPS_VALUES = {};
  public static final String[] EXTENSION_DIAGRAMS_VALUES = {};

  public static Set<String> UML_ELEMENTS = new HashSet<String>(Arrays.asList(EXTENSION_ELEMENTS_VALUES));
  public static Set<String> UML_RELATIONSHIPS =
      new HashSet<String>(Arrays.asList(EXTENSION_RELATIONSHIPS_VALUES));
  public static Set<String> UML_DIAGRAMS = new HashSet<String>(Arrays.asList(EXTENSION_DIAGRAMS_VALUES));
}