package by.tc.task02.main;

import by.tc.task02.entity.XMLObject;
import by.tc.task02.service.ServiceFactory;
import by.tc.task02.service.XMLService;
import by.tc.task02.service.exceptions.ServiceException;

public class Main {
    public static void main(String[] args) {
        ServiceFactory factory = ServiceFactory.getInstance();
        XMLService service = factory.getXmlService();
        XMLObject object;
        try {
            object = service.buildXMLObject();
            PrintXMLObjectInfo.print(object);
        } catch (ServiceException e){
            System.out.println(e.getMessage());
        }

    }
}
