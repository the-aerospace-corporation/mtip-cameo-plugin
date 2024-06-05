package org.aero.mtip.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
  private static Config instance;

  private static final String ID_MODE = "id.mode";
  private static final String ID_MODE_LOCAL = "local";
  private static final String ID_MODE_CLOUD = "cloud";
  private static final String[] ID_MODE_OPTIONS = {ID_MODE_LOCAL, ID_MODE_CLOUD};

  private static final String USER_DIR_PROPERTY_NAME = "user.dir";
  private static final String USER_DIR = System.getProperty(USER_DIR_PROPERTY_NAME);

  private static final Path MTIP_CONFIG_PATH =
      Paths.get(USER_DIR, "plugins/mtip", "mtip-config.properties");

  private Properties properties;

  public static Config getInstance() {
    if (instance == null) {
      instance = loadConfig();
    }

    return instance;
  }
  
  public static void save() {
    getInstance().saveConfig();
  }

  public static boolean isLocalId() {
    if (!getInstance().getIdMode().contentEquals(ID_MODE_LOCAL)) {
      return false;
    }

    return true;
  }

  private static Config loadConfig() {
    if (!Files.exists(MTIP_CONFIG_PATH)) {
      return createDefaultConfig();
    }

    try {
      FileInputStream fis = new FileInputStream(MTIP_CONFIG_PATH.toFile());

      Properties properties = new Properties();
      properties.load(fis);

      fis.close();
      
      return new Config(properties);
    } catch (IOException ioe) {
      CameoUtils.logExceptionToGui(ioe);
    }
    
    return createDefaultConfig();
  }

  private void saveConfig() {
    try {
      if (!Files.exists(MTIP_CONFIG_PATH)) {
        MTIP_CONFIG_PATH.toFile().createNewFile();
      }
      
      FileOutputStream fos = new FileOutputStream(MTIP_CONFIG_PATH.toFile());
      getInstance().getProperties().store(fos, null);

      fos.close();
    } catch (IOException e) {
      CameoUtils.logGui(
          String.format("Error saving updated config values to %s.", MTIP_CONFIG_PATH.toString()));
    }
  }

  public static String[] getIdModeOptions() {
    return ID_MODE_OPTIONS;
  }

  private static Config createDefaultConfig() {
    return new Config();
  }

  private Config() {
    properties = new Properties();
    setIdMode(ID_MODE_CLOUD);
  }

  private Config(Properties properties) {
    this.properties = properties;
  }

  private Properties getProperties() {
    return properties;
  }

  public String getIdMode() {
    return properties.getProperty(ID_MODE);
  }

  public void setIdMode(String idMode) {
    properties.setProperty(ID_MODE, idMode);
  }
}
