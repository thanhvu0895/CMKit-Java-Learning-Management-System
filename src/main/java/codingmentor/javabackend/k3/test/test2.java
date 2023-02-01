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
	private static int assignmentType;
	private static enum AssignmentEnum{
		RED, GREEN, BLUE;
	}
	private enum AssignmentTypes {
		student_file, student_repo, professor_file, grade_only
	}
	public static final ArrayList<AssignmentTypes> ALL = new ArrayList<>();
	public static void main(String[] args) {
		for (AssignmentTypes a  : AssignmentTypes.values()) {
			ALL.add(a);
		}
		System.out.println(AssignmentTypes.valueOf("student_repo").ordinal());
		System.out.println(ALL.get(0));
		
		System.out.println(AssignmentTypes.values()[0].toString());
		
		
		String test = "/assignments/:assignment_id/problems/:problem_id/rubric_items/:id/move_up";
		test = UrlUtils.putIdInPath(test, 1);
		test = UrlUtils.putSecondInPath(test, 2);
		test = UrlUtils.putSecondInPath(test, 3);
		
		System.out.println(test);
	}
	
}
