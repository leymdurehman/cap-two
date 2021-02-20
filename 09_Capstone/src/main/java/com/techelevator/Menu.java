package com.techelevator;

import java.util.ArrayList;
import java.util.List;
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
		
		
	public String VenuDetailsMenu()	{
		
		
		System.out.println("What would you like to do next?");
		System.out.println("1) View Spaces");
		System.out.println("2) Search for Reservation");
		System.out.println("R) Return to Previous Screen");
		
		
		String userChoice = in.nextLine();
		
		return userChoice;
	}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

