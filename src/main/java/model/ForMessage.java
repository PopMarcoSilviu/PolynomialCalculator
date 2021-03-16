package model;

@FunctionalInterface
public interface ForMessage <T,W>
{
     W apply(T t1, T t2, String s);
}

