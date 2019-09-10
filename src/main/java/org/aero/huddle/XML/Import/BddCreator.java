package org.aero.huddle.XML.Import;

import java.util.Collection;

import javax.swing.JOptionPane;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.ModelElementsManager;
import com.nomagic.magicdraw.openapi.uml.PresentationElementsManager;
import com.nomagic.magicdraw.openapi.uml.ReadOnlyElementException;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.magicdraw.uml.symbols.DiagramPresentationElement;
import com.nomagic.magicdraw.uml.symbols.layout.LayoutManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Diagram;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;

public class BddCreator {

	//Creates a diagram and adds all of the model elements with their relations to it
	//Utilizes helper method applyDiagramLayout to layout the diagram elements
	//Note this adds ALL classes to the diagram - it is not selective in any way
	public static void createDiagram(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package pkg)
	{
		Project project = Application.getInstance().getProject();
		
		//Status message for showing the current action. Primarily used for debugging purposes. May be removed.
		//JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), "Create Diagram");
		
		//Using the SessionManager while there is another active can throw IllegalStateException
		if(!SessionManager.getInstance().isSessionCreated(project))
		{
			SessionManager.getInstance().createSession(project, "Create SysML BDD");
			ModelElementsManager em = ModelElementsManager.getInstance();
			PresentationElementsManager pm = PresentationElementsManager.getInstance();
			Diagram d = null;
			
			try
			{
				//Create the new diagram and add it to the base model
				d = em.createDiagram("SysML Block Definition Diagram", pkg);
				d.setName("BDD");
				
				//Element used to encapsulate all other presentation elements in the diagram
				DiagramPresentationElement diagram = project.getDiagram(d);
				
				//Retrieve all classes in the top-level package
				Collection<Element> classCollection = Finder.byTypeRecursively().find(pkg, new java.lang.Class[] {com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class.class}, true);
				
				//Iterate through classes in model, adding each to the diagram
				//Associations are automatically added to the diagram
				for(Element system : classCollection)
				{
					pm.createShapeElement(system, diagram, true);
				}
			}
			catch(ReadOnlyElementException roee)
			{
				JOptionPane.showMessageDialog(MDDialogParentProvider.getProvider().getDialogParent(), roee.getMessage() + " - Create SysML BDD");
			}
			
			SessionManager.getInstance().closeSession(project);
			
			applyDiagramLayout(d);
		}
	}
	
	//Applies layout to the currently opened diagram
	//If there is no open diagram, does nothing
	public static void applyDiagramLayout(Diagram d)
	{
		Project project = Application.getInstance().getProject();
		
		//Req: Must have active project
		//Req: No active session manager, per MagicDraw documentation
		if(project != null && !SessionManager.getInstance().isSessionCreated(project))
		{
			DiagramPresentationElement diagram = project.getDiagram(d);
			
			//Must have diagram open
			diagram.open();
			
			LayoutManager lm = com.nomagic.magicdraw.uml.symbols.layout.LayoutManager.getInstance();
			diagram.layout(true, lm.getDefaultLayouter("SysML Block Definition Diagram"));
		}
	}
}
