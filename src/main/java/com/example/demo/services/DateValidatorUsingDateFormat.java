package com.example.demo.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidatorUsingDateFormat implements DateValidator {

	private static String format = "YYYY-MM-DD";
	
	public DateValidatorUsingDateFormat() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public boolean isValid(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
		try {
			Date dateParsed = dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        	return false;
        }
		return true;
	}

	public static String getFormat() {
		return format;
	}
	

}
