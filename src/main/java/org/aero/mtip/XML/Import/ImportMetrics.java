package org.aero.mtip.XML.Import;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;

public class ImportMetrics {
	private HashMap<String, Integer> importedElementsCount = new HashMap<String, Integer> ();
	
	public ImportMetrics() {
		
	}
	
	public void countElement(CommonElement ce) {
		int count = importedElementsCount.getOrDefault(ce.getElementType(), 0);
		importedElementsCount.put(ce.getElementType(), count + 1);
	}
	
	public HashMap<String, Integer> getElementCounts() {
		return this.importedElementsCount;
	}
}
