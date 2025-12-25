package exceptions;

import model.Screen;

public class ScreenAlreadyOccupiedException extends Exception {
    public ScreenAlreadyOccupiedException(Screen screen) {
        super("Screen " + screen.getId() + " is already occupied in Theatre " + screen.getTheatre().getName());
    }
}
