package by.tms.eshopspringboot.utils;

import java.util.function.Consumer;

public interface ThrowingConsumer<T, E extends Exception> {
    void consume(T t) throws E;

    static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
        return t -> {
            try {
                throwingConsumer.consume(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
