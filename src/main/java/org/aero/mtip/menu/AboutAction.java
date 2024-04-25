/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.menu;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;


/*
 * Action creates a dialog, displaying the plugin description.
 */
@SuppressWarnings("serial")
public class AboutAction extends MDAction {
	public static String VERSION = "Development version MTIP 2021x.b012"
			+ ".";
	public AboutAction(String id, String name) {
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		//Display information about the plugin.
		JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(),
				"Modeling Tool Integration Plugin for Cameo Systems Modeler (MTIP-CSM Plugin)\n\n"
				+ "Version: " + VERSION + "\n\n",
				"About", JOptionPane.INFORMATION_MESSAGE);
	}
}
