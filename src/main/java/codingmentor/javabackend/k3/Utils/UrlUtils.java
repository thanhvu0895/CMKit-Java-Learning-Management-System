package codingmentor.javabackend.k3.Utils;

public class UrlUtils {
	
	//AUTH API
	public static final String SIGN_IN = "/login";
	public static final String SIGN_OUT = "/logout";
	public static final String KLASSES = "/klasses";
	
	// SESSION API
	public static final String CHANGE_PASSWORD = "/change_password";
	public static final String REQUEST_PASSWORD_RESET = "/request_password_reset";
	
	//KLASSES API
	public static final String SSH_KEYS = "/ssh_keys";
	public static final String KLASSES_DETAILS = "/klasses_details";
	public static final String SUBMISSIONS = "/submissions";

	//HOME API
	public static final String HOME = "";
	
	//USERS API	
	public static final String EDIT_SELF = "/users/edit_self";
	public static final String NOTIFICATION_SETTINGS = "/users/notification_settings";
	
	
	
	
	
	public static final String NOT_FOUND = "/404";
    public static final String INTERNAL_ERROR = "/500";
    public static final String ALL = "/*";	
}
