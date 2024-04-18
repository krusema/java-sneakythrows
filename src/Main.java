import java.util.function.Supplier;


public class Main {
    public static void main(String[] args) {
        Supplier<Integer> converted = SneakyThrows.convert(Main::example);
        converted.get();
    }


    static int example() throws Exception {
        throw new Exception("Checked exception");
    }
}