package codingmentor.javabackend.k3.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Thanh Vu
 *
 */
public class UrlUtils {

	public static final String DEPARTMENTS_ALL_PATH = "/departments/*";
	public static final String DEPARTMENTS_PATH = "/departments";
	public static final String NEW_DEPARTMENT_PATH = "/departments/new";
	public static final String DEPARTMENT_COURSES_PATH = "/departments/:id/courses";
	public static final String DEPARTMENT_FILES_PATH = "/departments/:id/files";
	public static final String DEPARTMENT_CLASSES_PATH = "/departments/:id/klasses";
	public static final String EDIT_DEPARTMENT_PATH = "/departments/:id/edit";
	public static final String DEPARTMENT_DEPARTMENT_PROFESSOR_PATH = "/departments/:department_id/department_professors/:id";
	public static final String DEPARTMENT_DEPARTMENT_PROFESSORS_PATH = "/departments/:department_id/department_professors/";

	public static final String EXTENSIONS_PATH = "/extensions";
	public static final String NEW_EXTENSIONS_PATH = "/extensions/new";

	public static final String SSH_KEYS_PATH = "/ssh_keys";
	public static final String NEW_SSH_KEYS_PATH = "/ssh_keys/new";

	public static final String CONTRIBUTORS_ALL_PATH = "/contributors/*";
	public static final String CONTRIBUTORS_PATH = "/contributors";
	public static final String CONTRIBUTOR_INVITES_PATH = "/contributor_invites";

	public static final String SUBMISSIONS_ALL_PATH = "/submissions/*";
	public static final String SUBMISSIONS_PATH = "/submissions";
	public static final String NEW_SUBMISSION_PATH = "/submissions/new";
	public static final String SUBMISSION_PROFESSOR_NEW_UPLOAD_PATH = "/submission/new_professor";

	public static final String ASSIGNMENT_ALL_PATH = "/assignments/*";
	public static final String ASSIGNMENT_PATH = "/assignments";
	public static final String NEW_ASSIGNMENT_PATH = "/assignments/new";
	public static final String SHOW_COPY_ASSIGNMENT_PATH = "/copy_assignment";

	public static final String EDIT_ASSIGNMENT_PATH = "/assignments/:id/edit";

	public static final String ASSIGNMENT_PROBLEMS_PATH = "/assignments/:assignment_id/problems";
	public static final String ASSIGNMENT_PROBLEM_PATH = "/assignments/:assignment_id/problems/:id";
	public static final String ASSIGNMENT_GRADE_PROBLEM_PATH = "/assignments/:assignment_id/assigned/:id/problem/:problem_id";
	
	public static final String NEW_ASSIGNMENT_PROBLEM_PATH = "/assignments/:assignment_id/problems/new";
	public static final String EDIT_ASSIGNMENT_PROBLEM_PATH = "/assignments/:assignment_id/problems/:id/edit";
	public static final String ASSIGNMENT_PROBLEM_MOVE_DOWN_PATH = "/assignments/:assignment_id/problems/:id/move_down";
	public static final String ASSIGNMENT_PROBLEM_MOVE_UP_PATH = "/assignments/:assignment_id/problems/:id/move_up";
	public static final String ASSIGNMENT_VIEW_COPY_RUBRIC_PATH = "/assignments/:assignment_id/copy_rubric";

	public static final String ASSIGNMENT_ASSIGNED_PATH = "/assignments/:assignment_id/assigned/:id";
	public static final String ASSIGNMENT_ADJUSTMENTS_PATH = "/assignments/:assignment_id/assigned/:id/adjustments";
	public static final String ASSIGNMENT_ASSIGNED_RESET_NOTIFICATIONS_PATH = "/assignments/:assignment_id/assigned/:id/reset_notifications";

	public static final String ASSIGNMENT_ASSIGN_PATH = "/assignments/:assignment_id/assign";
	public static final String ASSIGNMENT_ASSIGNED_PROBLEMS_PATH = "/assignments/:assignment_id/assigned/:id/problems";
	public static final String ASSIGNMENT_ASSIGNED_GRADEBOOK_PATH = "/assignments/:assignment_id/assigned/:id/gradebook";
	public static final String ASSIGNMENT_GRADE_SETTINGS_PATH = "/assignments/:assignment_id/assigned/:id/grade_settings";
	public static final String ASSIGNMENT_UNASSIGN_PATH = "/assignments/:assignment_id/unassign/:id";
	public static final String ASSIGNMENT_ASSIGNED_TOGGLE_HIDE_GRADES_PATH = "/assignments/:assignment_id/assigned/:id/toggle_hide_grades";
	public static final String ASSIGNMENT_BULK_DOWNLOAD_PATH = "/assignments/:assignment_id/assigned/:id/bulk_download";
	public static final String ASSIGNMENT_VIEW_BULK_UPLOAD_PATH = "/assignments/:assignment_id/assigned/:id/bulk_upload";
	public static final String ASSIGNMENT_VIEW_BULK_SORT_PATH = "/assignments/:assignment_id/assigned/:id/bulk_sort";
	public static final String ASSIGNMENT_CLONE_ALL_PATH = "/assignments/:assignment_id/assigned/:id/clone_all";
	public static final String ASSIGNMENT_ASSIGNED_GRADEBOOK_CSV_PATH = "/assignments/:assignment_id/assigned/:id/gradebook_csv";
	
	public static final String ASSIGNED_GRADER_PATH = "/assigned_grader";
	public static final String REMOVE_GRADER_PATH = "/assigned_grader/:id";
	public static final String ASSIGNED_GRADER_ALL_PATH = "/assigned_grader/*";

	public static final String ASSIGNMENT_PROBLEM_RUBRIC_ITEMS_PATH = "/assignments/:assignment_id/problems/:id/rubric_items";
	public static final String ASSIGNMENT_PROBLEM_RUBRIC_ITEM_PATH = "/assignments/:assignment_id/problems/:problem_id/rubric_items/:id";
	public static final String ASSIGNMENT_PROBLEM_RUBRIC_ITEM_MOVE_UP_PATH = "/assignments/:assignment_id/problems/:problem_id/rubric_items/:id/move_up";
	public static final String ASSIGNMENT_PROBLEM_RUBRIC_ITEM_MOVE_DOWN_PATH = "/assignments/:assignment_id/problems/:problem_id/rubric_items/:id/move_down";
	public static final String ASSIGNMENT_PROBLEM_REUSABLE_COMMENTS_PATH = "/assignments/:assignment_id/problems/:id/reusable_comments";
	public static final String ASSIGNMENT_PROBLEM_REUSABLE_COMMENT_PATH = "/assignments/:assignment_id/problems/:problem_id/reusable_comments/:id";
	public static final String ASSIGNMENT_SHOW_GRADE_PROBLEM_PATH = "/assignments/:assignment_id/assigned/:id/problem/:problem_id";
	
	public static final String SUBMISSION_GRADE_PATH = "/submissions/:submission_id/grade";
	public static final String SUBMISSION_GRADED_PROBLEMS_PATH = "/submissions/:submission_id/graded_problems";
	public static final String EDIT_SUBMISSION_GRADED_PROBLEM_PATH = "/submissions/:submission_id/graded_problems/:id/edit";
	
	
	public static final String GRADERS_ALL_PATH = "/graders/*";
	public static final String GRADERS_PATH = "/graders";
	public static final String ADD_GRADER_PATH = "/add_grader";

	public static final String GRADE_CATEGORIES_ALL_PATH = "/grade_categories/*";
	public static final String GRADE_CATEGORIES_PATH = "/grade_categories";
	public static final String NEW_GRADE_CATEGORY_PATH = "/grade_categories/new";
	public static final String EDIT_GRADE_CATEGORY_PATH = "/grade_categories/:id/edit";
	public static final String GRADE_CATEGORY_PATH = "/grade_categories/:id";

	public static final String STUDENTS_ALL_PATH = "/students";
	public static final String STUDENTS_PATH = "/students";
	public static final String TOGGLE_ASSIGNED_NOTIFICATION_PATH = "/students/:id/toggle_assigned_notification";

	public static final String PROFESSORS_ALL_PATH = "/professors/*";
	public static final String PROFESSORS_PATH = "/professors";

	public static final String USERS_ALL_PATH = "/users/*";
	public static final String USERS_PATH = "/users";
	public static final String USER_EDIT_SELF_PATH = "/edit_self";
	public static final String CHANGE_PASSWORD_PATH = "/change_password";
	public static final String CREATE_USER_INVITE_PATH = "/users/create";
	public static final String SHOW_REQUEST_PASSWORD_RESET_PATH = "/request_password_reset";
	public static final String SHOW_USER_PASSWORD_RESET_PATH = "/use_password_reset";
	public static final String NOTIFICATION_SETTINGS_PATH = "/notification_settings";
	public static final String RESEND_USER_INVITE_PATH = "/users/:id/resend_invite";
	public static final String USER_EDIT_ADMIN_PATH = "/users/:id/edit_admin";
	public static final String SHOW_USER_INVITE_PATH = "/users/:id/set_up";
	public static final String ACCEPT_USER_INVITE_PATH = "/users/:id/accept_invite";

	public static final String KLASSES_ALL_PATH = "/klasses/*";
	public static final String KLASSES_PATH = "/klasses";
	public static final String NEW_KLASS_PATH = "/klasses/new";
	public static final String EDIT_KLASS_PATH = "/klasses/:id/edit";
	public static final String KLASS_FILES_PATH = "/klasses/:klass_id/files";
	public static final String KLASS_STUDENTS_PATH = "/klasses/:klass_id/students";
	public static final String KLASS_GRADERS_PATH = "/klasses/:klass_id/graders";
	public static final String KLASS_ASSIGNMENTS_PATH = "/klasses/:klass_id/assignments";
	public static final String KLASS_GRADEBOOK_PATH = "/klasses/:klass_id/gradebook";
	public static final String KLASS_GRADEBOOK_CSV_PATH = "/klasses/:klass_id/gradebook_csv";

	public static final String COURSES_PATH = "/courses";
	public static final String COURSES_ALL_PATH = "/courses/*";
	public static final String NEW_COURSE_PATH = "/courses/new";
	public static final String COURSE_FILES_PATH = "/courses/:id/files";
	public static final String EDIT_COURSE_PATH = "/courses/:id/edit";
	public static final String COURSE_GRADE_CATEGORIES_PATH = "/courses/:id/grade_categories";

	public static final String LOGIN_PATH = "/login";
	public static final String LOGOUT_PATH = "/logout";

	public static final String FILE_VIEWER_SETTINGS_PATH = "/file_viewer_settings";
	public static final String ENABLE_FILE_VIEWER_PATH = "/enable_file_viewer";
	public static final String DISABLE_FILE_VIEWER_PATH = "/disable_file_viewer";

	public static final String ROOT_PATH = "";

//	public static final String NOT_FOUND = "/404";
//	public static final String INTERNAL_ERROR = "/500";
	public static final String ALL_PATH = "/*";

	public static String putIdInPath(String path, int id) {
		path = path.replaceFirst("\\:id", String.valueOf(id));
		return path;
	}

	public static String putSecondInPath(String path, int secondId) {
		path = path.replaceFirst("\\:.*?id", String.valueOf(secondId));
		return path;
	}

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();
		if (length == 0) {
			return false;
		}

		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}

		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static Double convertDoubleFromStringWithPrecision(String doubleNumberString, int precision) {
		return BigDecimal.valueOf(Double.parseDouble(doubleNumberString)).setScale(precision, RoundingMode.HALF_UP)
				.doubleValue();
	}
	
	public static String get_color_for_grade(double grade) {
		double red = 0;
		double green = 0;
		double blue = 0;
	  
	  if (grade < 0.5)
		red = 255;
	  else if (grade < 0.75) {
		red = 255;
		green = (grade-0.5)*4 * 255;
	  }
	  else if (grade <= 1.0) {
	    green = 255;
		red = 255 - (grade - 0.75) * 4 * 255;
	  }
	  else
	    green = 255;
	  
	  return "rgb(" + Double.toString(red) + "," + Double.toString(green) +"," + Double.toString(blue) + ")";
	}
}
