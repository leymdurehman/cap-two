package com.techelevator;

import java.time.LocalDate;

import org.junit.*;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.Venue;

public class ExcelsiorUnitTest {
	
	private Venue testVenue;
	private Space testSpace;
	private Reservation testReservation;
	
	@Before
	public void setup(){
		Venue testVenue = new Venue();
		Space testSpace = new Space();
		Reservation testReservation = new Reservation();
		
	}
	
	@After
	public void cleanup() {
		
	}

	
	@Test
	public void get_month_from_user_test() {
		
		String testDate = "01-01-1992";
		testSpace.getMonth(testDate);
		// Debugger throws null after line 31. Why?
		
		Assert.assertEquals(testDate, "01");
	}
	 
	
}
