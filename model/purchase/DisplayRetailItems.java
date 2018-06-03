package model.purchase;

import java.util.LinkedList;

/**
 * DTO grouping the RetailItems and total
 */
public class DisplayRetailItems 
{
	
	LinkedList<RetailItem> RetailItems;
	double runningTotal;
	
	DisplayRetailItems(LinkedList<RetailItem> RetailItems, double runningTotal)
	{
		this.RetailItems = RetailItems;
		this.runningTotal = runningTotal;
	}
	
	/**
	 * Getter for RetailItem list
	 * @return RetailItem
	 */
	public LinkedList<RetailItem> getRetailItems()
	{
		return RetailItems;
	}
	
	/**
	 * Getter for running total
	 * @return runningTotal
	 */
	public double getTotal()
	{
		return runningTotal;
	}

}
