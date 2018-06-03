package model.purchase;

import integration.DbFailureException;
import integration.RetailItemNotFoundException;
import junit.framework.TestCase;

public class PurchaseTest extends TestCase 
{
	Purchase purchase = new Purchase();
	
	/**
	 * Tests adding an item to the list
	 * 
	 * @throws RetailItemNotFoundException
	 */
	public void testAddRetailItem() throws RetailItemNotFoundException
	{
		
		purchase.addRetailItem(5);
		
		if(!(purchase.getRetailItems().get(0).getId() == 5))
		{
			fail();
		}

	}

	
	public void testFinalize()
	{
		purchase.finalize();

		fail();
	}
	
	/**
	 * Tests if reset purchase empties the items and discount. 
	 * 
	 * @throws RetailItemNotFoundException 
	 * @throws DbFailureException 
	 */
	public void testReset() throws RetailItemNotFoundException, DbFailureException
	{
		
		purchase.addRetailItem(5);
		purchase.getPayment().enterDiscount(1);

		purchase.reset();
		
		if(!purchase.getRetailItems().isEmpty())
			fail();
		if(purchase.getPayment().hasDiscount())
			fail();
			
	}

}
