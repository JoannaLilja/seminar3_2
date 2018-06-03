package model.payment;

import java.io.IOException;

import integration.DbFailureException;
import integration.ErrorLogHandler;
import junit.framework.TestCase;


public class DiscountTest extends TestCase
{
	
	Discount discount;
	
	public DiscountTest() throws DbFailureException, IOException
	{
		discount = new Discount(1, new ErrorLogHandler());
	}
	
	/**
	 * Returns true if the discount has been successfully validated
	 * 
	 * @return valid
	 * @throws DbFailureException 
	 */
	public void isValidValidateTest() throws DbFailureException
	{
		if (discount.isValid())
			fail();
		
		discount.validate(1);
		
		if (!discount.isValid())
			fail();

	}

}
