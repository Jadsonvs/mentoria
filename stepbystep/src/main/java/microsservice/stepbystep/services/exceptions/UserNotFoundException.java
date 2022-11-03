package microsservice.stepbystep.services.exceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7393397952917252650L;
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
