package com.techelevator.projects.view;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.util.Date;
import java.util.*;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;
import com.techelevator.projects.model.jdbc.JDBCEmployeeDAO;


public class JDBCEmployeeDAOTest {
	
	private static SingleConnectionDataSource dataSource;
	private EmployeeDAO employeeDao;
	private JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void createDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);	
	}
	
	@AfterClass
	public static void destroyDataSource() {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		employeeDao = new JDBCEmployeeDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@After
	public void rollbackTransaction() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void save_employee_test() {
		Employee employee = testerEmployee();
		
		String sql = "INSERT INTO employee (employee_id, department_id, first_name, last_name, birth_date, gender, hire_date) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql,employee.getId(), employee.getDepartmentId(), employee.getFirstName(), 
				employee.getLastName(), employee.getBirthDay(), employee.getGender(), employee.getHireDate());
		
		result.next();
		employee.setId(result.getLong("employee_id"));
		
		List<Employee> employeeFromDatabase = employeeDao.getEmployeesByDepartmentId(employee.getDepartmentId());
		Assert.assertNotNull(employeeFromDatabase);
		Assert.assertEquals(employeeFromDatabase, employee);
	}
	
	@Test
	public void test_get_all_Employees() {
		Employee testEmployee = testerEmployee();
		
		
		List<Employee> employeesFromDatabase = new ArrayList<Employee>();
		List<Employee> tester = new ArrayList<Employee>();
		
		// pull test values from rows (DB Visualizer)
		// set the values
		
		Employee javaEmployee = ;
		
		String sql = "SELECT * FROM employee WHERE department_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, testEmployee.getDepartmentId());
		
		rows.next();
		
		testEmployee.setDepartmentId(rows.getLong("department_id"));
		
		employeesFromDatabase = employeeDao.getEmployeesByDepartmentId(rows.getLong("department_id"));
		tester.add(testEmployee);
		
		Assert.assertNotNull(employeesFromDatabase);
		Assert.assertEquals(employeesFromDatabase, testEmployee);	
	}
	
	private Employee testerEmployee() {
		
		LocalDate date = LocalDate.now();
		
		Employee testEmployee = new Employee();
		testEmployee.setFirstName("TestFirstName");
		testEmployee.setLastName("TestLastName");
		//testEmployee.setBirthDay(LocalDate.parse("1901-01-01"));
		testEmployee.setBirthDay(date);
		testEmployee.setGender("F".charAt(0));
		//testEmployee.setHireDate(LocalDate.parse("2020-01-01"));
		testEmployee.setHireDate(date);
		testEmployee.setDepartmentId((long) 1000000);
		
		return testEmployee;
	}

}
