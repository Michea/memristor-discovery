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

package org.knowm.memristor.discovery.gui.mvc.header;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;

import org.knowm.memristor.discovery.DWFProxy;

import eu.hansolo.component.SteelCheckBox;

public class HeaderController implements PropertyChangeListener {

  private final HeaderPanel headerPanel;
  private final DWFProxy dwf;

  /**
   * Constructor
   *
   * @param headerPanel
   * @param dwf
   */
  public HeaderController(HeaderPanel headerPanel, DWFProxy dwf) {

    this.headerPanel = headerPanel;
    this.dwf = dwf;
    dwf.addListener(this);
    setUpViewEvents();
  }

  private void setUpViewEvents() {

    for (int i = 0; i < headerPanel.getCheckBoxes().length; i++) {
      JCheckBox checkbox = headerPanel.getCheckBoxes()[i];
      checkbox.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent event) {

          SteelCheckBox cb = (SteelCheckBox) event.getSource();
          dwf.updateDigitalIOState(cb.getId(), cb.isSelected());
        }
      });
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {

    switch (evt.getPropertyName()) {

    case DWFProxy.AD2_STARTUP_CHANGE:

      headerPanel.enableAllDigitalIOCheckBoxes(dwf.isAD2Running());
      break;

    case DWFProxy.DIGITAL_IO_READ:

      headerPanel.updateDigitalIOSwitches(dwf.getDigitalIOStates());
      break;

    default:
      break;
    }
  }

}
