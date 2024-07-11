package shop.household.createemployeeservice.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * Represents the result of a computation that could have succeeded with a value of the type T
 * or failed with a Throwable.
 */

@FunctionalInterface
public interface ThrowableOperation<T> {
    T execute() throws Throwable;
}


/**
 * Represents a successful execution
 */
class Success<T> implements Try<T> {
    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public T getUnchecked() {
        return get();
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }

    @Override
    public T getOrElse(T defaultValue) {
        return value;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        return value;
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) {
        return value;
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E {
        action.accept(value);
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) {
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        return predicate.test(value) ? this : new Failure(new NoSuchElementException());
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        U res = null;
        try {
            res = function.apply(value);
        } catch (Throwable e) {
            return new Failure<U>(e);
        }
        return new Success<U>(res);
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        Try<U> proxy = null;
        try {
            proxy = function.apply(value);
        } catch (Throwable e) {
            return new Failure<U>(e);
        }
        return proxy;
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        return this;
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        return this;
    }

    @Override
    public String toString() {
        return "Success[" + value + "]";
    }
    @Override
    public Throwable getException(){
        return null;
    }
}

/**
 * Represents a failed execution
 */
class Failure<T> implements Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public T get() throws Throwable {
        throw e;
    }

    @Override
    public T getUnchecked() {
        throw new RuntimeException(e);
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }

    @Override
    public T getOrElse(T defaultValue) {
        return defaultValue;
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        return supplier.get();
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) {
        return this;
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E {
        action.accept(e);
        return this;
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        return this;
    }

    @Override
    public <U> Try<U> map(ThrowableFunction<? super T, ? extends U> function) {
        return new Failure<U>(e);
    }

    @Override
    public <U> Try<U> flatMap(ThrowableFunction<? super T, Try<U>> function) {
        return new Failure<U>(e);
    }

    @Override
    public Try<T> recover(ThrowableFunction<? super Throwable, T> function) {
        T res = null;
        try {
            res = function.apply(e);
        } catch (Throwable e) {
            return new Failure<T>(e);
        }
        return new Success<T>(res);
    }

    @Override
    public Try<T> recoverWith(ThrowableFunction<? super Throwable, Try<T>> function) {
        Try<T> proxy = null;
        try {
            proxy = function.apply(e);
        } catch (Throwable e) {
            return new Failure<T>(e);
        }
        return proxy;
    }

    @Override
    public String toString() {
        return "Failure[" + e + "]";
    }

    public Throwable getException(){
        return e;
    }
}