package by.tc.task02.service.impl;

import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.XMLDAO;
import by.tc.task02.dao.exception.DAOException;
import by.tc.task02.entity.XMLObject;
import by.tc.task02.service.XMLService;
import by.tc.task02.service.exceptions.ServiceException;

public class XMLServiteImpl implements XMLService{

    @Override
    public XMLObject buildXMLObject() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        XMLDAO XMLDAO = factory.getXmlDAO();
        XMLObject xmlObject;
        try {
            xmlObject = XMLDAO.buildXMLObject();
        } catch (DAOException e){
            throw new ServiceException(e.getMessage());
        }
        return xmlObject;
    }

}
