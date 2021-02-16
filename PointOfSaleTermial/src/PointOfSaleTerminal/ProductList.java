package PointOfSaleTerminal;

import java.util.*;

/**
 * 
 */
public class ProductList {

    /**
     * Default constructor
     */
    public ProductList() {
    	quantity = new HashMap<Product, Integer>();
    	PL = new ArrayList<Product>();
    }

    /**
     * 
     */
    public HashMap<Product, Integer> quantity;

    /**
     * 
     */
    public List<Product> PL;



    /**
     * @return 
     * 
     */
    public  HashMap<Product, Integer> getQuantity() {
        // TODO implement here
    	this.quantity = new HashMap<Product, Integer>();
    	for(int i=0; i<this.PL.size(); i++) {
    		Product P = PL.get(i);
    		int q = 1;
    		boolean eq = false;
    		for (int j=0; j<i; j++) {
    			if ((i!=j)&&PL.get(i).equals(PL.get(j))) {
    				P = PL.get(j);
    				eq = true;
    				break;
    			}
    		}
    		if (eq) {
    			q = 1+ this.quantity.get(P);
    			this.quantity.remove(P);
    		}	
    		this.quantity.put(P, q);
    	}
    	return this.quantity;
    }

    /**
     * @return 
     * 
     */
    public int getTotal() {
        // TODO implement here
    	this.getQuantity();
    	int total=0;
    	for (Product P: this.quantity.keySet())
    		total += this.quantity.get(P)*P.getDisPrice();
    	return total;
    }

    /**
     * 
     */
    public void discount(int index, int dis) {
        // TODO implement here
    	PL.get(index).setDisPrice(dis);
    	//can be error
    }

}