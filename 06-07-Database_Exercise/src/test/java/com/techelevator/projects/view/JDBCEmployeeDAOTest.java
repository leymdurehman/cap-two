package com.techelevator.projects.view;

import com.techelevator.projects.model.Employee;
import java.time.LocalDate;
import java.util.Date;
public class JDBCEmployeeDAOTest {
	
	
	
	private Employee testerEmployee() {
		 LocalDate date = LocalDate.now();
		Employee testEmployee = new Employee();
		testEmployee.setFirstName("TestFirstName");
		testEmployee.setLastName("TestLastName");
		testEmployee.setGender("F".charAt(0));
		testEmployee.setBirthDay(date);
		testEmployee.setHireDate(date);
		return testEmployee;
	}
	
	
	
	
	
	
	

}
