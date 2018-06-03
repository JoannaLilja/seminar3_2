package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import controller.Controller;
import integration.DbFailureException;
import integration.RetailItemNotFoundException;
import model.purchase.DisplayRetailItems;
import model.purchase.RetailItem;

/**
 * JFrame interface that, via the Controller, handles all communications between the system and the user (apart from total revenue which is handled by TotalRevenueView). 
 */
public class View extends JFrame implements ActionListener
{
	
	//Controller: 
	Controller contr;
	
	//-------------Interface components----------------
	
	JLabel totalLabel = new JLabel("Total: ");
	JLabel enterRetailItemLabel = new JLabel("Item ID: ");
	JLabel enterCountLabel = new JLabel("Item count: ");
	JLabel discountIdLabel = new JLabel("Discount id: ");
	JLabel customerIdLabel = new JLabel("Customer id: ");
	JLabel payLabel = new JLabel("Amount paid: ");
	
	JTextArea RetailItemList = new JTextArea(1, 1);
	JScrollPane textPane = new JScrollPane (RetailItemList);
	JLabel runningTot = new JLabel("0:-");
	JTextField RetailItemField = new JTextField("");
	JTextField countField = new JTextField("1");
	JButton enterBtn = new JButton ("ENTER");
	JButton doneBtn = new JButton("DONE");
	JButton startSaleBtn = new JButton("NEW SALE");
	
	JLabel discount = new JLabel();

	JTextField discountField = new JTextField();
	JTextField customerField = new JTextField();

	JButton discountBtn = new JButton("CONFIRM DISCOUNT");
	JButton noDiscountBtn = new JButton("SKIP DISCOUNT");
	
	JLabel pay = new JLabel();
	
	JTextField payField = new JTextField();
	JButton payBtn = new JButton("PAY");

	JPanel topPanel = new JPanel(new GridLayout(1,1));
	JPanel infoPanel = new JPanel(new GridLayout(1,1));
	JPanel inputPanel = new JPanel(new GridLayout(3,3));
	JPanel sidePanel = new JPanel(new GridLayout(10,2));
	JPanel discountPanel = new JPanel(new GridLayout(1,1));
	JPanel payPanel = new JPanel(new GridLayout(1,1));
	
	/**
	 * Constructor for View. Takes the Controller for communication with the rest of the program. 
	 * @param contr
	 */
	public View(Controller contr)
	{
		super("POS"); 

		this.contr = contr;
		
		//Set window options: 
		setSize(950,700); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		//Get contentPane: 
		Container contentPane = getContentPane(); 
	
		//Set each panel to "opaque", so that the background becomes visible
		inputPanel.setOpaque(false);
		setVisible (true);
		
		//Center window: 
		setLocationRelativeTo(null);

		//Make RetailItemList non-editable: 
		RetailItemList.setEditable(false);

		//Add ActionListeners: 
		enterBtn.addActionListener(this);
		doneBtn.addActionListener(this);
		discountBtn.addActionListener(this);
		noDiscountBtn.addActionListener(this);
		payBtn.addActionListener(this);
		startSaleBtn.addActionListener(this);

		//Add panels to different compass points on the content area:
		contentPane.add("Center", RetailItemList);
		contentPane.add("South", inputPanel);
		contentPane.add("East", sidePanel);
	    
		
		//-----------Add elements to panels------------
		
		inputPanel.add(totalLabel);
		inputPanel.add(enterRetailItemLabel);
		inputPanel.add(enterCountLabel);

		inputPanel.add(runningTot);
		inputPanel.add(RetailItemField);
		inputPanel.add(countField);
		inputPanel.add(new JLabel());
		inputPanel.add(enterBtn);
		inputPanel.add(doneBtn);
		
		//---------------SidePanel layout--------------
		
		//--discount--
		sidePanel.add(new JLabel());
		sidePanel.add(discount);
		
		sidePanel.add(discountIdLabel);
		sidePanel.add(discountField);

		sidePanel.add(customerIdLabel);
		sidePanel.add(customerField);

		sidePanel.add(new JLabel());
		sidePanel.add(discountBtn);
		
		sidePanel.add(new JLabel());
		sidePanel.add(noDiscountBtn);
		
		
		//--pay--
		sidePanel.add(new JLabel());
		sidePanel.add(payLabel);

		sidePanel.add(new JLabel());
		sidePanel.add(payField);

		sidePanel.add(new JLabel());
		sidePanel.add(payBtn);
		
		sidePanel.add(new JLabel());
		sidePanel.add(new JLabel());
		
		sidePanel.add(new JLabel());
		sidePanel.add(startSaleBtn);

		
		
				
		sidePanel.setPreferredSize(new Dimension(355,0));
				
		
		//-----------------Set initial visibilities------------------
		
		sidePanel.setVisible(false);
		discountPanel.setVisible(false);
		payPanel.setVisible(false);
		discount.setVisible(false);
		doneBtn.setEnabled(false);
		payLabel.setVisible(false);
		payField.setVisible(false);
		payBtn.setVisible(false);
		startSaleBtn.setVisible(false);
		
		// Set styling: 
		setStyle();

	}

	/**
	 * Called when an action is performed. Takes an ActionEvent that identifies the action.
	 */
	public void actionPerformed(ActionEvent e)
	{

		if(e.getSource().equals(enterBtn))
		{
			String input;
			int RetailItemId = -1;
			int RetailItemCount = -1;
			
			boolean countSuccess = false;
			boolean RetailItemSuccess = false;
			
			input = RetailItemField.getText();
			try	
			{ 
				RetailItemId = Integer.valueOf(input); 
				RetailItemSuccess = true;
			}catch (Exception ex) 
			{
				RetailItemField.setText("not an integer");
			}
			
			input = countField.getText();
			try	
			{ 
				RetailItemCount = Integer.valueOf(input); 
				if( RetailItemCount < 1)
					countField.setText("not >0");
				else
					countSuccess = true;
			}catch (Exception ex) 
			{
				countField.setText("not an integer");
			}
			
			if( RetailItemSuccess == true && countSuccess == true)
			{
				addRetailItem(RetailItemId, RetailItemCount);
			}
			
		}
		
		if(e.getSource().equals(doneBtn))
		{
			enterRetailItemsDoneStyle();
		}
		
		if(e.getSource().equals(discountBtn))
		{
			String input;

			int discountId = -1;
			int customerId = -1;
			
			boolean discountSuccess = false;
			boolean customerSuccess = false;
			
			
			input = discountField.getText();
			try	
			{ 
				discountId = Integer.valueOf(input); 
				discountSuccess = true;
			}
			catch (Exception ex) 
			{
				discountField.setText("not an integer");
			}
			
			
			input = customerField.getText();
			try	
			{ 
				customerId = Integer.valueOf(input); 
				customerSuccess = true;
			}
			catch (Exception ex) 
			{
				customerField.setText("not an integer");
			}
			
			
			if( discountSuccess && customerSuccess)
			{
				try {
					contr.enterDiscount(discountId);
				} catch (DbFailureException e1) {
					discountField.setText("Discount " + discountId + " not found.");
					e1.printStackTrace();
				}
				try {
					if(contr.validateDiscount(customerId))
					{
						discountDoneStyle();
						runningTot.setText(contr.getDiscountedAmount() + ":-");//TODO contr method for this
					}
				} catch (DbFailureException e1) 
				{
					discountField.setText("Customer " + customerId + " not valid");
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource().equals(noDiscountBtn))
		{
			discountDoneStyle();
		}
		
		if(e.getSource().equals(payBtn))
		{
			String input;
			
			double amount = 0;
			boolean success = false;
			double change;
			double newTotal = contr.getDiscountedAmount();
			
			input = payField.getText();
			
			//change = contr.pay(50);

			try	
			{ 
				amount = Double.valueOf(input); 
				success= true;
			}
			catch (Exception ex) 
			{
				payField.setText("not a number");
			}		
			
			if(success && amount>=newTotal)
			{
				contr.pay(amount);
				change = contr.getChange();
				payLabel.setText("Change: " + (change) + ":-");
				startSaleBtn.setVisible(true);
			}
			else
			{
				payField.setText("not above total");
			}
			
		}
		
		if(e.getSource().equals(startSaleBtn))
		{
			contr.startSale();
			startSaleBtn.setVisible(false);
			resetStyle();
		}

	}
	
	private void addRetailItem(int RetailItemId, int RetailItemCount)
	{
		DisplayRetailItems displayRetailItems = null;
		LinkedList<RetailItem> RetailItems = new LinkedList<RetailItem>();

		for (int i = 0; i < RetailItemCount; i++)
			try {
				displayRetailItems = contr.addRetailItem(RetailItemId);
			} catch (RetailItemNotFoundException e) 
			{
				RetailItemField.setText("Item " + RetailItemId + " not found");
				e.printStackTrace();
			}
		
		if (displayRetailItems != null) 
		{
			RetailItems = displayRetailItems.getRetailItems();
			runningTot.setText(displayRetailItems.getTotal() + ":-");

		}
		
		RetailItemList.setText("");
		for(RetailItem RetailItem : RetailItems)
		{
			int id = RetailItem.getId();
			String desc = RetailItem.getDescription().toString();
			int count = RetailItem.getCount();
			double price = RetailItem.getPrice().getAmount();
			double tax = RetailItem.getPrice().getTax().getAmount();
			
			RetailItemList.append( desc + " [ID: " + id + "], " + "amount: " + count 
									+ ", Price: " + price*count + ":- + " + tax*count + ":- tax\n" );
		}
				
		doneBtn.setEnabled(true);
		doneBtn.setBackground(Color.GREEN);
	}
	
	
	private void showDiscount()
	{
		discountIdLabel.setVisible(true);
		customerIdLabel.setVisible(true);
		discount.setVisible(true);
		discountBtn.setVisible(true);
		noDiscountBtn.setVisible(true);
		discountField.setVisible(true);
		customerField.setVisible(true);
	}
	
	private void discountDoneStyle()
	{
		discountIdLabel.setVisible(false);
		customerIdLabel.setVisible(false);
		discount.setVisible(false);
		discountBtn.setVisible(false);
		noDiscountBtn.setVisible(false);
		discountField.setVisible(false);
		customerField.setVisible(false);
		
		payLabel.setVisible(true);
		payField.setVisible(true);
		payBtn.setVisible(true);
	}
	
	private void enterRetailItemsDoneStyle()
	{
		RetailItemField.setVisible(false);
		countField.setVisible(false);
		enterBtn.setVisible(false);
		doneBtn.setVisible(false);
		
		enterRetailItemLabel.setVisible(false);
		enterCountLabel.setVisible(false);
		
		sidePanel.setVisible(true);
		
		showDiscount();
	}
	
	private void setStyle() 
	{
		
		enterBtn.setPreferredSize(new Dimension(10,60));

		totalLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		enterRetailItemLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		enterCountLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		
		RetailItemList.setFont(new Font("Arial", Font.PLAIN, 16));

		enterBtn.setFont(new Font("Arial", Font.BOLD, 30));
		startSaleBtn.setFont(new Font("Arial", Font.BOLD, 20));
		doneBtn.setFont(new Font("Arial", Font.BOLD, 30));
		RetailItemField.setFont(new Font("Arial", Font.ITALIC, 25));
		countField.setFont(new Font("Arial", Font.ITALIC, 25));
		runningTot.setFont(new Font("Arial", Font.PLAIN, 30));
				
		discountBtn.setFont(new Font("Arial", Font.BOLD, 14));
		noDiscountBtn.setFont(new Font("Arial", Font.BOLD, 14));
		
		payBtn.setFont(new Font("Arial", Font.BOLD, 30));	
		payBtn.setBackground(Color.CYAN);



		discountBtn.setForeground(Color.BLACK);noDiscountBtn.setForeground(Color.BLACK);
		
		discountBtn.setBackground(Color.CYAN);
		noDiscountBtn.setBackground(Color.RED);

		enterBtn.setBackground(Color.CYAN);
		doneBtn.setBackground(Color.GRAY);
		runningTot.setForeground(Color.RED);
		payBtn.setBackground(Color.GREEN);
		startSaleBtn.setBackground(Color.CYAN);

		


		
	}
	
	void resetStyle()
	{
		sidePanel.setVisible(false);
		discountPanel.setVisible(false);
		payPanel.setVisible(false);
		discount.setVisible(false);
		doneBtn.setEnabled(false);
		payLabel.setVisible(false);
		payField.setVisible(false);
		payBtn.setVisible(false);
		startSaleBtn.setVisible(false);
		//runningTot = new JLabel("0:-");
		
		discountField.setText("");
		customerField.setText("");
		payField.setText("");
		RetailItemList.setText("");
		payLabel.setText("");

		RetailItemField.setText("");
		runningTot.setText("0:-");
		doneBtn.setBackground(Color.GRAY);

		RetailItemField.setVisible(true);
		countField.setVisible(true);
		enterBtn.setVisible(true);
		doneBtn.setVisible(true);
	}
}
