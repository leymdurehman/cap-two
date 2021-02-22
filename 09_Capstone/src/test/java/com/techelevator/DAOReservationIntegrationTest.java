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
	
		String result = "INSERT INTO space (id, venue_id, name, open_from, open_to, daily_rate, max_occupancy) VALUES (111, 11, 'Library', 1, 12, '$140.00', 130)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(result);
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
		testSpaces.add(testSpace);
		//List<Space> spaceFromDatabase = new ArrayList<Space>();
		
		
		
		List<Space> newSpace = new ArrayList<Space>(); 
		
		newSpace = reservationDAO.getAvailableSpacesByByDateRange("2021-01-01", "2021-12-30", 130, 1, 12, 11);
		
		Assert.assertNotNull(newSpace);
		Assert.assertEquals(testSpaces.size(), newSpace.size());
		
	}
	@Test
    public void test_the_end_date() {
        
        String newStartDate = "2000-01-01";
        int numOfDays = 10;
        String expectedStartDate = "2000-01-11";
        
        String actualDate = reservationDAO.getEndDate(newStartDate, numOfDays);
        
        Assert.assertEquals(actualDate, expectedStartDate);
    }
    
    
    @Test
    public void test_pull_month_from_date() {
        
        String newStartDate = "2000-01-01";
        int expectedMonth = 01;
        
        int actualMonth = reservationDAO.getStartMonthNum(newStartDate) ;
        //String actualMonth = jdbcReservationDao.getStartMonthNum(newStartDate);
        
        Assert.assertEquals(actualMonth, expectedMonth);
    }
     
    
    @Test
    public void test_pull_month_from_endDate() {
        
        String newEndDate = "2000-01-01";
        int expectedMonth = 01;
        
        int actualMonth = reservationDAO.getStartMonthNum(newEndDate) ;
        //String actualMonth = jdbcReservationDao.getStartMonthNum(newStartDate);
        
        Assert.assertEquals(actualMonth, expectedMonth);
    }
    
    
    //NullPointer? Throws an Exception as below
    
    /*public abstract class ReflectiveCallable {
        public Object run() throws Throwable {
            try {
                return runReflectiveCall();
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }*/
    
    @Test
    public void test_wrong_format_pull_month_from_endDate() {
        
        String newEndDate = "01";
        //int expectedMonth = 01;
        
        int actualMonth = reservationDAO.getStartMonthNum("01-01-2021") ;
        //String actualMonth = jdbcReservationDao.getStartMonthNum(newStartDate);
        
        //Assert.assertNull(actualMonth);
        Assert.assertNotEquals(newEndDate, actualMonth);
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
	//	BigDecimal rate = new BigDecimal("999999.00");
		Space testSpace = new Space();
		
		testSpace.setAccessible(true);
		testSpace.setDailyRate("$999999");
		testSpace.setId(FAKE_SPACE_ID);
		testSpace.setVenueID(1);
		testSpace.setName("testName");
		testSpace.setMaxOccupancy(200);
		testSpace.setOpenFrom(1);
		testSpace.setOpenTo(12);
		
		return testSpace;
	}
	
	
	
	
	
	
	
	
	
	
	
}
