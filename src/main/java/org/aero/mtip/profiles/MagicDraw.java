package org.aero.mtip.profiles;

import java.io.File;
import java.util.List;
import javax.annotation.CheckForNull;
import org.aero.mtip.util.Logger;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class MagicDraw {
  private static MagicDraw instance;

  private Project project;
  private Profile profile;

  public static final String NAME = "MagicDraw Profile";

  static final String AUXILIARY_RESOURCE = "auxiliaryResource";
  static final String CUSTOM_IMAGE_HOLDER = "CustomImageHolder";
  static final String TERM_NAME = "Term";
  
  // CustomImageHolder properties
  static final String LOCATION = "Location";

  private MagicDraw() {
    project = Application.getInstance().getProject();
    profile = StereotypesHelper.getProfile(project, NAME);
  }
  
  public static void clearProfile() {
    instance = null;
  }

  public static MagicDraw getInstance() {
    if (instance == null) {
      instance = new MagicDraw();
    }

    return instance;
  }

  boolean hasStereotype(Element element, String stereotypeName) {
    if (profile == null) {
      Logger.log(String.format("Profile not initialized when looking for stereotype name %s",
          stereotypeName));
      return false;
    }

    if (element == null) {
      return false;
    }

    Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName, profile);

    if (stereotype == null) {
      Logger.log(String.format("Stereotype %s not found in profile %s", stereotypeName,
          profile.getHumanName()));
      return false;
    }

    if (!StereotypesHelper.hasStereotype(element, stereotype)) {
      return false;
    }

    return true;
  }
  
  @CheckForNull
  public static String getCustomImageName(Element element) {
    List<?> locations = StereotypesHelper.getStereotypePropertyValue(element, getCustomImageHolderStereotypeLocationProperty());
    
    if (locations.isEmpty() || !(locations.get(0) instanceof String)) {
      return null;
    }
    
    String filename = new File((String)locations.get(0)).getName();
    return filename.substring(0, filename.lastIndexOf('.'));
  }

  public static Stereotype getAuxiliaryResourceStereotype() {
    return getInstance().getStereotype(AUXILIARY_RESOURCE);
  }
  
  public static Stereotype getCustomImageHolderStereeotype() {
    return getInstance().getStereotype(CUSTOM_IMAGE_HOLDER);
  }
  
  public static Property getCustomImageHolderStereotypeLocationProperty() {
    return StereotypesHelper.getPropertyByName(getCustomImageHolderStereeotype(), LOCATION);
  }

  public static Stereotype getTermStereotype() {
    return getInstance().getStereotype(TERM_NAME);
  }

  public Stereotype getStereotype(String stereotypeName) {
    return StereotypesHelper.getStereotype(project, stereotypeName, profile);
  }

  public static boolean hasCustomImageHolderStereotype(Element element) {
    return getInstance().hasStereotype(element, CUSTOM_IMAGE_HOLDER);
  }

  public boolean isCurrentProject(Project activeProject) {
    if (project != activeProject) {
      return false;
    }

    return true;
  }
}
