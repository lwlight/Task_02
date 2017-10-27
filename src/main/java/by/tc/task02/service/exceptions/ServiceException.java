package by.tc.task02.service.exceptionservice;

public class ServiceException extends Exception {
    public ServiceException(){
        super("Exception in DAO");
    }
    public ServiceException(String message){
        super(message);
    }
}
