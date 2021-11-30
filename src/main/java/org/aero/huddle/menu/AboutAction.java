package org.aero.huddle.menu;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;


/*
 * Action creates a dialog, displaying the plugin description.
 */
@SuppressWarnings("serial")
public class AboutAction extends MDAction
{
	public AboutAction(String id, String name)
	{
		super(id, name, null, null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//Display information about the plugin.
		JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(),
				"Huddle-CSM Plugin\n\n"
				+ "Version: 1.0.0\n\n",
				"About", JOptionPane.INFORMATION_MESSAGE);
	}
}
