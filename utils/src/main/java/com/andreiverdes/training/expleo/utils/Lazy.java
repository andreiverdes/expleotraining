package com.andreiverdes.training.expleo.utils;

public class Lazy<Result> {

    public static <T> Lazy<T> byLazy(Initializer<T> initializer) {
        return new Lazy<>(initializer);
    }

    private Initializer<Result> initializer;

    private volatile Result value;

    private Lazy(Initializer<Result> initializer) {
        this.initializer = initializer;
    }

    public Result get() {
        // use a temporary variable to reduce the number of reads of the
        // volatile field
        Result result = value;
        if (result == null) {
            synchronized (this) {
                result = value;
                if (result == null) {
                    value = result = initializer.initialize();
                }
            }
        }
        return result;
    }

    public interface Initializer<T> {
        T initialize();
    }

}
