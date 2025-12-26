package exceptions;

import model.Screen;

public class ScreenAlreadyOccupiedException extends Exception {
    public ScreenAlreadyOccupiedException(String screen) {
        super("Screen " + screen.getId() + " is already occupied in Theatre " + string.getTheatre().getName());
    }
}
