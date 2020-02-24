package com.andreiverdes.training.expleo.victrola.util;

public class Lazy<T> {

    public static <T> Lazy<T> byLazy(Getter<T> getter) {
        return new Lazy<>(getter);
    }

    public static <T> Lazy<T> byLazy(Getter2<T> getter) {
        return new Lazy<>(getter);
    }

    private Getter<T> getter;
    private Getter2<T> getter2;
    private T value;

    private Lazy(Getter<T> getter) {
        this.getter = getter;
    }

    private Lazy(Getter2<T> getter) {
        this.getter2 = getter;
    }

    public T get() {
        if (value == null) {
            value = getter.find();
        }
        return value;
    }

    public <K> T get(K param) {
        if (value == null) {
            value = getter2.find(param);
        }
        return value;
    }

    public interface Getter<T> {
        T find();
    }

    public interface Getter2<T> {
        <K> T find(K param);
    }
}
