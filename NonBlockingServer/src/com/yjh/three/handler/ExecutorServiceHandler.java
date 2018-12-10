package com.yjh.three.handler;

import java.util.concurrent.*;

public class ExecutorServiceHandler<S>
    extends DecoratedHandler<S> {

    private final ExecutorService pool;
    private Thread.UncaughtExceptionHandler exceptionHandler;

    public ExecutorServiceHandler(Handler<S> other,
                                  ExecutorService pool,
                                  Thread.UncaughtExceptionHandler exceptionHandler) {
        super(other);
        this.pool = pool;
        this.exceptionHandler = exceptionHandler;
    }

    public ExecutorServiceHandler(Handler<S> other,
                                  ExecutorService pool){
        this(other, pool,
               (t, e) -> System.out.println("uncaught : " + t + "error " + e)
        );
    }

    public void handle(S s) {
        pool.submit(new FutureTask<>(() -> {
            super.handle(s);
            return null;
        }) {

            @Override
            protected void setException(Throwable t) {
                exceptionHandler.uncaughtException(Thread.currentThread(), t);
            }
        });
    }



}
