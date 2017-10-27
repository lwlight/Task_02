package by.tc.task02.main;

import by.tc.task02.entity.XMLObject;

import java.util.Map;


public class PrintXMLObjectInfo {
    public static void print(XMLObject xmlObject){
        if(xmlObject == null){
            System.out.println("Fail to read XML file");
        } else {
            if (xmlObject.getAttributes().size()>0){
                for (Map.Entry<String, String> pair : xmlObject.getAttributes().entrySet()){
                    System.out.print(pair.getValue() + ". ");
                    break;
                }
            }
            System.out.print(xmlObject.getName());
            if (xmlObject.getCharacters() != null){
                System.out.println(" - " + xmlObject.getCharacters());
            } else {
                System.out.println(":");
            }
            for (XMLObject childXML : xmlObject.getChildXMLObjects()){
                print(childXML);
            }
        }
    }
}
