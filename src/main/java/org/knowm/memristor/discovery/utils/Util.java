/**
 * Memristor-Discovery is distributed under the GNU General Public License version 3
 * and is also available under alternative licenses negotiated directly
 * with Knowm, Inc.
 *
 * Copyright (c) 2016 Knowm Inc. www.knowm.org
 *
 * This package also includes various components that are not part of
 * Memristor-Discovery itself:
 *
 * * `Multibit`: Copyright 2011 multibit.org, MIT License
 * * `SteelCheckBox`: Copyright 2012 Gerrit, BSD license
 *
 * Knowm, Inc. holds copyright
 * and/or sufficient licenses to all components of the Memristor-Discovery
 * package, and therefore can grant, at its sole discretion, the ability
 * for companies, individuals, or organizations to create proprietary or
 * open source (even if not GPL) modules which may be dynamically linked at
 * runtime with the portions of Memristor-Discovery which fall under our
 * copyright/license umbrella, or are distributed under more flexible
 * licenses than GPL.
 *
 * The 'Knowm' name and logos are trademarks owned by Knowm, Inc.
 *
 * If you have any questions regarding our licensing policy, please
 * contact us at `contact@knowm.org`.
 */

package org.knowm.memristor.discovery.utils;

import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

  private static final Logger logger = LoggerFactory.getLogger(Util.class);

  public static final String VERSION_PROPERTIES_FILENAME = "/version.properties";
  public static final String VERSION_PROPERTY_KEY_NAME = "version";

  /**
   * Get the version number specified in the version.properties file.
   * 
   * @return
   */
  public static String getVersionNumber() {

    String version = "";
    Properties versionProperties = new Properties();
    try {
      java.net.URL versionPropertiesURL = Util.class.getResource(VERSION_PROPERTIES_FILENAME);
      if (versionPropertiesURL != null) {
        versionProperties.load(versionPropertiesURL.openStream());
      }
    } catch (IOException ioe) {
      logger.error(ioe.getMessage(), ioe);
    }

    if (versionProperties != null) {
      version = versionProperties.getProperty(VERSION_PROPERTY_KEY_NAME);
      if (version == null) {
        version = "";
      }
    }
    return version;
  }

  /**
   * Returns an ImageIcon, or null if the path was invalid.
   */
  public static ImageIcon createImageIcon(String path) {

    if (path == null) {
      return null;
    }

    java.net.URL imgURL = Util.class.getClassLoader().getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    }
    else {
      logger.error("Could not find file: " + path);
      return null;
    }
  }
}
