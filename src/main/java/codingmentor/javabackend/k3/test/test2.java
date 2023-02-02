package codingmentor.javabackend.k3.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
		
	String[] strArray = new String[] {"[3, 4]", "[1, 2, 7, 7]"};
	String[] leftArray = strArray[0].toString().replaceAll("\\[", "").replaceAll("\\]", "").split(",");
	String[] rightArray = strArray[1].toString().replaceAll("\\[", "").replaceAll("\\]", "").split(",");

	for (int i = 0; i < rightArray.length; i ++) {
		if (leftArray[0] + leftArray[i] == leftArray[1]) {
			System.out.println("Value of strArray[i] is: " + strArray[i]) ;
		}
	}
		
	}
	
}
