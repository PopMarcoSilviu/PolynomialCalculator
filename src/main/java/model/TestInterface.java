package model;

@FunctionalInterface
public interface TestInterface<T,W>
{
     W apply(T t1, T t2);
}
