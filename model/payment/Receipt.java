package model.payment;

import java.util.LinkedList;

import integration.Printer;
import model.purchase.RetailItem;
import model.purchase.Purchase;

class Receipt 
{
	
	private Printer printer = new Printer();
	private LinkedList<String> receiptTextLines = new LinkedList<String>(); //Each node represents a line on the receipt
	
	Receipt()
	{

	}

	void print(Purchase purchase)
	{
		setreceiptTextLines(purchase);
		printer.printReceipt(receiptTextLines);
	}

	private void setreceiptTextLines(Purchase purchase)
	{
				
		receiptTextLines.add("");

		addRetailItems(purchase);
		
		double total = purchase.getPayment().getTotal();
		double amountPaid = purchase.getPayment().getAmountPaid();
		int discountId;
		int discountPerc;
		//double newTotal;
		
		receiptTextLines.add("-------------");
		receiptTextLines.add("Total: " + total + ":-");
		receiptTextLines.add("-------------");

		
		if(purchase.getPayment().hasDiscount())
		{
			discountId = purchase.getPayment().getDiscount().getId();
			discountPerc =  (int)((1-purchase.getPayment().getDiscount().getMultiplier())*100);
			total = purchase.getPayment().getTotal()*purchase.getPayment().getDiscount().getMultiplier();

			receiptTextLines.add( "Discount " + discountId + " redeemed: " + discountPerc + "% off");
			receiptTextLines.add("New total: " + total + ":-");
			receiptTextLines.add("-------------");
		}
		
		receiptTextLines.add("Entered: " + amountPaid + ":-");
		receiptTextLines.add("Change: " + (amountPaid - total) + ":-");
		receiptTextLines.add("-------------");

		receiptTextLines.add("");

	}
	
	private void addRetailItems(Purchase purchase)
	{
		int id;
		int count;
		double price;
		double tax;
		
		
		receiptTextLines.add("RetailItems: ");
		
		LinkedList<RetailItem> RetailItems = purchase.getRetailItems();	
		for(RetailItem RetailItem : RetailItems)
		{
			id = RetailItem.getId();
			String desc = RetailItem.getDescription().toString();
			count = RetailItem.getCount();
			price = RetailItem.getPrice().getAmount();
			tax = RetailItem.getPrice().getTax().getAmount();
			
			receiptTextLines.add( " * " + desc + " [ID: " + id + "], " + "amount: " + count 
										+ ", Price: " + price*count + ":- + " + tax*count + ":- tax" );
		}
		
		receiptTextLines.add("");

	}
	
}
