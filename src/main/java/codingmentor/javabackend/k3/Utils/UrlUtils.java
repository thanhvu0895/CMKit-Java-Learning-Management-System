package codingmentor.javabackend.k3.Utils;

/**
 * @author Thanh Vu
 *
 */
public class UrlUtils {

	public static final String DEPARTMENTS_PATH = "/departments";
	public static final String NEW_DEPARTMENT_PATH = "/departments/new";
	
	public static final String EXTENSIONS_PATH = "/extensions";
	public static final String NEW_EXTENSIONS_PATH = "/extensions/new";
	
	public static final String SSH_KEYS_PATH = "/ssh_keys";
	public static final String NEW_SSH_KEYS_PATH = "/ssh_keys/new";
	
	public static final String CONTRIBUTOR_INVITES_PATH = "/contributor_invites";
	public static final String CONTRIBUTORS_PATH = "/contributors";
	
	public static final String SUBMISSIONS_PATH = "/submissions";
	public static final String NEW_SUBMISSION_PATH = "/submissions/new";
	public static final String SUBMISSION_PROFESSOR_NEW_UPLOAD_PATH = "/submission/new_professor";
	
	public static final String ASSIGNMENT_PATH = "/assignments";
	public static final String NEW_ASSIGNMENT_PATH = "/assignments/new";
	public static final String SHOW_COPY_ASSIGNMENT_PATH = "/copy_assignment";
	
	public static final String GRADERS_PATH = "/graders";
	public static final String ADD_GRADER_PATH = "/add_grader";
	
	public static final String GRADE_CATEGORIES_PATH = "/grade_categories";
	public static final String NEW_GRADE_CATEGORY_PATH = "/grade_categories/new";
	
	public static final String STUDENTS_PATH = "/students";
	public static final String PROFESSORS_PATH = "/professors";
	
	public static final String USERS_ALL_PATH = "/users/*";
	public static final String USERS_PATH = "/users";
	public static final String USER_EDIT_SELF_PATH = "/edit_self";
	public static final String CHANGE_PASSWORD_PATH = "/change_password";
	public static final String CREATE_USER_INVITE_PATH = "/users/create";
	public static final String SHOW_REQUEST_PASSWORD_RESET_PATH = "/request_password_reset";
	public static final String SHOW_USE_PASSWORD_RESET_PATH = "/use_password_reset";
	public static final String NOTIFICATION_SETTINGS_PATH = "/notification_settings";
	public static final String RESEND_USER_INVITE_PATH = "/users/:id/resend_invite";
	public static final String USER_EDIT_ADMIN_PATH = "/users/:id/edit_admin";
	public static final String SHOW_USER_INVITE_PATH ="/users/:id/set_up";
	public static final String ACCEPT_USER_INVITE_PATH = "/users/:id/accept_invite";
	
	public static final String KLASSES_PATH = "/klasses";
	public static final String NEW_KLASS_PATH = "/klasses/new";
	
	public static final String COURSES_PATH = "/courses";
	public static final String NEW_COURSE_PATH = "/courses/new";
	
	public static final String LOGIN_PATH = "/login";
	public static final String LOGOUT_PATH = "/logout";
	
	public static final String FILE_VIEWER_SETTINGS_PATH = "/file_viewer_settings";
	public static final String ENABLE_FILE_VIEWER_PATH = "/enable_file_viewer";
	public static final String DISABLE_FILE_VIEWER_PATH = "/disable_file_viewer";
	
	public static final String ROOT_PATH = "";
	
	
//	public static final String NOT_FOUND = "/404";
//	public static final String INTERNAL_ERROR = "/500";
    public static final String ALL = "/*";	
    
    public static String putIdInPath(String path, int id) {
    	path = path.replaceFirst("\\:id", String.valueOf(id));
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
    
}


//		  get 'users/:id/edit_admin', to: 'users#edit_admin', as: 'user_edit_admin'		  
//		  get 'users/:id/set_up', to: 'users#show_invite', as: 'show_user_invite'
//		  post 'users/:id/accept_invite', to: 'users#accept_invite', as: 'accept_user_invite'
//		  post 'users/:id/resend_invite', to: 'users#resend_invite', as: 'resend_user_invite'
