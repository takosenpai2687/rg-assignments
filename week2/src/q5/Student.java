package q5;

public class Student {
    private String name;
    private Long id;

    public Student(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }

    public static void main(String[] args) {
        Student student = new Student("John", 1L);
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getId());
    }
}
