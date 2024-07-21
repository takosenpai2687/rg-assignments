import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello, World!");
        System.out.println(optional.get());

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.orElse("Hello, empty!"));
    }
}
