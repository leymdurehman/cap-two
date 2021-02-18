package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.Project_EmployeeDAO;

public class JDBCProject_employeeDAO implements Project_EmployeeDAO{

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	


//	@Override
//	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
//		
//		
//		String sql = "DELETE FROM project_employee WHERE employee_id = ? AND project_id = ?";
//		jdbcTemplate.update(sql, employeeId, projectId);
//		
//	}
//	
	
	
	
//	private Employee mapRowToEmployee(SqlRowSet rows) {
//		Employee employee = new Employee();
//		
//		employee.setId(rows.getLong("employee_id"));
//		employee.setFirstName(rows.getString("first_name") );
//		employee.setLastName(rows.getString("last_name"));
//		employee.setBirthDay(rows.getDate("birthDay").toLocalDate());
//		employee.setGender(rows.getString("gender").charAt(0));
//		employee.setHireDate(rows.getDate("hire_date").toLocalDate());
//		
//		return employee;
//	
	

}
