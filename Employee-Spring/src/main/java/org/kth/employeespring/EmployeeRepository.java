package org.kth.employeespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
    private final String DELETE_SQL = "DELETE FROM employees WHERE id = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM employees";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM employees WHERE id = ?";

    public void save(Employee employee) {
        jdbcTemplate.update(INSERT_SQL, employee.getId(), employee.getName(), employee.getDepartment());
    }

    public void update(Employee employee) {
        jdbcTemplate.update(UPDATE_SQL, employee.getName(), employee.getDepartment(), employee.getId());
    }

    public void deleteById(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new EmployeeRowMapper());
    }

    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new EmployeeRowMapper(), id));
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setDepartment(rs.getString("department"));
            return employee;
        }
    }
}
