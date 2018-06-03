package integration;

import java.util.LinkedList;
import model.purchase.RetailItem;

/**
 * Simulates connection to an inventory system by hardcoding the results. 
 */
public class InventoryManager
{

	ErrorLogHandler elh;
	
	public InventoryManager(ErrorLogHandler elh)
	{
		this.elh = elh;
	}
	
	/**
	 * Returns RetailItems from a hardcoded set of results. 
	 * @param id
	 * @return RetailItem
	 * @throws RetailItemNotFoundException
	 */
	public RetailItem getRetailItem(int id) throws RetailItemNotFoundException
	{
		if(id == 0)
			return new RetailItem(0, 10, "green shirt"); 
		if(id == 1)
			return new RetailItem(1, 10, "purple plums");
		if(id == 2)
			return new RetailItem(2, 10, "apple, Spain");
		if(id == 3)
			return new RetailItem(3, 10, "blue jeans");
		if(id == 4)
			return new RetailItem(4, 10, "mysterious RetailItem");
		if(id == 5)
			return new RetailItem(5, 10, "very mysterious RetailItem");
		if(id == 6)
			return new RetailItem(6, 10, "mysteriously mysterious RetailItem");
		if(id == 7)
			return new RetailItem(7, 10, "mysterious RetailItem of mystery");
		if(id == 8)
			return new RetailItem(8, 10, "mysteriously mysterious RetailItem of mystery");
		if(id == 9)
			return new RetailItem(9, 10, "mysteriously long description of mysterious RetailItem");
		if(id == 10)
			return new RetailItem(10, 10, "PANSAKES");
		
		throw new RetailItemNotFoundException(id, elh);

	}

	/**
	 * Pretends to clear the listed RetailItems from the external inventory system and prints a short message. 
	 * @param RetailItemList
	 */
	public void clearRetailItems(LinkedList<RetailItem> RetailItems) 
	{
		System.out.println("RetailItems cleared from inventory. ");
	}
	
}
