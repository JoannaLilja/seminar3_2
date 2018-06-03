package model.purchase;

/**
 * Holds tax related functionality. For now, the tax is simply declared a percentage of the price by the constructor. 
 *
 */
public class Tax
{

	double tax;
	
	Tax(double price)
	{
		tax = price*0.1;
	}
	
	/**
	 * Getter for the amount of tax
	 * @return tax
	 */
	public double getAmount()
	{
		return tax;
	}
	
	
}
