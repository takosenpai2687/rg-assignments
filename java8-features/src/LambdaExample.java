import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Print all numbers with lambda
        Consumer<Integer> print = (Integer i) -> System.out.println(i);

        numbers.forEach(print);
    }
}
