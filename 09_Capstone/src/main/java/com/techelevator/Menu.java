package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.excelsior.jdbc.JDBCVenueDAO;
import com.techelevator.excelsior.model.Venue;

public class Menu {
	
	JDBCVenueDAO jdbcVenueDao = new JDBCVenueDAO(datasource);
	
	private  Scanner in = new Scanner(System.in);
	
	
	
	public String showHomeMenu() {
		
		System.out.println("What would you like to do?");
		System.out.println("1) List Venues");
		System.out.println("Q) Quit");	
		
		String userChoice = in.nextLine();
	}
	
	
	public String showVenueViewMenu() {
		System.out.println("Which venue would you like to view?");
		printVenueNames(xxxxxxx); //**************** Questionable ********************
		System.out.println("Q) Quit");
		
		String userChoice = in.nextLine();
	}
	
	
	
	private String printVenueNames(List<Venue> venueList) {
		for (Venue venue : venueList) {
			int i = 1;
			System.out.println(i + ") " + venue.getName());
			i++;
		}
		
	}
	
	
	private String printVenueSpaces(Venue venue) {
		for (Venue venue : venueList) {
			int i = 1;
			System.out.println(i + ") " + venue.getName());
			i++;
		}
		
	}
	
	//Will print an extra comment on the end.. Try a regular for loop and then print out the last index of list
	private String printVenueCategories(List<String> categoryList) {
		for (String category : categoryList) {
			System.out.print(category + ", ");
		}
		
	}
	
	
	
	public String showVenueSpacesMenu() {
		
		System.out.println(Venue.getName()); //**********************************
		System.out.println("Location: " + Venue.getCity() + ", " + Venue.getState);
		System.out.print("Categories: ");
		printVenueCategories(List<String> categoryList); //**********************************
		
		System.out.println();
		System.out.println(venue.getDescription()); //**********************************
		System.out.println();
		
		System.out.println("What would you like to do next?");
		System.out.println("1) View Spaces");
		System.out.println("2) Search for Reservation");
		System.out.println("R) Return to Previous Screen");
		
		
		String userChoice = in.nextLine();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
