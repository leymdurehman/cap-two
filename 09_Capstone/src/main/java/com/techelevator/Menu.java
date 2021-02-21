package com.techelevator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.techelevator.excelsior.jdbc.JDBCSpaceDAO;
import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.SpaceDAO;
import com.techelevator.excelsior.model.Venue;

public class Menu {
	
	//JDBCVenueDAO jdbcVenueDao = new JDBCVenueDAO(datasource);
	
	private  Scanner in = new Scanner(System.in);
	
	double totalCost = 0.0;
	




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
			
		}
		return venueMap;
		
	}
	
	public String venueChoice() {
		System.out.println("Q) Quit");
		System.out.println("Which venue would you like to view?");
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	
	public void printVenueSpaces(List<Space> spacesForVenue) {
		for (Space space : spacesForVenue) {
			int i = 1;
			System.out.println(i + ") " + space.getName());
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
		System.out.printf(venueMap.getName());
		System.out.printf(venueMap.getCity() + ", " + venueMap.getState());
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
		
		for (Space space : spacesPerVenue) {
			int i = 1;
			System.out.printf("%1s %4s %30s %10s %10s %10s %10s %10s", "#" + i + space.getName() 
					+ monthList.get(space.getOpenFrom()) + monthList.get(space.getOpenTo()) 
					+ space.getDailyRate() + space.getMaxOccupancy());
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
	
	

		
		public String spaceStartDate() {
			System.out.println("When do you need the space? (YYYY-MM-DD) ");
			String userDateChoice = in.nextLine();
			return userDateChoice;
		}
		
		public int lengthOfStay() {
			System.out.println("How many days will you need the space? ");
			String userSpanChoice = in.nextLine();
			int userSpanChoiceAsInt = Integer.parseInt(userSpanChoice);
			
			return userSpanChoiceAsInt;
		}
		
		public int attendanceNumber() {
			System.out.println("How many people will be in attendance? ");
			String userAttendanceChoice = in.nextLine();
			int attendanceNumberAsInt = Integer.parseInt(userAttendanceChoice);
			return attendanceNumberAsInt;
		}
		
		
		public void spaceReservationMenu( List<Space> availableSpaces){
		System.out.println("The following spaces are available based on your needs: ");
		System.out.println();
		
		System.out.printf("%10s %20s %20s %20s %20s %20s ", "Space #" + "Name" + "Daily Rate" 
		+ "Max Occup." + "Accessible?" 
		+ "Total Cost");
		
		for (Space space : availableSpaces) {
			int i = 1;
			// ??? Christian
			
			this.totalCost = lengthOfStay() * space.getDailyRate();
			System.out.printf("%10s %20s %20s %20s %20s %20s", "#" + space.getId() + space.getName() 
					+ space.getDailyRate() + space.getMaxOccupancy() 
					+ space.isAccessible() + totalCost);
			}
		
		}
		
		public String userSpaceSelection() {
			System.out.println("Which space would you like to reserve? ");
			String userSpaceSelection = in.nextLine();
			return userSpaceSelection;
		}
		public String userName() {
			System.out.println("Who is this reservation for? ");
			String reservationName = in.nextLine();
			return reservationName;
		}
		
		public void userReservation (Reservation reservation, Space space, Venue venue) { 
		System.out.println("Thanks for submitting your reservation! The details for your event are listed below:/n");
		int confirmationNumber = 0;
		System.out.println("Confirmation #: " + confirmationNumber);
		
		System.out.println("Venue: " + venue.getName());
		
		System.out.println("Space: " + space.getName());
		
		System.out.println("Reserved For: " + reservation.getReservedFor());
		
		System.out.println("Attendees: " + reservation.getNumberOfattendees());
		
		System.out.println("Arrival Date: " + spaceStartDate());
		
		System.out.println("Departure Date: " + reservation.getEndDate());
		
		System.out.println("Total Cost: " + totalCost);
	
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

