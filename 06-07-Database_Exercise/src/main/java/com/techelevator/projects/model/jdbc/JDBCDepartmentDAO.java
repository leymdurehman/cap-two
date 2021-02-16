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
		
	
		String sql = "SELECT name FROM department";
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
		String sqlUpdate = "UPDATE department SET department_id = ?, name = ? WHERE department_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlUpdate, updatedDepartment.getId(),updatedDepartment.getName(), updatedDepartment.getId());
		
	}

	
	@Override
	public Department createDepartment(Department newDepartment) {
		
		String sqlCreate = "INSERT INTO department (department_id, name) VALUES (DEFAULT, ?) RETURNING department_id";
		SqlRowSet resultsOfCreate = jdbcTemplate.queryForRowSet(sqlCreate, newDepartment);
		resultsOfCreate.next();
		newDepartment.setId(resultsOfCreate.getLong("id"));
		
		
		return newDepartment;
	}

	@Override
	public Department getDepartmentById(Long id) {
		
		Department department = null;
		
		String sqlSearchId = "SELECT department_id, name FROM department WHERE department_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSearchId, id);
		
		mapRowToDepartment(result);
		
		if (result.next()) {
		department = mapRowToDepartment(result);
		}
		
		
		
		return department;
	}

	private Department mapRowToDepartment(SqlRowSet row) {
		Department department = new Department();
		department.setId(row.getLong("department_id"));
		department.setName( row.getString("name") );
		
		return department;
	}
	
	
	
	
	
}
