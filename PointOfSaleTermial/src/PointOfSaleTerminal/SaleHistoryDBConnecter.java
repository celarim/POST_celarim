package PointOfSaleTerminal;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * 
 */
public class SaleHistoryDBConnecter {

    /**
     * Default constructor
     */
    public SaleHistoryDBConnecter() {
    	MyDB = Paths.get("").toAbsolutePath().toString()
    			.concat("\\src\\PointOfSaleTerminal\\bills\\");
    			
    }

    /**
     * 
     */
    private String MyDB;

    /**
     * @param hashMap 
     * @return
     */
    public void PushSaleHistory(HashMap<Product, Integer> hashMap) {
        // TODO implement here
    	LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
		
		String formattedDateTime = currentDateTime.format(formatter);
		String w = "";
		// read bill.txt all
		try {
    		File file = new File(MyDB.concat("bills.txt"));
    		FileReader filereader = new FileReader(file);
    		@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
    		String line = "";
    		while((line = bufferedreader.readLine()) != null) {
    			w = w.concat(line).concat("\n");
    		}
    	}catch(FileNotFoundException e) {
			System.out.println("there's no product!");
		} catch(IOException e) {
			System.out.println("io error!");
		}
		
		//write bill.txt plus now
		try {
    		File file = new File(MyDB.concat("bills.txt"));
    		FileWriter filewriter = new FileWriter(file);
    		filewriter.write(w.concat(formattedDateTime).concat("\n"));
    		filewriter.close();
    		
		} catch(IOException e) {
			System.out.println("io error!");
		}
		
		
		String path = MyDB.concat(formattedDateTime).concat(".txt");
		//write new date.txt
		try {
			File file = new File(path);
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
			for(Product p: hashMap.keySet()) {
				String newline = p.getName().concat(" ").concat(p.getPNumber()).concat(" ")
						.concat(p.getCategory()).concat(" ")
						.concat(Integer.toString(p.getPrice()))
    					.concat(" ").concat(Integer.toString(p.DisPrice))
    					.concat(" ").concat(Integer.toString(hashMap.get(p)))
    					.concat("\n");
				bufferedwriter.write(newline);
			}
			bufferedwriter.write("0");
			bufferedwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
    }

    /**
     * @param date 
     * @return
     */
    public ArrayList<String> FindSaleHistory(int date) {
        // TODO implement here

		String strdate = Integer.toString(date);
		if (strdate.length() == 9) {
			strdate = "0".concat(strdate);
		}
    	ArrayList<String> read = new ArrayList<String>();
    	boolean isExist = false;
    	try {
    		File file = new File(MyDB.concat("bills.txt"));
    		FileReader filereader = new FileReader(file);
    		@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
    		String line = "";
    		while((line = bufferedreader.readLine()) != null) {
    			System.out.println(line);
    			System.out.println(strdate);
    			if(line.equals(strdate)) {
    				isExist = true;
    				break;
    			}
    		}
    	}catch(FileNotFoundException e) {
			System.out.println("there's no product!");
		} catch(IOException e) {
			System.out.println("io error!");
		}
    	
    	if (isExist) {
    		try {
        		File file = new File(MyDB.concat(strdate).concat(".txt"));
				FileReader filereader = new FileReader(file);
				BufferedReader bufferedreader = new BufferedReader(filereader);
				String line = "";
		    	while((line = bufferedreader.readLine()) != null) {
		    		read.add(line);
		    	}
				bufferedreader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(IOException e) {
				System.out.println("io error!");
			}
    		
    	}
    	
    	return read;
    }

    /**
     * @param date 
     * @return
     */
    public void ChangeIsRefund(int date) {
        // TODO implement here
    	String w = "";
    	boolean ischange = false;

    	String strdate = Integer.toString(date);
		if (strdate.length() == 9) {
			strdate = "0".concat(strdate);
		}
    	try {
	    	File file = new File(MyDB.concat(strdate).concat(".txt"));
	    	FileReader filereader = new FileReader(file);
	    	@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
	    	String line = "";
	    	while((line = bufferedreader.readLine()) != null) {
	    		if (line.equals("0")) {
	    			w = w.concat("1");
	    			ischange = true;
	    		} else {
	    			w = w.concat(line).concat("\n");
	    			
	    		}
	    	}
    	} catch(FileNotFoundException e) {
			System.out.println("there's no bills");
		} catch(IOException e) {
			System.out.println("io error!");
		}
    	
    	if (ischange) {
    		try {
    			File file = new File(MyDB.concat(strdate).concat(".txt"));
    			FileWriter filewriter = new FileWriter(file);
    			filewriter.write(w);
    			filewriter.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }

    /**
     * @param date 
     * @return
     */
    public boolean IsRefund(int date) {
        // TODO implement here

    	try {
	    	File file = new File(MyDB.concat(Integer.toString(date)).concat(".txt"));
	    	FileReader filereader = new FileReader(file);
	    	@SuppressWarnings("resource")
			BufferedReader bufferedreader = new BufferedReader(filereader);
	    	String line = "";
	    	while((line = bufferedreader.readLine()) != null) {
	    		String[] ps = line.split(" ");
	    		if (ps[0].equals("0")) {
	    			return true;
	    		}
	    	}
    	} catch(FileNotFoundException e) {
			System.out.println("there's no product!");
		} catch(IOException e) {
			System.out.println("io error!");
		}
        return false;
    }

    /**
     * @param date 
     * @return
     */
    public int FindSaleMoney(int date) {
        // TODO implement here
        return 0;
    }

}