package org.custom.exceptions;

public class RepositoryNotFoundException extends Exception {
    public RepositoryNotFoundException(String s) {
        super("Repository not found");
    }
}
