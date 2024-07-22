package net.anyforum.backend.exceptions;

public class FailedTransactionException extends Exception{
    public FailedTransactionException() {
        super("Database transaction failed.");
    }
}
