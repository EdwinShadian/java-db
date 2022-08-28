package main.exceptions;

public class VillainIdNotFoundException extends Throwable {
    public VillainIdNotFoundException() {
        super("No villain with such id!");
    }
}
