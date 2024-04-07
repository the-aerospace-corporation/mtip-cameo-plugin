package org.aero.mtip.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.PresentationElement;
import com.nomagic.magicdraw.uml.symbols.shapes.TextView;

public class DiagramUtils {
	public static List<String> filterViews = Arrays.asList(
		"ConstraintsView",
		"TaggedValuesView",
		"StereotypeIconView",
		"TextAreaView"
	);
	
	public static List<PresentationElement> findNestedPresentationElements(DiagramPresentationElement dpe) {
		List<PresentationElement> allPresentationElements = new ArrayList<PresentationElement>();
		allPresentationElements.addAll(dpe.getPresentationElements());
		
		for(int i = 0; i < dpe.getPresentationElements().size(); i++) {
			PresentationElement presentationElement = dpe.getPresentationElements().get(i);
			allPresentationElements.addAll(findNestedPresentationElements(presentationElement));
		}
		
		return allPresentationElements;
	}
	
	public static List<PresentationElement> findNestedPresentationElements(PresentationElement pe) {
		List<PresentationElement> allPresentationElements = new ArrayList<PresentationElement>();
		allPresentationElements.addAll(pe.getPresentationElements());
		
		for(int i = 0; i < pe.getPresentationElements().size(); i++) {
			PresentationElement presentationElement = pe.getPresentationElements().get(i);
			allPresentationElements.addAll(findNestedPresentationElements(presentationElement));
		}
		
		return allPresentationElements;
	}
}
