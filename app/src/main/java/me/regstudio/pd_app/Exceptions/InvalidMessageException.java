package me.regstudio.pd_app.Exceptions;

public class InvalidMessageException extends Exception {

    /* For when attempting to add an invalid message to the data packet.
    *  Codes:
    *  0 = General failure (default error)
    *  1 = Empty message - User attempted to add an empty message to the data packet.
    */

    private int code = 0;

    // Helper code integers for clarification and ease-of-use when handling InvalidMessageExceptions.
    public int GENERAL_FAILURE = 0;
    public int EMPTY_MESSAGE = 1;

    public InvalidMessageException(String message) {
        super(message);
    }

    public InvalidMessageException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
