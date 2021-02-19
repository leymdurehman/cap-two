package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;
import com.techelevator.excelsior.model.Space;

public class JDBCReservationDAO implements ReservationDAO{
	
	
private JdbcTemplate jdbcTemplate;	
	
	
	
	public JDBCReservationDAO(DataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		
	}
	
	@Override
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
	public Reservation createReservation(int spaceID, int numOfAttendees, String startDate, String endDate, String reservedFor) {
		
		String sql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for ) \n" + 
				"VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id";
		SqlRowSet resultsOfCreate = jdbcTemplate.queryForRowSet(sql, spaceID, numOfAttendees, startDate, endDate, reservedFor );
		Reservation confirmedReservation = mapRowToReservation(resultsOfCreate);
		return confirmedReservation;

		

	}

	@Override
	public void saveReservation(Reservation savedReservation) {
		// TODO Auto-generated method stub
		
		
		
		
		
	}

	@Override
	public Reservation getReservationById(long resID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	
	public Reservation mapRowToReservation(SqlRowSet row) {
	
		Reservation newRes = new Reservation();
		
		newRes.setReservationID(row.getLong("reservation_id"));
		newRes.setSpaceID(row.getLong("space_id"));
		newRes.setEndDate(row.getDate("start_date"));
		newRes.setEndDate(row.getDate("end_date"));
		newRes.setNumberOfattendees(row.getInt("number_of_attendees"));
		newRes.setReservedFor(row.getString("reserved_for"));
		
		
		
		
		
		
		return newRes;
		
		
		
		
	}

	@Override
	public Reservation getReservationBy(long resID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation createReservation(Reservation newReservation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
