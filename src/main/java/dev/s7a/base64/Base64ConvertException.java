package dev.s7a.base64;

import org.jetbrains.annotations.NotNull;

public class Base64ConvertException extends RuntimeException {
    private final Exception exception;

    public Base64ConvertException(@NotNull Exception exception) {
        super(exception.getClass().getSimpleName() + ": " + exception.getMessage());
        this.exception = exception;
    }

    public @NotNull Exception getException() {
        return exception;
    }
}
