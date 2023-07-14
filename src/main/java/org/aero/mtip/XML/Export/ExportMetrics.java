package org.aero.mtip.XML.Export;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;

public class ExportMetrics {
	private HashMap<String, Integer> importedElementsCount = new HashMap<String, Integer> ();
	
	public ExportMetrics() {
		
	}
	
	public void countElement(CommonElement ce) {
		int count = importedElementsCount.getOrDefault(ce.getElementType(), 0);
		importedElementsCount.put(ce.getElementType(), count + 1);
	}
	
	public HashMap<String, Integer> getElementCounts() {
		return this.importedElementsCount;
	}
}
