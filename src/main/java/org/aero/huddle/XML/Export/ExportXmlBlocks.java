package org.aero.huddle.XML.Export;

import java.util.Collection;

import org.w3c.dom.Document;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Profile;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

import org.aero.huddle.XML.Import.ImportXmlPuddle;
import org.aero.huddle.util.LinkingHelper;
import org.aero.huddle.util.XmlTagConstants;

// Export the model represented in the blocks only format into XML.
public class ExportXmlBlocks {
	
	private static final String profileName = "Huddle Stereotypes";

	public static boolean buildXML(Document doc, Element pkg) throws NullPointerException
	{
		//First build the root
		org.w3c.dom.Element rootElement = ExportXmlPuddle.buildXMLRoot(doc, pkg);

		//From the root, move down the children of the root element pkg
		return buildXMLChildren(doc, rootElement, pkg);
	}
	
	public static boolean buildXMLChildren(Document doc, org.w3c.dom.Element parentElement, Element pkg)
	{
		Profile profile = StereotypesHelper.getProfile(Application.getInstance().getProject(), profileName);
		
		Collection<Element> interactions = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "sos:interaction", profile));
		Collection<Element> systems = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "sos:system", profile));
		Collection<Element> processes = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "behavior:process", profile));
		Collection<Element> functions = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "behavior:function", profile));
		Collection<Element> outports = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "behavior:outport", profile));
		Collection<Element> eventTraces = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "analysis:eventTrace", profile));
		Collection<Element> events = StereotypesHelper.getExtendedElements(StereotypesHelper.getStereotype(Application.getInstance().getProject(), "analysis:event", profile));
		
		LinkingHelper lh = new LinkingHelper();
		
		boolean retval = false;
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, interactions, XmlTagConstants.Type.INTERACTION, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, systems, XmlTagConstants.Type.SYSTEM, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, processes, XmlTagConstants.Type.PROCESS, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, functions, XmlTagConstants.Type.FUNCTION, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, outports, XmlTagConstants.Type.OUTPORT, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, eventTraces, XmlTagConstants.Type.EVENT_TRACE, lh);
		retval = retval | ExportXmlPuddle.buildXMLChildren(doc, parentElement, events, XmlTagConstants.Type.EVENT, lh);
		
		// TODO: Add linking between exported elements here
		
		return retval;
	}
	
}
