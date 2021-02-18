package com.techelevator.projects.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;
import com.techelevator.projects.model.jdbc.JDBCDepartmentDAO;

public class JDBCDepartmentDAOTest {
	
	
	private static SingleConnectionDataSource dataSource;
	private DepartmentDAO departmentDao;
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
		departmentDao = new JDBCDepartmentDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@After
	public void rollbackTransaction() throws SQLException {
		dataSource.getConnection().rollback();
	}



	
	@Test
	public void testing_select_with_single_result() {
		/*
		 * ARRANGE
		 */
		// Create the Dummy data and store it
		Department createdTestDepartment = getTestDepartment();
		// Insert it into the table and get the id
		String sql = "INSERT INTO department (department_id, name) VALUES (DEFAULT, ?) RETURNING department_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, createdTestDepartment.getName());
		result.next();
		createdTestDepartment.setId(result.getLong("department_id"));
		
		Department departmentFromDatabase = departmentDao.getDepartmentById(createdTestDepartment.getId());
		
		Assert.assertNotNull( departmentFromDatabase );
		Assert.assertEquals(createdTestDepartment, departmentFromDatabase);
	}
	
	@Test
	public void save_department_test() {
		Department savedDepartment = getTestDepartment();

		
		departmentDao.saveDepartment(savedDepartment);
		
		Department departmentFromDatabase = departmentDao.getDepartmentById(savedDepartment.getId());
		
		Assert.assertTrue(savedDepartment.getId() > 0);
		
		
		Assert.assertEquals(departmentFromDatabase, savedDepartment);
	}


	@Test
	public void create_department_test() {
		
		
		Department newDepartment = getTestDepartment();
		
		departmentDao.createDepartment(newDepartment);
		
		Department createdDepartmentFromDatabase = 	departmentDao.getDepartmentById(newDepartment.getId());
		
		Assert.assertNotNull(createdDepartmentFromDatabase);
		
		Assert.assertEquals(createdDepartmentFromDatabase, newDepartment);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	private Department getTestDepartment() {
		Department createdTestDepartment = new Department();
		
		// dont need department_id here because our methods as we call them are generating the ID or we are putting "Default"
		
		createdTestDepartment.setName("TestName");
		return createdTestDepartment;
	}
	

}

