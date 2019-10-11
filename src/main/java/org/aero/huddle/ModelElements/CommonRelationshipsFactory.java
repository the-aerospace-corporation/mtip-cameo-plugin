package org.aero.huddle.ModelElements;

import org.aero.huddle.ModelElements.Requirements.Copy;
import org.aero.huddle.ModelElements.Requirements.DeriveRequirement;
import org.aero.huddle.ModelElements.Requirements.Refine;
import org.aero.huddle.ModelElements.Requirements.Satisfy;
import org.aero.huddle.ModelElements.Requirements.Trace;
import org.aero.huddle.ModelElements.Requirements.Verify;

public class CommonRelationshipsFactory {
	public CommonRelationship createElement(String type, String name, String EAID) {
		CommonRelationship relationship = null;
		switch(type) {
			case "Abstraction":
				relationship = new Abstraction(name, EAID);
				break;
			case "Aggregation":
				relationship = new Aggregation(name, EAID);
				break;
			case "Association":
				relationship = new Association(name, EAID);
				break;
			case "Composition":
				relationship = new Composition(name, EAID);
				break;
			case "Copy":
				relationship = new Copy(name, EAID);
				break;
			case "Connector":
				relationship = new Connector(name, EAID);
				break;
			case "DeriveRequirement":
				relationship = new DeriveRequirement(name, EAID);
				break;
			case "Generalization":
				relationship = new Generalization(name, EAID);
				break;
			case "Refine":
				relationship = new Refine(name, EAID);
				break;
			case "Satisfy":
				relationship = new Satisfy(name, EAID);
				break;
			case "Trace":
				relationship = new Trace(name, EAID);
				break;
			case "Verify":
				relationship = new Verify(name, EAID);
				break;
			default:
				break;
		}
		return relationship;
	}
}
