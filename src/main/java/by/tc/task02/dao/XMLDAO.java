package by.tc.task02.dao;

import by.tc.task02.dao.exceptiondao.DAOException;
import by.tc.task02.entity.XMLObject;

public interface XMLDAO {
    XMLObject buildXMLObject() throws DAOException;
}
