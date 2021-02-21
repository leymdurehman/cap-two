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
	
	
	public void deleteReservation(String reservedFor);





	public void createReservation(int spaceID, int maxOccupancy, String startDate, String EndDate, String reservedFor);


	Reservation getReservationsById(long resId);

	public int getEndMonthNum(String endDate);
	
	public int getStartMonthNum(String startDate);
	public String getEndDate(String startDate, int numOfDays);
	
	public List<Space> getAvailableSpacesByByDateRange(String startDate, String endDate, int numOfAttendees, int startMonth, int endMonth, long venueID);


	void createReservation(Reservation reservation, int spaceID, int numOfAttendees, String startDate, String endDate,
			String reservedFor);


	
	
}
