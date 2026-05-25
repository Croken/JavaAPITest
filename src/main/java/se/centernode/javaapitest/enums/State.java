package se.centernode.javaapitest.enums;

public enum State {
	/**
	 * Used for unanswered / unresolved questions
	 */
	UNRESOLVED,
	
	/**
	 * Used for resolved questions
	 */
	RESOLVED,
	
	/**
	 * No support or challenge has been made.
	 */
	UNCONTESTED,
	
	/**
	 * Used for statements that has supporting statements and no challenges.
	 */
	SUPPORTED,
	
	/**
	 * Statement has been challenged and found incorrect.
	 */
	INVALLIDATED
}