package model.payment;

import java.io.IOException;

import integration.DbFailureException;
import integration.ErrorLogHandler;
import junit.framework.TestCase;

public class PaymentTest extends TestCase 
{
	Payment payment;

	/**
	 * Test if a discount can be entered and validated
	 * 
	 * @throws DbFailureException
	 * @throws IOException 
	 */
	public void testDiscount() throws DbFailureException, IOException
	{
		Payment payment = new Payment(new ErrorLogHandler());
		
		payment.enterDiscount(1);
		if(!payment.getDiscount().validate(1))
			fail();
	}
	
	/**
	 * Tests if addToTotal works
	 * @param amount
	 */
	public void addToTotalTest(double amount)
	{
		payment.addToTotal(50);
		payment.addToTotal(25);
		
		if (!(payment.getTotal() == 75))
			fail();
	}
	

}
