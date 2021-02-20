package com.techelevator;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.excelsior.jdbc.JDBCSpaceDAO;
import com.techelevator.excelsior.model.Space;

public class DAOSpaceIntegrationTest extends DAOIntegrationTest {

	private JDBCSpaceDAO jdbcSpaceDao;
	private JdbcTemplate jdbcTemplate;
	// store venue_id;
	private static final long FAKE_VENUE_ID = 888888;
	
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
		jdbcSpaceDao = new JDBCSpaceDAO(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		// set dummy for a venue 
		
		String result = "INSERT INTO venue (id, name, city_id, description) VALUES (?, 'JWs Oasis', 1, 'Yo this capstone is lit.')";
		jdbcTemplate.update(result, FAKE_VENUE_ID);
		
//		String reservationInput = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for) VALUES (111111, 999999, 10, '2020-01-01', '2020-01-15', 'Kriewall Family')";
//		jdbcTemplate.update(reservationInput);
//		
	}
	


	
	@Test
	public void get_month_from_user_test() {
		
		String testDate = "01-01-1992";
		//getMonth(testDate);
		// Debugger throws null after line 31. Why?
		
		Assert.assertEquals(testDate, "01");
	}
	
	@Test
	public void spaces_at_user_selected_venue_id_test() {
		
		save(getSpace());
	
		List<Space> venueSpaces = new ArrayList<Space>();
		
		String sql = "SELECT * FROM space WHERE venue_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, 888888);
		
		
		while(result.next()) {
			venueSpaces.add(jdbcSpaceDao.mapRowToSpace(result));
		}	
		
		Assert.assertNotNull(venueSpaces);
		Assert.assertEquals(getSpace().getVenueID(), venueSpaces.get(0).getVenueID());
		
	}

	
	@Test 
	public void get_end_date_test(){
		int numberNumberOfDays = 10;
		
		LocalDate startDate = LocalDate.of(2010, 01, 28);
		LocalDate expectedDate = LocalDate.of(2010, 02, 07);
		
		LocalDate actualDate = jdbcSpaceDao.getEndDate(startDate, numberNumberOfDays);
		Assert.assertEquals(expectedDate, actualDate);
	}
	
	@Test
	public void get_available_spaces_test() {
		LocalDate desiredCheckInDate = LocalDate.of(2022, 8, 8);
		LocalDate checkOutDate = LocalDate.of(2022, 8, 12);
		
		List<Space> availableRooms = jdbcSpaceDao.getSpacesByClient(desiredCheckInDate, checkOutDate);
		
		Assert.assertNotNull(availableRooms);
	}
	
	private Space getSpace() {
		//BigDecimal rate = new BigDecimal("$999999.00");
		Space testSpace = new Space();
		
		testSpace.setAccessible(true);
		//testSpace.setDailyRate(rate);
		testSpace.setId(999999);
		testSpace.setVenueID(888888);
		testSpace.setName("testName");
		testSpace.setMaxOccupancy(50);
		testSpace.setOpenFrom(1);
		testSpace.setOpenTo(12);
		
		return testSpace;
	}
	
	public void save(Space savedTestSpace) {
		// Get the next Sequence Id
		// Set the contact object Id with that sequence #
		// use the sequence number as the contactId in the insert
		String sql = "INSERT INTO space (id, venue_id, name, is_accessible, open_from, open_to, daily_rate, max_occupancy) VALUES (?, ?, ?, ?, ?, ?, ?::Numeric, ?)";
		
		savedTestSpace = getSpace();
		// Because we are using RETURNING must use queryForRowSet(), WITHOUT RETURNING would use update()
		jdbcTemplate.update(sql, savedTestSpace.getId(), savedTestSpace.getVenueID(), 
		savedTestSpace.getName(), savedTestSpace.isAccessible(), savedTestSpace.getOpenFrom(), 
		savedTestSpace.getOpenTo(), savedTestSpace.getDailyRate(), savedTestSpace.getMaxOccupancy());
//		if ( rows.next() ) {
//			//savedTestSpace.setId(rows.getLong("id"));
//			savedTestSpace.setAccessible(rows.getBoolean("is_accessible"));
//			//savedTestSpace.setDailyRate(rows.getBigDecimal("daily_rate"));
//			savedTestSpace.setMaxOccupancy(rows.getInt("max_occupancy"));
//			savedTestSpace.setName(rows.getString("name"));
//			savedTestSpace.setOpenFrom(rows.getInt("open_from"));
//			savedTestSpace.setOpenTo(rows.getInt("open_to"));
//			savedTestSpace.setVenueID(rows.getLong("venue_id"));
//		}
		
	}
	
}
