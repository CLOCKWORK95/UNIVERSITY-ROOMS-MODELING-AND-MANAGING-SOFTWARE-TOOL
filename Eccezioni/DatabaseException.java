package Eccezioni;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String message)
	{
		super(message);
	}
	
	public DatabaseException(String message, SQLException sqlEx)
	{
		super(message + " [" + sqlEx.getMessage() + "]");
	}

}