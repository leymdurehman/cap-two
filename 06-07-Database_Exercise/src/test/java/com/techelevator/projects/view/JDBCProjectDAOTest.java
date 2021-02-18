package com.techelevator.projects.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.jdbc.JDBCProjectDAO;

import junit.framework.Assert;

public class JDBCProjectDAOTest {
	
	private static final String TEST_COUNTRY = "XYZ";


	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private JDBCProjectDAO dao;
	private EmployeeDAO employeeDao;
	private JdbcTemplate jdbcTemplate;
	

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertProject = "INSERT INTO projects (project_id, name, from_date, to-date) "
				+ "VALUES (DEFAULT, sqlInsertProject, 2000-01-01, 2001-01-01)";
		jdbcTemplate.update(sqlInsertProject);
		dao = new JDBCProjectDAO(dataSource);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
//	@Test
//	public void returns_cities_by_country_code() {
//		City theCity = getCity("SQL Station", "South Dakota", TEST_COUNTRY, 65535);
//
//		dao.save(theCity);
//		List<City> results = dao.findCityByCountryCode(TEST_COUNTRY);
//
//		assertNotNull(results);
//		assertEquals(1, results.size());
//		City savedCity = results.get(0);
//		assertCitiesAreEqual(theCity, savedCity);
//	}
	
	
	
	
	//Need to print out the list to doublecheck, although I know there are only 2: 
	//the created & 'Never ending project'. Realistically, should delete prior to ln 92
	@Test
	public void test_get_all_active_projects() {
		//test project: createdTestProject
		createTestProject();
		
		List<Project> results = dao.getAllActiveProjects();
		
		assertEquals(2, results.size());
		
	}
	
	
//	@Override
//	public void addEmployeeToProject(Long projectId, Long employeeId) {
//		
//		String sql = "INSERT INTO project_employee(project_id, employee_id) VALUES (?,?)";
//		
//		jdbcTemplate.update(sql, projectId, employeeId);
//		
//	}
	
	@Test
	public void test_add_employee_to_project() {
		
		testerEmployee(); //new employee 150
		createTestProject(); //new project 7
		
		dao.addEmployeeToProject((long)7, (long)150); //adding employee to project
		//dao.addEmployeeToProject(7, 150); //adding employee to project
		
		
		//getTestEmployeeByProject(); //instantiates employeeAddedToProject list
		//same as below, couldn't call it without an error
		
		//getTestEmployeeByProject()
		String sql = "SELECT employee_id FROM project_employee WHERE project_id = 7";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		//SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		List<Employee> employeeAddedToProject = new ArrayList<Employee>();
		
		while(rows.next()) {
			employeeAddedToProject.add(mapRowToEmployee(rows));
		}
		
		
		
		//List<Project> results = dao.getAllActiveProjects();
		
		assertEquals(1, employeeAddedToProject.size());
		
	}
	
	
	
	
	
	
	
	@Test
	public void test_remove_employee_from_project() {
		//test project: createdTestProject
		testerEmployee();
		createTestProject();
		
		
		List<Project> results = dao.getAllActiveProjects();
		
		assertEquals(2, results.size());
		
	}
	
	
	
	
	
	

	private Project createTestProject() {
		Project createdTestProject = new Project();
		
		createdTestProject.setId((long) 7);
		createdTestProject.setName("TestProject");
		createdTestProject.setStartDate(LocalDate.parse("2000-01-01"));
		//createdTestProject.setEndDate(population);
		

	
		return createdTestProject;
	}
	
	private Employee testerEmployee() {
			
			LocalDate date = LocalDate.now();
			
			Employee testEmployee = new Employee();
			testEmployee.setFirstName("TestFirstName");
			testEmployee.setLastName("TestLastName");
			//testEmployee.setBirthDay(LocalDate.parse("1901-01-01"));
			testEmployee.setBirthDay(date);
			testEmployee.setGender("F".charAt(0));
			testEmployee.setId((long) 150);
			//testEmployee.setHireDate(LocalDate.parse("2020-01-01"));
			testEmployee.setHireDate(date);
			testEmployee.setDepartmentId((long) 1000000);
			
			return testEmployee;
		}
	
	
	//method to be able to able to call during test. to select test employee from test project
	private void getTestEmployeeByProject() {
		String sql = "SELECT employee_id FROM project_employee WHERE project_id = 7";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		//SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		List<Employee> employeeAddedToProject = new ArrayList<Employee>();
		
		while(rows.next()) {
			employeeAddedToProject.add(mapRowToEmployee(rows));
		}
	}
	
	private Employee mapRowToEmployee(SqlRowSet rows) {
		Employee employee = new Employee();
		
		employee.setId(rows.getLong("employee_id"));
		employee.setDepartmentId(rows.getLong("department_id"));
		employee.setFirstName(rows.getString("first_name") );
		employee.setLastName(rows.getString("last_name"));
		employee.setBirthDay(rows.getDate("birthDay").toLocalDate());
		employee.setGender(rows.getString("gender").charAt(0));
		employee.setHireDate(rows.getDate("hire_date").toLocalDate());
		
		
		return employee;
	}
	
}
