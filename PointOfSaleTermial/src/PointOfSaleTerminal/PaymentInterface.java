package PointOfSaleTerminal;

import java.util.*;

/**
 * 
 */
public abstract class PaymentInterface {

    /**
     * 
     */
    public SaleHistoryDBConnecter SHDBC;

    /**
     * 
     */
    public StockDBConnecter SDBC;



    /**
     * 
     */
    public abstract void calc();

}