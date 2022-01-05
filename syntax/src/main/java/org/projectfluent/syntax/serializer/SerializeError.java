package org.projectfluent.syntax.serializer;

public final class SerializeError extends Exception {

    public SerializeError(String message) {
        Objects.requireNonNull(message, "message");
        super(message);
    }
}
