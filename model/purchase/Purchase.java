package model.purchase;

import java.io.IOException;
import java.util.LinkedList;
import integration.PurchaseUpdate;
import model.payment.Payment;
import integration.ErrorLogHandler;
import integration.InventoryManager;
import integration.RetailItemNotFoundException;

/**
 * Handles the purchase itself, that is the RetailItems to be bought. 
 * Also holds a Payment, which in turn handles the payment made by the customer. 
 */
public class Purchase
{
	
	private Payment payment;
	private InventoryManager im;
	private LinkedList<RetailItem> RetailItems = new LinkedList<RetailItem>();
	private ErrorLogHandler elh;
	
	/**
	 * Instantiates a purchase with a printer connection. 
	 * @param elh
	 */
	public Purchase()
	{
		try {
			elh = new ErrorLogHandler();
		} catch (IOException e) 
		{
			System.err.println("ErrorLogHandler could not be created");
			e.printStackTrace();
		}
		payment = new Payment(elh);
		im = new InventoryManager(elh);
	}
	
	/**
	 * Getter for the Payment. 
	 * @return payment
	 */
	public Payment getPayment()
	{
		return payment;
	}
	/**
	 * Getter for the list of RetailItems in the purchase
	 * @return RetailItemList
	 */
	public LinkedList<RetailItem> getRetailItems()
	{
		return RetailItems;
	}
	
	/**
	 * Adds an RetailItem 
	 * @param id
	 * @return
	 * @throws RetailItemNotFoundException 
	 */
	public DisplayRetailItems addRetailItem(int id) throws RetailItemNotFoundException
	{
		RetailItem foundRetailItem = null;
		RetailItem newRetailItem = null;
		
		for(RetailItem RetailItem : RetailItems)
		{
			if(RetailItem.getId() == id)
			{
				foundRetailItem = RetailItem;
				break;
			}
		}
		
		if(foundRetailItem != null)
		{
			foundRetailItem.incrementCount();
			payment.addToTotal(foundRetailItem.getPrice().getAmount());
			payment.addToTotal(foundRetailItem.getPrice().getTax().getAmount());

		}
		else
		{
			newRetailItem = im.getRetailItem(id);

			RetailItems.add(newRetailItem);
			payment.addToTotal(newRetailItem.getPrice().getAmount());
			payment.addToTotal(newRetailItem.getPrice().getTax().getAmount());

		}
		
		return new DisplayRetailItems(RetailItems, payment.getTotal());
		
	}
	
	/**
	 * Called when a purcase is done and updates must be executed. 
	 */
	public void finalize()
	{
		
		PurchaseUpdate update = new PurchaseUpdate(this, elh);
		update.execute();
		
	}
	
	/**
	 * Resets the purchase
	 */
	public void reset()
	{
		RetailItems.clear();
		payment.reset();
	}


	
}
