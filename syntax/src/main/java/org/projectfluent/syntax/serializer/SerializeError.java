package org.projectfluent.syntax.serializer;

import java.util.Objects;

public final class SerializeError extends Exception {

    public SerializeError(String message) {
        Objects.requireNonNull(message, "message");
        super(message);
    }
}
