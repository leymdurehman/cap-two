package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;
import com.techelevator.excelsior.model.Space;

public class JDBCReservationDAO implements ReservationDAO {
	
	
private JdbcTemplate jdbcTemplate;	
	
	
	
	public JDBCReservationDAO(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		
	}
	
	public List<Reservation> getAllReservations() {

		String sql = "SELECT * FROM reservation";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		List<Reservation> reservations = new ArrayList <Reservation>();
		
		while (rows.next()) {
			
			reservations.add(mapRowToReservation(rows));
			
			
		}
		
		return reservations;
	}



	@Override
	public List<Space> getAvailableSpacesByByDateRange(String startDate, String endDate, int numOfAttendees, int startMonth, int endMonth, long venueID) {
		// for get month - since date format is standardized, just return the substring
		List<Space> availableSpaces = new ArrayList<Space>();
		startMonth = getStartMonthNum(startDate);
		endMonth = getEndMonthNum(endDate);
		
		
		
		String sql = "SELECT space.id, space.name, CAST(daily_rate AS varchar), space.max_occupancy FROM space WHERE space.id NOT IN (SELECT space_id FROM reservation where (start_date BETWEEN CAST( ? AS DATE) AND CAST( ? AS DATE)) AND (end_date BETWEEN CAST(? AS DATE) AND CAST( ? AS DATE))) AND max_occupancy >= ? AND ((open_from IS null) "
				+ "OR ((? BETWEEN open_from AND open_to) AND ((? BETWEEN open_from AND open_to)))) AND venue_id = ?";
				
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, startDate, endDate, startDate, endDate, numOfAttendees, startMonth,  endMonth, venueID );
		
	
		
		while(rows.next()) {
			
			availableSpaces.add(mapRowToAvailableSpace(rows));
			
		}
		
		return availableSpaces;
		
	
	}
	
	@Override
	public void createReservation(Reservation reservation, int spaceID, int numOfAttendees, String startDate, String endDate, String reservedFor) {
		
		String sql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for ) VALUES (DEFAULT, ?, ?, CAST(? AS DATE), CAST(? AS DATE), ?)";
		jdbcTemplate.update(sql, spaceID, numOfAttendees, startDate, endDate, reservedFor );

	}	

	
	
	

	
	public Reservation getReservationsById(long resId) 
	{
		
		String sqlFindReservationName = "select * from reservation where reservation_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindReservationName, resId);
		
			Reservation reservation = mapRowToReservation(result);
		
		
		return reservation;
	}

	public void deleteReservation(String reservedFor) {
		String sql = "DELETE FROM reservation WHERE reserved_for = ?";
		jdbcTemplate.update(sql, reservedFor);
		
	}

	
	// three methods below - can make unit tests
	
	//below- test 
	@Override
	public String getEndDate(String startDate, int numOfDays) {
		
        LocalDate startDateAsDate = LocalDate.parse(startDate);
        LocalDate endDateAsDate = startDateAsDate.plusDays(numOfDays);
        String endDate = endDateAsDate.toString();
        return endDate;
    }
	
	
	
	
	// (below) - perhaps test when someone passes something too short for the substring length 7 (like 2021 instead of full date)
	@Override
	public int getStartMonthNum(String startDate){
    String startMonth = startDate.substring(5,7);

        int startMonthNum = Integer.parseInt(startMonth);

        return  startMonthNum;

    }
	
	
	@Override
	public int getEndMonthNum(String endDate){

        String endMonth = endDate.substring(5,7);

        int endMonthNum = Integer.parseInt(endMonth);

        return  endMonthNum;

    }
	
	
	
	
	public Space mapRowToAvailableSpace(SqlRowSet row) {
		Space space = new Space();
		space.setId(row.getInt("id"));
		space.setName(row.getString("name"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setDailyRate(row.getString("daily_rate"));
	
		return space;
				
	}

	
	public Reservation mapRowToReservation(SqlRowSet row) {
	
		Reservation newRes = new Reservation();
		
		newRes.setReservationID(row.getLong("reservation_id"));
		newRes.setSpaceID(row.getLong("space_id"));
		newRes.setEndDate(row.getString("start_date"));
		newRes.setEndDate(row.getString("end_date"));
		newRes.setNumberOfattendees(row.getInt("number_of_attendees"));
		newRes.setReservedFor(row.getString("reserved_for"));
		
		return newRes;
	
	}

	
	public Space mapRowToSpace(SqlRowSet row) {
		Space space = new Space();
		//space.setAccessible(row.getBoolean("is_accessible"));
		space.setDailyRate(row.getString("daily_rate"));
		space.setId(row.getInt("id"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setName(row.getString("name"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setVenueID(row.getInt("venue_id"));
		
		return space;
	}

	@Override
	public Reservation getReservationBy(long resID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Space getReservationBySpaceID(long spaceID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationByDateRange(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation(Reservation newReservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReservation(Reservation savedReservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createReservation(int spaceID, int maxOccupancy, String startDate, String EndDate, String reservedFor) {
		// TODO Auto-generated method stub
		
	}


	
	
}
