package by.tc.task02.dao.exceptiondao;

public class DAOException extends Exception {
    public DAOException(){
        super("Exception in DAO");
    }
    public DAOException(String message){
        super(message);
    }
}
