package microsservice.stepbystep.services.exceptions;


public class UserAlreadyExistException extends Exception   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8173880464979332482L;
	
	public UserAlreadyExistException(String message) {
		super(message);
	}
	
	

}
