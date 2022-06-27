package uaf.Strategic;

import org.aero.mtip.ModelElements.CommonElement;
import org.aero.mtip.XML.Import.ImportXmlSysml;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

import com.nomagic.magicdraw.sysml.util.SysMLProfile;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;

import uaf.UAFElement;

public class Desirer extends CommonElement implements UAFElement {
	
	public Desirer(String name, String EAID) {
		super(name, EAID);
	}
}
