package controller;
import integration.DbFailureException;
import integration.RetailItemNotFoundException;
import junit.framework.TestCase;

public class ControllerTest extends TestCase
{
	
	Controller contr;
	
	/**
	 * Instantiates the unit test with a Controller
	 */
	public ControllerTest()
	{
		contr = new Controller();	
	}
	
	/**
	 * Tests if an added item shows up in the item list upon adding it
	 * 
	 * @throws RetailItemNotFoundException
	 */
	public void addRetailtemTest() throws RetailItemNotFoundException
	{
		
		contr.addRetailItem(1);
		
		if (contr.getPurchase().getRetailItems().isEmpty())
			fail();
	}
	
	/**
	 * Checks if a discount validation is successful after entering the discoount
	 * 
	 * @throws DbFailureException
	 */
	public void enterValidateDiscountTest() throws DbFailureException
	{
		contr.enterDiscount(1);
		if (!contr.validateDiscount(1))
			fail();
	}
	
	/**
	 * Tests if startSale resets the old sale
	 * @throws DbFailureException 
	 * @throws RetailItemNotFoundException 
	 */
	public void startSaleTest() throws DbFailureException, RetailItemNotFoundException
	{
		contr.addRetailItem(2);
		contr.enterDiscount(2);

		contr.startSale();
		if(!contr.getPurchase().getRetailItems().isEmpty())
			fail();
	}
	
	
	
}
