package by.tc.task02.dao.tags;

import java.util.HashMap;
import java.util.Map;

public class Tag {
    private String fullTagName;
    private String tagName;
    private String characters;
    private boolean closeTagAtThisLine;
    private Map<String, String> attributes = new HashMap<>();


    public Tag(String fullTagName, boolean hasCloseTagAtThisLine, String characters) {
        this.fullTagName = fullTagName;
        this.closeTagAtThisLine = hasCloseTagAtThisLine;
        this.characters = characters;
    }

    public Tag(String fullTagName, boolean hasCloseTagAtThisLine) {
        this.fullTagName = fullTagName;
        this.closeTagAtThisLine = hasCloseTagAtThisLine;
    }

    public boolean isOpen(){
        if (fullTagName.startsWith("</")){
            return false;
        } else {
            return true;
        }
    }

    public void parseTagNameAndAttributes(){
        if ((fullTagName.indexOf(" ")) == -1){
            this.tagName = fullTagName.substring(1, fullTagName.length()-1);
        } else {
            this.tagName = fullTagName.substring(1, fullTagName.indexOf(" "));
            String attributesLine = fullTagName.substring(fullTagName.indexOf(" ")+1, fullTagName.length()-1);

            attributesLine = attributesLine.replace(" ", "");
            attributesLine = attributesLine.replace("\"", "");
            attributesLine = attributesLine.replace(",", "=");


            String[] attributesArr = attributesLine.split("=");

            for (int i=0; i<attributesArr.length-1; i+=2){
                attributes.put(attributesArr[i], attributesArr[i+1]);
            }
        }
    }

    @Override
    public String toString() {
        return this.fullTagName;
    }





    public String getFullTagName() {
        return fullTagName;
    }

    public void setFullTagName(String fullTagName) {
        this.fullTagName = fullTagName;
    }

    public boolean isCloseTagAtThisLine() {
        return closeTagAtThisLine;
    }

    public void setCloseTagAtThisLine(boolean closeTagAtThisLine) {
        this.closeTagAtThisLine = closeTagAtThisLine;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
