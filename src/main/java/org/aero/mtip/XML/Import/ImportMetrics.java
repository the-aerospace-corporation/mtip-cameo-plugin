package org.aero.mtip.XML.Import;

import java.util.HashMap;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.util.ImportLog;

public class ImportMetrics {
	private HashMap<String, Integer> importedElementsCount = new HashMap<String, Integer> ();
	
	public ImportMetrics() {
		
	}
	
	public boolean countElement(CommonElement ce) {
		if (ce.getElementType() == null) {
			ImportLog.log(String.format("Common element with id %s has no element type.", ce.getElementID()));
			return false;
		}
		
		int count = importedElementsCount.getOrDefault(ce.getElementType(), 0);
		importedElementsCount.put(ce.getElementType(), count + 1);
		return true;
	}
	
	public HashMap<String, Integer> getElementCounts() {
		return this.importedElementsCount;
	}
}
