import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class SneakyThrowsImpl implements Supplier {

    Callable callable;

    private SneakyThrowsImpl(Callable callable) {
        this.callable = callable;
    }


    public static <T> Supplier<T> toSupplier(Callable<T> callable) {
        return new SneakyThrowsImpl(callable);
    }

    @Override
    public Object get() {
        try {
            return callable.call();
        } catch (Exception e) {
            return e;
        }
    }
}
