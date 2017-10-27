package by.tc.task02.dao.impl;

import by.tc.task02.dao.XMLDAO;
import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.entity.XMLObject;
import by.tc.task02.dao.tags.Tag;
import by.tc.task02.dao.tags.TagManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLDAOImpl implements XMLDAO {

    private List<String> fileXMLList = new ArrayList<>();
    private static int incapsulationLvl = 0;
    private static Map<Integer, XMLObject> parentMap = new HashMap<>();
    static {
        parentMap.put(0, null);
    }

    public XMLObject buildXMLObject() throws DAOException{
        readXMLFile();
        TagManager tagManager = new TagManager(fileXMLList);

        return recursiveBuild(tagManager);
    }

    private XMLObject recursiveBuild(TagManager tagManager) {
        Tag tag = tagManager.getNext();

        if (tag == null) {
            return null;
        }

        if (tag.isOpen()) {
            XMLObject xmlObject = new XMLObject();
            tag.parseTagNameAndAttributes();
            xmlObject.setAttributes(tag.getAttributes());
            xmlObject.setName(tag.getTagName());
            xmlObject.setCharacters(tag.getCharacters());

            Tag nextTag = tagManager.checkNextTag();

            if (!tag.isCloseTagAtThisLine() && nextTag != null && nextTag.isOpen()) {
                incapsulationLvl++;
                parentMap.put(incapsulationLvl,xmlObject);
                if (parentMap.get(incapsulationLvl - 1) != null) {
                    parentMap.get(incapsulationLvl - 1).getChildXMLObjects().add(xmlObject);
                }
            } else {
                parentMap.get(incapsulationLvl).getChildXMLObjects().add(xmlObject);
                if (tag.isCloseTagAtThisLine() && nextTag != null && !nextTag.isOpen()) {
                    incapsulationLvl--;
                }
            }
            if (tagManager.checkNextTag() != null) {
                recursiveBuild(tagManager);
            }
            return xmlObject;
        } else {
            return recursiveBuild(tagManager);
        }
    }


    private void readXMLFile() throws DAOException {
        try(InputStream fis = XMLDAOImpl.class.getClassLoader().getResourceAsStream("example.xml");
            BufferedReader bfr = new BufferedReader(new InputStreamReader(fis))){
            String readed = "";
            while ((readed = bfr.readLine()) != null) {
                fileXMLList.add(readed);
            }
        } catch (IOException e){
            throw new DAOException(e.getMessage());
        }
    }
}
