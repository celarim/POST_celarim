package PointOfSaleTerminal;

import java.util.*;

/**
 * 
 */
public class Refund extends PaymentInterface {

    /**
     * Default constructor
     */
    public Refund() {
    	SDBC = new StockDBConnecter();
    	SHDBC = new SaleHistoryDBConnecter();
    }
    private int date;
    private ArrayList<String> refund;

    /**
     * 
     */
    public boolean FindHistory(int date) {
        // TODO implement here
    	this.date = date;
    	refund = SHDBC.FindSaleHistory(date);
    	if (refund.size() == 0) 
    		return false;
    	else
    		return true;
    }

    /**
     * 
     */
    public void calc() {
        // TODO implement here
    	for(String str: refund) {
    		if (str.equals("0")) {
    			
    		} else {
    			SDBC.ChangeStockCount(str.split(" ")[1],
    					Integer.parseInt(str.split(" ")[5]));
    		}
    	}
    	SHDBC.ChangeIsRefund(this.date);
    }

}