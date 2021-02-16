package PointOfSaleTerminal;


/**
 * 
 */
public class Product {

    /**
     * Default constructor
     */
    public Product(String name, String pnum, String cat, int price, int disprice) {
    	this.Name = name;
    	this.PNumber = pnum;
    	this.Category = cat;
    	this.Price = price;
    	this.DisPrice = disprice;
    }

    /**
     * 
     */
    private String Name;

    /**
     * 
     */
    private String PNumber;

    /**
     * 
     */
    private String Category;

    /**
     * 
     */
    public int Price;

    /**
     * 
     */
    public int DisPrice;




    /**
     * @return Product Name
     */
    public String getName() {
        // TODO implement here
        return this.Name;
    }

    /**
     * @return Product Number
     */
    public String getPNumber() {
        // TODO implement here
        return this.PNumber;
    }

    /**
     * @return Category
     */
    public String getCategory() {
        // TODO implement here
        return this.Category;
    }

    /**
     * @return Price
     */
    public int getPrice() {
        // TODO implement here
        return this.Price;
    }

    /**
     * set discount percent price
     */
    public void setDisPrice( int dis) {
        // TODO implement here
        this.DisPrice = this.Price/100*(100-dis);
    }
    
    /**
     * @return Disprice
     */
    public int getDisPrice() {
    	//TODO implement here
    	return this.DisPrice;
    }
    public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Product))
			return false;
		
		Product a = (Product) o;
		if (this.PNumber.equals(a.PNumber))
			return true;
		else
			return false;
    	
    }

}