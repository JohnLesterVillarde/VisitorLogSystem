package com.visitor.log.system.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
	
	public static int monthAndDay() {
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMdd");
		
		String date = currentDate.format(formatter);
		return Integer.parseInt(date);
		
	}
	
	public static String currentDateAndTime() {		
		LocalDateTime dateAndTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentDateTime = dateAndTime.format(formatter);
		return currentDateTime;
	}
	
	/*
	public static void getCurrentYear() {
		
	}
	*/
	
	public static void main(String[] args) {
		System.out.println(currentDateAndTime());
		System.out.println(monthAndDay());
	}
	
}
