package com.techelevator.excelsior.jdbc;

import java.sql.Date;
import java.util.List;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;
import com.techelevator.excelsior.model.Space;

public class JDBCReservationDAO implements ReservationDAO{

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservationsById(long resID) {
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

}
