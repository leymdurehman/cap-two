package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		
	
		String sql = "SELECT department_id, name FROM department";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Department> departments = new ArrayList<Department>();
		while (rows.next()) {
			departments.add( mapRowToDepartment(rows) );
		}

		return departments;	//RETURNING ALL DEPARTMENTS
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		
		// SQL statement to search department for a particular name 
		String sqlSearch = "SELECT name FROM department WHERE name = ?";
	
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlSearch, nameSearch);
		
		List<Department> departmentSearched = new ArrayList<Department>();
		while (rows.next()) {
			departmentSearched.add( mapRowToDepartment(rows) );
		}
		
		return departmentSearched;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		String sql = "INSERT INTO department (department_id, name) VALUES (DEFAULT, ?) RETURNING department_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, updatedDepartment.getName());
		if (rows.next()) {
			
			updatedDepartment.setId(rows.getLong("department_id"));
			
		}
	}

	
	@Override
	public Department createDepartment(Department newDepartment) {
		
		String sql = "INSERT INTO department (department_id, name) VALUES (DEFAULT, ?) RETURNING department_id";
		SqlRowSet resultsOfCreate = jdbcTemplate.queryForRowSet(sql, newDepartment.getName());
		resultsOfCreate.next();
		newDepartment.setId(resultsOfCreate.getLong("department_id"));
		
		
		return newDepartment;
	}

	@Override
	public Department getDepartmentById(Long id) {
		
		
		String sql = "SELECT department_id, name FROM department " + "WHERE department_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
	
		
		if (result.next()) {
			return	mapRowToDepartment(result);
		
		}
		
		return null;
	}

	private Department mapRowToDepartment(SqlRowSet row) {
		Department department = new Department();
		department.setId(row.getLong("department_id"));
		department.setName( row.getString("name") );
		
		return department;
	}
	
	
	
	
	
}
