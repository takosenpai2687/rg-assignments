package q7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import q6.Employee;

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/week2";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO t_employee (id, name, department) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getDepartment());
            pstmt.executeUpdate();
            System.out.println("Added: " + employee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM t_employee";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery(sql)) {

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String department = res.getString("department");
                Employee employee = new Employee(id, name, department);
                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Read - Get an employee by ID
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM t_employee WHERE id = ?";
        Employee resultEmployee = null;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String department = rs.getString("department");
                    resultEmployee = new Employee(id, name, department);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultEmployee;
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM t_employee WHERE department = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Employee employee = new Employee(id, name, department);
                    employees.add(employee);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // Update: return true if employee is updated, false if not found
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE t_employee SET name = ?, department = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getDepartment());
            pstmt.setInt(3, employee.getId());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated: " + employee);
                return true;
            } else {
                System.out.println("Employee not found with ID: " + employee.getId());
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete - Remove an employee by ID
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM t_employee WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deleted employee with ID: " + id);
                return true;
            } else {
                System.out.println("Employee not found with ID: " + id);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Create
        Employee emp1 = new Employee(1, "Alice", "Engineering");
        Employee emp2 = new Employee(2, "Bob", "Marketing");
        Employee emp3 = new Employee(3, "Charlie", "Finance");

        employeeJDBC.addEmployee(emp1);
        employeeJDBC.addEmployee(emp2);
        employeeJDBC.addEmployee(emp3);

        // Read
        System.out.println("\nAll Employees:");
        employeeJDBC.getAllEmployees().forEach(System.out::println);

        System.out.println("\nEmployee with ID 2:");
        System.out.println(employeeJDBC.getEmployeeById(2));

        // Update
        emp2.setName("Bobby");
        emp2.setDepartment("Sales");
        employeeJDBC.updateEmployee(emp2);

        // Delete
        employeeJDBC.deleteEmployee(3);

        // Final state
        System.out.println("\nAll Employees after updates:");
        employeeJDBC.getAllEmployees().forEach(System.out::println);
    }
}