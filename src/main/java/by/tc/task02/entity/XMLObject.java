package by.tc.task02.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLObject implements Serializable {
    private String name;
    private String characters;
    private List<XMLObject> childXMLObjects = new ArrayList();
    private Map<String, String> attributes = new HashMap<>();

    public XMLObject() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Name: " + this.name + " ");
        result.append("Characters: " + this.characters + " ");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XMLObject xmlObject = (XMLObject) o;

        if (!name.equals(xmlObject.name)) return false;
        if (!characters.equals(xmlObject.characters)) return false;
        if (!childXMLObjects.equals(xmlObject.childXMLObjects)) return false;
        return attributes.equals(xmlObject.attributes);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + characters.hashCode();
        result = 31 * result + childXMLObjects.hashCode();
        result = 31 * result + attributes.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public List<XMLObject> getChildXMLObjects() {
        return childXMLObjects;
    }

    public void setChildXMLObjects(List<XMLObject> childXMLObjects) {
        this.childXMLObjects = childXMLObjects;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
