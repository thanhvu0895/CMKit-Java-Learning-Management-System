package codingmentor.javabackend.k3.Utils;

public class UrlUtils {
	
	//AUTH API
	public static final String SIGN_IN = "/login";
	public static final String SIGN_OUT = "/logout";
	
	//KLASSES API
	public static final String KLASSES = "/klasses";
	public static final String SSH_KEYS = "/ssh_keys";
	public static final String KLASSES_DETAILS = "/klasses_details";
	public static final String SUBMISSIONS = "/submissions";

	//HOME API
	public static final String HOME = "";
	
	//USERS API
	public static final String USERS_EDIT_SELF = "/users/edit_self";
	public static final String USERS_NOTIFICATION_SETTINGS = "/users/notification_settings";
	public static final String USERS_CHANGE_PASSWORD = "/change_password";
	public static final String USERS_CREATE = "/users/create";
	
	public static final String USERS = "/users/*";
	
	//PASSWORD RESET API
	public static final String REQUEST_PASSWORD_RESET = "/request_password_reset";
	public static final String USE_PASSWORD_RESET = "/use_password_reset";
	
	
	
//	public static final String NOT_FOUND = "/404";
//	public static final String INTERNAL_ERROR = "/500";
    public static final String ALL = "/*";	
}


//		  get 'users/:id/edit_admin', to: 'users#edit_admin', as: 'user_edit_admin'		  
//		  get 'users/:id/set_up', to: 'users#show_invite', as: 'show_user_invite'
//		  post 'users/:id/accept_invite', to: 'users#accept_invite', as: 'accept_user_invite'
//		  post 'users/:id/resend_invite', to: 'users#resend_invite', as: 'resend_user_invite'
