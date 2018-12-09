package com.yjh.two.handler;

public class ThreadHandler<S>
        extends UncheckedIOExceptionConverterHandler<S>  {


    public ThreadHandler(Handler<S> other) {
        super(other);
    }

    public void handler (S s) {
        new Thread(() -> super.handle(s)).start();
    }

}
