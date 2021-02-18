package com.techelevator.excelsior.model;

import java.sql.Date;
import java.util.List;

public interface ReservationDAO {

	
	
	public List <Reservation> getAllReservations();
	
	
	public Reservation getReservationById(long resID);
	
	
	public Space getReservationBySpaceID (long spaceID);
	
	
	public List<Reservation> getReservationByDateRange(Date startDate, Date endDate);
	
	
	public Reservation createReservation(Reservation newReservation); 
	
	
	public void saveReservation(Reservation savedReservation); 
	
	
	public void deleteReservation(Reservation reservation);
	
	
	
}
