
/* The Aerospace Corporation MTIP_Cameo
Copyright 2022 The Aerospace Corporation

This product includes software developed at
The Aerospace Corporation (http://www.aerospace.org/). */

package org.aero.mtip.menu;

import org.aero.mtip.menu.actions.ExportDiagramAction;
import org.aero.mtip.util.GetMessageViewInfoAction;
import org.aero.mtip.util.InspectDiagramElementAction;
import org.aero.mtip.util.InspectDiagramElementNestedAction;

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
		ActionsCategory mtipCategory = new MDActionsCategory("MTIPDiagram", "MTIP");
		mtipCategory.setNested(true);
		manager.addCategory(mtipCategory);
		
		//Add actions to MTIP category here
		ActionsCategory category = new ActionsCategory("","");
		category.addAction(new InspectDiagramElementAction(null, "Inspect Diagram Elements", diagramPresentationElement, selectedElements, requestor));
		category.addAction(new InspectDiagramElementNestedAction(null, "Inspect Nested Diagram Elements", diagramPresentationElement, selectedElements, requestor));
		category.addAction(new ExportDiagramAction(null, "Export Diagram", diagramPresentationElement));
		mtipCategory.addAction(category);
		
		if (diagramPresentationElement.getDiagramType().getType().contentEquals("Sequence Diagram")
				|| diagramPresentationElement.getDiagramType().getType().contentEquals("SysML Sequence Diagram")) {
		
				category.addAction(new GetMessageViewInfoAction(null, "Get Message View Info", diagramPresentationElement, selectedElements, requestor));
		}
		
		mtipCategory.addAction(category);
	}
}
