package model.purchase;

import junit.framework.TestCase;

public class RetailItemTest extends TestCase
{
	
	int id = 5;
	double price = 10;
	String testDescription = "test description";

	RetailItem RetailItem = new model.purchase.RetailItem(5, 10, testDescription);
	
	
	/**
	 * Tests weather an item of the correct id is retrieved
	 */
	public void testGetId()
	{
		
		if (!(RetailItem.getId() == id))
			fail();
		
	}

	/**
	 * Tests weather an item of the correct description is retrieved
	 */
	public void getDescriptionTest()
	{
		
		if (!(RetailItem.getDescription().equals(testDescription)))
			fail();	
		
	}

	/**
	 * Tests weather an item of the correct price is retrieved
	 */
	public void getPriceTest()
	{
		
		RetailItem RetailItem = new model.purchase.RetailItem(5, price, testDescription);
		
		if (!(RetailItem.getPrice().getAmount() == price))
			fail();	

	}
	
	/**
	 * Tests if incrementCount works as it should. Also makes use of getCount
	 */
	public void incrementCountTest()
	{
		RetailItem RetailItem = new model.purchase.RetailItem(5, 10, testDescription);

		RetailItem.incrementCount();
		
		if (!(RetailItem.getCount() == 2))
			fail();	
	}

}
