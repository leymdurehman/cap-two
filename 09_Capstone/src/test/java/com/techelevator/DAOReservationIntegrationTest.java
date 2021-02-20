package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.excelsior.jdbc.JDBCReservationDAO;
import com.techelevator.excelsior.jdbc.JDBCSpaceDAO;
import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.Space;
public class DAOReservationIntegrationTest extends DAOIntegrationTest{

	
	
	
	private JdbcTemplate jdbcTemplate;
	private JDBCReservationDAO reservationDAO;
	private static final long FAKE_SPACE_ID = 111;	
	
	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;

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
		 * The following line disables autocommit for connections returned by this
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

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	
	@Before
	public void setup() {
		;

		
		
	
		
		
		String result = "INSERT INTO space (id, venue_id, name, open_from, open_to, daily_rate, max_occupancy) VALUES (?, 1, 'Library', 1, 12, '$140.00', 130)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(result, FAKE_SPACE_ID);
		reservationDAO = new JDBCReservationDAO(dataSource);
	}
	
	@Test
	public void get_All_Reservations() {
		
		List<Reservation> results = reservationDAO.getAllReservations();
		
		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() >= 1);
		
		
	}
	
	@Test
	public void get_Available_Spaces_By_DateRange() {
	
		
		List<Space> testSpaces = new ArrayList<Space>();
		
		Space testSpace = getTestSpace();
		
		testSpaces.add(testSpace);
		
		//List<Space> spaceFromDatabase = new ArrayList<Space>();
		
		
		
		List<Space> newSpace = new ArrayList<Space>(); 
		
		newSpace = reservationDAO.getAvailableSpacesByByDateRange("2021-01-01", "2021-12-30", 130, 1, 12, 11);
		
		Assert.assertNotNull(newSpace);
		Assert.assertEquals(testSpaces.size(), newSpace.size());
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	private Reservation getTestReservation() {
		Reservation testRes = new Reservation();
		testRes.setReservationID(99);
		testRes.setSpaceID(992);
		testRes.setNumberOfattendees(130);
		testRes.setStartDate(("2022-01-23"));
		testRes.setEndDate(("2022-01-29"));
		testRes.setReservedFor("Ley A");
		return testRes;
	}
	
	private Space getTestSpace() {
		BigDecimal rate = new BigDecimal("999999.00");
		Space testSpace = new Space();
		
		testSpace.setAccessible(true);
		testSpace.setDailyRate(rate);
		testSpace.setId(FAKE_SPACE_ID);
		testSpace.setVenueID(1);
		testSpace.setName("testName");
		testSpace.setMaxOccupancy(200);
		testSpace.setOpenFrom(1);
		testSpace.setOpenTo(12);
		
		return testSpace;
	}
	
	
	
	
	
	
	
	
	
	
	
}
