package by.tc.task02.dao;

import by.tc.task02.dao.impl.XMLDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final XMLDAO xmlDAO = new XMLDAOImpl();

    private DAOFactory(){}

    public XMLDAO getXmlDAO(){
        return xmlDAO;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}
