package integration;
import java.io.IOException;

import junit.framework.TestCase;
import model.purchase.Purchase;


public class DbHandlerTest extends TestCase
{
	DbHandler dbh;
	ErrorLogHandler elh;
	
	public DbHandlerTest()
	{
		
		try
		{
			elh = new ErrorLogHandler();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		dbh = new DbHandler(elh);

	}
	
	public void logEntryTest()
	{
		try
		{
			dbh.addLogEntry(new LogEntry(new Purchase(), elh));
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	
	public void validateDiscountTest() throws DbFailureException
	{
		
		if (dbh.validateDiscount(1, 1) != true)
			fail();
	
	}
	
	public void getDiscountTest() throws DbFailureException
	{
		dbh.getDiscount(1);
	}
	

}
