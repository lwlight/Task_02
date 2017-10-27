package by.tc.task02.service.exceptions;

public class ServiceException extends Exception {
    public ServiceException(){
        super("Exception in DAO");
    }
    public ServiceException(String message){
        super(message);
    }
}
