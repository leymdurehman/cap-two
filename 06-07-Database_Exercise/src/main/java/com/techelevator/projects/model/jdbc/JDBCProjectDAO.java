package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {

		 
		String sql = "SELECT * FROM project\n" + 
				"WHERE to_date IS NOT NULL AND from_date IS NOT NULL AND to_date - current_date >= 0";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Project> projects = new ArrayList<Project>();
		while (rows.next()) {
			projects.add( mapRowToProject(rows) );
		}

		return projects;	//RETURNING ALL projects
		

	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		
		
		String sql = "DELETE FROM project_employee WHERE employee_id = ? AND project_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, employeeId, projectId);
		
	}

	
	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		
		String sql = "INSERT INTO project_employee(project_id, employee_id) VALUES (?,?)";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, projectId, employeeId);
		
	}

	
	private Project mapRowToProject(SqlRowSet row) {
		Project project = new Project();
		project.setId(row.getLong("project_id"));
		project.setName( row.getString("name") );
	//	project.setStartDate(row.getDate("from_date"));
	//	project.setEndDate(row.getDate("to_date"));
		return project;
	}
	







}
