package com.techelevator.projects.model.jdbc;
import java.time.LocalDate;
import java.util.Date;
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
			EmployeeByDepartment.add(mapRowToEmployee(rows));
		}
		
		return EmployeeByDepartment;
	}
	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		
		List<Employee> EmployeesWithoutProjects = new ArrayList<Employee>();
<<<<<<< HEAD
		String sql = "SELECT first_name, last_name FROM employee\n" + 
=======
		String sql = "SELECT first_name, last_name FROM employee\n" +
>>>>>>> 0daf2be74d75e421c83253c5531d4f68ce7ac9f2
				"WHERE NOT EXISTS (SELECT employee_id FROM project_employee WHERE project_employee.employee_id = employee.employee_id)";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Employee> employeesWithProjects = new ArrayList<>();
<<<<<<< HEAD
		List<Employee> missingEmployee = new ArrayList<Employee>();

=======
		
>>>>>>> 0daf2be74d75e421c83253c5531d4f68ce7ac9f2
		while(rows.next()) {
			EmployeesWithoutProjects.add(mapRowToEmployee(rows));
		}
		
		return EmployeesWithoutProjects;
	}
	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		
		String sql = "SELECT first_name, last_name FROM employee JOIN project_employee ON employee.employee_id = project_employee.employee_id WHERE project_employee.project_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, projectId);
	
		List <Employee>employeesByIdOnProjects = new ArrayList<Employee>();
		while (rows.next()) {
			employeesByIdOnProjects.add(mapRowToEmployee(rows));
		}
		return employeesByIdOnProjects;
	}
	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
<<<<<<< HEAD
		String sql = "UPDATE employee SET department_id = ?\n" + 
=======
		String sql = "UPDATE employee SET department_id = ?\n" +
>>>>>>> 0daf2be74d75e421c83253c5531d4f68ce7ac9f2
				"WHERE employee_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, departmentId, employeeId);
		
		List <Employee>changedDepartments = new ArrayList<Employee>();
		
		while (rows.next()) {
			changedDepartments.add(mapRowToEmployee(rows));
		}
	
		
	}
	
	
	
	private Employee mapRowToEmployee(SqlRowSet rows) {
		Employee employee = new Employee();
		
		// String gender = employee.setGender("gender");
		
		employee.setId(rows.getLong("employee_id"));
		employee.setFirstName(rows.getString("first_name") );
		employee.setLastName(rows.getString("last_name"));
	//	employee.setBirthDay(rows.getDate("birthDay"));
	//	employee.setGender(rows.getGender("gender"));
	//	employee.setHireDate(rows.getDate("hire_date"));
		
		return employee;
	}
}


