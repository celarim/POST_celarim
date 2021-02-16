package PointOfSaleTerminal;

import java.util.*;

/**
 * 
 */
public class Sale extends PaymentInterface{

    /**
     * Default constructor
     */
    public Sale() {
    	this.Cart = new ProductList();
    	this.SDBC = new StockDBConnecter();
    	this.SHDBC = new SaleHistoryDBConnecter();
    }

	@Override
	public void calc() {
		// TODO Auto-generated method stub
		for(Product p: Cart.getQuantity().keySet()) {
			SDBC.ChangeStockCount(p.getPNumber(), -Cart.getQuantity().get(p));
		}
		SHDBC.PushSaleHistory(Cart.getQuantity());
	}
	
	/**
	 * 
	 */
	private ProductList Cart;
	
	/**
	 * @return 
	 * 
	 */
	public List<Product> getProductList() {
		return Cart.PL;
	}
	
	/**
	 * 
	 */
	public Product AddProduct(String pnum) {
		Product product = this.SDBC.FindStock(pnum);
		if (product != null) {
			Cart.PL.add(product);
		}
		return product;
	}
	
	/**
	 * 
	 * @param pnum
	 */
	public void DeleteProduct(int index) {
		Cart.PL.remove(index);
	}
	
	/**
	 * 
	 */
	public int getTotal() {
		return Cart.getTotal();
	}
	
	public void Discount(int index, int dis) {
		Cart.discount(index, dis);
	}
	

}