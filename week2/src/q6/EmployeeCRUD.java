package q6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeCRUD {

    private final List<Employee> employees;

    public EmployeeCRUD() {
        this.employees = new ArrayList<>();
        employees.addAll(List.of(new Employee(1, "John", "HR"), new Employee(2, "Jane", "IT"), new Employee(3, "Doe", "HR"), new Employee(4, "Smith", "IT"), new Employee(5, "Alex", "HR"), new Employee(6, "Alice", "IT")));
    }

    // Deep copy of Employee object in order to avoid modification of original object
    private Employee deepCopy(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new Employee(employee.getId(), employee.getName(), employee.getDepartment());
    }

    public List<Employee> getAllEmployees() {
        return employees.stream().map(this::deepCopy).toList();
    }

    public Employee getEmployeeById(int id) {
        final Employee employee = employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        return deepCopy(employee);
    }

    public List<Employee> getEmployeesByName(String name) {
        final List<Employee> employees = this.employees.stream().filter(e -> e.getName().equals(name)).toList();
        return employees.stream().map(this::deepCopy).toList();
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        final List<Employee> employees = this.employees.stream().filter(e -> e.getDepartment().equals(department)).toList();
        return employees.stream().map(this::deepCopy).toList();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(deepCopy(employee));
    }

    public void updateEmployee(Employee employee) {
        final Optional<Employee> optionalEmployee = this.employees.stream().filter(e -> e.getId() == employee.getId()).findFirst();
        if (optionalEmployee.isPresent()) {
            final Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setDepartment(employee.getDepartment());
        }
    }

    public void deleteEmployee(int id) {
        this.employees.removeIf(e -> e.getId() == id);
    }

    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();
        System.out.println("All employees: " + employeeCRUD.getAllEmployees());
        System.out.println("Employee with id 1: " + employeeCRUD.getEmployeeById(1));
        System.out.println("Employees with name John: " + employeeCRUD.getEmployeesByName("John"));
        System.out.println("Employees with department HR: " + employeeCRUD.getEmployeesByDepartment("HR"));
        employeeCRUD.addEmployee(new Employee(7, "Bob", "IT"));
        System.out.println("All employees after adding Bob: " + employeeCRUD.getAllEmployees());
        employeeCRUD.updateEmployee(new Employee(7, "Bob", "HR"));
        System.out.println("All employees after updating Bob's department: " + employeeCRUD.getAllEmployees());
        employeeCRUD.deleteEmployee(7);
        System.out.println("All employees after deleting Bob: " + employeeCRUD.getAllEmployees());
    }
}
