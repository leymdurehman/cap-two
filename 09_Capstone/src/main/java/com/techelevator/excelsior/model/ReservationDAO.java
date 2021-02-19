package com.techelevator.excelsior.model;

import java.sql.Date;
import java.util.List;

public interface ReservationDAO {

	
	
	public List <Reservation> getAllReservations();
	
	
	public Reservation getReservationBy(long resID);
	
	
	public Space getReservationBySpaceID (long spaceID);
	
	
	public List<Reservation> getReservationByDateRange(Date startDate, Date endDate);
	
	
	public Reservation createReservation(Reservation newReservation); 
	
	
	public void saveReservation(Reservation savedReservation); 
	
	
	public void deleteReservation(Reservation reservation);


	Reservation getReservationById(long resID);


	public Reservation createReservation(int spaceID, int maxOccupancy, String startDate, String EndDate, String reservedFor);
	
	
	
}
