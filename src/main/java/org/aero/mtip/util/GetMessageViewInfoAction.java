package org.aero.mtip.util;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.paths.SeqMessageView;
import com.nomagic.magicdraw.uml.symbols.shapes.SequenceLifelineView;

public class GetMessageViewInfoAction extends MDAction {
	private static final long serialVersionUID = 54774384368710046L;

	DiagramPresentationElement diagramPresentationElement;
	PresentationElement[] selectedPresentationElements;
	PresentationElement requestorPresentationElement;
	
	public GetMessageViewInfoAction(String id, String name, DiagramPresentationElement diagramPresentationElement, PresentationElement[] selectedPresentationElements, PresentationElement requestorPresentationElement)	{
		super(id, name, null, null);
		this.diagramPresentationElement = diagramPresentationElement;
		this.selectedPresentationElements = selectedPresentationElements;
		this.requestorPresentationElement = requestorPresentationElement;
	}
	
	public void actionPerformed(ActionEvent e) {
		List<PresentationElement> messageViews = 
					DiagramUtils.findNestedPresentationElements(diagramPresentationElement)
						.stream()
						.filter(x -> x instanceof SeqMessageView)
						.collect(Collectors.toList());
		
		CameoUtils.logGui(String.format("%s SeqBaseMessageViews found on sequence diagram", messageViews.size()));
		
		for(int i = 0; i < messageViews.size(); i++) {
			SeqMessageView smv = (SeqMessageView)messageViews.get(i);
			SequenceLifelineView clv = smv.getClientSequenceLifelineView();
			SequenceLifelineView slv = smv.getSupplierSequenceLifelineView();
			PresentationElement spe = smv.getSupplier();
			PresentationElement cpe = smv.getClient();
			
			CameoUtils.logGui(String.format("Client sequence lifeline view element %s id %s", clv.getElement().getHumanType(), MtipUtils.getId(clv.getElement())));
			CameoUtils.logGui(String.format("Supplier sequence lifeline view element %s id %s", slv.getElement().getHumanType(), MtipUtils.getId(slv.getElement())));
			CameoUtils.logGui(String.format("Client presentation element %s id %s", cpe.getElement().getHumanType(), MtipUtils.getId(cpe.getElement())));
			CameoUtils.logGui(String.format("Client sequence lifeline view element %s id %s", spe.getElement().getHumanType(), MtipUtils.getId(spe.getElement())));
		}
	}
}
