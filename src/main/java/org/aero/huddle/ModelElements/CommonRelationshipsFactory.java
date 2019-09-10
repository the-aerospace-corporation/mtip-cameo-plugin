package org.aero.huddle.ModelElements;

public class CommonRelationshipsFactory {
	public CommonRelationship createElement(String type, String name, String EAID) {
		CommonRelationship relationship = null;
		switch(type) {
			case "Aggregation":
				relationship = new Aggregation(name, EAID);
				break;
			case "Allocate":
				relationship = new Allocate(name, EAID);
				break;
			case "Association":
				relationship = new Association(name, EAID);
				break;
			case "Composition":
				relationship = new Composition(name, EAID);
				break;
			case "Generalization":
				relationship = new Generalization(name, EAID);
				break;
			default:
				break;
		}
		return relationship;
	}
}
