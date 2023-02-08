package codingmentor.javabackend.k3.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import codingmentor.javabackend.k3.Utils.DateValidatorDateTimeFormatter;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Assignment;

public class test2 {
	
	public static void main(String[] args) {
//		for (AssignmentTypes a  : AssignmentTypes.values()) {
//			ALL.add(a);
//		}
//		System.out.println(AssignmentTypes.valueOf("student_repo").ordinal());
//		System.out.println(ALL.get(0));
//		
//		System.out.println(AssignmentTypes.values()[0].toString());
//		
//		
//		String test = "/assignments/:assignment_id/problems/:problem_id/rubric_items/:id/move_up";
//		test = UrlUtils.putIdInPath(test, 1);
//		test = UrlUtils.putSecondInPath(test, 2);
//		test = UrlUtils.putSecondInPath(test, 3);
//		
//		System.out.println(test);
		
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(now.getDayOfWeek());
//		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM);
//		System.out.println(now.format(dtf));
//		
//        LocalDateTime currentLocalDateTime = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE MMM d, uuuu h:mm:ss a ");
//        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
//        System.out.println("Formatted LocalDateTime in String format : " + formattedDateTime);
        
        
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT-5"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE MMM d 'at' hh:mma", Locale.ENGLISH);        
        
        System.out.println(now.format(dtf));

        LocalDateTime assignedDueDate = LocalDateTime.of(2023, 9, 14, 20, 30);
        LocalDateTime current = LocalDateTime.now();
        ZoneId zone = ZoneId.of("US/Eastern");

        Timestamp.valueOf(assignedDueDate);
        System.out.println(Timestamp.valueOf(assignedDueDate));
        

	}
}
