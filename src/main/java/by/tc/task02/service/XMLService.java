package by.tc.task02.service;

import by.tc.task02.entity.XMLObject;
import by.tc.task02.service.exceptions.ServiceException;

public interface XMLService {
    XMLObject buildXMLObject()throws ServiceException;
}
