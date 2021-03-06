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
package org.knowm.memristor.discovery.gui.mvc.apps.hysteresis;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knowm.memristor.discovery.gui.mvc.apps.AppPrefencesPanel;
import org.knowm.memristor.discovery.gui.mvc.apps.AppPreferences;

public class HysteresisPrefencesPanel extends AppPrefencesPanel {

  private JLabel waveformLabel;
  private JComboBox<HysteresisPreferences.Waveform> waveformComboBox;

  private JLabel offsetLabel;
  private JTextField offsetTextField;

  private JLabel amplitudeLabel;
  private JTextField amplitudeTextField;

  private JLabel frequencyLabel;
  private JTextField frequencyTextField;

  private JLabel seriesResistorLabel;
  private JTextField seriesResistorTextField;

  private JLabel kLabel;
  private JTextField kTextField;

  /**
   * Constructor
   *
   * @param owner
   */
  public HysteresisPrefencesPanel(JFrame owner) {

    super(owner);
  }

  @Override
  public void doCreateAndShowGUI(JPanel preferencesPanel) {

    GridBagConstraints gc = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.insets = new Insets(10, 10, 10, 10);

    gc.gridy = 0;
    gc.gridx = 0;
    this.waveformLabel = new JLabel("Waveform:");
    preferencesPanel.add(waveformLabel, gc);

    gc.gridx = 1;
    this.waveformComboBox = new JComboBox<>();
    this.waveformComboBox.setModel(new DefaultComboBoxModel<HysteresisPreferences.Waveform>(HysteresisPreferences.Waveform.values()));
    HysteresisPreferences.Waveform waveform = HysteresisPreferences.Waveform.valueOf(appPreferences.getString(HysteresisPreferences.WAVEFORM_INIT_STRING_KEY,
        HysteresisPreferences.WAVEFORM_INIT_STRING_DEFAULT_VALUE));
    this.waveformComboBox.setSelectedItem(waveform);
    preferencesPanel.add(waveformComboBox, gc);

    /////////////////////////////////////////////////////////

    gc.gridy++;
    gc.gridx = 0;
    this.offsetLabel = new JLabel("Offset [V]:");
    preferencesPanel.add(offsetLabel, gc);

    gc.gridx = 1;
    this.offsetTextField = new JTextField(12);
    this.offsetTextField.setText(String.valueOf(appPreferences.getFloat(HysteresisPreferences.OFFSET_INIT_FLOAT_KEY, HysteresisPreferences.OFFSET_INIT_FLOAT_DEFAULT_VALUE)));
    preferencesPanel.add(offsetTextField, gc);

    /////////////////////////////////////////////////////////

    gc.gridy++;
    gc.gridx = 0;
    this.amplitudeLabel = new JLabel("Amplitude [V]:");
    preferencesPanel.add(amplitudeLabel, gc);

    gc.gridx = 1;
    this.amplitudeTextField = new JTextField(12);
    this.amplitudeTextField.setText(String.valueOf(appPreferences.getFloat(HysteresisPreferences.AMPLITUDE_INIT_FLOAT_KEY, HysteresisPreferences.AMPLITUDE_INIT_FLOAT_DEFAULT_VALUE)));
    preferencesPanel.add(amplitudeTextField, gc);

    /////////////////////////////////////////////////////////

    gc.gridy++;
    gc.gridx = 0;
    this.frequencyLabel = new JLabel("Frequency [Hz]:");
    preferencesPanel.add(frequencyLabel, gc);

    gc.gridx = 1;
    this.frequencyTextField = new JTextField(12);
    this.frequencyTextField.setText(String.valueOf(appPreferences.getInteger(HysteresisPreferences.FREQUENCY_INIT_KEY, HysteresisPreferences.FREQUENCY_INIT_DEFAULT_VALUE)));
    preferencesPanel.add(frequencyTextField, gc);

    /////////////////////////////////////////////////////////

    gc.gridy++;
    gc.gridx = 0;
    this.seriesResistorLabel = new JLabel("Series Resistor [Ω]:");
    preferencesPanel.add(seriesResistorLabel, gc);

    gc.gridx = 1;
    this.seriesResistorTextField = new JTextField(12);
    this.seriesResistorTextField.setText(String.valueOf(appPreferences.getInteger(HysteresisPreferences.SERIES_R_INIT_KEY, HysteresisPreferences.SERIES_R_INIT_DEFAULT_VALUE)));
    preferencesPanel.add(seriesResistorTextField, gc);

    /////////////////////////////////////////////////////////

    gc.gridy++;
    gc.gridx = 0;
    this.kLabel = new JLabel("K:");
    preferencesPanel.add(kLabel, gc);

    gc.gridx = 1;
    this.kTextField = new JTextField(12);
    this.kTextField.setText(String.valueOf(appPreferences.getDouble(HysteresisPreferences.K_INIT_DOUBLE_KEY, HysteresisPreferences.K_INIT_DOUBLE_DEFAULT_VALUE)));
    preferencesPanel.add(kTextField, gc);

  }

  @Override
  public void doSavePreferences() {

    System.out.println(waveformComboBox.getSelectedItem().toString().trim());
    appPreferences.setString(HysteresisPreferences.WAVEFORM_INIT_STRING_KEY, waveformComboBox.getSelectedItem().toString().trim());
    appPreferences.setFloat(HysteresisPreferences.OFFSET_INIT_FLOAT_KEY, Float.parseFloat(offsetTextField.getText()));
    appPreferences.setFloat(HysteresisPreferences.AMPLITUDE_INIT_FLOAT_KEY, Float.parseFloat(amplitudeTextField.getText()));
    appPreferences.setInteger(HysteresisPreferences.FREQUENCY_INIT_KEY, Integer.parseInt(frequencyTextField.getText()));
    appPreferences.setInteger(HysteresisPreferences.SERIES_R_INIT_KEY, Integer.parseInt(seriesResistorTextField.getText()));
    appPreferences.setDouble(HysteresisPreferences.K_INIT_DOUBLE_KEY, Double.parseDouble(kTextField.getText()));
  }

  @Override
  public AppPreferences initAppPreferences() {

    return new HysteresisPreferences();
  }

  @Override
  public String getAppName() {

    return "Hysteresis";
  }
}
