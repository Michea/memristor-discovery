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

package org.knowm.memristor.discovery.gui.mvc.apps.pulse;

import java.beans.PropertyChangeListener;

import org.knowm.memristor.discovery.gui.mvc.apps.AppModel;
import org.knowm.memristor.discovery.gui.mvc.apps.AppPreferences;
import org.knowm.memristor.discovery.utils.driver.Driver;
import org.knowm.memristor.discovery.utils.driver.Square;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PulseModel extends AppModel {

  private final Logger logger = LoggerFactory.getLogger(PulseModel.class);

  /** Waveform */
  private float amplitude;
  private int pulseWidth;
  private final double[] waveformTimeData = new double[PulsePreferences.CAPTURE_BUFFER_SIZE];
  private final double[] waveformAmplitudeData = new double[PulsePreferences.CAPTURE_BUFFER_SIZE];

  /** Shunt */
  private int seriesResistance;

  /**
   * Constructor
   */
  public PulseModel() {

    updateWaveformChartData();
  }

  @Override
  public void loadModelFromPrefs() {

    // load model from prefs
    seriesResistance = appPreferences.getInteger(PulsePreferences.SERIES_R_INIT_KEY, PulsePreferences.SERIES_R_INIT_DEFAULT_VALUE);
    amplitude = appPreferences.getFloat(PulsePreferences.AMPLITUDE_INIT_FLOAT_KEY, PulsePreferences.AMPLITUDE_INIT_FLOAT_DEFAULT_VALUE);
    pulseWidth = appPreferences.getInteger(PulsePreferences.PULSE_WIDTH_INIT_KEY, PulsePreferences.PULSE_WIDTH_INIT_DEFAULT_VALUE);
    swingPropertyChangeSupport.firePropertyChange(AppModel.EVENT_PREFERENCES_UPDATE, true, false);
  }

  /**
   * Here is where the Controller registers itself as a listener to model changes.
   * 
   * @param listener
   */
  public void addListener(PropertyChangeListener listener) {

    swingPropertyChangeSupport.addPropertyChangeListener(listener);
  }

  /**
   * Given the state of the model, update the waveform x and y axis data arrays.
   */
  void updateWaveformChartData() {

    int freq = getCalculatedFrequency();

    Driver driver = new Square("Square", amplitude / 2, 0, amplitude / 2, getCalculatedFrequency());

    double stopTime = 1 / (double) getCalculatedFrequency() * PulsePreferences.CAPTURE_PERIOD_COUNT;
    double timeStep = 1 / (double) getCalculatedFrequency() * PulsePreferences.CAPTURE_PERIOD_COUNT / PulsePreferences.CAPTURE_BUFFER_SIZE;

    int counter = 0;
    for (double i = 0.0; i < stopTime; i = i + timeStep) {
      if (counter >= PulsePreferences.CAPTURE_BUFFER_SIZE) {
        break;
      }
      waveformTimeData[counter] = i * 1000000;
      waveformAmplitudeData[counter++] = driver.getSignal(i);
    }
  }

  /////////////////////////////////////////////////////////////
  // GETTERS AND SETTERS //////////////////////////////////////
  /////////////////////////////////////////////////////////////

  public float getAmplitude() {

    return amplitude;
  }

  public void setAmplitude(float amplitude) {

    this.amplitude = amplitude;
    swingPropertyChangeSupport.firePropertyChange(AppModel.EVENT_WAVEFORM_UPDATE, true, false);
  }

  public int getPulseWidth() {

    return pulseWidth;
  }

  public int getCalculatedFrequency() {

    return (int) (1.0 / (2.0 * (double) pulseWidth) * 1000000);
  }

  public void setPulseWidth(int pulseWidth) {

    this.pulseWidth = pulseWidth;
    swingPropertyChangeSupport.firePropertyChange(AppModel.EVENT_WAVEFORM_UPDATE, true, false);
  }

  public double[] getWaveformTimeData() {

    return waveformTimeData;
  }

  public double[] getWaveformAmplitudeData() {

    return waveformAmplitudeData;
  }

  public int getShunt() {

    return seriesResistance;
  }

  public void setShunt(int shunt) {

    this.seriesResistance = shunt;
  }

  @Override
  public AppPreferences initAppPreferences() {

    return new PulsePreferences();
  }

}
