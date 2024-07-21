interface IVehicle {
    // Abstract method
    void start();

    // Default method
    default void stop() {
        System.out.println("Vehicle is stopping");
    }

    // Static method
    static void checkStatus() {
        System.out.println("Checking vehicle status");
    }
}