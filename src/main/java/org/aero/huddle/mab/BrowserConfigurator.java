package org.aero.huddle.mab;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.BrowserContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.ui.browser.Tree;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class BrowserConfigurator implements BrowserContextAMConfigurator {

	public int getPriority() {
		return AMConfigurator.HIGH_PRIORITY;
	}

	public void configure(ActionsManager manager, Tree tree) {
		ActionsCategory indepthCategory = manager.getCategory("HUDDLE");
		if(indepthCategory == null) {
			indepthCategory = new MDActionsCategory("HUDDLE", "HUDDLE");
			indepthCategory.setNested(true);
			manager.addCategory(indepthCategory);
		}
		ActionsCategory transformCategory = new ActionsCategory(null, "Import");
		indepthCategory.addAction(transformCategory);
		
		if(tree.getSelectedNode() != null) {
			Object selectedObj = tree.getSelectedNode().getUserObject();
			Element element = (Element)selectedObj;
	        transformCategory.addAction(new ImportAction(null, "MAB Import", element));
		}		
	}
}
