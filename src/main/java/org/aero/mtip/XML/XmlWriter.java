package org.aero.mtip.XML;

import java.util.Iterator;
import java.util.List;
import javax.annotation.CheckForNull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.aero.mtip.XML.Export.Exporter;
import org.aero.mtip.util.CameoUtils;
import org.aero.mtip.util.Logger;
import org.aero.mtip.util.XmlTagConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ElementValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Expression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.InstanceValue;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralBoolean;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralInteger;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralNull;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralReal;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralSpecification;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralString;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.LiteralUnlimitedNatural;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.OpaqueExpression;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.TypedElement;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.ValueSpecification;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.Duration;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.DurationInterval;
import com.nomagic.uml2.ext.magicdraw.commonbehaviors.mdsimpletime.Interval;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;

public class XmlWriter {
	public static XmlWriter instance;
	
	private Document xmlDoc;
	private Element root;
	
	
	public XmlWriter() {
		try {
			xmlDoc = createDocument();
		} catch (ParserConfigurationException e) {
			Logger.log("Failed to create XML document.");
			Logger.logException(e);
		}
	}
	
	public static boolean initialize() {
		instance = new XmlWriter();
		
		if (instance == null) {
			return false;
		}
		
		createPacketTag();
		return true;
	}
	
	public static Element createTag(String tagName) {
		return instance.xmlDoc.createElement(tagName);
	}
	
	public static Element createTag(String tagName, String dataType) {
		org.w3c.dom.Element tag = createTag(tagName);
		addAttribute(tag, XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataType);
		
		return tag;
	}
	
	public static Element createTag(String tagName, String dataType, String text) {
		org.w3c.dom.Element tag = createTag(tagName);
		addAttribute(tag, XmlTagConstants.ATTRIBUTE_DATA_TYPE, dataType);
		setText(tag, text);
		
		return tag;
	}
	
	/**
	 * Sets text to empty string if text is null.
	 */
	public static void setText(Element tag, String text) {
	  if (text == null) {
	    text = "";
	  }
	
	  tag.appendChild(instance.xmlDoc.createTextNode(text));
	}
	
	public static void addToRoot(Element data) {
		if (instance.root == null) {
			return;
		}
		
		add(instance.root, data);
	}
	
	public static void add(Element parent, Element child) {
		parent.appendChild(child);
	}
	
	public static void createPacketTag() {
		instance.root = instance.xmlDoc.createElement("packet");
		instance.xmlDoc.appendChild(instance.root);
	}
	
	public static Element createDataTag() {
		return instance.xmlDoc.createElement(XmlTagConstants.DATA);
	}
	
	public static void addAttribute(Element tag, String key, String value) {
		tag.setAttribute(key, value);
	}
	
	public static void addAttributeKey(Element tag, String value) {
		tag.setAttribute(XmlTagConstants.ATTRIBUTE_KEY, value);
	}
	
	@CheckForNull
	public static Element createStereotypeTag(Stereotype stereotype) {
		if (stereotype.getName() == null || stereotype.getName().trim().isEmpty()) {
			Logger.log(String.format("Stereotype not added to xml. Stereotype of with id %s is not named.", stereotype.getID()));
			return null;
		}
		
		com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package profile = StereotypesHelper.getProfileForStereotype(stereotype);
		
		org.w3c.dom.Element stereotypeAttribute = createMtipDictAttribute(XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE);
		
		org.w3c.dom.Element stereotypeNameAttribute = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, stereotype.getName());
		org.w3c.dom.Element stereotypeIdAttribute = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_ID, XmlTagConstants.ATTRIBUTE_TYPE_STRING, stereotype.getID());
		org.w3c.dom.Element profileNameAttribute = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, profile.getName());
		org.w3c.dom.Element profileIdAttribute = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_PROFILE_ID, XmlTagConstants.ATTRIBUTE_TYPE_STRING, profile.getID());
		
		stereotypeAttribute.appendChild(stereotypeNameAttribute);
		stereotypeAttribute.appendChild(stereotypeIdAttribute);
		stereotypeAttribute.appendChild(profileNameAttribute);
		stereotypeAttribute.appendChild(profileIdAttribute);

		return stereotypeAttribute;
	}
	
	@CheckForNull
	public static Element createTaggedValueTag(Stereotype stereotype, String propertyName, String valueType, List<String> values) {
		Element taggedValueValueTag = createTaggedValueValueTag(values, valueType);
		
		if (taggedValueValueTag == null) {
			return null;
		}
		
		Element attribute = createMtipDictAttribute(XmlTagConstants.STEREOTYPE_TAGGED_VALUE);
		
		Element stereotypeNameTag = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_STEREOTYPE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, stereotype.getName());
		Element profileNameTag = createMtipDictAttributeValue(XmlTagConstants.ATTRIBUTE_KEY_PROFILE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, StereotypesHelper.getProfileForStereotype(stereotype).getName());
		Element taggedValueNameTag = createMtipDictAttributeValue(XmlTagConstants.TAGGED_VALUE_NAME, XmlTagConstants.ATTRIBUTE_TYPE_STRING, propertyName);
		Element taggedValueTypeTag = createMtipDictAttributeValue(XmlTagConstants.TAGGED_VALUE_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING, valueType);
		
		add(attribute, stereotypeNameTag);
		add(attribute, profileNameTag);
		add(attribute, taggedValueNameTag);
		add(attribute, taggedValueTypeTag);
		add(attribute, taggedValueValueTag);
		
		return attribute;	
	}
	
	@CheckForNull
	public static Element createTaggedValueValueTag(List<String> values, String valueType) {
		Element valuesParentTag = createMtipListAttribute(XmlTagConstants.TAGGED_VALUE_VALUES);
		boolean noneFound = true;
		
		for (String value : values) {
			Element valueTag = null;
			
			if (valueType.contentEquals(XmlTagConstants.TV_TYPE_BOOLEAN)) {
				valueTag = createBoolAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, value);
			} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_INTEGER)) {
				valueTag = createIntAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, value);
			} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_REAL)) {
				valueTag = createFloatAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, value);
			} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_STRING)) {
				valueTag = createMtipStringAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, value);
			} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_ELEMENT)) {
				valueTag = createMtipAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, XmlTagConstants.TV_TYPE_ELEMENT, value);
			} else if (valueType.contentEquals(XmlTagConstants.TV_TYPE_ENUMERATION_LITERAL)) {
				valueTag = createMtipAttribute(XmlTagConstants.TAGGED_VALUE_VALUE, XmlTagConstants.TV_TYPE_ENUMERATION_LITERAL, value);
			}
			
			if (valueTag == null) {
				continue;
			}
			
			noneFound = false;
			XmlWriter.add(valuesParentTag, valueTag);
		}
		
		if (noneFound) {
			return null;
		}
		
		return valuesParentTag;
	}
	
	@CheckForNull
	public static Element createTypedByTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element) {
		if (!(element instanceof TypedElement)) {
			return null;
		}
		
		Type cameoType = ((TypedElement)element).getType();
		
		if(cameoType == null) {
			return null;
		}

		if(CameoUtils.isPrimitiveValueType(cameoType)) {
			return createPrimitiveRelationshipTypedByTag(cameoType);
		}
		
		return createMtipRelationship(cameoType, XmlTagConstants.TYPED_BY);
	}
	
	public static Element createMtipIdTag(String id) {
		Element idTag = createTag(XmlTagConstants.ID, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		Element cameoIdTag = createTag(XmlTagConstants.CAMEO, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		setText(cameoIdTag, id);
		
		idTag.appendChild(cameoIdTag);
		
		return idTag;
	}
	
	public static Element createMtipRelationshipsTag() {
		return createTag(XmlTagConstants.RELATIONSHIPS, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	}
	
	public static Element createMtipTypeTag(String xmlType) {
		return createTag(XmlTagConstants.TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING, xmlType);
	}
	
	public static Element createMtipAttributesTag() {
		return createTag(XmlTagConstants.ATTRIBUTES, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
	}
	
	public static Element createMtipDictAttribute(String keyValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_DICT, null);
	}
	
	public static Element createMtipDictAttributeValue(String key, String dataType, String tagValue) {
		Element attributeTag = createTag(XmlTagConstants.ATTRIBUTE, dataType, tagValue);
		addAttributeKey(attributeTag, key);
		
		return attributeTag;
	}
	
	public static Element createMtipListAttribute(String keyValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_LIST, null);
	}
	
	public static Element createMtipStringAttribute(String keyValue, String attributeValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_STRING, attributeValue);
	}
	
	public static org.w3c.dom.Element createFloatAttribute(String keyValue, String attributeValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_FLOAT, attributeValue);
	}
	
	public static org.w3c.dom.Element createIntAttribute(String keyValue, String attributeValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_INT, attributeValue);
	}
	
	public static org.w3c.dom.Element createBoolAttribute(String keyValue, String attributeValue) {
		return createMtipAttribute(keyValue, XmlTagConstants.ATTRIBUTE_TYPE_BOOL, attributeValue);
	}
	
	public static org.w3c.dom.Element createMtipListItem(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element, String xmlTag, String index) {
		org.w3c.dom.Element listItemTag = XmlWriter.createTag(xmlTag, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		XmlWriter.addAttributeKey(listItemTag, index);
		
		org.w3c.dom.Element typeTag = XmlWriter.createSimpleTypeTag(element);
		org.w3c.dom.Element valueTag = XmlWriter.createSimpleIdTag(element);
		
		org.w3c.dom.Element relDataTag = createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		listItemTag.appendChild(typeTag);
		listItemTag.appendChild(valueTag);
		listItemTag.appendChild(relDataTag);
		
		return listItemTag;
	}
	
	public static Element createMtipKeyValueAttribute(String keyValue, String dataTypeValue, String attributeValue) {
		Element tag = createTag(XmlTagConstants.ATTRIBUTE, dataTypeValue);
		addAttribute(tag, XmlTagConstants.ATTRIBUTE_KEY, keyValue);
		
		if(attributeValue == null) {
			return tag;
		}
		
		setText(tag, attributeValue);
		
		return tag;
	}
	
	public static org.w3c.dom.Element createMtipAttribute(String keyValue, String dataTypeValue, String attributeValue) {
		Element tag = createTag(XmlTagConstants.ATTRIBUTE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		addAttribute(tag, XmlTagConstants.ATTRIBUTE_KEY, keyValue);
		
		if(attributeValue == null) {
			return tag;
		}
		
		Element subTag = createTag(XmlTagConstants.ATTRIBUTE, dataTypeValue, attributeValue);
		addAttribute(subTag, XmlTagConstants.ATTRIBUTE_KEY, XmlTagConstants.ATTRIBUTE_KEY_VALUE);
		
		XmlWriter.add(tag, subTag);
		
		return tag;
	}
	
	public static Element createMtipHasParentTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element owner) {
		return createMtipRelationship(owner, XmlTagConstants.HAS_PARENT);
	}
	
	public static Element createPrimitiveRelationshipTypedByTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element) {
		org.w3c.dom.Element relTag = createTag(XmlTagConstants.TYPED_BY, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		org.w3c.dom.Element value = createTag(
				XmlTagConstants.ID, 
				XmlTagConstants.ATTRIBUTE_TYPE_STRING, 
				CameoUtils.primitiveValueTypesByID.get(element.getLocalID()));

		org.w3c.dom.Element type = createTag(
				XmlTagConstants.TYPE, 
				XmlTagConstants.ATTRIBUTE_TYPE_STRING, 
				XmlTagConstants.PRIMITIVE_VALUE_TYPE);
		
		add(relTag, value);
		add(relTag, type);
		
		return relTag;
	}
	
	public static Element createSimpleIdTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element) {
		return createSimpleIdTag(element.getID());
	}
	
	public static Element createSimpleIdTag(String elementId) {
		return createTag(
				XmlTagConstants.ID, 
				XmlTagConstants.ATTRIBUTE_TYPE_STRING, 
				elementId);
	}
	
	public static Element createSimpleTypeTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element) {
		return createSimpleTypeTag(Exporter.getEntityType(element));
	}
	
	public static Element createSimpleTypeTag(String elementType) {
		// TODO: If introducing different metamodels change string literal in String.format()
		return createTag(
				XmlTagConstants.TYPE, 
				XmlTagConstants.ATTRIBUTE_TYPE_STRING, 
				String.format("sysml.%s", elementType));
	}
	
	public static Element createMtipDiagramConnector(int number) {
		Element diagramConnectorTag = createTag(XmlTagConstants.DIAGRAM_CONNECTOR, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		addAttributeKey(diagramConnectorTag, String.valueOf(number));
		
		return diagramConnectorTag;
	}
	
	public static Element createMtipDiagramElement(int number) {
		Element diagramElementTag = createTag(XmlTagConstants.ELEMENT, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		addAttributeKey(diagramElementTag, String.valueOf(number));

		return diagramElementTag;
	}
	
	public static Element createMtipRelationship(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element, String xmlTag) {
		Element relTag = createTag(xmlTag);
		addAttribute(relTag, XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		Element typeTag = createSimpleTypeTag(element);
		Element idTag = createSimpleIdTag(element);
		
		org.w3c.dom.Element relDataTag = createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		add(relTag, typeTag);
		add(relTag, idTag);
		add(relTag, relDataTag);
		
		return relTag;
	}
	
	public static Element createClassifiedByPrimitiveTag(com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element element) {
		Element relTag = createTag(XmlTagConstants.CLASSIFIED_BY);
		addAttribute(relTag, XmlTagConstants.ATTRIBUTE_DATA_TYPE, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		Element typeTag = createTag(XmlTagConstants.TYPE, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		setText(typeTag, XmlTagConstants.PRIMITIVE_VALUE_TYPE);
		
		Element idTag = createTag(XmlTagConstants.ID, XmlTagConstants.ATTRIBUTE_TYPE_STRING);
		setText(idTag, ((NamedElement)element).getName());
		
		org.w3c.dom.Element relDataTag = createTag(XmlTagConstants.RELATIONSHIP_METADATA, XmlTagConstants.ATTRIBUTE_TYPE_DICT);
		
		add(relTag, typeTag);
		add(relTag, idTag);
		add(relTag, relDataTag);
		
		return relTag;
	}
	
	public static Element createDefaultValueTag(ValueSpecification vs) {
		String defaultValue = CameoUtils.getValueSpecificationValueAsString(vs);
		
		if (defaultValue == null || defaultValue.trim().isEmpty()) {
			return null;
		}
		
		return createMtipStringAttribute(XmlTagConstants.ATTRIBUTE_KEY_DEFAULT_VALUE, defaultValue);
	}
	
	public static Document createDocument() throws ParserConfigurationException	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setNamespaceAware(true);
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		return doc;
	}
	
	@CheckForNull
	public static org.w3c.dom.Element createAttributeFromValueSpecification(ValueSpecification vs, String attrName) {
		org.w3c.dom.Element strAttr = null;
		
		if (vs instanceof Duration) {
			Duration dur = (Duration)vs;
			LiteralString ls = (LiteralString) dur.getExpr();
			
			if (ls == null) {
				return null;
			}
			
			String strVal = ls.getValue();
			strAttr = createMtipStringAttribute(attrName, strVal);
		} else if (vs instanceof DurationInterval) {
			DurationInterval di = (DurationInterval)vs;
			
		} else if (vs instanceof ElementValue) {
			ElementValue ev = (ElementValue)vs;
			String strVal = ev.getElement().getID();
			strAttr = createMtipStringAttribute(attrName, strVal);
		} else if (vs instanceof InstanceValue) {
			InstanceValue iv = (InstanceValue)vs;
			String strVal = iv.getInstance().getID();
			strAttr = createMtipStringAttribute(attrName, strVal);
		} else if (vs instanceof LiteralString) {
			LiteralString ls = (LiteralString)vs;
			String value = ls.getValue();
			strAttr = createMtipStringAttribute(attrName, value);
		} else if (vs instanceof LiteralReal) {
			LiteralReal lr = (LiteralReal)vs;
			double value = lr.getValue();
			String strVal = String.valueOf(value);
			strAttr = createFloatAttribute(attrName, strVal);
		} else if (vs instanceof LiteralInteger) {
			LiteralInteger lr = (LiteralInteger)vs;
			int value = lr.getValue();
			String strVal = String.valueOf(value);
			strAttr = createIntAttribute(attrName, strVal);
		} else if (vs instanceof LiteralBoolean) {
			LiteralBoolean lr = (LiteralBoolean)vs;
			boolean value = lr.isValue();
			String strVal = String.valueOf(value);
			strAttr = createBoolAttribute(attrName, strVal);
		} else if (vs instanceof OpaqueExpression) {
			OpaqueExpression oe = (OpaqueExpression)vs;
			List<String> bodies= oe.getBody();
			Iterator<String> bodyIter = bodies.iterator();
			String body = null;
			if(bodyIter.hasNext()) {
				body = bodyIter.next();
			}
			if(body != null) {
				strAttr = createMtipStringAttribute(attrName, body);
			} else {
				Logger.log(String.format("Body of opaque expression with id %s has empty body.", vs.getID()));
			}
		} else if (vs instanceof LiteralUnlimitedNatural) {
			
		} else if (vs instanceof LiteralNull) {
			
		} else if (vs instanceof LiteralSpecification) {
			
		} else if (vs instanceof Expression) {
			
		} else if (vs instanceof Interval) {
			
		} else {
			Logger.log(String.format("Value specification with id %s was not a recognized type.", vs.getID()));
		}
		
		return strAttr;
	}
	
	public static Document getDocument() {
		return instance.xmlDoc;
	}

}
