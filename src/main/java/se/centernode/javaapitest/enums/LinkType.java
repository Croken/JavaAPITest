package se.centernode.javaapitest.enums;

public enum LinkType {
	/**
	 * Points to source material that the statement is based on.
	 */
	REFERENCE,
	
	/**
	 * Points to an update to the statement.
	 */
	UPDATE,
	
	/**
	 * Points to the statement that is challenged by this statement.
	 */
	CHALLENGE,
	
	/**
	 * Points to a statement that is supported by this statement.
	 */
	SUPPORT,
	
	/**
	 * This statement has been divided in to these statements.
	 */
	SPLIT,
	
	/**
	 * This statement has been mgerged in to this statements.
	 */
	GROUP
}
