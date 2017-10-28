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
        if (currentLineNumber == END_OF_LIST){
            return null;
        }
        String currentLine = fileXMLList.get(currentLineNumber);

        if (currentLine.startsWith("<?")){
            if (currentLineNumber < END_OF_LIST){
                currentLineNumber++;
            }
            return getNext();
        }
        if (currentLine.contains("<")){
            tag = buildTag(currentLine);

            if (currentLineNumber < END_OF_LIST){
                currentLineNumber++;
            }
            return tag;

        } else {
            if (currentLineNumber < END_OF_LIST){
                currentLineNumber++;
                return getNext();
            } else {
                return null;
            }
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
        if (nextLineNumber == END_OF_LIST){
            return null;
        }
        String currentLine = fileXMLList.get(nextLineNumber);
        if (currentLine.contains("<")){
            return buildTag(currentLine);
        } else {
            if (nextLineNumber < END_OF_LIST){
                nextLineNumber++;
                return recursiveCheckNextTag(nextLineNumber);
            } else {
                return null;
            }
        }
    }

    private String parseCharacters(boolean hasCloseTagOnThisLine){
        if (hasCloseTagOnThisLine){
            String currentLine = fileXMLList.get(currentLineNumber);
            int startIndex = currentLine.indexOf(">")+1;
            int lastIndex = currentLine.indexOf("</");
            if (startIndex >= lastIndex){
                return null;
            } else {
                return currentLine.substring(startIndex, lastIndex);
            }
        } else {
            String currentLine = fileXMLList.get(currentLineNumber);
            currentLineNumber++;
            Tag nextTag = checkNextTag();
            currentLineNumber--;
            if (nextTag != null && !nextTag.isOpen()){
                String holeLine = fileXMLList.get(currentLineNumber);
                int readedLineNumber = currentLineNumber+1;
                while (currentLineNumber < END_OF_LIST){
                    holeLine = holeLine.replace("\n", "");
                    holeLine += fileXMLList.get(readedLineNumber);
                    if (holeLine.contains("</")){
                        break;
                    }
                    readedLineNumber++;
                }
                holeLine = holeLine.replace("\n", " ");
                int startIndex = holeLine.indexOf(">")+1;
                int lastIndex = holeLine.indexOf("</");
                if (startIndex >= lastIndex){
                    return null;
                } else {
                    holeLine = holeLine.substring(startIndex, lastIndex);
                    return holeLine.trim();
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

