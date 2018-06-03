package integration;


/**
 * Exception to be thrown when an item cannot be found in the InventorySystem
 */
public class RetailItemNotFoundException extends Exception
{
	/**
	 * Takes a message and prints it when the error is thrown
	 * @param msg
	 */
	public RetailItemNotFoundException(int itemId, ErrorLogHandler elh) 
	{ 
		super("Item " + itemId + " not found in external inventory system.");
		elh.thisExists();
		elh.logException(this);
		
	} 
	
}
