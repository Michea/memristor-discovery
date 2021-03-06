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

package org.knowm.memristor.discovery.gui.mvc.apps.pulse2;

import org.knowm.memristor.discovery.gui.mvc.apps.AppPreferences;

/**
 * Stores various operational preferences
 *
 * @author timmolter
 */
public class PulsePreferences2 extends AppPreferences {

  private static final String PREFIX = "PULSE_2_";

  public static final String SERIES_R_INIT_KEY = PREFIX + "SERIES_R_INIT_KEY";
  public static final int SERIES_R_INIT_DEFAULT_VALUE = 24_900;

  public static final String AMPLITUDE_INIT_FLOAT_KEY = PREFIX + "AMPLITUDE_INIT_FLOAT_KEY";
  public static final float AMPLITUDE_INIT_FLOAT_DEFAULT_VALUE = 0.1f;

  public static final String PULSE_WIDTH_INIT_KEY = PREFIX + "PULSE_WIDTH_INIT_KEY";
  public static final int PULSE_WIDTH_INIT_DEFAULT_VALUE = 5000;

  ///////////////////////////////////////////////////////////////////////////////////////

  public static final CurrentUnits CURRENT_UNIT = CurrentUnits.MicroAmps;
  public static final ResistanceUnits RESISTANCE_UNIT = ResistanceUnits.KiloOhms;

  // public static final int CAPTURE_BUFFER_SIZE = DWF.AD2_MAX_BUFFER_SIZE;
  public static final int CAPTURE_BUFFER_SIZE = 2000;

  /**
   * Constructor
   */
  public PulsePreferences2() {

    super(PulsePreferences2.class);
  }

}
