package com.techelevator;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.util.Scanner;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.Space;

import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;

public class Menu {
	
	
	private  Scanner in = new Scanner(System.in);

	public String showHomeMenu() {
		
		System.out.println("What would you like to do?");
		System.out.println("1) List Venues");
		System.out.println("Q) Quit");	
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}

	
	public Map<Integer, Venue> showVenueNamesMenu(List<Venue> venueList) {
		//List<Venue> venueList = new ArrayList<Venue>();
		Map <Integer, Venue> venueMap = new LinkedHashMap<Integer, Venue>();
		
		int i = 1;
		for (Venue venue : venueList) {	
			System.out.println(i + ") " + venue.getName());
			venueMap.put(i, venue);
			i++;
			
		} System.out.println("R) Return to Previous Screen");
		return venueMap;
		
	}
	
	public String venueChoice() {
		System.out.println("Which venue would you like to view?");
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	
	public void printVenueAllSpaces(List<Space> spacesForVenue) {
		
		
		int i =1;
		for (Space space : spacesForVenue) {
			
			System.out.println("#" + i + space.getName() );
			i++;
		}
		
	}
	
	//Will print an extra comment on the end.. Try a regular for loop and then print out the last index of list
	public void printVenueCategories(List<String> categoryList) {
		System.out.print("Categories: ");
		for (int i = 0; i < categoryList.size(); i++) {
			if (i == categoryList.size() -1) {
				System.out.print(categoryList.get(i));
				
			} else  System.out.print(categoryList.get(i) + ", ");
		}
		
	}
	
	
	
	public void showVenueDetails(Venue venue) {
		System.out.println();
		System.out.println(venue.getDescription()); //**********************************
		System.out.println();
	}
	
	public void displayVenueDetails(Venue venueMap) {
		System.out.printf(venueMap.getName() + " ");
		
		System.out.printf(venueMap.getCity() + ", " + venueMap.getState());
		// venueMap is not populating City and State
	}
		
		
	public String venueDetailsMenu()	{
		
		
		System.out.println("What would you like to do next?");
		System.out.println("1) View Spaces");
		System.out.println("2) Search for Reservation");
		System.out.println("R) Return to Previous Screen");
		
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	
	
	public void venueSpacesMenu(Venue venue, List<Space> spacesPerVenue) {
		System.out.println(venue.getName() + " Spaces");
		System.out.println();		
	
//	Map<Integer, String> monthList = new HashMap<Integer, String>();
//	monthList.put(1, "Jan.");

		Map<Integer, String> monthList = new HashMap<Integer, String>();
		monthList.put(1, "Jan.");
		monthList.put(2, "Feb.");
		monthList.put(3, "Mar.");
		monthList.put(4, "Apr.");
		monthList.put(5, "May");
		monthList.put(6, "June");
		monthList.put(7, "July");
		monthList.put(8, "Aug.");
		monthList.put(9, "Sep.");
		monthList.put(10, "Oct.");
		monthList.put(11, "Nov.");
		monthList.put(12, "Dec.");
		
		int i = 1;
		for (Space space : spacesPerVenue) {
				
			if (monthList.get(space.getOpenFrom()) != null ) {
			System.out.println( "#" + i + "  " + space.getName() + "  "
					+ monthList.get(space.getOpenFrom()) + "  " + monthList.get(space.getOpenTo()) 
					+ "  "
					+ space.getDailyRate() + "  " + space.getMaxOccupancy());
			
		} else {	System.out.println( "#" + i + "  " + space.getName() + "  "
				+ "  "  + "  " + "  "  
				+ "  "
				+ space.getDailyRate() + "  " + space.getMaxOccupancy());
		} i++;
	}
}
	
	public String spaceDetailsMenu(){
		
		
		System.out.println("What would you like to do next?");
		System.out.println("1) Reserve a Space");
		System.out.println("2) Search for Reservation");
		System.out.println("R) Return to Previous Screen");
		
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	

		
		public String spaceStartDate() throws ParseException {
			Date currentDate = Calendar.getInstance().getTime();
			DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
			
			String userDateChoice = null;
			boolean time;
			
			do {
				System.out.println("When do you need the space? (YYYY-MM-DD) ");
				userDateChoice = in.nextLine();
				Date userDateEntered = format.parse(userDateChoice);
				
				if (userDateEntered.before(currentDate)) {
					System.out.println("Please enter a valid date.");
					//userDateChoice = in.nextLine();
					time = false;
				}
				else {
					time = true;
				}
			}
			while(!time);
			
			return userDateChoice;
			
//			
//				System.out.println("When do you need the space? (YYYY-MM-DD) ");
//				String userDateChoice = in.nextLine();
//		
//			return userDateChoice;
		}
		
		public int lengthOfStay() {
			boolean length;
			int userSpanChoiceAsInt = 0;
			
			do {
				
				System.out.println("How many days will you need the space? ");
				String userSpanChoice = in.nextLine();
				userSpanChoiceAsInt = Integer.parseInt(userSpanChoice);
				
					if (userSpanChoiceAsInt < 1) {
						notAValidChoice();
						length = false;
					}
					else {
						length = true;
					}
				}
				while(!length);
			return userSpanChoiceAsInt;
		}
		
		public int attendanceNumber() {
			boolean attendance;
			int attendanceNumberAsInt = 0;
			
			do {
			System.out.println("How many people will be in attendance? ");
			String userAttendanceChoice = in.nextLine();
			attendanceNumberAsInt = Integer.parseInt(userAttendanceChoice);
			
				if (attendanceNumberAsInt < 1) {
					notAValidChoice();
					attendance = false;
				}
				else {
					attendance = true;
				}	
			}
			while (!attendance);
			return attendanceNumberAsInt;
		}
		
		public void notAValidChoice() {
			System.out.println("Sorry, that's not a valid choice.");
			
		}
		
		public void noAvabilibity() {
			
			System.out.println("Sorry there is no availability for those dates! Please try agan!");
			
			
		}
		
		
		
		public void spaceReservationMenu( List<Space> availableSpaces, int numOfdays){
		System.out.println("The following spaces are available based on your needs: ");
		System.out.println();
		
		System.out.printf("%1s %10s %15s %15s %15s \n", "Space #", "Name", "Daily Rate" 
		, "Max Occup.", "Total Cost");
		
			//double totalCost = calculateTotalCost(availableSpaces, numOfdays);
	
			for (Space space : availableSpaces) {
			
			//this.totalCost = lengthOfStay() * space.getDailyRate();
			String dailyRate = space.getDailyRate();
			String str = dailyRate.replaceAll(",", "");
			String str2 = str.replace("$", "");
			double dailyRateInt = Double.parseDouble(str2);
			double totalCost = dailyRateInt * numOfdays;
			
			System.out.println("#" + space.getId() + space.getName() 
					+ space.getDailyRate() + space.getMaxOccupancy() + totalCost);
			}
		
		}
		
//		public double calculateTotalCost(List<Space>availableSpaces, int numOfdays) {
//			double totalCost = 0;
//			for (Space space : availableSpaces) {
//				String dailyRate = space.getDailyRate();
//				String str = dailyRate.replaceAll(",", "");
//				String str2 = str.replace("$", "");
//				double dailyRateInt = Double.parseDouble(str2);
//				totalCost = dailyRateInt * numOfdays;
//			}
//			return totalCost;
//		}
		
		public int userSpaceSelection() {
			System.out.println("Which space would you like to reserve (enter 0 to cancel)? ");
			int userSpace = in.nextInt();
			return userSpace;
		}
		public String userName() {
			System.out.println("Who is this reservation for? ");
			String reservationName = in.nextLine();
			return reservationName;
		}
		
		public void userReservation (int numOfAttendees, String reservationName, String startDate, String endDate, String spaceName, String venue) {
			
			
			System.out.println("Thanks for submitting your reservation! The details for your event are listed below:");
			System.out.println();
			
			Map<Integer, String> confirmedReservation = new LinkedHashMap<Integer, String>();
			
			int confirmationNumber = 0;
			
			System.out.println("Confirmation #: " + confirmationNumber);
			
			confirmedReservation.put(confirmationNumber, reservationName);
			
			System.out.println("Venue: " + venue);
			
			System.out.println("Space: " + spaceName);
			
			System.out.println("Reserved For: " + reservationName);
			
			System.out.println("Attendees: " + numOfAttendees);
			
			System.out.println("Arrival Date: " + startDate);
			
			System.out.println("Departure Date: " + endDate);
			
			//System.out.println("Total Cost: " + totalCost);
		
		}
	
}
