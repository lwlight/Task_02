package by.tc.task02.dao.tags;

import java.util.List;

public class TagManager {
    private List<String> fileXMLList;
    private Tag tag;
    private int currentLineNumber = 0;
    private final int END_OF_LIST;

    public TagManager(List<String> fileXMLList) {
        this.fileXMLList = fileXMLList;
        if (this.fileXMLList != null) {
            this.END_OF_LIST = fileXMLList.size() - 1;
        } else {
            END_OF_LIST = 0;
        }
    }


    public Tag getNext(){
        if (fileXMLList == null){
            return null;
        }

        if (currentLineNumber >= END_OF_LIST){
            return null;
        }

        String currentLine = fileXMLList.get(currentLineNumber);
        if (currentLine.startsWith("<?")){
            currentLineNumber++;
            return getNext();
        }

        if (currentLine.contains("<")){
            tag = buildTag(currentLine);
            currentLineNumber++;
            return tag;

        } else {
            currentLineNumber++;
            return getNext();
        }
    }

    public Tag checkNextTag() {
        if (fileXMLList == null){
            return null;
        }
        int nextLineNumber = currentLineNumber;
        return recursiveCheckNextTag(nextLineNumber);
    }

    private Tag recursiveCheckNextTag(int nextLineNumber){
        if (nextLineNumber >= END_OF_LIST){
            return null;
        }
        String currentLine = fileXMLList.get(nextLineNumber);
        if (currentLine.contains("<")){
            return buildTag(currentLine);
        } else {
            nextLineNumber++;
            return recursiveCheckNextTag(nextLineNumber);
        }
    }

    private String parseCharacters(boolean hasCloseTagOnThisLine){
        if (hasCloseTagOnThisLine){
            String charactersLine = fileXMLList.get(currentLineNumber);
            int startIndex = charactersLine.indexOf(">")+1;
            int lastIndex = charactersLine.indexOf("</");
            if (startIndex >= lastIndex){
                return null;
            } else {
                return charactersLine.substring(startIndex, lastIndex);
            }
        } else {
            Tag nextTag = checkTagAboveNext();

            if (nextTag != null && !nextTag.isOpen()){
                String charactersLine = fileXMLList.get(currentLineNumber);
                int readedLineNumber = currentLineNumber+1;
                while (readedLineNumber < END_OF_LIST){
                    charactersLine += (fileXMLList.get(readedLineNumber)).trim();
                    charactersLine = charactersLine.replace("\n", " ");
                    if (charactersLine.contains("</")){
                        break;
                    }
                    readedLineNumber++;
                }
                int startIndex = charactersLine.indexOf(">")+1;
                int lastIndex = charactersLine.indexOf("</");
                if (startIndex >= lastIndex){
                    return null;
                } else {
                    charactersLine = charactersLine.substring(startIndex, lastIndex);
                    return charactersLine.trim();
                }
            } else {
                return null;
            }
        }
    }

    private Tag buildTag(String currentLine){
        int startIndex = currentLine.indexOf("<");
        int lastIndex = currentLine.indexOf(">");
        String tagName = currentLine.substring(startIndex, lastIndex+1);
        boolean hasCloseTagOnThisLine = hasCloseTagOnThisLine(currentLine, tagName);
        Tag tag = new Tag(tagName, hasCloseTagOnThisLine);
        if (tag.isOpen()){
            String characters = parseCharacters(hasCloseTagOnThisLine);
            tag.setCharacters(characters);
        }
        return tag;
    }

    private Tag checkTagAboveNext(){
        currentLineNumber++;
        Tag tagAboveNext = checkNextTag();
        currentLineNumber--;
        return tagAboveNext;
    }

    private boolean hasCloseTagOnThisLine(String currentLine, String tagName){
        StringBuilder closeTagName = new StringBuilder(tagName);
        closeTagName.insert(1, "/");
        if (currentLine.contains(closeTagName.toString())){
            return true;
        } else {
            return false;
        }

    }
}

