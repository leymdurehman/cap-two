package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.city.City;
import com.techelevator.city.JDBCCityDAO;
import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Venue;
import com.techelevator.projects.model.Project;

public class JDBCVenueDAOTest {
	
	
	private static SingleConnectionDataSource dataSource;

	private JDBCVenueDAO dao;
	

	/*
	 * Before any tests are run, this method initializes the datasource for testing.
	 */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connect
		 * ions returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	
	
	
	@Test
	public void get_venue_names() {
		
//		String sqlInsertVenue = "INSERT INTO venue (venue_id, venue_name, description, category) "
//				+ "VALUES (1000, 'FunFactory', 'The test place to have fun at a factory', 'SuperFun')";
//		
//		update(sqlInsertVenue);
//		
//		Venue testVenue;
//		testVenue = makeNewVenue();
//		
	
		makeNewVenue();
		
		List<Venue> results = dao.getAllVenues();
		
		assertEquals(2, results.size());
		
		
		
		
		
		
		
	}
	
	
	
	
	private Venue makeNewVenue() {
		Venue testVenue = new Venue();
		testVenue.setId(50);
		testVenue.setName("The Fun Factory Tester");
		testVenue.setDescription("The test place to have fun at a factory");
		testVenue.setCategory("SuperFun");
		
		return testVenue;
	}
	
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue theVenue;
		theVenue = new Venue();
		theVenue.setId(results.getLong("venue_id"));
		theVenue.setName(results.getString("venue_name"));
//		theVenue.setCityID(results.getLong("city"));
		theVenue.setDescription(results.getString("description"));
		theVenue.setCategory(results.getString("category_name"));
		
		return theVenue;
	}
	
	
	
	

}
