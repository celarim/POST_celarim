package PointOfSaleTerminal;

import java.util.*;

/**
 * 
 */
public class POST {

    /**
     * Default constructor
     */
    public POST() {
    	bank = new Bank();
    	sale = new Sale();
    	refund = new Refund();
    }

    /**
     * 
     */
    private Bank bank;

    /**
     * 
     */
    private Refund refund;

    /**
     * 
     */
    private Sale sale;

    /**
     * 
     */
    private POSTUI UI;








    /**
     * @param PNumber
     */
    public void AddCart(String PNumber ) {
        // TODO implement here
    	this.sale.AddProduct(PNumber);
    }
    
    /**
     * 
     */
    public ArrayList<String> getCart(){
    	List<Product> list = this.sale.getProductList();
    	ArrayList<String> str = new ArrayList<String>();
    	for(Product p : list){
    		str.add(p.getName().concat("\t\t").concat(p.getPNumber())
    				.concat("\t\t").concat(Integer.toString(p.getPrice()))
    				.concat("\t\t").concat(Integer.toString(p.getDisPrice())));
    	}
    	
		return str;
    }

    /**
     * @param index
     */
    public void DeleteCart(int index) {
        // TODO implement here
    	this.sale.DeleteProduct(index);
    }

    /**
     * @param index 
     * @param howmuch
     */
    public void Discount(int index, int howmuch) {
        // TODO implement here
    	this.sale.Discount(index, howmuch);
    }

    /**
     * @param date
     */
    public boolean StartRefund(int date) {
        // TODO implement here
    	return this.refund.FindHistory(date);
    	
    }

    /**
     * @param PNumber
     */
    public void SearchProduct(String PNumber) {
        // TODO implement here
    }

    /**
     * 
     */
    public void HowSold() {
        // TODO implement here
    }

    /**
     * 
     */
    public void AddStock() {
        // TODO implement here
    }

    /**
     * 
     */
    public void DeleteStock() {
        // TODO implement here
    }

    /**
     * @param PNumber
     */
    public void FindStock(String PNumber) {
        // TODO implement here
    }

    /**
     * 
     */
    public boolean Purchase() {
        // TODO implement here
    	this.sale.calc();
    	this.sale = new Sale();
    	return true;
    }
    
    public boolean Repurchase() {
    	this.refund.calc();
    	this.refund = new Refund();
    	return true;
    }

}