package br.com.sistxweb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateToString {
	
	public static String currentTimeString(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date now = Calendar.getInstance().getTime();        
		String nowString = df.format(now);
		
		return nowString;
	}
}
