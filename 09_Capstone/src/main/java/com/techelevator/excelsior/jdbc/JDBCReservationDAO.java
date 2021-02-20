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




	public List<Space> getAvailableSpacesByByDateRange(String startDate, String endDate, int numOfAttendees, int startMonth, int endMonth, int venueID) {
		// for get month - since date format is standardized, just return the substring
		List<Space> availableSpaces = new ArrayList<Space>();
		startMonth = getStartMonthNum(startDate);
		endMonth = getEndMonthNum(endDate);
		
		
		
		String sql = "SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, space.daily_rate, space.max_occupancy \n" + 
				"FROM space\n" + 
				"JOIN reservation ON space.id = reservation.space_id\n" + 
				"WHERE (NOT (start_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)))\n" + 
				"AND (NOT (end_date BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)))\n" + 
				"AND max_occupancy <= ? AND (open_from BETWEEN ? AND ?) AND (open_to BETWEEN ? AND ?) AND space.venue_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, startDate, endDate, startDate, endDate, numOfAttendees, startMonth,  endMonth, startMonth, endMonth, venueID );
		
	
		
		while(rows.next()) {
			
			availableSpaces.add(mapRowToSpace(rows));
			
		}
		
		return availableSpaces;
		
	
	}

	public Reservation createReservation(int spaceID, int numOfAttendees, String startDate, String endDate, String reservedFor) {
		
		String sql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for ) \n" + 
				"VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id";
		SqlRowSet resultsOfCreate = jdbcTemplate.queryForRowSet(sql, spaceID, numOfAttendees, startDate, endDate, reservedFor );
		Reservation confirmedReservation = mapRowToReservation(resultsOfCreate);
		return confirmedReservation;
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

	
	
	
	public int getStartMonthNum(String startDate){



        String startMonth = startDate.substring(5,7);

        int startMonthNum = Integer.parseInt(startMonth);

        return  startMonthNum;

    }
	
	
	
	public int getEndMonthNum(String endDate){



        String endMonth = endDate.substring(5,7);

        int endMonthNum = Integer.parseInt(endMonth);

        return  endMonthNum;

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
		space.setDailyRate(row.getBigDecimal("daily_rate"));
		space.setId(row.getInt("id"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));
		space.setName(row.getString("name"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setVenueID(row.getInt("venue_id"));
		
		return space;
	}
	
	
	
}
