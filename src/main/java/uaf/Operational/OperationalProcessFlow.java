package uaf.Operational;

import org.aero.mtip.ModelElements.Activity.ActivityDiagram;
import org.aero.mtip.util.UAFConstants;
import org.aero.mtip.util.XmlTagConstants;

public class OperationalProcessFlow extends ActivityDiagram{
	public OperationalProcessFlow(String name, String EAID)
	{
		super(name, EAID);
		this.sysmlConstant = UAFConstants.OPERATIONAL_PROCESS_FLOW;
		this.xmlConstant = XmlTagConstants.OPERATIONAL_PROCESS_FLOW;
		//do I need to specify the cameoDiagramConstant if it is already not a custom?
		this.allowableElements = UAFConstants.OPERATIONAL_PROCESS_FLOW_TYPES;
		
	}
	
	@Override
	public String getDiagramType()
	{
		return this.xmlConstant;
	}
}
