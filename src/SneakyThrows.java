import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class SneakyThrows {

    private static Method creator;

    static {
        try {
            creator = new CL().getCheatClass().getMethod("toSupplier", Callable.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Supplier<T> convert(Callable<T> callable) {
        try {
            return (Supplier<T>) creator.invoke(null, callable);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static class CL extends ClassLoader {

        String asB64 =
                "yv66vgAAAD0AJAoAAgADBwAEDAAFAAYBABBqYXZhL2xhbmcvT2JqZWN0AQAGPGluaXQ+AQADKClWCQAIAAkHAAoMAAsADAEAEFNuZWFreVRocm93c0ltcGwBAAhjYWxsYWJsZQEAH0xqYXZhL3V0aWwvY29uY3VycmVudC9DYWxsYWJsZTsKAAgADgwABQAPAQAiKExqYXZhL3V0aWwvY29uY3VycmVudC9DYWxsYWJsZTspVgsAEQASBwATDAAUABUBAB1qYXZhL3V0aWwvY29uY3VycmVudC9DYWxsYWJsZQEABGNhbGwBABQoKUxqYXZhL2xhbmcvT2JqZWN0OwcAFwEAE2phdmEvbGFuZy9FeGNlcHRpb24HABkBABtqYXZhL3V0aWwvZnVuY3Rpb24vU3VwcGxpZXIBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQAKdG9TdXBwbGllcgEAPihMamF2YS91dGlsL2NvbmN1cnJlbnQvQ2FsbGFibGU7KUxqYXZhL3V0aWwvZnVuY3Rpb24vU3VwcGxpZXI7AQAJU2lnbmF0dXJlAQBePFQ6TGphdmEvbGFuZy9PYmplY3Q7PihMamF2YS91dGlsL2NvbmN1cnJlbnQvQ2FsbGFibGU8VFQ7PjspTGphdmEvdXRpbC9mdW5jdGlvbi9TdXBwbGllcjxUVDs+OwEAA2dldAEADVN0YWNrTWFwVGFibGUBAApTb3VyY2VGaWxlAQAVU25lYWt5VGhyb3dzSW1wbC5qYXZhACEACAACAAEAGAABAAAACwAMAAAAAwACAAUADwABABoAAAAqAAIAAgAAAAoqtwABKiu1AAexAAAAAQAbAAAADgADAAAACAAEAAkACQAKAAkAHAAdAAIAGgAAACEAAwABAAAACbsACFkqtwANsAAAAAEAGwAAAAYAAQAAAA4AHgAAAAIAHwABACAAFQABABoAAABBAAEAAgAAAA0qtAAHuQAQAQCwTCu/AAEAAAAJAAoAFgACABsAAAAOAAMAAAAUAAoAFQALABYAIQAAAAYAAUoHABYAAQAiAAAAAgAj";
        byte[] asBytes = Base64.getDecoder().decode(asB64);

        public Class<?> getCheatClass() {
            return defineClass("SneakyThrowsImpl", asBytes, 0, asBytes.length);
        }
    }
}
