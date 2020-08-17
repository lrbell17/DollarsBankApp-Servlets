package exceptions;

public class InvalidAccountNumberException extends Exception {

	private static final long serialVersionUID = 810990746692601682L;

	public InvalidAccountNumberException() {
		super("We were unable to find an account with that account number!");
	}
}
