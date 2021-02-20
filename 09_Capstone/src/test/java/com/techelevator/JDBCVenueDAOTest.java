package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Venue;

import junit.framework.Assert;

public class JDBCVenueDAOTest {
	
	
	private static SingleConnectionDataSource dataSource;

	private JDBCVenueDAO dao;
	private JdbcTemplate jdbcTemplate;
	private static final long FAKE_CITY_ID = 888888;
	


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

	
	@Before
	public void setup() {
		dao = new JDBCVenueDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
//		String sqlInsertVenue = "INSERT INTO category_venue (id, name, state_abbreviation) "
//				  + "VALUES (?, 'Christian', 'OH')";
//		String sqlInsertVenue = "INSERT INTO city (id, name, state_abbreviation) "
//			  + "VALUES (?, 'Christian', 'OH')";
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update(sqlInsertVenue, FAKE_CITY_ID);
		
		//dao = new JDBCVenueDAO(dataSource);
	}
	
//	@Before
//	public void setup() {
//		jdbcSpaceDao = new JDBCSpaceDAO(dataSource);
//		jdbcTemplate = new JdbcTemplate(dataSource);
//	}
	
	
	

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
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.update(sqlInsertVenue);
//		
//		Venue testVenue;
//		testVenue = makeNewVenue();
//		
		List<Venue> oldResults = new ArrayList<>();
		oldResults = dao.getAllVenues(); //old list prior to add
		//oldResults.add(save(makeNewVenue()));
		
		int sizeOfOldList = oldResults.size(); //size of old list
		
		//save(makeNewVenue()); //save to add new venue (+1)

		List<Venue> results = dao.getAllVenues(); //new list
		
		//Assert.assertEquals(sizeOfOldList, 1);
		Assert.assertEquals(sizeOfOldList, results.size());
		
	}
	
	
	
	private Venue makeNewVenue() {
		Venue testVenue = new Venue();
		testVenue.setId(50);
		testVenue.setName("The Fun Factory Tester");
		testVenue.setDescription("The test place to have fun at a factory");
		//testVenue.setCategory("SuperFun");
		testVenue.setCityID(4);
		
		return testVenue;
	}
	
	private Venue mapRowToVenue(SqlRowSet results) {
		Venue theVenue;
		theVenue = new Venue();
		theVenue.setId(results.getLong("id"));
		theVenue.setName(results.getString("name"));
		theVenue.setCityID(results.getLong("city_id"));
		theVenue.setDescription(results.getString("description"));
		//theVenue.setCategory(results.getString("category_name"));
		
		return theVenue;
	}
	
	
	private void save(Venue newVenue) {
		String sqlInsertVenue = "INSERT INTO venue(id, name, city_id, description) "
				+ "VALUES(DEFAULT, ?, ?, ?)";
		//newVenue.setId(getNextVenueId());
		jdbcTemplate.update(sqlInsertVenue, newVenue.getName(), newVenue.getCityID(), newVenue.getDescription());
	}
	
	
	private long getNextVenueId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_venue_id')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new venue");
		}
	}
	
	
	

}
