package PointOfSaleTerminal;

import java.io.*;
import java.nio.file.Paths;


/**
 * 
 */
public class StockDBConnecter {


	/**
     * Default constructor
     */
    public StockDBConnecter() {
    	MyDB = Paths.get("").toAbsolutePath().toString()
    			.concat("\\src\\PointOfSaleTerminal\\products.txt");
    }

    /**
     * 
     */
    private String MyDB;

    /**
     * @param PNumber 
     * @return PNumber를 가지는 Product
     */
    public Product FindStock(String PNumber) {
        // TODO implement here
    	try {
	    	File file = new File(MyDB);
	    	FileReader filereader = new FileReader(file);
	    	@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
	    	String line = "";
	    	while((line = bufferedreader.readLine()) != null) {
	    		String[] ps = line.split(" ");
	    		if (ps[1].equals(PNumber)) {
	    			Product a = new Product(ps[0], ps[1], ps[2],
	    					Integer.parseInt(ps[3]), Integer.parseInt(ps[3]));
	    			return a;
	    		}
	    	}
	    	bufferedreader.close();
    	} catch(FileNotFoundException e) {
			System.out.println("there's no product!");
		} catch(IOException e) {
			System.out.println("io error!");
		}
    	return null;
    }

    /**
     * @param PNumber 
     * @param count 
     * @return PNumber를 가지는 Product의 개수를 count만큼 조정한다.
     */
    public void ChangeStockCount(String PNumber, int count) {
        // TODO implement here
    	String w = "";
    	boolean ischange = false;

    	try {
	    	File file = new File(MyDB);
	    	FileReader filereader = new FileReader(file);
	    	@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
	    	String line = "";
	    	while((line = bufferedreader.readLine()) != null) {
	    		String[] ps = line.split(" ");
	    		if (ps[1].equals(PNumber)) {
	    			String newline = ps[0].concat(" ").concat(ps[1]).concat(" ").concat(ps[2])
	    					.concat(" ").concat(ps[3])
	    					.concat(" ").concat(Integer.toString(Integer.parseInt(ps[4])+count)).concat("\n");
	    			w = w.concat(newline);
	    			ischange = true;
	    		} else {
	    			w = w.concat(line).concat("\n");
	    			
	    		}
	    	}
	    	bufferedreader.close();
    	} catch(FileNotFoundException e) {
			System.out.println("there's no product!");
		} catch(IOException e) {
			System.out.println("io error!");
		}
    	
    	if (ischange) {
    		try {
    			File file = new File(MyDB);
    			FileWriter filewriter = new FileWriter(file);
    			filewriter.write(w);
    			filewriter.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

}