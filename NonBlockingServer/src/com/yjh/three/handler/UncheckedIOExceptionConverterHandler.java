package com.yjh.three.handler;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedIOExceptionConverterHandler<S> extends DecoratedHandler<S> {

    public UncheckedIOExceptionConverterHandler(Handler<S> other) {
        super(other);
    }

    public void handle(S s) {
        try {
            super.handle(s);
        } catch (IOException ex){
            throw new UncheckedIOException(ex);
        }
    }
}
