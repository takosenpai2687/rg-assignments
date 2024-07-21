import java.time.LocalDate;
import java.time.LocalDateTime;

public class CurrentDateExample {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println(currentDateTime);
    }
}
