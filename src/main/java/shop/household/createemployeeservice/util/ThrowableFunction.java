package shop.household.createemployeeservice.util;


/**
 * Represents a Function that accepts T and returns R and may fail with an exception
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {
    R apply(T t) throws Throwable;
}
