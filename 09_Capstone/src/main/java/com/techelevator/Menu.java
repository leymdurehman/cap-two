package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.Venue;

public class Menu {
	
	//JDBCVenueDAO jdbcVenueDao = new JDBCVenueDAO(datasource);
	
	private  Scanner in = new Scanner(System.in);
	
	
	
	public String showHomeMenu() {
		
		System.out.println("What would you like to do?");
		System.out.println("1) List Venues");
		System.out.println("Q) Quit");	
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	


	
	private void showVenueNamesMenu(List<Venue> venueList) {
		for (Venue venue : venueList) {
			int i = 1;
			System.out.println(i + ") " + venue.getName());
			i++;
		}
		System.out.println("Q) Quit");
		System.out.println("Which venue would you like to view?");
		String userChoice = in.nextLine();
	}
	
	
	private void printVenueSpaces(List<Space> spacesForVenue) {
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
		
		System.out.println(venue.getName()); //**********************************
		System.out.println("Location: " + venue.getCity() + ", " + venue.getState());
	
		System.out.println();
		System.out.println(venue.getDescription()); //**********************************
		System.out.println();
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
	
	
//	When do you need the space? 9/29/2019
//			How many days will you need the space? 5
//			How many people will be in attendance? 100
//
//			The following spaces are available based on your needs:
//
//			Space #   Name                Daily Rate   Max Occup.   Accessible?   Total Cost
//			111       The Rotunda         $350         100          Yes           $1,750
//			333       The Golden Walrus   $900         150          No            $4,500
//
//			Which space would you like to reserve (enter 0 to cancel)? 111
//			Who is this reservation for? Happy Madison
//
//			Thanks for submitting your reservation! The details for your event are listed below:
//
//			Confirmation #: 98783478
//			         Venue: Trapp Family Lodge
//			         Space: The Rotunda
//			  Reserved For: Happy Madison
//			     Attendees: 100
//			  Arrival Date: 9/29/2019
//			   Depart Date: 10/3/2019
//			    Total Cost: $1,750
	
	
	
	//to be completed! with additional info for reservation confirmation
	public String spaceReservationMenu( List<Space> availableSpaces){
		
		
		System.out.println("When do you need the space?");
		String userDateChoice = in.nextLine();
		
		System.out.println("How many days will you need the space?");
		String userSpanChoice = in.nextLine();
		int userSpanChoiceAsInt = Integer.parseInt(userSpanChoice);
		
		System.out.println("How many people will be in attendance?");
		String userAttendanceChoice = in.nextLine();
		
		System.out.println("The following spaces are available based on your needs:");
		System.out.println();
		
		System.out.printf("%10s %20s %20s %20s %20s %20s ", "Space #" + "Name" + "Daily Rate" 
		+ "Max Occup." + "Accessible?" 
		+ "Total Cost");
		
		for (Space space : availableSpaces) {
			int i = 1;
			
			double totalCost = userSpanChoiceAsInt * space.getDailyRate();
			System.out.printf("%10s %20s %20s %20s %20s %20s", "#" + space.getId() + space.getName() 
					+ space.getDailyRate() + space.getMaxOccupancy() 
					+ space.isAccessible() + totalCost);
		
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

