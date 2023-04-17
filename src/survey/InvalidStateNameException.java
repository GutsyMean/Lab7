package survey;

public class InvalidStateNameException extends Exception 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidStateNameException() 
    {
        super("Invalid State Abbreviation Exception. Your state abbreviation is not valid.");
    }
}
