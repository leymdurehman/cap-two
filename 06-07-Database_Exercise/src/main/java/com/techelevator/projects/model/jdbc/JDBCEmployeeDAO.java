package com.techelevator.projects.model.jdbc;

import java.time.LocalDate;

import java.util.Date
import java.sql.Date

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;
	

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		String sql = "SELECT first_name, last_name FROM employee WHERE first_name NOT NULL OR last_name NOT NULL";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Employee>EmployeeList = new ArrayList<Employee>();
		while (rows.next()) {
			EmployeeList.add(mapRowToEmployee(rows));
		}
		
		return EmployeeList;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		String sql = "SELECT first_name, last_name FROM employee WHERE first_name like %" + "?" + 
				"% AND last_name like %" + "?" + "%";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		List<Employee> EmployeeList = new ArrayList<Employee>();
		
		while(rows.next()) {
			EmployeeList.add(mapRowToEmployee(rows));
		}
		
		return EmployeeList;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		String sql = "SELECT first_name, last_name FROM employee WHERE department_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		List<Employee> EmployeeByDepartment = new ArrayList<Employee>();
		
		while(rows.next()) {
			EmployeeByDepartment.add(rows)
		}
		
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		return new ArrayList<>();
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		return new ArrayList<>();
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
	}
	
	
	
	private Employee mapRowToEmployee(SqlRowSet rows) {
		Employee employee = new Employee();
		
		// String gender = employee.setGender("gender");
		
		employee.setId(rows.getLong("employee_id"));
		employee.setFirstName(rows.getString("first_name") );
		employee.setLastName(rows.getString("last_name"));
		employee.setBirthDay(rows.getDate("birthDay"));
		employee.setGender(rows.getGender("gender"));
		employee.setHireDate(rows.getDate("hire_date"));
		
		return employee;
	}

}
