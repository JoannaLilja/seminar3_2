package model.purchase;

import junit.framework.TestCase;

public class PriceTest extends TestCase
{

	Price price = new Price(100);
	

	public void getAmountTest()
	{
		price.getAmount();
	}
	

	public void getTaxTest() 
	{
		try
		{
			price.getTax();
		}catch(Exception e)
		{
			fail();
		}
	}

}
