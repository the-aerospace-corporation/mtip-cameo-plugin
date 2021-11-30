package org.aero.huddle;

import org.aero.huddle.menu.BrowserConfigurator;
import org.aero.huddle.menu.DiagramConfigurator;
import org.aero.huddle.menu.MainMenuConfigurator;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.uml.DiagramTypeConstants;

public class HuddleCSMPlugin extends com.nomagic.magicdraw.plugins.Plugin
{
	public void init()
	{
		//Initialize the menu items for the Huddle-CSM plugin.
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
		manager.addMainMenuConfigurator(new MainMenuConfigurator());
		manager.addContainmentBrowserContextConfigurator(new BrowserConfigurator());
		manager.addDiagramContextConfigurator(DiagramTypeConstants.UML_ANY_DIAGRAM, new DiagramConfigurator());
	}
	
	public boolean close()
	{
		return true;
	}
	
	public boolean isSupported()
	{
		return true;
	}
}
