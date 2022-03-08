/* The Aerospace Corporation Huddle_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.huddle.menu;

import org.aero.huddle.XML.Export.ExportDiagramAction;
import org.aero.huddle.util.InspectDiagramElementAction;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.DiagramContextAMConfigurator;
import com.nomagic.magicdraw.actions.MDActionsCategory;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;

public class DiagramConfigurator implements DiagramContextAMConfigurator
{
	public int getPriority() {
		return AMConfigurator.MEDIUM_PRIORITY;
	}

	@Override
	public void configure(ActionsManager manager, DiagramPresentationElement diagramPresentationElement, PresentationElement[] selectedElements, PresentationElement requestor) {
		ActionsCategory huddleCategory = new MDActionsCategory("HuddleDiagram", "Huddle");
		huddleCategory.setNested(true);
		manager.addCategory(huddleCategory);
		
		//Add actions to Huddle category here
		ActionsCategory category = new ActionsCategory("","");
		category.addAction(new InspectDiagramElementAction(null, "Inspect Diagram Element", diagramPresentationElement, selectedElements, requestor));
		category.addAction(new ExportDiagramAction(null, "Export Diagram", diagramPresentationElement));
		huddleCategory.addAction(category);
	}
}
