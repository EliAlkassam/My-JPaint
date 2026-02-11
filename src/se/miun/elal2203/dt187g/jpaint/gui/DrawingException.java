package se.miun.elal2203.dt187g.jpaint.gui;

/**
 * This class handles the exceptions messages, where they appear either in the
 * UI or in the terminal.
 * Extends from the class Exception.
 *
 * @author elal2203
 * @version 0.1
 * @since 2025-09-27
 */

public class DrawingException extends Exception {

    public DrawingException() {
        super();
    }

    public DrawingException(String message) {
        super(message);
    }

}
