package codingmentor.javabackend.k3.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
// THANKS TO: https://mkyong.com/java/how-to-check-if-date-is-valid-in-java/

public class DateValidatorDateTimeFormatter {
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
    public static boolean isValid(String year, String month, String day) {
    	
    	boolean valid = false;
        try {
        	String dateStr = String.format("%s-%s-%s", year, month, day);
	    	// ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
	        LocalDate.parse(dateStr,
	                DateTimeFormatter.ofPattern("uuuu-M-d")
	                        .withResolverStyle(ResolverStyle.STRICT)
	        );
	        
	        valid = true;
        } catch (DateTimeParseException e) {
            return false;
        }
        
        return valid;
    }
}