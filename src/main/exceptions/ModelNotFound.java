package main.exceptions;

public class ModelNotFound extends Throwable {
    public ModelNotFound(String message){
        super(message);
    }
}
