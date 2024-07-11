package q4;

public class SingletonExample {
    private static SingletonExample instance;

    private SingletonExample() {
        // Private constructor to prevent instantiation
    }

    public static synchronized SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }

    public void foo() {
        System.out.println("foo");
    }

    public static void main(String[] args) {
        SingletonExample singleInstance = SingletonExample.getInstance();
        singleInstance.foo();
    }
}