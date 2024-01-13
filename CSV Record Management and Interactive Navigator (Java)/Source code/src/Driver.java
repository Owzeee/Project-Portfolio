/* 
 Assignment Description: The app is an interactive program that allows the user to navigate through book records
 						 categorized by genres, also this program searches for any semantic or syntax errors and 
 						 puts them in their seperate files. This program also serializes the files and opens them 
 						 command.  
Omar Zaari Id:40194212
COMP249
Assignment 3
29/03/2023
*/
import java.io.*;
import java.util.*;
public class Driver {

	public static class Book implements Serializable {
		/*
		 * Declaring the object's instance variables.
		 */
	    private String title;
	    private String authors;
	    private double price;
	    private String isbn;
	    private String genre;
	    private int year;
	    
	    /**
		 * Default contructor that sets the attributes to their default values
		 */
	    public Book() {
	    	 title = "";
		     authors = "";
		     price = 0.0;
		     isbn = "";
		     genre = "";
		     year = 0;
	    }
	    
	    /*
		 * parameterized constructor 
		 * 
		 * @param title is a String
		 * @param authors is a String
		 * @param price is a double value
		 * @param String is a String
		 * @param genre is a String
		 * @param year is a integer
		 */
	    public Book(String title, String authors, double price, String isbn, String genre, int year) {
	        this.title = title;
	        this.authors = authors;
	        this.price = price;
	        this.isbn = isbn;
	        this.genre = genre;
	        this.year = year;
	    }
	    /*
	     * copy contructor
	     * @param other is a Book object
	     */
	    public Book(Book other) {
	        this.title = other.title;
	        this.authors = other.authors;
	        this.price = other.price;
	        this.isbn = other.isbn;
	        this.genre = other.genre;
	        this.year = other.year;
	    }

	    /**
		 * This method returns the title.
		 * 
		 * @return content of title(String).
		 */
	    public String getTitle() {
	        return title;
	    }

	    /**
		 * This method sets the title.
		 * 
		 * @return content of number_of_wheels(String).
		 */
	    public void setTitle(String title) {
	        this.title = title;
	    }

	    /**
		 * This method returns the authors.
		 * 
		 * @return value of authors(String).
		 */
	    public String getAuthors() {
	        return authors;
	    }

	    /**
		 * This method sets the authors.
		 * 
		 * @return content of authors(String).
		 */
	    public void setAuthors(String authors) {
	        this.authors = authors;
	    }

	    /**
		 * This method returns the price.
		 * 
		 * @return value of price(double).
		 */
	    public double getPrice() {
	        return price;
	    }

	    /**
		 * This method sets the price.
		 * 
		 * @return content of price(double).
		 */
	    public void setPrice(double price) {
	        this.price = price;
	    }

	    /**
		 * This method returns the isbn.
		 * 
		 * @return content of isbn(String).
		 */
	    public String getIsbn() {
	        return isbn;
	    }

	    /**
		 * This method sets the isbn.
		 * 
		 * @return content of isbn(String).
		 */
	    public void setIsbn(String isbn) {
	        this.isbn = isbn;
	    }

	    /**
		 * This method returns the genre.
		 * 
		 * @return value of genre(String).
		 */
	    public String getGenre() {
	        return genre;
	    }

	    /**
		 * This method sets the genre.
		 * 
		 * @return content of genre(String).
		 */
	    public void setGenre(String genre) {
	        this.genre = genre;
	    }

	    /**
		 * This method returns the year.
		 * 
		 * @return value of year(integer).
		 */
	    public int getYear() {
	        return year;
	    }

	    /**
		 * This method sets the year.
		 * 
		 * @return content of year(int).
		 */
	    public void setYear(int year) {
	        this.year = year;
	    }
	    
	    /*
	     * This method is of boolean type, it compares 2 objects, and returns wether they are equal or not
	     * @param obj of type object
	     */
	    @Override
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (obj == this) {
	            return true;
	        }
	        if (!(obj.getClass() == this.getClass())) {
	            return false;
	        }
	        Book other = (Book) obj;
	        return title.equals(other.title) && authors.equals(other.authors) && 
	               price == other.price && isbn.equals(other.isbn) && 
	               genre.equals(other.genre) && year == other.year;
	    	}
	  
	   /*
	    * This method returns a string with the informatiom of the object. 
	    */
	    @Override
	    public String toString() {
	        return(title + ", " + authors + ", " + price + ", " + isbn + ", " + genre + ", " + year);
	    	}
	}
	
	/*
	 * This method reads from a file named part1 input file names.txt, 
	 * which provides the names of book data input files. 
	 * The technique starts a file for each input file name, 
	 * reads the book data from it, then separates the data into fields. 
	 * The book data is then examined for syntax mistakes of many things.
	 * If a syntax error is discovered, 
	 * the method writes an error message to the syntax error file.txt file.
	 * If no syntax mistakes are identified, 
	 * the book records is then printed int appropriate files depending on the genre.
	 */
	public static void do_part1() {
	    PrintWriter ccb = null;
	    PrintWriter hcb = null;
	    PrintWriter mtv = null;
	    PrintWriter mrb = null;
	    PrintWriter neb = null;
	    PrintWriter otr = null;
	    PrintWriter ssm = null;
	    PrintWriter tpa = null;
	    PrintWriter sef = null;
	    Scanner sc = null;
	    Scanner sc2 = null;
	    String authors = "";
	    double price = 0;
	    String genre = "";
	    int isbn = 0;
	    int count = 0;
	    int year = 0;
	    int syntaxErr1 = 0;
	    int syntaxErr2 = 0;
	    int syntaxErr3 = 0;
	    int syntaxErr4 = 0;
	    int syntaxErr5 = 0;
	    int syntaxErr6 = 0;
	    int syntaxErr7 = 0;
	    int syntaxErr8 = 0;
	    String books = "";
	    
	    try {
	    	ccb = new PrintWriter(new FileOutputStream("Cartoons_Comics.csv.txt"));
	    	hcb = new PrintWriter(new FileOutputStream("Hobbies_Collectibles.csv.txt"));
	    	mtv = new PrintWriter(new FileOutputStream("Movies_TV_Books.csv.txt"));
	    	mrb = new PrintWriter(new FileOutputStream("Music_Radio_Books.csv.txt"));
	    	neb = new PrintWriter(new FileOutputStream("Nostalgia_Eclectic_Books.csv.txt"));
	    	otr = new PrintWriter(new FileOutputStream("Old_Time_Radio_Books.csv.txt"));
	    	ssm = new PrintWriter(new FileOutputStream("Sports_Sports_Memorabilia.csv.txt"));
	    	tpa = new PrintWriter(new FileOutputStream("Trains_Planes_Automobiles.csv.txt"));
	        sef = new PrintWriter(new FileOutputStream("syntax_error_file.txt"));
	        sc  = new Scanner(new FileInputStream("part1_input_file_names.txt"));
	        
	        //the code enters a while loop to read each input file name from the Scanner object. For each input file, a new Scanner object is created to read the file's contents.
	        //The while loop then enters a nested while loop to read each line of the input file. For each line, the code splits the line into an array of strings using
	        //a regular expression to handle comma-separated values that are enclosed in double-quotes. It then extracts the author and genre information from the array 
	        //and checks for syntax errors in the input line using if statements.
	        //=========================================================================================================================================================
	        while (sc.hasNextLine()) {
	            String temp = sc.nextLine();
	            sc2 = new Scanner(new FileInputStream(temp));
                while (sc2.hasNextLine()) {	                	
                	books = sc2.nextLine();
                    String[] b = books.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);												
                    authors = b[1];
                    genre = b[4];	                    
                    String title = b[0];
                    if(b.length != 6 || title.length() == 0 || (authors.length() == 0) || b[2].length() == 0 || b[3].length() == 0 || b[4].length() == 0 || 
                    		b[5].length() == 0 || (!genre.equals("CCB") && !genre.equals("HCB") && !genre.equals("MTV") && !genre.equals("MRB") && 
                    				!genre.equals("NEB") && !genre.equals("OTR") && !genre.equals("SSM") && !genre.equals("TPA"))) {
                    	
                		sef.println("Syntax error in file: " + temp + "\n====================");
                		break;
                    } //===============================================================================================================================================

                }
                
                //In this section of the code, the method is recuperating file records and putting them into arrays, splitting them,
                //and then checking for syntax errors using try and catch statements and loops, if error are found, the appropriate Exceptions are thrown.
                //=================================================================================================================================
	            try {
	                sc2 = new Scanner(new FileInputStream(temp));
	                while (sc2.hasNextLine()) {	                	
	                	books = sc2.nextLine();
	                	String[] b = books.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	                    authors = b[1];
	                    genre = b[4];	                    
	                    String title = b[0];
	                    
	                    try {
	                    if (title.length() == 0) {
	                    	count++;
	                        throw new MissingFieldException("Missing title", books, temp);
	                    	}       
	                    }
	            		catch (MissingFieldException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}
	                    try {
	                    if (b.length != 6) {     
	                    	count++;
	                        if (b.length < 6) {
	                            throw new TooFewFieldsException("too few fields", books, temp);
	                        } 
	                        if(b.length > 6) {
	                            throw new TooManyFieldsException("too many fields", books, temp);    
	                        	}
	                    	}
	                    }
	            		catch (TooFewFieldsException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		} 
	            		catch (TooManyFieldsException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}
	                    
	                    try {
	                    if (authors.length() == 0) {
	                    	count++;
	                        throw new MissingFieldException("Missing authors", books, temp);
	                    	}
	                    }
            		catch (MissingFieldException e) {
            		    sef.println(e.getMessage() + "\n");
            		}
	                    try {
	                    if (b[2].length() == 0) {
	                    	count++;
	                        throw new MissingFieldException("Missing price", books, temp);
	                    	}
	                    }
	            		catch (MissingFieldException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}
	                    
	                    try {
	                    if (b[3].length() == 0) {
	                    	count++;
	                        throw new MissingFieldException("Missing isbn", books, temp);
	                    	}
	                    }
	            		catch (MissingFieldException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}
	                    
	                    try {  
	                        if (b[4].length() == 0) {
	                        	count++;
	                        	throw new MissingFieldException("Missing genre", books, temp);
	                        }
	                    	if (!genre.equals("CCB") && !genre.equals("HCB") && !genre.equals("MTV") && !genre.equals("MRB") && !genre.equals("NEB") && !genre.equals("OTR") && !genre.equals("SSM") && !genre.equals("TPA")) {
	 	                    	count++;
	 	                    	throw new UnknownGenreException("Unknown genre", books, temp);
	 	                    	}
	                    	}	                    
	            		catch (MissingFieldException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}                       
	            	    catch (UnknownGenreException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		} 
	                    
	                    try {
	                    if (b[5].length() == 0) {
	                    	count++;
	                        throw new MissingFieldException("Missing year", books, temp);
	                    }
	                }
	            		catch (MissingFieldException e) {
	            		    sef.println(e.getMessage() + "\n");
	            		}
	                    //=====================================================================================================================================================
	                    
	                    //The method here is checking if the record is valid and then placing the records in genre based files.
	                    //=====================================================================================================================================================
	                    if(b.length == 6 && title.length() != 0 && (authors.length() != 0) && b[2].length() != 0 && b[3].length() != 0 && b[4].length() != 0 && b[5].length() != 0) {
	                    if(genre.length() != 0) {
	                    	if(genre.equals("CCB")) {
	                    		syntaxErr1++;
	                    		ccb.println(books);
	                    	}
	                    	else if(genre.equals("HCB")) {
	                    		syntaxErr2++;
	                    		hcb.println(books);
	                    	}
	                    	else if(genre.equals("MTV")) {
	                    		syntaxErr3++;
	                    		mtv.println(books);
	                    	}
	                    	else if(genre.equals("MRB")) {
	                    		syntaxErr4++;
	                    		mrb.println(books);
	                    	}
	                    	else if(genre.equals("NEB")) {
	                    		syntaxErr5++;
	                    		neb.println(books);
	                    	}
	                    	else if(genre.equals("OTR")) {
	                    		syntaxErr6++;
	                    		otr.println(books);
	                    	}
	                    	else if(genre.equals("SSM")) {
	                    		syntaxErr7++;
	                    		ssm.println(books);
	                    	}
	                    	else if(genre.equals("TPA")) {
	                    		syntaxErr8++;
	                    		tpa.println(books);
	                    		}
	                    	}
	                    }
	                  //=====================================================================================================================================================
	                }
	            }       
		catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		} 
		catch (Exception e) {
		    System.out.println(e.getMessage());
		} 
	  }																					//catching exception.
    } 				
	    catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		}
		catch (Exception e) {
		    System.out.println(e.getMessage());
		} 
	    
		finally {
			sef.println("Syntax Errors found: " + count);    //Printing to the file the amount of errors found 
															// and also closing all Stream inputs to avoid leakage
		    if (sef != null) {
		        sef.close();
		    }
		    if (sc2 != null) {
		        sc2.close();
		    }
	        ccb.close();
	        hcb.close();
	        mtv.close();
	        mrb.close();
	        neb.close();
	        otr.close();
	        ssm.close();
	        tpa.close();
	        sc2.close();
		}	
	}
	
	/*
	 * This is a custom exception that extends Exception class
	 * It is called when there are many fiels 
	 */
	public static class TooManyFieldsException extends Exception {
	    public TooManyFieldsException(String message, String record, String fileName) {
	        super(("Error: " + message + "\nRecord: " + record));
	    }
	}
	
	/*
	 * This is a custom exception that extends Exception class
	 * It is called when there are few fiels
	 */
	public static class TooFewFieldsException extends Exception {
	    public TooFewFieldsException(String message, String record, String fileName) {
	        super(("Error: " + message + "\nRecord: " + record));
	    }
	}

	/*
	 * This is a custom exception that extends Exception class
	 * It is called when there is a missing field
	 */
	public static class MissingFieldException extends Exception {
	    public MissingFieldException(String message, String record, String fileName) {
	        super("Error: " + message + "\nRecord: " + record);
	    }
	}

	/*
	 * This is a custom exception that extends Exception class
	 * It is called when genre is unknown
	 */
	public static class UnknownGenreException extends Exception {
	    public UnknownGenreException(String message, String record, String fileName) {
	        super("Error: " + message + "\nRecord: " + record);
	    }
	}
	
	/*
	 * This method reads from defferent text files created in part 1, 
	 * opens them and checks for semantic error, writes bad records in the appropriate file.
	 * after that it searched for records that are clean and put them in genre based binary files.
	 * The method has several ObjectOutputStrems that write serelized files ".ser".
	 * The binary files are genre based and contain only valid records.
	 * The code utilizes the book class to store information of the records.
	 */
	public static void do_part2() {
		ObjectOutputStream oos1 = null;
		ObjectOutputStream oos2 = null;
		ObjectOutputStream oos3 = null;
		ObjectOutputStream oos4 = null;
		ObjectOutputStream oos5 = null;
		ObjectOutputStream oos6 = null;
		ObjectOutputStream oos7 = null;
		ObjectOutputStream oos8 = null;
				
		FileOutputStream ccs = null;
		FileOutputStream hcs = null;
		FileOutputStream mts = null;
		FileOutputStream mrs = null;
		FileOutputStream nes = null;
		FileOutputStream ots = null;
		FileOutputStream sss = null;
		FileOutputStream tps = null;
		
			PrintWriter pw2 = null;
			PrintWriter pw = null;
			
		    Scanner sc = null;
		    Scanner sc2 = null;
		    
		    String authors = "";
		    double price = 0;
		    String genre = "";
		    String title = "";
		    String isbn = "";
		    int count = 0;
		    int year = 0;
		    String books = "";
		    
		    try {
		    		pw2 = new PrintWriter(new FileOutputStream("semantic_error_file.txt"));
					pw = new PrintWriter(new FileOutputStream("Text_Files_Produced_In_Part.txt"));
					pw.println("Cartoons_Comics.csv.txt");
					pw.println("Hobbies_Collectibles.csv.txt");
					pw.println("Movies_TV_Books.csv.txt");
					pw.println("Music_Radio_Books.csv.txt");						//writing the names of the files to a file to be able to loop through the whole thing efficiently
					pw.println("Nostalgia_Eclectic_Books.csv.txt");
					pw.println("Old_Time_Radio_Books.csv.txt");
					pw.println("Sports_Sports_Memorabilia.csv.txt");
					pw.println("Trains_Planes_Automobiles.csv.txt");	
					pw.close();
					
		    		ccs = new FileOutputStream("Cartoons_Comics.csv.ser");
		    		oos1 = new ObjectOutputStream(ccs);
		    		hcs = new FileOutputStream("Hobbies_Collectibles.csv.ser");
		    		oos2 = new ObjectOutputStream(hcs);
		    		mts = new FileOutputStream("Movies_TV_Books.csv.ser");
		    		oos3 = new ObjectOutputStream(mts);
		    		mrs = new FileOutputStream("Music_Radio_Books.csv.ser");
		    		oos4 = new ObjectOutputStream(mrs);									//creating the required binary files
		    		nes = new FileOutputStream("Nostalgia_Eclectic_Books.csv.ser");
		    		oos5 = new ObjectOutputStream(nes);
		    		ots = new FileOutputStream("Old_Time_Radio_Books.csv.ser");
		    		oos6 = new ObjectOutputStream(ots);
		    		sss = new FileOutputStream("Sports_Sports_Memorabilia.csv.ser");
		    		oos7 = new ObjectOutputStream(sss);
		    		tps= new FileOutputStream("Trains_Planes_Automobiles.csv.ser");
		    		oos8 = new ObjectOutputStream(tps);
		    		
		        sc = new Scanner(new FileInputStream("Text_Files_Produced_In_Part.txt"));
		        //the code enters a while loop to read each input file name from the Scanner object. For each input file, a new Scanner object is created to read the file's contents.
		        //The while loop then enters a nested while loop to read each line of the input file. For each line, the code splits the line into an array of strings using
		        //a regular expression to handle comma-separated values that are enclosed in double-quotes. It then extracts the author and genre information from the array 
		        //and checks for syntax errors in the input line using if statements.
		        //=========================================================================================================================================================
		        while (sc.hasNextLine()) {
		            String temp = sc.nextLine();
		            sc2 = new Scanner(new FileInputStream(temp));
	                while (sc2.hasNextLine()) {	        
                    	int sum = 0;
	                    books = sc2.nextLine();
	                    String[] b = books.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	                    title = b[0];
	                    authors = b[1];
	                    genre = b[4];
	                    isbn = b[3];
	                    String priceStr = b[2];		                    
	                    String yearStr = b[5];
	                    String isbnStr = b[3];
	                    if((year = Integer.parseInt(yearStr)) < 1995 || (year = Integer.parseInt(yearStr)) > 2010 || (price = Double.parseDouble(priceStr)) < 0 || ((isbnStr.contains("X"))) || ((isbnStr.length() == 13 && sum % 10 != 0) || ((isbnStr.length() == 10) && (sum % 11 != 0)))) {
	                    	pw2.println("Syntax error in file: " + temp + "\n====================");
	                    	break;
	                    }
	               //=========================================================================================================================================================
	                }
	                
	                
	                //In this section of the code, the method is recuperating file records and putting them into arrays, splitting them,
	                //and then checking for syntax errors using try and catch statements and loops, if error are found, the appropriate Exceptions are thrown.
	                //=================================================================================================================================
		            try {
		                sc2 = new Scanner(new FileInputStream(temp));
		                while (sc2.hasNextLine()) {	        
	                    	int sum = 0;
		                    books = sc2.nextLine();
		                    String[] b = books.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		                    title = b[0];
		                    authors = b[1];
		                    genre = b[4];
		                    isbn = b[3];
		                    String priceStr = b[2];		                    
		                    String yearStr = b[5];
		                    String isbnStr = b[3];
		                    Book book = new Book(title, authors, Double.parseDouble(priceStr), isbn, genre, Integer.parseInt(yearStr));
		                    try {
		                    if ((price = Double.parseDouble(priceStr)) < 0) {
		                    	count++;
		                        throw new BadPriceException("Invalid price", books, temp);
		                    	}
		                    }
		            		catch (BadPriceException e) {
		            			pw2.println(e.getMessage() + "\n");
		            		}
		                   
		                    try {
		                        if (isbnStr.length() == 10) {
		                            sum = 0;
		                            for (int i = 0; i < isbnStr.length(); i++) {
		                                int digit = (int) isbnStr.charAt(i) - 48;
		                                sum += digit * (10 - i);
		                            }
		                            if (sum % 11 == 0) {
		                            }
		                    	else {
		                    	count++;
		                        throw new BadIsbn10Exception("invalid ISBN-10", books, temp);
		                    		}
		                    	}
		                    }
		            		catch (BadIsbn10Exception e) {
		            			pw2.println(e.getMessage() + "\n");
		            		}   
		                    
		                    try {
		                        if (isbnStr.length() == 13) {
		                            sum = 0;
		                            for (int i = 0; i < isbnStr.length(); i++) {
		                                int digit = (int) isbnStr.charAt(i) - 48;
		                                if (i % 2 == 0) {
		                                    sum += digit;
		                                } else { 
		                                    sum += digit * 3;
		                                }
		                            }
		                            if (sum % 10 == 0) {
		                            }
		                    	else {
		                    	count++;
		                        throw new BadIsbn13Exception("invalid ISBN-13", books, temp);
		                    		}
		                    	}
		                    }		                    
		            		catch (BadIsbn13Exception e) {
		            			pw2.println(e.getMessage() + "\n");
		            		}

		                    try {
		                    if ((year = Integer.parseInt(yearStr)) < 1995) {
		                    	count++;
		                        throw new BadYearException("invalid year", books, temp);
		                    }
		                }
		            		catch (BadYearException e) {
		            			pw2.println(e.getMessage() + "\n");
		            		}    
		                    
		                    try {
		                    if ((year = Integer.parseInt(yearStr)) > 2010) {
		                    	count++;
		                        throw new BadYearException("invalid year", books, temp);
		                    }
		                }
		            		catch (BadYearException e) {
		            			pw2.println(e.getMessage() + "\n");
		            		} 
		               
		                    if(((year = Integer.parseInt(yearStr)) > 1994 && (year = Integer.parseInt(yearStr)) < 2011) && (price = Double.parseDouble(priceStr)) > 0 && (!(isbnStr.contains("X"))) && ((isbnStr.length() == 13 && sum % 10 == 0) || ((isbnStr.length() == 10) && (sum % 11 == 0)))) {
		                    		if(genre.equals("CCB")) {
			                    		oos1.writeObject(book);
			                    	}
			                    	else if(genre.equals("HCB")) {
			                    		oos2.writeObject(book);
			                    		}
			                    	else if(genre.equals("MTV")) {
			                    		oos3.writeObject(book);			
			                    		}
			                    	else if(genre.equals("MRB")) {
			                    		oos4.writeObject(book);		
			                    		}
			                    	else if(genre.equals("NEB")) {
			                    		oos5.writeObject(book);		
			                    		}
			                    	else if(genre.equals("OTR")) {
			                    		oos6.writeObject(book);			
			                    		}
			                    	else if(genre.equals("SSM")) {
			                    		oos7.writeObject(book);		
			                    		}
			                    	else if(genre.equals("TPA")) {
			                    		oos8.writeObject(book);		
			                    		}
		                   	}
		                   //=================================================================================================================================
		           }
		    }           
			catch (FileNotFoundException e) {
			    System.out.println(e.getMessage());
			} 
			catch (IOException e) {
			    System.out.println(e.getMessage());								//catching exception.
			}
			catch (Exception e) {
			    System.out.println(e.getMessage());
			} 
		  }
	    } 
		    catch (FileNotFoundException e) {
			    System.out.println(e.getMessage());
			}
			catch (IOException e) {
			    System.out.println(e.getMessage());
			} 
		  //==========================================================================================================  
			finally {
				pw2.println(count);				//Printing to the file the amount of errors found 
												// and also closing all Stream inputs to avoid leakage
				pw2.close();
		        sc2.close();
		        
		        try {
					oos1.flush();
					oos2.flush();
					oos3.flush();
					oos4.flush();
					oos5.flush();
					oos6.flush();
					oos7.flush();
					oos8.flush();
				} 
		        
		        catch (FileNotFoundException e) {
		        	e.printStackTrace();
		        }
		        catch (IOException e) {
					e.printStackTrace();
				}
		        try {
					oos1.close();
					oos2.close();
					oos3.close();
					oos4.close();
					oos5.close();
					oos6.close();
					oos7.close();
					oos8.close();		    
					}
		        catch (FileNotFoundException e) {
		        	e.printStackTrace();
		        }
		        catch (IOException e) {
		        	e.printStackTrace();
				}
			}
		    //==================================================================================================
	}
	
	/*
	 * This method is a custom exception that extends the Exception class
	 * It is called when the isbn number is not a multiple of 11.
	 */
	public static class BadIsbn10Exception extends Exception {
	    public BadIsbn10Exception(String message, String record, String fileName) {
	        super(("Error: " + message + "\nRecord: " + record));
	    }
	}
;
	/*
	 * This method is a custom exception that extends the Exception class
	 * It is called when the isbn number is not a multiple of 10.
	 */
	public static class BadIsbn13Exception extends Exception {
	    public BadIsbn13Exception(String message, String record, String fileName) {
	        super(("Error: " + message + "\nRecord: " + record));
	    }
	}

	/*
	 * This method is a custom exception that extends the Exception class
	 * It is called when the price value is below zero.
	 */
	public static class BadPriceException extends Exception {
	    public BadPriceException(String message, String record, String fileName) {
	        super("Error: " + message + "\nRecord: " + record);
	    }
	}

	/*
	 * This method is a custom exception that extends the Exception class
	 * It is called when the year value is not within the right interval.
	 */
	public static class BadYearException extends Exception {
	    public BadYearException(String message, String record, String fileName) {
	        super("Error: " + message + "\nRecord: " + record);
	    }
	}

	/*
	 * This method reads serialized binary files created in part2.
	 * The method reads the binary files from the "Binary_Files_Produced_In_Part2.txt"
	 * The method processes the binary files to find out their lengths
	 * and then initializes the book array with appropriate size.
	 * The method initializes book arrays for the for the binary files,
	 * then it creates scanner object to read the names of the binary files.
	 * for each binary file name, the method creates a fileinputstream ObjectInputStream
	 * to read the serialized book objects.
	 * The method determines the number of book objects in the binary file by counting 
	 * how many times it can read an object from the ObjectInputStream before encountering an exception. 
	 * After determining the number of book objects in the binary file, 
	 * the method initializes an array of Book objects for that binary file 
	 * and reads the book objects from the binary file into the array.
	 * Finally, the method stores the arrays of Book objects for each binary file in separate variables
	 * and closes the FileInputStream and ObjectInputStream objects.
	 */
	public static void do_part3() {

		ObjectInputStream ois = null;
		FileInputStream fis = null;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		int count7 = 0;
		int count8 = 0;
		Book[] book1 = null;
		Book[] book2 = null;
		Book[] book3 = null;
		Book[] book4 = null;
		Book[] book5 = null;
		Book[] book6 = null;
		Book[] book7 = null;
		Book[] book8 = null;
		
		Scanner sc = null;
		Scanner sc2 = null;
		PrintWriter pw = null;
		
		try {
		    pw = new PrintWriter(new FileOutputStream("Binary_Files_Produced_In_Part2.txt"));
		    pw.println("Cartoons_Comics.csv.ser");
		    pw.println("Hobbies_Collectibles.csv.ser");
		    pw.println("Movies_TV_Books.csv.ser");									//writing the names of the files to a file to be able to loop through the whole thing efficiently
		    pw.println("Music_Radio_Books.csv.ser");
		    pw.println("Nostalgia_Eclectic_Books.csv.ser");
		    pw.println("Old_Time_Radio_Books.csv.ser");
		    pw.println("Sports_Sports_Memorabilia.csv.ser");
		    pw.print("Trains_Planes_Automobiles.csv.ser"); 
		    pw.close();
		    sc = new Scanner(new FileInputStream("Binary_Files_Produced_In_Part2.txt"));	
		    while(sc.hasNextLine()) {
		        String temp = sc.nextLine();
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        int numObjects = 0;
		        
		        try {
		            while (true) {
		            	ois.readObject();										//Looping through all the binary files and to find the size of the book array            														
		                numObjects++;											//and storing it in a variable
		            }	           
		        } catch (IOException e) {
		        }
		        ois.close();
		        //====================================================================================================
		        if(temp.equals("Cartoons_Comics.csv.ser")) {
		        book1 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count1++;
		            book1[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Hobbies_Collectibles.csv.ser")) {
		        book2 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {							//Reading the binary files and storing them in their appropriate book array to be able to use them
		        	count2++;													//to navigate them on the console
		            book2[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Movies_TV_Books.csv.ser")) {
		        book3 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count3++;	        	
		            book3[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Music_Radio_Books.csv.ser")) {
		        book4 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count4++;
		            book4[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Nostalgia_Eclectic_Books.csv.ser")) {
		        book5 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count5++;
		            book5[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Old_Time_Radio_Books.csv.ser")) {
		        book6 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count6++;
		            book6[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Sports_Sports_Memorabilia.csv.ser")) {
		        book7 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count7++;
		            book7[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		        if(temp.equals("Trains_Planes_Automobiles.csv.ser")) {
		        book8 = new Book[numObjects];
		        fis = new FileInputStream(temp);
		        ois = new ObjectInputStream(fis);
		        for (int i = 0; i < numObjects; i++) {
		        	count8++;
		            book8[i] = (Book) ois.readObject();
		        }
			       ois.close();   
		        }
		    }
		    
		    sc.close();
		}

		catch(ClassNotFoundException e) {
		    System.out.println(e.getMessage());
		}
		catch(FileNotFoundException e) {
		    System.out.println(e.getMessage());					//Catching all necessary exception and printing the approprite message
		}
		catch(IOException e) {
		    System.out.println(e.getMessage());
		}
		//===================================================================================================
		
		   String[] fileName = {"Cartoons_Comics_Books.csv.ser", "Hobbies_Collectibles_Books.csv.ser", "Movies_TV.csv.ser", "Music_Radio_Books.csv.ser",
		   						"Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
		   
		   String output = "Cartoons_Comics_Books.csv.ser";
		   Scanner input = new Scanner(System.in);   
		   
	      do {																//Initiating a do while loop to keep executing the code as long as the user is still navigating through the records
	    	  System.out.println();											
	    	  getMenu(output, count1);
	    	  String choice = input.next();
	       switch(choice) {	       
	       case "x":
	    	   System.out.println("Thank you for using The Book Record Viewer App!\nUntil next time!.");
	    	   System.exit(0);
	    	 break;
	    	 
	       case "s":
	    	   int choice1 = 0;
	    	   boolean validInput = false;
	    	   do {
	    	       System.out.println();
	    	       getSub_Menu(count1, count2, count3, count4, count5, count6, count7, count8);  
	    	       try {
	    	           choice1 = input.nextInt();
	    	           validInput = true;
	    	           if (choice1 == 9) {
	    	               break;
	    	           }
		        Book[][] bookArrs = {
		        	    book1,
		        	    book2,
		        	    book3,
		        	    book4,
		        	    book5,
		        	    book6,
		        	    book7,
		        	    book8
		        	};

		       int index = (choice1) - 1; 
		       Book[] selectedBookArr = bookArrs[index];
		        	
		       int index2 = (choice1) - 1; 
		       if (index2 >= 0 && index2 < fileName.length) {
		           output = fileName[index2];
		       }
		       }
	    	   catch (InputMismatchException e) {
	    	        System.out.println("Invalid input. Please enter a valid integer.");
	    	        input.nextLine(); // clear the input
	    	        validInput = false;
	    	    }
	    	   catch (IndexOutOfBoundsException e) {
	    	        System.out.println("Invalid input. Please enter a valid integer.");
	    	        input.nextLine(); // clear the input 
	    	        validInput = false;
	    	    }
	    	} while (!validInput);

		       System.out.println();
		       getMenu(output, count1);
		       choice = input.next();

		       if(choice.equals("x")) {
		        	System.out.println("Thank you for using The Book Record Viewer App!\nUntil next time!");
		        	System.exit(0);
		       }
		       //==============================================================================================================================================================================================
		       /*
		        * Inside the loop, the program attempts to read an integer input from the user using the Scanner object "input". 
		        * If the input is not an integer, an error message is printed and the loop continues. 
		        * If the input is valid, the program checks if the input is 0. If it is, the loop ends and the program returns to the main menu. 
		        * If the input is positive, the program displays a certain number of records starting from the current index ("currentIndex") 
		        * and updates the current index accordingly. If the input is negative, 
		        * the program displays a certain number of records before the current index and updates the current index accordingly.
		        */
		       if(choice.equals("v")) {		    	   
		    	    System.out.println("viewing: " + output);	    	  
		    	    int n_index = 0;
		    	    int currentIndex = 0;
		    	    boolean validInput1 = true;
		    	    do {
		    	        System.out.println();
		    	        System.out.print("Enter a viewing command (or 0 to end): ");
		    	        try {
		    	            n_index = input.nextInt();
		    	            validInput1 = true;
		    	        }
		    	        catch (InputMismatchException e) {
		    	            System.out.println("Invalid input. Please enter a valid integer.");
		    	            input.nextLine(); 
		    	            validInput1 = false;
		    	        }
		    	        if (validInput1) {
		    	            if (n_index == 0) {
		    	                break;
		    	            }
		    	            if (n_index > 0) {
		    	                int lastIdx = currentIndex + n_index;
		    	                if (lastIdx >= book1.length) {
		    	                    lastIdx = book1.length - 1;
		    	                    System.out.println("EOF has been reached.");
		    	                }
		    	                for (int i = currentIndex + 1; i <= lastIdx; i++) {
		    	                    System.out.println("Record " + i + ": " + book1[i]);
		    	                }
		    	                currentIndex = lastIdx;
		    	            } else if (n_index < 0) {
		    	                int startIndex = currentIndex + n_index;
		    	                if (startIndex < 0) {
		    	                    startIndex = 0;
		    	                    System.out.println("BOF has been reached.");
		    	                }
		    	                for (int i = startIndex; i < currentIndex; i++) {
		    	                    System.out.println("Record " + i + ": " + book1[i]);
		    	                }
		    	                currentIndex = startIndex;
		    	            }
		    	        }
		    	    } while (n_index != 0);
		    	    break;	   
		       }		       
	    	   
	    	 break;
		     //==============================================================================================================================================================================================
	    	 
	       case "v":													//SAME FOR CASE "V"
	    	    int n_index = 0;
	    	    int currentIndex = 0;
	    	    boolean validInput1 = true;
	    	    do {
	    	        System.out.println();
	    	        System.out.print("Enter a viewing command (or 0 to end): ");
	    	        try {
	    	            n_index = input.nextInt();
	    	            validInput1 = true;
	    	        } 
	    	        
	    	        catch (InputMismatchException e) {
	    	            System.out.println("Invalid input. Please enter a valid integer.");
	    	            input.nextLine();
	    	            validInput1 = false;
	    	        }
	    	        if (validInput1) {
	    	            if (n_index == 0) {
	    	                break;
	    	            }
	    	            if (n_index > 0) {
	    	                int lastIdx = currentIndex + n_index;
	    	                if (lastIdx >= book1.length) {
	    	                    lastIdx = book1.length - 1;
	    	                    System.out.println("EOF has been reached.");
	    	                }
	    	                for (int i = currentIndex + 1; i <= lastIdx; i++) {
	    	                    System.out.println("Record " + i + ": " + book1[i]);
	    	                }
	    	                currentIndex = lastIdx;
	    	            } else if (n_index < 0) {
	    	                int startIndex = currentIndex + n_index;
	    	                if (startIndex < 0) {
	    	                    startIndex = 0;
	    	                    System.out.println("BOF has been reached.");
	    	                }
	    	                for (int i = startIndex; i < currentIndex; i++) {
	    	                    System.out.println("Record " + i + ": " + book1[i]);
	    	                }
	    	                currentIndex = startIndex;
	    	            }
	    	        }
	    	    } while (n_index != 0);
	    	    break;
	    	   
	       default:
	    	   System.out.println("Please enter a valid input: ");
	       }   
	      } while(true);


	}
	
	private static void getSub_Menu(int count1, int count2, int count3, int count4, int count5, int count6, int count7, int count8) {
	    System.out.println("------------------------------");
        System.out.println("        File Sub-Menu");
        System.out.println("------------------------------");
        System.out.println("1 Cartoons_Comics_Books.csv.ser      (" + count1 + " records)");
        System.out.println("2 Hobbies_Collectibles_Books.csv.ser (" + count2 + " records)");			//Putting the main menu content in method, so it is more efficient
        System.out.println("3 Movies_TV.csv.ser                  (" + count3 + " records)");
        System.out.println("4 Music_Radio_Books.csv.ser          (" + count4 + " records)");
        System.out.println("5 Nostalgia_Eclectic_Books.csv.ser   (" + count5 + " records)");
        System.out.println("6 Old_Time_Radio.csv.ser             (" + count6 + " records)");
        System.out.println("7 Sports_Sports_Memorabilia.csv.ser  (" + count7 + " records)");
        System.out.println("8 Trains_Planes_Automobiles.csv.ser  (" + count8 + " records)");
        System.out.println("9 Exit");
        System.out.println("------------------------------");
        System.out.print("Enter Your Choice: ");
	}

	public static void getMenu(String output, int count1) {
	       System.out.println("-----------------------------");
	       System.out.println("         Main Menu");
	       System.out.println("-----------------------------");
	       System.out.println("v View the selected file: " + output + " (" + count1 + " records)");   //Putting the sub-menu content in method, so it is more efficient
	       System.out.println("s Select a file to view");
	       System.out.println("x Exit");
	       System.out.println("-----------------------------\n");
	       System.out.print("Enter Your Choice: ");
	}
	
	public static void main(String[] args) {
		do_part1();
		do_part2();			//Calling all the methods to the main method.
		do_part3();
	}

}

/*
 * This section of the code i commented out because i used to create the files given for the assignment 
 * but realized that i wont need it anymore so i commented it out
 */
/*PrintWriter b1 = null;
PrintWriter b2 = null;
PrintWriter b3 = null;
PrintWriter b4 = null;
PrintWriter b5 = null;
PrintWriter b6 = null;
PrintWriter b7 = null;
PrintWriter b8 = null;
PrintWriter b9 = null;
PrintWriter b10 = null;
PrintWriter b11 = null;
PrintWriter b12 = null;
PrintWriter b13 = null;
PrintWriter b14 = null;
PrintWriter b15 = null;
PrintWriter b16 = null;
System.out.println("hello");
if(book.equals("books1995.csv")) {
b1 = new PrintWriter(book);
b1.println("\"Zimbalist, Efrem - A Life\",Roy Malan,19.95,1574670913,MRB,1905\r\n"
		+ "\"Carney, Art - A Biography\",Michael Seth Starr,11.95,1557835659,MTV,1905\r\n"
		+ "Hitchcock's London: A Reference Guide to Locations,Gary Giblin,19.95,188766467X,MTV,1905\r\n"
		+ "Modern Amazons: Warrior Women on Screen,Dominique Mainon & James Ursini,19.95,1879103272,MTV,1905\r\n"
		+ "You're Not Old Enough Son,Barry Atkinson,19.95,1887664688,MTV,1905\r\n"
		+ "Gunsmoke - A Complete History And Analysis of The Legendary Broadcast Series With A Comprehensive Episode - By - Episode Guide To Both The Radio And Television Programs,Suzanne Barabas & Gabor Barabas,85.5,9780899504186,MTV,1990\r\n"
		+ "\"Horror Film Directors, 1931 - 1990\",Dennis Fischer,85.5,9780899506098,MTV,1991\r\n"
		+ "\"Mingus, Charles - Mingus / Mingus: Two Memoirs\",Janet Coleman & Al Young,7.95,0879101490,MRB,1994\r\n"
		+ "\"Lewis, Jerry - An Analytical Filmography of The Innovative Comic\",James L. Neibaur & Ted Okuda,44.95,9780899509617,MTV,1994\r\n"
		+ "Hammer Films - An Exhaustive Filmography,Tom Johnson & Deborah Del Vecchio,66.95,9780786400348,MTV,1995\r\n"
		+ "Official Mickey Mouse Club Book,Lorraine Santoli,1.99,0786880422,MTV,1995\r\n"
		+ "\"Baseball - Baseball By The Numbers: How Statistics Are Collected, What They Mean, and How They Reveal the Game\",Willie Runquist,30.95,9780786400065,SSM,1995\r\n"
		+ "\"Baseball - One-Armed Wonder: Pete Gray, Wartime Baseball, and the American Dream\",William C. Kashatus,26.96,9780786400942,SSM,1995\r\n"
		+ "\"Baseball - Pacific Coast League: A Statistical History, 1903-1957\",Dennis Snelling,30.95,9780786400454,SSM,1995");
System.out.println(book + " created successfully.");
b1.close();
}

if(book.equals("books1996.csv")) {
b2 = new PrintWriter(book);
b2.println("");
b2.close();
}

if(book.equals("books1997.csv")) {
b3 = new PrintWriter(book);
b3.println("\"Collecting Movie Posters - An Illustrated Reference Guide To Movie Art: Posters, Press Kits, And Lobby Cards\",Edwin E. Poole & Susan T. Poole,30.95,9780786401697,HCB,1997\r\n"
		+ "\"B Western Actors Encyclopedia - Facts, Photos And Filmographies For More Than 250 Familiar Faces\",Ted Holland,35.95,9780786404759,MTV,1997\r\n"
		+ "Dark City - The Film Noir,Spencer Selby,30.95,9780786404780,MTV,1997\r\n"
		+ "Dark Romance - Sexuality In The Horror Film,David J. Hogan,30.95,9780786404742,MTV,1997\r\n"
		+ "Golden Corral - A Roundup of Magnificent Western Films,Ed Andreychuk,30.95,9780786403936,MTV,1997\r\n"
		+ "In The Nick of Time - Motion Picture Sound Serials,William C. Cline,30.95,9780786404711,MTV,1997\r\n"
		+ "Jump Cut! - Memoirs of A Pioneer Television Editor,Arthur Schneider,30.95,9780786403455,MTV,1997\r\n"
		+ "Keep Watching The Skies! - American Science Fiction Movies of The Fifties,Bill Warren,44.95,9780786404797,MTV,1997\r\n"
		+ "\"Meyer, Russ - The Life And Films: A Biography And A Comprehensive, Illustrated And Annotated Filmography And Bibliography\",David K. Frasier,30.95,9780786404728,MTV,1997\r\n"
		+ "\"Ryan, Ryan - A Biography And Critical Filmography\",Franklin Jarlett,30.95,9780786404766,MTV,1997\r\n"
		+ "\"Stars of Hollywood Remembered - Career Biographies of 82 Actors And Actresses of The Golden Era, 1920S - 1950S\",J.G. Ellrod,35.95,9780786402946,MTV,1997\r\n"
		+ "\"Western Movies - A TV and Video Guide to 4,200 Genre Films\",Michael R. Pitts,39.95,9780786404216,MTV,1997,\r\n"
		+ "Baseball - Baseball Pioneers: Ratings of Nineteenth Century Players,Charles F. Faber,30.95,9780786402953,SSM,1997\r\n"
		+ "Baseball - Umpires: Classic Baseball Stories from the Men Who Made the Calls,John C. Skipper,30.95,9780786403646,SSM,1997\r\n"
		+ "");
System.out.println(book + " created successfully.");
b3.close();
}
if(book.equals("books1998.csv")) {
b4 = new PrintWriter(book);
b4.println("\"Collecting Movie Posters - An Illustrated Reference Guide To Movie Art: Posters, Press Kits, And Lobby Cards\",Edwin E. Poole & Susan T. Poole,30.95,9780786401697,HCB,1997\r\n"
		+ "\"B Western Actors Encyclopedia - Facts, Photos And Filmographies For More Than 250 Familiar Faces\",Ted Holland,35.95,9780786404759,MTV,1997\r\n"
		+ "Dark City - The Film Noir,Spencer Selby,30.95,9780786404780,MTV,1997\r\n"
		+ "Dark Romance - Sexuality In The Horror Film,David J. Hogan,30.95,9780786404742,MTV,1997\r\n"
		+ "Golden Corral - A Roundup of Magnificent Western Films,Ed Andreychuk,30.95,9780786403936,MTV,1997\r\n"
		+ "In The Nick of Time - Motion Picture Sound Serials,William C. Cline,30.95,9780786404711,MTV,1997\r\n"
		+ "Jump Cut! - Memoirs of A Pioneer Television Editor,Arthur Schneider,30.95,9780786403455,MTV,1997\r\n"
		+ "Keep Watching The Skies! - American Science Fiction Movies of The Fifties,Bill Warren,44.95,9780786404797,MTV,1997\r\n"
		+ "\"Meyer, Russ - The Life And Films: A Biography And A Comprehensive, Illustrated And Annotated Filmography And Bibliography\",David K. Frasier,30.95,9780786404728,MTV,1997\r\n"
		+ "\"Ryan, Ryan - A Biography And Critical Filmography\",Franklin Jarlett,30.95,9780786404766,MTV,1997\r\n"
		+ "\"Stars of Hollywood Remembered - Career Biographies of 82 Actors And Actresses of The Golden Era, 1920S - 1950S\",J.G. Ellrod,35.95,9780786402946,MTV,1997\r\n"
		+ "\"Western Movies - A TV and Video Guide to 4,200 Genre Films\",Michael R. Pitts,39.95,9780786404216,MTV,1997,\r\n"
		+ "Baseball - Baseball Pioneers: Ratings of Nineteenth Century Players,Charles F. Faber,30.95,9780786402953,SSM,1997\r\n"
		+ "Baseball - Umpires: Classic Baseball Stories from the Men Who Made the Calls,John C. Skipper,30.95,9780786403646,SSM,1997\r\n"
		+ "");
System.out.println(book + " created successfully.");
b4.close();
}
if(book.equals("books1999.csv")) {
	b5 = new PrintWriter(book);
	b5.println("Blues Singers - Biographies of 50 Legendary Artists of The Early 20th Century,David Dicaire,35.95,9780786406067,MRB,1999\r\n"
			+ "\"Franklin, Aretha - Aretha: From These Roots\",Aretha Franklin & David Ritz,2.95,0375500332,MRB,1999\r\n"
			+ "\"Burr, Raymond - A Film, Radio and Television Biography\",Ona L. Hill,30.95,9780786408337,MTV,1999\r\n"
			+ "\"Horror In Silent Films - A Filmography, 1896 - 1929\",Roy Kinnard,30.95,9780786407514,MTV,1999\r\n"
			+ "Monogram Checklist,Ted Okuda,35.95,9780786407507,MTV,1999\r\n"
			+ "Poverty Row Horrors!,Tom Weaver,30.95,9780786407989,MTV,1999\r\n"
			+ "\"Vanishing Legion - A History of Mascot Pictures, 1927 - 1935\",Jon Tuska,30.95,9780786407491,MTV,1999\r\n"
			+ "\"Wayne, John - Actor, Artist, Hero\",Richard D. McGhee,30.95,9780786407521,MTV,1999\r\n"
			+ "Western Movie Quotations,Jim Kane,44.95,9780786405947,MTV,1999\r\n"
			+ "\"Baseball - Athletics of Philadelphia: Connie Mack's White Elephants, 1901-1954\",David M. Jordan,26.96,9780786406203,SSM,1999\r\n"
			+ "Baseball - Baseball's Complete Players: Ratings of Total-Season Performance for the Greatest Players of the 20th Century,Michael Hoban,30.95,9780786406333,SSM,1999\r\n"
			+ "\"Baseball - Lopez, Al: The Life of Baseball's El Senor\",Wes Singletary,30.95,9780786406562,SSM,1999\r\n"
			+ "Baseball - Moments In The Sun: Baseball's Briefly Famous,Mark McGuire & Michael Sean Gormley,30.95,9780786405497,SSM,1999\r\n"
			+ "\"Baseball - Ott, Mel: The Little Giant of Baseball\",Fred Stein,-28.8,9780786406586,SSM,1999\r\n"
			+ "\"Baseball - Schmidt, Mike: Philadelphia's Hall of Fame Third Baseman\",William C. Kashatus,26.96,9780786407132,SSM,1999\r\n"
			+ "\"Baseball - Young Babe Ruth: His Early Life and Baseball Career, from the Memoirs of a Xaverian Brother\",Brother Gilbert C.F.X.,28.8,9780786406524,SSM,1999");
	System.out.println(book + " created successfully.");
	b5.close();
}
if(book.equals("books2000.csv")) {
	b6 = new PrintWriter(book);
		b6.println("African American Jazz And Rap - Social And Philosophical Examinations of Black Expressive Behavior,,39.95,9780786408283,MRB,2000\r\n"
				+ "Beginning of Broadcast Regulation In The Twentieth Century,Marvin R. Bensman,35.95,9780786407378,MRB,2000\r\n"
				+ "Classical Guitarists - Conversations,Jim Tosone,35.95,9780786408139,MRB,2000\r\n"
				+ "\"Traditional Musicians of The Central Blue Ridge - Old Time, Early Country, Folk And Bluegrass Label Recording Artists, With Discographies\",Marty McGee,33.75,9780786408764,MRB,2000\r\n"
				+ "Arthurian Legends On Film And Television,Bert Olton,39.95,9780786407187,MTV,2000\r\n"
				+ "Colonial America On Film And Television - A Filmography,Bertil O. Osterberg,66.95,9780786408627,MTV,2000\r\n"
				+ "\"Cyborgs, Santa Claus And Satan - Science Fiction, Fantasy And Horror Films Made For Television\",Fraser A. Sherman,29.95,9780786407934,MTV,2000\r\n"
				+ "\"Encyclopedia of Film Themes, Settings And Series\",Richard B. Armstrong & Mary Willems Armstrong,112.5,9780786408931,MTV,2000\r\n"
				+ "\"Fantasy Femmes of Sixties Cinema - Interviews With 20 Actresses From Biker, Beach, And Elvis Movies\",Tom Lisanti,39.95,9780786408689,MTV,2000\r\n"
				+ "\"Films of The Seventies - A Filmography of American, British And Canadian Films 1970 - 1979\",Marc Sigoloff,44.95,9780786408825,MTV,2000\r\n"
				+ "Golden Images - 41 Essays On Silent Film Stars,Eve Golden,35.95,9780786408344,MTV,2000,\r\n"
				+ "\"Hong Kong Filmography, 1977 - 1997 - A Complete Reference To 1,100 Films Produced By British Hong Kong Studios\",John Charles,66.95,9780786408429,MTV,2000\r\n"
				+ "I Have A Lady In The Balcony - Memoirs of A Broadcaster In Radio And Television,George Ansbro,39.95,9780786404254,MTV,2000\r\n"
				+ "\"Ness, Eliot And The Untouchables - The Historical Reality And The Film And Television Depictions\",Kenneth Tucker,30.95,9780786407729,MTV,2000\r\n"
				+ "Nitrate Won't Wait - A History of Film Preservation In The United States,Anthony Slide,30.95,9780786408368,MTV,2000\r\n"
				+ "\"Poplit, Popcult And The X - Files - A Critical Exploration\",Jan Delasara,35.95,9780786407897,MTV,2000\r\n"
				+ "\"Republic Chapterplays - A Complete Filmography of The Serials Released By Republic Pictures Corporation, 1934 - 1955\",R.M. Hayes,35.95,9780786409341,MTV,2000\r\n"
				+ "\"Science Fiction Film Directors, 1895 - 1998\",Dennis Fischer,157.5,9780786407408,MTV,2000\r\n"
				+ "\"Screen Sirens Scream! - Interviews With 20 Actresses From Science Fiction, Horror, Film Noir And Mystery Movies, 1930S To 1960S\",Paul Parla & Charles P. Mitchell,44.95,9780786407019,MTV,2000\r\n"
				+ "Serials-Ly Speaking - Essays On Cliffhangers,William C. Cline,30.95,9780786409181,MTV,2000\r\n"
				+ "\"Television Horror Movie Hosts - 68 Vampires, Mad Scientists And Other Denizens of The Late - Night Airwaves Examined And Interviewed\",Elena M. Watson,26.96,9780786409402,MTV,2000\r\n"
				+ "\"Television Sitcom Factbook - Over 8,700 Details From 130 Shows, 1985 - 2000\",Vincent Terrace,35.95,9780786409006,MTV,2000\r\n"
				+ "Western Villains - Bad At The Bijou,William R. Horner,28.8,9780786409389,MTV,2000\r\n"
				+ "Zombie Movie Encyclopedia,Peter Dendle,30.95,9780786408597,,2000\r\n"
				+ "Baseball - Baseball's Other All-Stars,William F. McNeil,-30.95,9780786407842,SSM,2000\r\n"
				+ "Baseball - Collecting Baseball Memorabilia,Dan Zachofsky,22.46,9780786407613,SSM,2000\r\n"
				+ "Baseball - Day-By-Day In Baseball History,,35.95,9780786408863,SSM,2000\r\n"
				+ "Baseball - Dizzy And The Gas House Gang: The 1934 St. Louis Cardinals and Depression-Era Baseball,Doug Feldmann,26.96,9780786408580,SSM,2000\r\n"
				+ "Baseball - Fouled Away: The Baseball Tragedy of Hack Wilson,Clifton Blue Parker,26.96,9780786408641,SSM,2000\r\n"
				+ "Baseball - Macphails: Baseball's First Family of the Front Office,G. Richard McKelvey,30.95,9780786406395,SSM,2000\r\n"
				+ "\"Baseball - Rickey, Branch In Pittsburgh: Baseball's Trailblazing General Manager for the Pirates, 1950-1955\",Andrew O'Toole,30.95,9780786408399,SSM,2000\r\n"
				+ "Baseball - Take Me Out To The Cubs Game: 35 Former Ballplayers Speak of Losing at Wrigley,John C. Skipper,30.95,9780786408108,SSM,2000\r\n"
				+ "\"Baseball - Voices From The Pastime: Oral Histories of Surviving Major Leaguers, Negro Leaguers, Cuban Leaguers and Writers, 1920-1934\",Nick Wilson,30.95,9780786408245,SSM,2000\r\n"
				+ "\"Baseball - Waddell, Rube: The Zany, Brilliant Life of a Strikeout Artist\",Alan H. Levy,26.96,9780786407866,SSM,2000");
		System.out.println(book + " created successfully.");
		b6.close();
	}
if(book.equals("books2001.csv")) {
	b7 = new PrintWriter(book);
			b7.println("\"Classics Illustrated - Cultural History, with Illustrations\",William B. Jones Jr.,49.5,9780786410774,CCB,2001\r\n"
					+ "Early Days of Radio Broadcasting,George H. Douglas,35.95,9780786411993,MRB,2001\r\n"
					+ "\"Ellington, Duke - Music For The Theatre\",John Franceschina,35.95,9780786408566,MRB,2001\r\n"
					+ "\"Fitzgerald, Ella - Annotated Discography: Including A Complete Discography of Chick Webb\",J. Wilfred Johnson,66.95,9780786409068,MRB,2001\r\n"
					+ "\"Great Radio Heroes, Revised Edition\",Jim Harmon,35.95,9780786408504,MBR,2001\r\n"
					+ "\"Jethro Tull - A History of The Band, 1968 - 2001\",Scott Allen Nollen,35.95,9780786411016,MRB,2001\r\n"
					+ "Madonna - Goddess: Inside Madonna,Barbara Victor,4.95,006019930X,MRB,2001\r\n"
					+ "More Blues Singers - Biographies of 50 Artists From The Later 20th Century,David Dicaire,35.95,9780786410354,MRB,2001\r\n"
					+ "Presidential Sheet Music - Illustrated Catalogue of Published Music Associated with the American Presidency and Those Who Sought the Office [2 Volumes],Danny O. Crew,69.95,9780786443253,MRB,2001\r\n"
					+ "Radio Comedy Diary - A Researcher's Guide To The Actual Jokes And Quotes of The Top Comedy Programs of 1947 - 1950,Gary Poole,35.95,9780786409686,MRB,2001\r\n"
					+ "247 Best Movie Scenes In Film History,Sanford Levine,28.8,9780786411115,MTV,2001\r\n"
					+ "\"Allied Artists Checklist - Feature Films And Short Subjects of Allied Artists Pictures Corporation, 1947 - 1978\",Len D. Martin,35.95,9780786411139,MTV,2001\r\n"
					+ "American Film Studios - An Historical Encyclopedia,Gene Fernett,35.95,9780786413256,MTV,2001\r\n"
					+ "\"Arness, James - An Autobiography\",James Arness,35.95,9780786412211,MTV,2001\r\n"
					+ "Beyond Ballyhoo - Motion Picture Promotion And Gimmicks,Mark Thomas McGee,30.95,9780786411146,MTV,2001\r\n"
					+ "\"Clift, Montgomery - A Biography\",Patricia Bosworth,14.95,0879101350,MTV,2001\r\n"
					+ "Coen Brothers - Films of Joel and Ethan Coen,Carolyn R. Russell,30.95,9780786409730,MTV,2001\r\n"
					+ "\"Comedy Quotes From The Movies - Over 4,000 Bits of Humorous Dialogue From All Film Genres, Topically Arranged And Indexed\",Larry Langman & Paul Gold,30.95,9780786411108,MTV,2001\r\n"
					+ "\"Culture Meets Culture In The Movies - An Analysis East, West, North And South, With Filmographies\",David H. Budd,39.95,9780786410958,MTV,2001\r\n"
					+ "Down And Dirty - Hollywood's Exploitation Filmmakers And Their Movies,Mike Quarles,30.95,9780786411429,MTV,2001\r\n"
					+ "Encyclopedia of Motion Picture Sound,Marty McGee,-85.5,9780786410231,MTV,2001\r\n"
					+ "\"End - Closing Lines of Over 3,000 Theatrically-Released American Films\",R. Donna Chesher,30.95,9780786411078,MTV,2001\r\n"
					+ "\"Espionage Filmography - United States Releases, 1898 Through 1999\",Paul Mavis,44.95,9780786408610,MTV,2001\r\n"
					+ "Famous Hollywood Locations - Descriptions And Photographs of 382 Sites Involving 289 Films And 105 Television Series,Leon Smith,30.95,9780786411160,MTV,2001\r\n"
					+ "\"Feature Films, 1960 - 1969 - A Filmography of English - Language And Major Foreign - Language United States Releases\",Harris M. Lentz III,112.5,9780786411009,MTV,2001\r\n"
					+ "\"Filmed Television Drama, 1952 - 1958\",William Hawes,44.95,9780786411320,MTV,2001\r\n"
					+ "\"Films of The Nineties - A Complete, Qualitative Filmography of Over 3000 Feature - Length English Language Films, Theatrical And Video - Only, Released Between January 1, 1990, And December 31, 1999\",Robert A. Nowlan & Gwendolyn Wright Nowlan,112.5,9780786409747,MTV,2001\r\n"
					+ "Flights of Fancy - The Great Fantasy Films,Kenneth Von Gunden,30.95,9780786412143,MTV,2001\r\n"
					+ "\"Global Television And The Shaping of World Politics: CNN, Telediplomacy, And Foreign Policy\",Royce J. Ammon,30.95,9780786410620,MTV,2001\r\n"
					+ "Gorehound's Guide To Splatter Films of The 1960S And 1970S,Scott Aaron Stine,30.95,9780786409242,MTV,2001\r\n"
					+ "\"Griffith, D.W. - D.W. Griffith's Intolerance - Its Genesis And Its Vision\",William M. Drew,30.95,9780786412099,MTV,2001\r\n"
					+ "Gunmen And Gangsters - Profiles of Nine Actors Who Portrayed Memorable Screen Tough Guys,Michael Schlossheimer,35.95,9780786409891,MTV,2001\r\n"
					+ "\"Hark, Tsui - Cinema of Tsui Hark\",Lisa Morton,44.95,9780786409907,MTV,2001\r\n"
					+ "\"Heroes, Antiheroes And Dolts - Portrayals of Masculinity In American Popular Films, 1921 - 1999\",Ashton D. Trice & Samuel A. Holland,35.95,9780786410972,MTV,2001\r\n"
					+ "Hollywood Cauldron - Thirteen Horror Films From The Genre's Golden Age,Gregory William Mank,35.95,9780786411122,MTV,2001\r\n"
					+ "I Was A Monster Movie Maker - Conversations With 22 Sf And Horror Filmmakers,Tom Weaver,39.95,9780786410002,MTV,2001\r\n"
					+ "\"Investigating Couples - A Critical Analysis of The Thin Man, The Avengers and The X-Files\",Tom Soter,35.95,9780786411238,MTV,2001\r\n"
					+ "\"Keaton, Diane - Artist And Icon\",Deborah C. Mitchell,35.95,9780786410828,MTV,2001\r\n"
					+ "Kings of The Jungle - Tarzan On Screen And Television,David Fury,30.95,9780786411092,MTV,2001\r\n"
					+ "Laurel & Hardy - Boys: The Cinematic World of Laurel And Hardy,Scott Allen Nollen,30.95,9780786411153,MTV,2001\r\n"
					+ "\"Live Television Drama, 1946 - 1951\",William Hawes,44.95,9780786409051,MTV,2001\r\n"
					+ "\"Motor City Marquees - A Comprehensive, Illustrated Reference To Motion Picture Theaters In The Detroit Area, 1906 - 1992\",Stuart Galbraith IV,35.95,9780786411436,MTV,2001\r\n"
					+ "New Hollywood - What The Movies Did With The New Freedoms of The Seventies,James Bernardoni,30.95,9780786412068,MTV,2001\r\n"
					+ "\"Performers' Television Credits, 1948-2000, Volume 1: A-F\",David M. Inman,265.5,9780786410415,MTV,2001\r\n"
					+ "\"Performers' Television Credits, 1948-2000, Volume 3: N-Z\",David M. Inman,265.5,9780786410415,MTV,2001\r\n"
					+ "Producers On Producing - The Making of Film And Television,Irv Broughton,30.95,9780786412075,MTV,2001\r\n"
					+ "\"Screwball Comedy Films - A History And Filmography, 1934 - 1942\",Duane Byrge & Robert Milton Miller,26.96,9780786411061,MTV,2001\r\n"
					+ "Silent Film Necrology (2nd Edition),Eugene Michael Vazzana,85.5,9780786410590,MTV,2001\r\n"
					+ "Silent Stars Speak - Interviews With Twelve Cinema Pioneers,Tony Villecco,35.95,9780786408146,MTV,2001\r\n"
					+ "Suspense Thriller - Films In The Shadow of Alfred Hitchcock,Charles Derry,30.95,9780786412082,MTV,2001\r\n"
					+ "\"von Stroheim, Erich - Life and Films of Erich Von Stroheim\",Richard Koszarski,19.95,0879109548,MTV,2001\r\n"
					+ "\"Westerns And American Culture, 1930-1955\",R. Philip Loy,-35.95,9780786410767,MTV,2001\r\n"
					+ "\"Westerns And The Trail of Tradition - A Year - By - Year History, 1929-1962\",Barrie Hanfling,44.95,9780786411252,MTV,2001\r\n"
					+ "\"Wheeler & Woolsey - The Vaudeville Comic Duo And Their Films, 1929-1937\",Edward Watz,30.95,9780786411412,MTV,2001\r\n"
					+ "\"Women of Warner Brothers - Lives And Careers of 15 Leading Ladies, With Filmographies For Each\",Daniel Bubbeo,35.95,9780786411375,MTV,2001\r\n"
					+ "Baseball - 1919 World Series: What Really Happened?,William A. Cook,28.8,9780786410699,SSM,2001\r\n"
					+ "\"Baseball - Blackball, The Black Sox, And The Babe: Baseball's Crucial 1920 Season\",Robert C. Cottrell,30.95,9780786411641,SSM,2001\r\n"
					+ "Baseball - Bushville: Life and Time in Amateur Baseball,Jerry Kelly,22.46,9780786409792,SSM,2001\r\n"
					+ "\"Baseball - Chase, Hal: The Defiant Life and Turbulent Times of Baseball's Biggest Crook\",Martin Donell Kohout,28.8,9780786410675,SSM,2001\r\n"
					+ "Baseball - Cooperstown Verses: Poems About Each Hall of Famer,Mark W. Schraf,22.46,9780786411481,SSM,2001\r\n"
					+ "\"Baseball - Diamonds In The Coalfields: 21 Remarkable Baseball Players, Managers, and Umpires from Northeast Pennsylvania\",William C. Kashatus,30.95,9780786411764,SSM,2001\r\n"
					+ "Baseball - Early Baseball And The Rise of The National League,Tom Melville,26.96,9780786409624,SSM,2001\r\n"
					+ "Baseball - Idols of The Spring: Baseball Interviews About Preseason Training,Dan Zachofsky,26.96,9780786410798,SSM,2001\r\n"
					+ "\"Baseball - Munson, Thurman: A Baseball Biography\",Christopher Devine,26.96,9780786410897,SSM,2001\r\n"
					+ "Baseball - Pastime In Turbulence: Interviews with Baseball Players of the 1940s,Brent Kelley,30.95,9780786409754,SSM,2001\r\n"
					+ "Baseball - Pittsburgh Crawfords,Jim Bankes,30.95,9780786409921,SSM,2001\r\n"
					+ "Baseball - POW Baseball In World War II: The National Pastime Behind Barbed Wire,Tim Wolter,30.95,9780786411863,SSM,2001\r\n"
					+ "Baseball - Road To Cooperstown: A Critical History of Baseball's Hall of Fame Selection Process,James F. Vail,30.95,9780786410125,SSM,2001\r\n"
					+ "Baseball - Shadow Ball: A Novel of Baseball and Chicago,Peter M. Rutkoff,22.46,9780786409815,SSM,2001\r\n"
					+ "Baseball - Shoeless: The Life and Times of Joe Jackson,David L. Fleitz,26.96,9780786409785,SSM,2001\r\n"
					+ "Baseball - This Day In Yankees History,Ronald L. Meinstereifel,30.95,9780786410026,SSM,2001\r\n"
					+ "Baseball - Waiting For Godot's First Pitch: More Poems from Baseball,Tim Peeler,22.46,9780786411276,SSM,2001\r\n"
					+ "");
			System.out.println(book + " created successfully.");
			b7.close();
		}
if(book.equals("books2002.csv")) {
	b8 = new PrintWriter(book);
			b8.println("Architectural Acoustics,Christopher N. Brooks,35.95,9780786413980,MRB,2002\r\n"
					+ "Broadway Musicals - A Hundred Year History,David H. Lewis,44.95,9780786412693,MRB,2002\r\n"
					+ "\"Gravesites of Southern Musicians - A Guide To Over 300 Jazz, Blues, Country And Rock Performers' Burial Places\",Edward Amos,35.95,9780786412709,MBR,2002\r\n"
					+ "Radio Crime Fighters - Over 300 Programs From The Golden Age,Jim Cox,49.5,9780786413904,MRB,2002\r\n"
					+ "\"Say Goodnight, Gracie - The Last Years of Network Radio\",Jim Cox,35.95,9780786411689,MRB,2002\r\n"
					+ "\"Suffragist Sheet Music - An Illustrated Catalogue of Published Music Associated With The Women's Rights And Suffrage Movement In America, 1795 - 1921, With Complete Lyrics\",Danny O. Crew,66.95,9780786412983,MRB,2002\r\n"
					+ "African American Women And Sexuality In The Cinema,Norma Manatu,35.95,9780786414314,MTV,2002\r\n"
					+ "\"Allen, Woody - Eighteen Woody Allen Films Analyzed: Anguish, God And Existentialism\",Sander H. Lee,26.96,9780786413195,MTV,2002\r\n"
					+ "\"Angeli, Pier - A Fragile Life\",Jane Allen,35.95,9780786413928,MTV,2002\r\n"
					+ "Art of The Short Fiction Film - A Shot By Shot Study of Nine Modern Classics,Richard Raskin,49.5,9780786411832,MTV,2002\r\n"
					+ "\"Austen, Jane On Film And Television - A Critical Study of The Adaptations\",Sue Parrill,30.95,9780786413492,MTV,2002\r\n"
					+ "\"Baggot, King - A Biography And Filmography of The First King of The Movies\",Sally A. Dumaux,49.5,9780786413508,MTV,2002\r\n"
					+ "\"Baseball Filmography, 1915 Through 2001 (2nd Edition)\",Hal Erickson,66.95,9780786412723,MTV,2002\r\n"
					+ "\"Braff Silent Short Film Working Papers - Over 25,000 Films, 1903 - 1929, Alphabetized And Indexed\",Richard E. Braff,175.5,9780786410316,MTV,2002\r\n"
					+ "Brooklyn Film - Essays In The History of Filmmaking,,35.95,9780786414055,MTV,2002\r\n"
					+ "\"Brooks, Mel - Big Screen Comedies of Mel Brooks\",Robert Alan Crick,44.95,9780786410330,MTV,2002\r\n"
					+ "Celluloid Gaze,Boze Hadleigh,11.95,0879109718,MTV,2002\r\n"
					+ "\"Cinema Arthuriana - Twenty Essays, Revised Edition\",,49.5,9780786413447,MTV,2002\r\n"
					+ "Cinema of Generation X - A Critical Study of Films And Directors,Peter Hanson,30.95,9780786413348,MTV,2002\r\n"
					+ "\"Clift, Montgomery - Biography\",Michelangelo Capua,30.95,9780786414321,MTV,2002\r\n"
					+ "Comical Co - Stars of Television - From Ed Norton To Kramer,Robert Pegg,-39.95,9780786413416,MTV,2002\r\n"
					+ "\"Conried, Hans - A Biography: With A Filmography And A Listing of Radio, Television, Stage And Voice Work\",Suzanne Gargiulo,39.95,9780786413386,MTV,2002\r\n"
					+ "\"Crime Fighting Heroes of Television - Over 10,000 Facts From 151 Shows, 1949 - 2001\",Vincent Terrace,35.95,9780786413959,MTV,2002\r\n"
					+ "\"De Sica, Vittorio - Director, Actor, Screenwriter\",Bert Cardullo,-66.95,9780786411351,MTV,2002\r\n"
					+ "\"Devil On Screen - Feature Films Worldwide, 1913 Through 2000\",Charles P. Mitchell,44.95,9780786410491,MTV,2002\r\n"
					+ "Film And Knowledge - Essays On The Integration of Images And Ideas,,35.95,9780786413201,MTV,2002\r\n"
					+ "\"Film And Television Locations - A State - By - State Guidebook To Moviemaking Sites, Excluding Los Angeles\",Doug Gelbert,44.95,9780786412938,MTV,2002\r\n"
					+ "\"Film Fatales - Women In Espionage Films And Television, 1962 - 1973\",Tom Lisanti & Louis Paul,39.95,9780786411948,MTV,2002\r\n"
					+ "\"Fisher, Terence - Horror, Myth And Religion\",Paul Leggett,35.95,9780786411672,MTV,2002\r\n"
					+ "Frankenstein Archive,Donald F. Glut,30.95,9780786413539,MTV,2002\r\n"
					+ "\"Frankenstein Archive - Essays On The Monster, The Myth, The Movies, And More\",Donald F. Glut,30.95,9780786413539,MTV,2002\r\n"
					+ "\"Gable, Clark - Biography, Filmography, Bibliography\",Chrystopher J. Spicer,39.95,9780786411245,MTV,2002\r\n"
					+ "\"Going To Pieces - The Rise And Fall of The Slasher Film, 1978 - 1986\",Adam Rockoff,35.95,9780786412273,MTV,2002\r\n"
					+ "Growing Up On The Set - Interviews With 39 Former Child Actors of Classic Film And Television,Tom Goldrup & Jim Goldrup,30.95,9780786412549,MTV,2002\r\n"
					+ "\"Hayward, Susan - Her Films and Life\",Kim R. Holston,29.95,8.78079E+12,MTV,2002\r\n"
					+ "\"Hitler Filmography - Worldwide Feature Film And Television Miniseries Portrayals, 1940 Through 2000\",Charles P. Mitchell,-34.95,9780786445851,MTV,2002\r\n"
					+ "\"Hooper, Tobe - Eaten Alive At A Chainsaw Massacre: The Films of Tobe Hooper\",John Kenneth Muir,44.95,9780786412822,MTV,2002\r\n"
					+ "\"Horror Film Stars, Third Edition\",Michael R. Pitts,-39.95,9780786410521,MTV,2002\r\n"
					+ "Independent Film Experience - Interviews With Directors And Producers,Kevin J. Lindenmuth,35.95,9780786410750,MTV,2002\r\n"
					+ "\"Jarman, Derek - Films of Derek Jarman\",William Pencak,35.95,9780786414307,MTV,2002\r\n"
					+ "\"Kennedy, Arthur - Man of Characters - A Stage And Cinema Biography\",Meredith C. Macksoud & Craig R. Smith & Jackie Lohrke,35.95,9780786413843,MTV,2002\r\n"
					+ "\"Luxford, Albert J., The Gimmick Man - Memoir of A Special Effects Maestro\",Albert J. Luxford & Gareth Owen,35.95,9780786411504,MTV,2002\r\n"
					+ "\"Montana, Patsy - The Cowboy's Sweetheart\",Patsy Montana & Jane Frost,30.95,9780786410804,MTV,2002\r\n"
					+ "\"Nevada Filmography - Nearly 600 Works Made In The State, 1897 Through 2000\",Gary DuVal,66.95,9780786412716,MTV,2002\r\n"
					+ "\"North Carolina Filmography - Over 2000 Film And Television Works Made In The State, 1905 Through 2000\",Jenny Henderson,66.95,9780786412945,MTV,2002\r\n"
					+ "Photoplay Editions - A Collector's Guide,Rick Miller,39.95,9780786413126,MTV,2002\r\n"
					+ "Queer (Un)Friendly Film And Television,James R. Keller,35.95,9780786412464,MTV,2002\r\n"
					+ "\"Robards, Jason Remembered - Essays And Recollections\",,35.95,9780786413560,MTV,2002\r\n"
					+ "Science Fiction Confidential - Interviews With 23 Monster Stars And Filmmakers,Tom Weaver,39.95,9780786411757,MTV,2002\r\n"
					+ "\"Sitcom Factfinder, 1948 - 1984 - Over 9,700 Details From 168 Television Shows\",Vincent Terrace,35.95,9780786412433,MTV,2002\r\n"
					+ "\"Stone, Fred - Circus Performer And Musical Comedy Star\",Armond Fields,-39.95,9780786411610,MTV,2002\r\n"
					+ "Stop - Motion Armature Machining - A Construction Manual,Tom Brierton,44.95,9780786412440,MTV,2002\r\n"
					+ "\"Veidt, Conrad On Screen - A Comprehensive Illustrated Filmography\",John T. Soister,34.95,9780786445110,MTV,2002\r\n"
					+ "Baseball - Baseball In The Carolinas: 25 Essays on the States' Hardball Heritage,,30.95,9780786413188,SSM,2002\r\n"
					+ "Baseball - Cardinal Points: Poems on St. Louis Cardinals Baseball,Joseph Stanton,22.46,9780786413737,SSM,2002\r\n"
					+ "Baseball - Catcher In The Wry: Baseball Poems,Dan Zamudio,22.46,9780786413140,SSM,2002\r\n"
					+ "\"Baseball - Cunning Kind of Play, A: The Cubs-Giants Rivalry, 1876-1932\",Warren N. Wilbert,30.95,9780786411566,SSM,2002\r\n"
					+ "Baseball - Fleeter Than Birds: The 1985 St. Louis Cardinals and Small Ball's Last Hurrah,Doug Feldmann,26.96,9780786411658,SSM,2002\r\n"
					+ "Baseball - Late And Close: A History of Relief Pitching,Paul Votano,28.8,9780786411627,SSM,2002\r\n"
					+ "Baseball - Outrageous Fortune: What's Wrong with Hall of Fame Voting and How to Make It Statistically Sound,James F. Vail,30.95,9780786411269,SSM,2002\r\n"
					+ "Baseball - Prince At First: Fictional Autobiography of Baseball's Hal Chase,Ed Dinger,22.46,9780786413300,SSM,2002\r\n"
					+ "\"Baseball - San Francisco Seals, The, 1946-1957: Interviews with 25 Former Baseballers\",Brent P. Kelley,26.96,9780786411887,SSM,2002\r\n"
					+ "\"Baseball - That Was Part of Baseball Then: Interviews with 24 Former Major League Baseball Players, Coaches and Managers\",Victor Debs Jr.,30.95,9780786411788,SSM,2002\r\n"
					+ "\"Baseball - Western League: A Baseball History, 1885 through 1999\",W.C. Madden & Patrick J. Stewart,30.95,9780786410033,SSM,2002\r\n"
					+ "");
			System.out.println(book + " created successfully.");
			b8.close();
			}
if(book.equals("books2003.csv")) {
	b9 = new PrintWriter(book);
			b9.println("Dc Comics - A Celebration of The World's Favorite Comic Book Heroes,Les Daniels,19.95,0823079198,CCB,2003\r\n"
					+ "African American Concert Singers Before 1950,Darryl Glenn Nettles,44.95,9780786414673,MRB,2003\r\n"
					+ "\"Cavalieri, Lina - The Life of Opera's Greatest Beauty, 1874 - 1944\",Paul Fryer & Olga Usova,44.95,9780786416851,MRB,2003\r\n"
					+ "Coon - Sanders Nighthawks - The Band That Made Radio Famous,Fred W. Edmiston,58.5,9780786413409,MRB,2003\r\n"
					+ "Death Metal Music - The Passion And Politics of A Subculture,Natalie J. Purcell,35.95,9780786415854,MRB,2003\r\n"
					+ "\"Encyclopedia of Women In Radio, 1920 - 1960\",Leora M. Sies & Luther F. Sies,130.5,9780786414765,MRB,2003\r\n"
					+ "\"Herrold, Charles - Inventor of Radio Broadcasting\",Gordon Greb & Mike Adams,39.95,9780786416905,MRB,2003\r\n"
					+ "Hummert's Radio Factory - The Programs And Personalities of Broadcasting's Most Prolific Producers,Jim Cox,35.95,9780786416318,MRB,2003\r\n"
					+ "\"Jazz Musicians of The Early Years, To 1945\",David Dicaire,35.95,9780786415830,MRB,2003\r\n"
					+ "Music of Emily Dickinson's Poems and Letters: A Study of Imagery and Form,Carolyn Lindley Cooley,35.95,9780786414918,MRB,2003\r\n"
					+ "\"North, Alex - Alex North, Film Composer: A Biography, with Musical Analyses of A Streetcar Named Desire, Spartacus, The Misfits, Under the Volcano, and Prizzi?s Honor\",Sanya Shoilevska Henderson,39.95,9780786443338,MRB,2003\r\n"
					+ "\"Opera for Libraries: A Guide to Core Works, Audio and Video Recordings, Books and Serials\",Clyde T. McCants,44.95,9780786414420,MRB,2003\r\n"
					+ "\"Radio Mystery And Adventure And Its Appearances In Film, Television And Other Media\",Jim Harmon,35.95,9780786418107,MRB,2003\r\n"
					+ "\"Radio Program Openings And Closings, 1931 - 1972\",Vincent Terrace,66.95,9780786414857,MRB,2003\r\n"
					+ "\"Springsteen, Bruce - Bruce Springsteen's America: People Listening Poet Singing\",Robert Coles,7.95,0375505598,MRB,2003\r\n"
					+ "Start & Run Your Own Record Label - Updated & Expanded Edition,Daylle Deanna Schwartz,12.95,0823084337,MRB,2003\r\n"
					+ "Wagner and Suicide,John Louis DiGaetani,39.95,9780786414772,BRM,2003\r\n"
					+ "Who - A Who's Who,Richard Bogovich & Cheryl Posner,30.95,9780786415694,MRB,2003\r\n"
					+ "\"Bergman's Muses - Aesthetic Versatility In Film, Theatre, Television And Radio\",Egil Tornqvist,35.95,9780786416035,MTV,2003\r\n"
					+ "\"Bronson, Charles - The 95 Films And The 156 Television Appearances\",Michael R. Pitts,35.95,9780786417025,MTV,2003\r\n"
					+ "Czech New Wave Filmmakers In Interviews,Robert Buchar,44.95,9780786417209,MTV,2003\r\n"
					+ "\"Double Feature Creature Attack: A Monster Merger of Two More, Volumes of Classic Interviews\",Tom Weaver,39.95,9780786413669,MTV,2003\r\n"
					+ "Dreams on Film: The Cinematic Struggle Between Art and Science,Leslie Halpern,30.95,9780786415960,MTV,2003\r\n"
					+ "Drive-in Dream Girls: A Galaxy of B-Movie Starlets of the Sixties,Tom Lisanti,39.95,9780786415755,MTV,2003\r\n"
					+ "\"Early Film Noir - Greed, Lust And Murder Hollywood Style\",William Hare,35.95,9780786416295,MTV,2003\r\n"
					+ "Film Industry In Argentina - An Illustrated Cultural History,Jorge Finkielman,44.95,9781786416288,MTV,2003\r\n"
					+ "\"Film Noir Guide: 745 Films of the Classic Era, 1940-1959\",Michael F. Keaney,66.95,9780786415472,MTV,2003\r\n"
					+ "\"Fuller, Sam - Film Is A Battleground: Critical Study, With Interviews Filmography And A Bibliography\",Lee Server,30.95,9780786417001,MTV,2003\r\n"
					+ "\"Garfield, John - He Ran All the Way - The Life of John Garfield\",Robert Nott,19.95,0879109858,MTV,2003\r\n"
					+ "Gorehound's Guide To Splatter Films of The 1980S,Scott Aaron Stine,30.95,9780786415328,MTV,2003\r\n"
					+ "\"Hellman, Monte - His Life and Films\",Brad Stevens,35.95,9780786414345,MTV,2003\r\n"
					+ "High on the Hogs: A Biker Filmography,David Stidworthy,35.95,9780786414185,MTV,2003\r\n"
					+ "Hollywood Musicals Nominated For Best Picture,Frederick G. Vogel,66.95,9780786412907,MTV,2003\r\n"
					+ "\"Huntley, Chet - Good Night, Chet: A Biography of Chet Huntley\",Lyle Johnston,35.95,9780786415021,MTV,2003\r\n"
					+ "\"Leigh, Vivien - A Biography\",Michelangelo Capua,30.95,9780786414970,MTV,2003\r\n"
					+ "Let The Credits Roll - Interviews With Film Crew,Barbara Baker,35.95,9780786416790,MTV,2003\r\n"
					+ "\"Lewton, Val - Fearing The Dark: The Val Lewton Career\",Edmund G. Bansak,35.95,9780786417094,MTV,2003\r\n"
					+ "\"Lloyd, Harold - Harold Lloyd Encyclopedia\",Annette D'Agostino Lloyd,66.95,9780786415144,MTV,2003\r\n"
					+ "\"Mexican Filmography, 1916 Through 2001\",David E. Wilt,175.5,9780786415373,MTV,2003\r\n"
					+ "\"Monty Python, Shakespeare and English Renaissance Drama\",Darl Larsen,-30.95,9780786415045,MTV,2003\r\n"
					+ "\"Moviegoing Experience, 1968-2001\",Richard W. Haines,35.95,9780786413614,MTV,2003\r\n"
					+ "\"Movies Were Always Magical: Interviews with 19 Actors, Directors, and Producers from the Hollywood of the 1930s through the 1950s\",Leo Verswijver,35.95,9780786411290,MTV,2003\r\n"
					+ "Nabokov At The Movies - Film Perspectives In Fiction,Barbara Wyllie,39.95,9780786416387,MTV,2003\r\n"
					+ "Piracy in the Motion Picture Industry,Kerry Segrave,35.95,9780786414734,MTV,2003\r\n"
					+ "Poe Cinema,Don G. Smith,35.95,9780786417032,MTV,2003\r\n"
					+ "Radio Live! Television Live! - Those Golden Days When Horses Were Coconuts,Robert L. Mott,35.95,9780786418121,MTV,2003\r\n"
					+ "\"Reel Baseball: Essays and Interviews on the National Pastime, Hollywood and American Culture\",Stephen C. Wood,30.95,9780786413898,MTV,2003\r\n"
					+ "\"Reel Portrayals - The Lives of 640 Historical Persons On Film, 1929 Through 2001\",Michael G. Stevens,44.95,9780786414611,MTV,2003\r\n"
					+ "\"Saint - A Complete History In Print, Radio, Film And Television of Leslie Charteris' Robin Hood of Modern Crime, Simon Templar, 1928 - 1992\",Burl Barer,35.95,9780786416806,MTV,2003\r\n"
					+ "Seeing Hardy: Film and Television Adaptations of the Fiction of Thomas Hardy,Paul J. Niemeyer,35.95,9780786414291,MTV,2003\r\n"
					+ "\"Short-Lived Television Series, 1948 - 1978 - Thirty Years of More Than 1,000 Flops\",Wesley Hyatt,44.95,9780786414208,MTV,2003\r\n"
					+ "\"Slasher Films - An International Filmography, 1960 Through 2001\",Kent Byron Armstrong,66.95,9780786414628,MTV,2003\r\n"
					+ "\"Strong, Silent Type - Over 100 Screen Cowboys, 1903 - 1930\",Buck Rainey,66.95,9780786412860,MTV,2003\r\n"
					+ "Survivor Lessons - Essays On Communication And Reality Television,,35.95,9780786416684,MTV,2003\r\n"
					+ "Technicolor Movies - The History of Dye Transfer Printing,Richard W. Haines,30.95,9780786418091,MTV,2003\r\n"
					+ "\"Television Crime Fighters Factbook - Over 9,800 Details From 301 Programs, 1937 - 2003\",Vincent Terrace,35.95,9780786415335,MTV,2003\r\n"
					+ "\"Television Program Master Index: Access to Critical and Historical Information on 1,927 Shows in 925 Books, Dissertations, and Journal Articles, 2d ed.\",Charles V. Dintrone,66.95,9780786414925,MTV,2003\r\n"
					+ "\"Universal Sound Westerns, 1929 - 1946 - The Complete Filmography\",Gene Blottner,66.95,9780786415113,MTV,2003\r\n"
					+ "Vitaphone Films - A Catalogue of The Features And Shorts,Roy Liebman,-85.5,9780786412792,MTV,2003\r\n"
					+ "War And Film In America - Historical And Critical Essays,,30.95,9780786416738,MTV,2003\r\n"
					+ "War Veteran in Film,Emmett Early,35.95,9780786414710,MTV,2003\r\n"
					+ "\"Western Film Highlights - The Best of The West, 1914-2001\",Henryk Hoffmann,35.95,9780786445011,MTV,2003\r\n"
					+ "\"Wong, Anna May - A Complete Guide to Her Film, Stage, Radio and Television Work\",Philip Leibfried & Chei Mi Lane,49.5,9780786416332,MTV,2003\r\n"
					+ "\"Zinnemann, Fred - Films of Character And Conscience\",Neil Sinyard,35.95,9780786417117,MTV,2003\r\n"
					+ "Baseball - 1917 White Sox: Their World Championship Season,Warren N. Wilbert & William C. Hageman,30.95,9780786416226,SSM,2003\r\n"
					+ "Baseball - Ashes of Lou Gehrig And Other Baseball Essays,Sean Peter Kirst,30.95,9780786415786,SSM,2003\r\n"
					+ "Baseball - Busting 'Em And Other Big League Stories,Ty Cobb,30.95,9780786415991,SSM,2003\r\n"
					+ "Baseball - Commy: The Life Story of Charles A. Comiskey,G.W. Axelson,30.95,9780786415984,SSM,2003\r\n"
					+ "\"Baseball - Everything Happens In Chillicothe: A Summer in the Frontier League with Max McLeary, The One-Eyed Umpire\",Mike Shannon,26.96,9780786416943,SSM,2003\r\n"
					+ "\"Baseball - League of My Own, A: Memoir of a Pitcher for the All-American Girls Professional Baseball League\",Patricia I. Brown,26.96,9780786414741,SSM,2003\r\n"
					+ "\"Baseball - Marsans, Armando: A Cuban Pioneer in the Major Leagues\",Peter T. Toot,28.8,9780786415847,SSM,2003\r\n"
					+ "");
			System.out.println(book + " created successfully.");
			b9.close();
				}
if(book.equals("books2004.csv")) {
	b10 = new PrintWriter(book);
			b10.println("Popeye: Illustrated Cultural History,Fred M. Grandinetti,39.95,9780786416059,CCB,2004\r\n"
					+ "Up From The Vault - Rare Thrillers of The 1920S And 1930S,John T. Soister,49.5,9780786417452,CTV,2004\r\n"
					+ "Collecting Western Memorabilia,Tim Lasiuta,30.95,9780786416608,HCB,2004\r\n"
					+ "American Film Musical Themes And Forms,Michael Dunne,44.95,9780786418770,MRB,2004\r\n"
					+ "American Opera Singers And Their Recordings,Clyde T. McCants,66.95,9780786419524,MRB,2004\r\n"
					+ "\"Blueswomen - Profiles of 37 Early Performers, With An Anthology of Lyrics, 1920 - 1945\",Anna Stong Bourgeois,30.95,9780786421220,MRB,2004\r\n"
					+ "\"CBS Radio Mystery Theater - An Episode Guide and Handbook to Nine Years of Broadcasting, 1974 - 1982\",Gordon Payton & Martin Grams Jr.,39.95,9780786418909,MRB,2004\r\n"
					+ "Confederate Sheet Music,E. Lawrence Abel,85.5,9780786415076,MRB,2004\r\n"
					+ "\"Getz, Stan - An Annotated Bibliography And Filmography, With Song And Session Information For Albums\",Nicholas Churchill,66.95,9780786419494,MRB,2004\r\n"
					+ "\"Great Composers Portrayed On Film, 1913 Through 2002\",Charles P. Mitchell,66.95,9780786417957,MRB,2004\r\n"
					+ "Manipulating The Ether - The Power of Broadcast Radio In Thirties America,Robert J. Brown,39.95,9780786420667,MRB,2004\r\n"
					+ "\"Marching Band Handbook - Competitions, Instruments, Clinics, Fundraising, Publicity, Uniforms, Accessories, Trophies, Drum Corps, Twirling, Color Guard, Indoor Guard, Music, Travel, Directories, Bibliographies, Index, 3D Ed.\",Kim R. Holston,44.95,9780786416509,MRB,2004\r\n"
					+ "\"Mr. Keen, Tracer of Lost Persons - A Complete History And Episode Log of Radio's Most Durable Detective\",Jim Cox,58.5,9780786417384,MRB,2004\r\n"
					+ "\"Pop, Iggy - Gimme Danger: The Story of Iggy Pop\",Joe Ambrose,13.5,1844493288,MRB,2004\r\n"
					+ "Radio's Captain Midnight - The Wartime Biography,Stephen A. Kallis Jr.,26.96,9780786421763,BRM,2004\r\n"
					+ "\"Storytelling In The Pulps, Comics, And Radio - How Technology Changed Popular Fiction In America\",Tim DeForest,30.95,9780786419029,MRB,2004\r\n"
					+ "String Bands In The North Carolina Piedmont,Bob Carlin,35.95,9780786418268,MRB,2004\r\n"
					+ "Abbott & Costello - The Horror Spoofs of Abbott And Costello,Jeffrey S. Miller,30.95,9780786419227,MTV,2004\r\n"
					+ "Almost Shakespeare - Reinventing His Works For Cinema And Television,,30.95,9780786419098,MTV,2004\r\n"
					+ "American Martial Arts Film,M. Ray Lott,-44.95,9780786418367,MTV,2004\r\n"
					+ "American Plays And Musicals On Screen - 650 Stage Productions And Their Film And Television Adaptations,Thomas S. Hischak,66.95,9780786420032,MTV,2004\r\n"
					+ "Architecture For The Screen - A Critical Study of Set Design In Hollywood's Golden Age,Juan Antonio Ramirez,49.5,9780786417810,MTV,2004\r\n"
					+ "Assault of The Killer B's - Interviews With 20 Cult Film Actresses,Jason Paul Collum,30.95,9780786418183,MTV,2004\r\n"
					+ "\"Batman Filmography - Live - Action Features, 1943 - 1997\",Mark S. Reinhart,44.95,9780786420230,MTV,2004\r\n"
					+ "\"Boxing Filmography - American Features, 1920 - 2003\",Frederick V. Romano,35.95,9780786417933,MTV,2004\r\n"
					+ "\"Chan, Charlie - At The Movies\",Ken Hanke,30.95,9780786419210,MTV,2004\r\n"
					+ "\"Chaney, Jr., Lon - Horror Film Star, 1906 - 1973\",Don G. Smith,30.95,9780786418138,MTV,2004\r\n"
					+ "\"Craven, Wes - The Art of Horror\",John Kenneth Muir,30.95,9780786419234,MTV,2004\r\n"
					+ "\"Definitive Andy Griffith Show Reference - Episode - By - Episode, With Cast And Production Biographies And A Guide To Collectibles\",Dale Robinson & David Fernandes,35.95,9780786420681,MTV,2004\r\n"
					+ "\"Doyle, Sir Arthur Conan - At The Cinema - A Critical Study of The Film Adaptations\",Scott Allen Nollen,30.95,9780786421244,MTV,2004\r\n"
					+ "Eighty Odd Years In Hollywood - Memoir of A Career In Film And Television,John Meredyth Lucas,30.95,9780786418381,MTV,2004\r\n"
					+ "\"First Hollywood Musicals - A Critical Filmography of 171 Features, 1927 Through 1932\",Edwin M. Bradley,44.95,9780786420292,MTV,2004\r\n"
					+ "\"Flynn, Errol - The Life And Career\",Thomas McNulty,58.5,9780786417506,MTV,2004\r\n"
					+ "Foreign Films In America - A History,Kerry Segrave,35.95,9780786417643,MTV,2004\r\n"
					+ "Ghost Images - Cinema of The Afterlife,Tom Ruffles,35.95,9780786420056,MTV,2004\r\n"
					+ "\"Harryhausen, Ray - An Animated Life\",Ray Harryhausen & Tony Dalton,34.95,0823084027,MTV,2004\r\n"
					+ "\"Harryhausen, Ray - Dinosaur Films of Ray Harryhausen\",Roy P. Webber,44.95,9780786416660,MTV,2004\r\n"
					+ "Hick Flicks - The Rise And Fall of Redneck Cinema,Scott Von Doviak,30.95,9780786419975,MTV,2004\r\n"
					+ "\"Hill, George Roy - Films of George Roy Hill, Revised Edition\",Andrew Horton,49.5,9780786419319,MTV,2004\r\n"
					+ "\"Hitchcock, Alfred - Alfred Hitchcock's Silent Films\",Marc Raymond Strauss,35.95,9780786419012,MTV,2004\r\n"
					+ "\"Hope, Bob - The Bob Hope Films\",James L. Neibaur,35.95,9780786410507,MTV,2004\r\n"
					+ "Horror And Mystery Photoplay Editions And Magazine Fictionizations - The Catalog of A Collection,Thomas Mann,35.95,9780786417223,MTV,2004\r\n"
					+ "Independent Filmmaker's Guide To Writing A Business Plan For Investors,Gabriel Campisi,35.95,9780786416820,MTV,2004\r\n"
					+ "It Came From Horrorwood - Interviews With Moviemakers In The Sf And Horror Tradition,Tom Weaver,28.8,9780786420698,MTV,2004\r\n"
					+ "Italian Horror Film Directors,Louis Paul,44.95,9780786418343,MTV,2004\r\n"
					+ "Italian Horror Films of The 1960S,Lawrence McCallum,30.95,9780786419685,MTV,2004\r\n"
					+ "Jekyll And Hyde Dramatized - The 1887 Richard Mansfield Script And The Evolution of The Story On Stage,Martin A. Danahay & Alex Chisholm,39.95,9780786418701,MTV,2004\r\n"
					+ "Killer Tomatoes - Fifteen Tough Film Dames,Ray Hagen & Laura Wagner,35.95,9780786418831,MTV,2004\r\n"
					+ "\"Lee, Christopher Filmography\",Tom Johnson & Mark A. Miller,35.95,9780786446919,MTV,2004\r\n"
					+ "Life Through The Lens - Memoirs of A Film Cameraman,Alan Hume & Gareth Owen,30.95,9780786418039,MTV,2004\r\n"
					+ "\"Lynch, David - Pervert In The Pulpit: Morality In The Works of David Lynch\",Jeff Johnson,30.95,9780786417537,MTV,2004\r\n"
					+ "\"Makeover In Movies - Before And After In Hollywood Films, 1941-2002\",Elizabeth A. Ford & Deborah C. Mitchell,35.95,9780786417216,MTV,2004\r\n"
					+ "Making of Rebel Without A Cause,Douglas L. Rathgeb,44.95,9780786419760,MTV,2004\r\n"
					+ "Medieval Hero On Screen,Martha W. Driver,30.95,9780786419265,MTV,2004\r\n"
					+ "Movie Moguls Speak - Interviews With Top Film Producers,Steven Prigge,35.95,9780786419296,MTV,2004\r\n"
					+ "Movies And The Mind - Theories of The Great Psychoanalysts Applied To Film,William Indick,35.95,9780786419531,MTV,2004\r\n"
					+ "Moving Pictures And Classic Images - Memories of Forty Years In The Vintage Film Hobby,Samuel K. Rubin,35.95,9780786417575,MTV,2004\r\n"
					+ "Mr. Deeds Goes To Yankee Stadium - Baseball Films In The Capra Tradition,Wes D. Gehring,30.95,9780786417735,MTV,2004\r\n"
					+ "\"Nightmares In Red, White And Blue - The Evolution of The American Horror Film\",Joseph Maddrey,30.95,9780786418602,MTV,2004\r\n"
					+ "\"O'Brien, Margaret - A Career Chronicle And Biography\",Allan R. Ellenberger,35.95,9780786421558,MTV,2004\r\n"
					+ "Plato And Popcorn - A Philosopher's Guide To 75 Thought - Provoking Movies,William G. Smith,30.95,9780786418787,MTV,2004\r\n"
					+ "Product Placement In Hollywood Films,Kerry Segrave,-35.95,9780786419043,MTV,2004\r\n"
					+ "Production Design In The Contemporary American Film - A Critical Study of 23 Movies And Their Designers,Beverly Heisner,26.96,9780786418657,MTV,2004\r\n"
					+ "\"Psychotherapists On Film, 1899 - 1999 - A Worldwide Guide To Over 5000 Films, Volume 2 - M - Z, Appendices, Index\",John Flowers & Paul Frizler,130.5,9780786412976,MTV,2004\r\n"
					+ "\"Rooney, Mickey - His Films, Television Appearances, Radio Work, Stage Shows, And Recordings\",Alvin H. Marill,35.95,9780786420155,MTV,2004\r\n"
					+ "\"Science Fiction And Fantasy Film Flashbacks - Conversations With 24 Actors, Writers, Producers And Directors From The Golden Age\",Tom Weaver,28.8,9780786420704,MTV,2004\r\n"
					+ "Scorsese Psyche On Screen - Roots of Themes And Characters In The Films,Maria T. Miliora,35.95,9780786417636,MTV,2004\r\n"
					+ "Six Day Horror Movie - A No - Nonsense Guide To No - Budget Filmmaking,Michael P. DiPaolo,35.95,9780786419050,MTV,2004\r\n"
					+ "\"Smirk, Sneer And Scream\",Mark Clark,44.95,9780786419326,MTV,2004\r\n"
					+ "Space Patrol,Jean-Noel Bassior,44.95,9780786419111,MTV,2004\r\n"
					+ "This Is A Thriller,Alan Warren,30.95,9780786419692,MTV,2004\r\n"
					+ "\"Verne, Jules On Film\",Thomas C. Renzi,35.95,9780786419661,MTV,2004\r\n"
					+ "\"Westerns In A Changing America, 1955-2000\",R. Philip Loy,35.95,9780786418718,MTV,2004\r\n"
					+ "Westerns Women - Interviews With 50 Leading Ladies of Movie And Television Westerns From The 1930S To The 1960S,Boyd Magers & Michael G. Fitzgerald,35.95,9780786420285,MTV,2004\r\n"
					+ "\"Wilder, Billy - American Film Realist\",Richard Armstrong,30.95,9780786421190,MTV,2004\r\n"
					+ "\"Brown, Charles Brockden and the Literary Magazine\",Michael Cody,35.95,9780786417841,NEB,2004\r\n"
					+ "Baseball - Base-Ball Ballads,Grantland Rice,-30.95,9780786420384,SSM,2004\r\n"
					+ "Baseball - Baseball In Cincinnati: A History,Harry Ellard,30.95,9780786417261,SSM,2004\r\n"
					+ "Baseball - Fix Is In: A History of Baseball Gambling and Game Fixing Scandals,Daniel E. Ginsburg,26.96,9780786419203,SSM,2004\r\n"
					+ "\"Baseball - Foxx, Jimmie: The Life and Times of a Baseball Hall of Famer, 1907-1967\",W. Harrison Daniel,28.8,9780786418671,SSM,2004\r\n"
					+ "Baseball - Hardball On The Home Front: Major League Replacement Players of World War II,Craig Allen Cleve,30.95,9780786418978,SSM,2004\r\n"
					+ "\"Baseball - Mathewson, Christy: A Biography\",Michael Hartley,28.8,9780786416530,SSM,2004\r\n"
					+ "Baseball - Puerto Rico's Winter League: A History of Major League Baseball's Launching Pad,Thomas E. Van Hyning,30.95,9780786419708,SSM,2004\r\n"
					+ "\"Baseball - Textile League Baseball: South Carolina's Mill Teams, 1880-1955\",Thomas K. Perry,26.96,9780786418756,SSM,2004\r\n"
					+ "Baseball - Touching Second,John J. Evers & Hugh S. Fullerton,30.95,9780786418695,SSM,2004\r\n"
					+ "Baseball - Woman's Work: Writing Baseball History with Harold Seymour,Dorothy Jane Mills,30.95,9780786418480,SSM,2004\r\n"
					+ "");
			System.out.println(book + " created successfully.");
			b10.close();
	}
if(book.equals("books2005.csv")) {
	b11 = new PrintWriter(book);
		b11.println("Batman - The Complete History,Les Daniels,14.95,0811842320,CCB,2005\r\n"
				+ "Comics Buyer's Guide Standard Catalog of Comic Books,John Jackson Miller & Maggie Thompson & Peter Bickford & Brent Frankenhoff,24.95,087349993X,CCB,2005\r\n"
				+ "\"Crumb, R. - The Life And Times of R. Crumb\",,5.95,0312195710,CCB,2005\r\n"
				+ "\"Dr. Seuss Catalog - An Annotated Guide To Works By Theodor Geisel In All Media, Writings About Him, And Appearances of Characters And Places In The Books, Stories And Films\",Richard H.F. Lindemann,35.95,9780786422234,CCB,2005\r\n"
				+ "Film Cartoons - A Guide To 20th Century American Animated Features And Shorts,Douglas L. McCall,30.95,9780786424504,CCB,2005\r\n"
				+ "Golden Age of DC Comics,Les Daniels & Chip Kidd & Geoff Spear,14.95,0810949695,CCB,2005\r\n"
				+ "Hanna-Barbera Cartoons,Michael Mallory,18.95,0883631083,CBB,2005\r\n"
				+ "Krazy Kat - The Comic Art of George Herriman,Patrick McDonnell & Karen O'Connell & Georgia Riley de Havenon,5.95,0810991853,CCB,2005\r\n"
				+ "Masters of Animation,John Grant,11.95,0823030415,CCB,2005\r\n"
				+ "Private Eyes In the Comics,John A. Dinan,9.95,159393002X,CCB,2005\r\n"
				+ "Superhero Book,,24.95,1578591546,CCB,2005\r\n"
				+ "Superman - The Complete History,Lee Daniels,-12.95,0811842312,CCB,2005\r\n"
				+ "Antique Golf Collectibles - A Price and Reference Guide (2nd Edition),Chuck Furjanic,14.95,0873417909,HCB,2005\r\n"
				+ "Antique Trader - Antiques & Collectibles 2006 Price Guide,Kyle Husfloen,16.95,,HCB,2005\r\n"
				+ "Antiques 101,Frank Farmer Loomis IV,17.95,,HCB,2005\r\n"
				+ "Campbell's Soup Collectibles - A Price & Identification Guide,David & Micki Young,11.95,0873416031,HCB,2005\r\n"
				+ "Christie's Century of Teddy Bears,Leyla Maniera,19.95,082300645X,HCB,2005\r\n"
				+ "Collecting The Old West,Jim & Nancy Schaut,7.95,0873416910,HCB,2005\r\n"
				+ "GI Joe: Complete Story of America's Favorite Man of Action,John Michlig,8.95,0811818225,HCB,2005\r\n"
				+ "Hake's Guide To Cowboy Character Collectibles,Ted Hake,6.95,0870696475,HCB,2005\r\n"
				+ "Japanese Toys - So Crazy Japanese Toys!,Jimbo Matison,9.95,0811835294,HCB,2005\r\n"
				+ "Lunchbox - Inside And Out,Jack Mingo & Erin Barrett,4.95,0060595191,HCB,2005\r\n"
				+ "\"Monroe, Marilyn - Marilyn Memorabilia\",Clark Kidder,9.95,0873493427,HCB,2005\r\n"
				+ "\"Monroe, Marilyn Collectibles\",Clark Kidder,2.95,038079909X,HCB,2005\r\n"
				+ "Music Boxes,Gilbert Bahl,4.95,1561382205,HCB,2005\r\n"
				+ "O'Brien's Collecting Toy Cars & Trucks,,21.95,0873498364,HCB,2005\r\n"
				+ "Petretti's Soda Pop Collectibles Price Guide (3rd Edition),Allan Petretti,29.95,0873495160,HCB,2005\r\n"
				+ "Price Guide to Antique Silver,Peter Waldron,24.95,1851493808,HCB,2005\r\n"
				+ "Ray Gun,Eugene W. Metcalf & Frank Maresca,7.95,1584180048,HCB,2005\r\n"
				+ "Space Toys of the 60's,James H. Gillam,16.95,1896522378,HCB,2005\r\n"
				+ "Star Wars - A Universe of Star Wars Collectibles: Identification and Price Guide (2nd Edition),Stuart W. Wells III,11.95,0873494156,HCB,2005\r\n"
				+ "Warman's Fiesta Ware Identification & Price Guide,Mark F. Moran,19.95,0873497511,HCB,2005\r\n"
				+ "Warman's Toys Field Guide,,-9.95,0873498178,HCB,2005\r\n"
				+ "\"Wonder of American Toys, 1920-1950\",Charles Dee Sharp,14.95,1888054700,HBC,2005\r\n"
				+ "\"45 RPM - The History, Heroes & Villains of a Pop Music Revolution\",Jim Dawson & Steve Propes,12.95,0879307579,MRB,2005\r\n"
				+ "Abba - The Complete Guide To Their Music,Carl Magnus Palm,4.95,,MRB,2005\r\n"
				+ "Album: Classic Sleeve Design,Nick de Ville,24.95,1845331303,MRB,2005\r\n"
				+ "\"American Singing Groups: A History, 1940-1990\",Jay Warner,14.98,0306809230,MRB,2005\r\n"
				+ "\"Armstrong, Louis - The Offstage Story of Satchmo\",Michael Cogswell,19.95,1888054816,MRB,2005\r\n"
				+ "Art of Digital Music,David Battino & Kelli Richards,19.95,0879308303,MRB,2005\r\n"
				+ "\"Bacharach, Burt & Hal David - What the World Needs Now\",Robin Platts,11.95,1896522777,MRB,2005\r\n"
				+ "\"Baker, Josephine Story\",Ean Wood,7.95,,MRB,2005\r\n"
				+ "Beach Boys,Keith Badman,19.95,0879308184,MRB,2005\r\n"
				+ "Beatles - 365 Days,Simon Wells,19.95,0810959119,MRB,2005\r\n"
				+ "Beatles - After The Break-Up: In Their Own Words,David Bennahum,4.95,0711925585,MRB,2005\r\n"
				+ "\"Beatles - Beatles Diary, Volume 1: Beatles Years\",Barry Miles,15.95,0711983089,MRB,2005\r\n"
				+ "\"Beatles - Beatles Diary, Volume 2: After The Break-Up, 1970-2001\",Keith Badman,10.95,0711983070,MRB,2005\r\n"
				+ "Beatles - Diary: An Intimate Day By Day History (Oversized Edition),Barry Miles,24.95,0711963150,MRB,2005\r\n"
				+ "Beatles - Quarrymen,Hunter Davies,-4.95,071198526X,MRB,2005\r\n"
				+ "Beatles - Ticket To Ride: Inside The Beatles' 1964 Tour That Changed The World,Larry Kane,5.95,014303426X,MRB,2005\r\n"
				+ "Beatles - True Beginnings,Roag Best & Pete Best & Rory Best,12.95,,MRB,2005\r\n"
				+ "Beatles - Unseen Archives,Tim Hill & Marie Clayton,17.95,0752583697,MRB,2005\r\n"
				+ "Beatles - Walrus Was Ringo: 101 Beatles Myths Debunked,Alan Clayson & Spencer Leigh,14.95,,MRB,2005\r\n"
				+ "\"Beck, Jeff - Crazy Fingers\",Annette Carson,9.95,0879306327,MRB,2005\r\n"
				+ "\"Bickersons Scripts, Volume 1\",Philip Rapp,15.95,0971457042,MRB,2005\r\n"
				+ "Big Star,Rob Jovanovic,12.95,,MRB,2005\r\n"
				+ "Billboard Book of Number One Adult Contemporary Hits,Wesley Hyatt,5.95,,BBM,2005\r\n"
				+ "Billboard Book of Number One Albums,Craig Rosen,7.95,0823075869,MRB,2005\r\n"
				+ "Billboard Book of Top 40 R&B and Hip-Hop Hits,Joel Whitburn,22.95,0823082830,MRB,2005\r\n"
				+ "\"Billboard Christmas In The Charts, 1920-2004\",Joel Whitburn,19.95,0898201616,MRB,2005\r\n"
				+ "Billboard Guide To Progressive Music,Bradley Smith,1.99,0823176652,MRB,2005\r\n"
				+ "Billboard Hot 100 Charts - The Sixties,Joel Whitburn,56.95,,MRB,2005\r\n"
				+ "Billboard's Music And Video Yearbook: 1987,Joel Whitburn,24,0898200652,MRB,2005\r\n"
				+ "Billboard's Music And Video Yearbook: 1991,Joel Whitburn,24,0898200814,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1985,Joel Whitburn,24,0898200571,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1986,Joel Whitburn,24,0898200636,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1993,Joel Whitburn,24,0898201020,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1995,Joel Whitburn,26,0898201160,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1998,Joel Whitburn,25.5,0898201314,MRB,2005\r\n"
				+ "Billboard's Music Yearbook: 1999,Joel Whitburn,26.95,0898201381,MRB,2005\r\n"
				+ "Billboard's Rock Tracks (Album Rock 1981 To 1995 / Modern Rock 1988 To 1995),Joel Whitburn,25,0898201144,MRB,2005\r\n"
				+ "Billboard's This Business of Music,Shemel Krasilovsky,4.95,0823077063,MRB,2005\r\n"
				+ "Billboard's Top Pop Album Tracks: 1993 To 1996,Joel Whitburn,11,0898201187,MRB,2005\r\n"
				+ "\"Bloomfield, Michael - If You Love These Blues\",Jan Mark Wolkin & Bill Keenom,16.95,0879306173,MRB,2005\r\n"
				+ "Blue Note - Album Cover Art: The Ultimate Collection,,14.95,0811836886,MRB,2005\r\n"
				+ "\"Bocelli, Andrea - A Celebration\",Antonia Felix,4.95,0312253095,MRB,2005\r\n"
				+ "\"Bonham, John - A Thunder of Drums\",Chris Welch & Geoff Nicholls,12.95,0879306580,MMR,2005\r\n"
				+ "\"Bowie, David - Complete Guide To His Music\",David Buckley,4.95,1844494233,MRB,2005\r\n"
				+ "Boyz II Men (Us II You),David Cohen,3.95,0006492487,MRB,2005\r\n"
				+ "Bristol Sessions - Writings About The Big Bang of Country Music,,30.95,9780786419456,MRB,2005\r\n"
				+ "\"Buchanan, Roy - American Axe\",Phil Carson,11.95,0879306394,MRB,2005\r\n"
				+ "\"Buckley, Tim - Blue Melody: Tim Buckley Remembered\",Lee Underwood,12.95,0879307188,MRB,2005\r\n"
				+ "\"Cash, Johnny - The Autobiography\",Johnny Cash & Patrick Carr,4.95,0062515004,MRB,2005\r\n"
				+ "\"Cash, Johnny - The Man Called Cash\",Steve Turner,11.95,0849908159,MRB,2005\r\n"
				+ "Chaka! Through The Fire,Chaka Khan & Tonya Bolden,4.95,0312319258,MRB,2005\r\n"
				+ "\"Christian, Charlie - Solo Flight: The Seminal Electric Guitarist\",Peter Broadbent,4.95,1872639569,MRB,2005\r\n"
				+ "Clash,Paul Du Noyer (Introduction),4.95,0312179391,MRB,2005\r\n"
				+ "Clash - Complete Guide To Their Music,Tony Fletcher,4.95,184449506X,MRB,2005\r\n"
				+ "Clash - Return of The Last Gang In Town,Marcus Gray,14.95,063408240X,MRB,2005\r\n"
				+ "\"Cole, Nat King\",Daniel Mark Epstein,6.95,0374219125,MRB,2005\r\n"
				+ "Country Music Changed My Life,Ken Burke,14.95,1556525389,MRB,2005\r\n"
				+ "Cream,Chris Welch,12.95,0879306246,MRB,2005\r\n"
				+ "\"Darin, Bobby\",Al DiOrio,5.95,0762418168,MRB,2005\r\n"
				+ "\"Darin, Bobby - Me And Bobby D: A Memoir\",Steve Karmen,11.95,0634080261,MRB,2005\r\n"
				+ "\"Davis, Jr., Sammy - Gonna Do Great Things: The Life of Sammy Davis, Jr.\",Gary Fishgall,4.95,0743227417,MRB,2005\r\n"
				+ "\"Davis, Miles - Miles And Me\",Quincy Troupe,4.95,0520216245,MRB,2005\r\n"
				+ "\"Davis, Miles - Miles Beyond: The Electric Explorations of Miles Davis, 1967-1991\",Paul Tingen,16.95,0823083462,MRB,2005\r\n"
				+ "Death Discs,Alan Clayson,6.95,1860741959,MRB,2005\r\n"
				+ "\"Diamond, Neil - His Life, His Music, His Passion\",Laura Jackson,19.95,1550227076,MRB,2005\r\n"
				+ "Doors - Light My Fire: My Life With The Doors,Ray Manzarek,4.95,0425170454,MRB,2005\r\n"
				+ "\"Dylan, Bob - In His Own Words\",Christian Williams,8.98,0711932190,MRB,2005\r\n"
				+ "\"Dylan, Bob - Performing Artist, Volume 1 - The Early Years: 1960-1973\",Paul Williams,12.95,1844490955,MRB,2005\r\n"
				+ "\"Dylan, Bob Anthology, Volume 2 - 20 Years of ISIS\",,16.95,1842403095,MRB,2005\r\n"
				+ "Eight Miles High - Folk-Rock's Flight From Haight-Asbury To Woodstock,Richie Unterberger,14.95,0879307439,MRB,2005\r\n"
				+ "\"Ellington, Duke\",Scott Yanow,12.95,1567998550,MRB,2005\r\n"
				+ "\"Ellington, Duke - The World of Duke Ellington\",Stanley Dance,4.95,0306810158,MRB,2005\r\n"
				+ "\"Elliot, Cass - Dream a Little Dream of Me: The Life of Cass Elliot\",Eddi Fiegel,19.95,1556525885,MRB,2005\r\n"
				+ "\"Evans, Bill - Everything Happens To Me: A Musical Biography\",Keith Shadwick,12.95,0879307080,MRB,2005\r\n"
				+ "\"Evans, Gil - Out of The Cool: His Life And Music\",Stephanie Stein Crease,18,1556524935,MRB,2005\r\n"
				+ "Fifty Greatest Jazz Piano Players of All Time,Gene Rizzo,16.95,0634074164,MRB,2005\r\n"
				+ "From the Velvets to the Voidoids,Clinton Heylin,12.95,,MRB,2005\r\n"
				+ "\"Gallagher, Marriott, Derringer & Trower: Their Lives And Music\",Dan Muise,12.95,0634029568,MRB,2005\r\n"
				+ "\"Gatton, Danny - Unfinished Business: The Life & Times of Danny Gatton\",Ralph Heibutzki,12.95,087930748X,MRB,2005\r\n"
				+ "\"Gaye, Marvin, My Brother\",Frankie Gaye & Fred E. Basten,14.95,,MRB,2005\r\n"
				+ "\"Genesis - Turn It On Again - Peter Gabriel, Phil Collins & Genesis\",Dave Thompson,12.95,0879308109,MRB,2005\r\n"
				+ "\"Getz, Stan - Nobody Else But Me\",Dave Gelly,15.95,0879307293,MRB,2005\r\n"
				+ "Goldmine Comedy Record Price Guide,Ronald L. Smith,2.95,0873414446,MRB,2005\r\n"
				+ "Goldmine Price Guide To 45 RPM Records (4th Edition),Tim Neely,16.95,0873496302,MRB,2005\r\n"
				+ "Goldmine Price Guide To Rock 'N' Roll Memorabilia,Mark Allen Baker,7.95,087341490X,MRB,2005\r\n"
				+ "Guitar - Guitar Chord & Scale Decks,,14.95,0825629721,MRB,2005\r\n"
				+ "\"Haley, Bill - Sound and Glory\",John W. Haley & John von Hoelle,5.95,1878970011,MRB,2005\r\n"
				+ "\"Heavy Metal - Collector's Guide to Heavy Metal, Volume 2: The Eighties\",Martin Popoff,24.95,0971457018,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - Bold As Love: The Jimi Hendrix Experience\",Frank Moriarty,11.95,1567993850,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - Complete Guide To His Music\",Peter Doggett,4.95,1844494241,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - Midnight Lighting: Jimi Hendrix And The Black Experience\",Greg Tate,13.5,1556524692,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - Musician\",Keith Shadwick,24.95,0879307641,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - Talking\",Tony Brown,8.95,1844490068,MRB,2005\r\n"
				+ "\"Hendrix, Jimi - The Making of Are You Experienced\",Sean Egan,10.95,1556524714,MRB,2005\r\n"
				+ "History of the Cavalcade of America,Martin Grams Jr.,19.95,0739201387,MRB,2005\r\n"
				+ "Hits Just Keep on Coming - History of Top 40 Radio,Ben Fong-Torres,12.95,0879306645,MRB,2005\r\n"
				+ "Hot Wacks Supplement 5,,12.95,0969808089,MRB,2005\r\n"
				+ "Hot Wacks Supplement 7,,12.95,,MRB,2005\r\n"
				+ "I Love a Mystery Companion,Martin Grams Jr.,19.95,0970331053,MRB,2005\r\n"
				+ "Iggy & The Stooges - Raw Power,Mick Rock,19.95,,MRB,2005\r\n"
				+ "Ill Tempered String Quartet - A Vademecum For The Amateur Musician,Lester Chafetz,30.95,9780786421510,MRB,2005\r\n"
				+ "\"Information, Please\",Martin Grams Jr.,19.95,0971457077,MRB,2005\r\n"
				+ "Inner Sanctum Mysteries - Behind The Creaking Door,Martin Grams Jr.,17.95,0970331037,MRB,2005\r\n"
				+ "It's Only Rock And Roll: Rock And Roll Currents In Contemporary Art,David S. Rubin,3.95,3791316273,RBM,2005\r\n"
				+ "\"Jackson, Hal - House That Jack Built\",Hal Jackson,6.95,0060198478,MRB,2005\r\n"
				+ "Jefferson Airplane - Got a Revolution!: The Turbulent Flight of Jefferson Airplane,Jeff Tamarkin,3.95,0634056786,MRB,2005\r\n"
				+ "Jews Who Rock,Guy Oseary,2.95,0312272677,MRB,2005\r\n"
				+ "\"Joel, Billy - The Life & Times of an Angry Young Man\",Hank Bordowitz,18.95,0823082504,MRB,2005\r\n"
				+ "\"Jones, Spike - Off the Record\",Jordan R. Young,-24.95,1593930127,MRB,2005\r\n"
				+ "\"King, B.B. - 'Blues Boy': The Life And Music of B.B. King\",Sebastian Danchin,4.95,0578060176,MRB,2005\r\n"
				+ "Kiss - Hotter Than Hell,Paul Elliott,5.95,1560254181,MRB,2005\r\n"
				+ "Kiss - Kiss and Sell: The Making of a Supergroup,C.K. Lendt,5.95,0823075516,MRB,2005\r\n"
				+ "Kiss - Warman's Kiss Collectibles Field Guide,Tom Shannon,9.95,,MRB,2005\r\n"
				+ "Klezmer! Jewish Music From Old World To Our World,Henry Sapoznik,14.95,0825671914,MRB,2005\r\n"
				+ "Land of A Thousand Dances - Chicano Rock 'N' Roll From Southern California,David Reyes & Tom Waldman,4.95,0826318835,MRB,2005\r\n"
				+ "Led Zeppelin - Press Reports...,,19.95,1894959175,MRB,2005\r\n"
				+ "\"Lee, Arthur\",Barney Hoskyns,4.95,1841950858,MRB,2005\r\n"
				+ "\"Lee, Peggy - Career Chronicle\",Robert Strom,49.5,9780786419364,MRB,2005\r\n"
				+ "\"Lennon, John - All We Are Saying: The Last Major Interview With John Lennon And Yoko Ono\",David Sheff,4.95,0312254644,MRB,2005\r\n"
				+ "\"Lennon, John - Come Together: John Lennon in His Time\",Jon Wiener,4.95,0571135765,MRB,2005\r\n"
				+ "\"Lennon, John - Lives of John Lennon\",Albert Goldman,12.95,1556523998,MRB,2005\r\n"
				+ "\"Lennon, John - New York Years\",Bob Gruen,19.95,,MRB,2005\r\n"
				+ "Little Richard - Life And Times of Little Richard,Charles White,4.95,0711997616,MRB,2005\r\n"
				+ "\"McGraw, Tim And The Dancehall Doctors\",Tim McGraw & Martin Huxley,1.99,074346706X,MRB,2005\r\n"
				+ "\"Mendelssohn, John - I, Caramba\",John Mendelssohn,2.95,1568265530,MRB,2005\r\n"
				+ "Metallica - Making of Metallica's Metallica,Mick Wall & Malcolm Dome,4.95,1896522343,MRB,25\r\n"
				+ "\"Monkees - Hey, Hey, We're The Monkees\",,5.95,1575440121,MRB,2005\r\n"
				+ "\"Montgomery, Wes\",Adrian Ingram,29.99,0950622494,MRB,2005\r\n"
				+ "\"Music, Money, And Success - The Insider's Guide To Making Money In The Music Industry (4th Ed.)\",Jeffrey Brabec & Todd Brabec,14.98,0825673062,MRB,2005\r\n"
				+ "\"Newman's, Randy American Dreams\",Kevin Courrier,15.95,1550226908,MRB,2005\r\n"
				+ "\"Ono, Yoko - Yes\",Alexandra Munroe & Jon Hendricks,29.95,0810945878,MRB,2005\r\n"
				+ "\"Osbourne, Ozzy - Talking\",Harry Shaw,1.99,0711992908,MRB,2005\r\n"
				+ "\"Pastorius, Jaco - Jaco: The Extraordinary and Tragic Life of Jaco Pastorius (Anniversary Edition)\",Bill Milkowski,12.95,0879308591,MRB,2005\r\n"
				+ "\"Petty, Tom - Conversations with Tom Petty\",Paul Zollo,5.95,1844498158,MRB,2005\r\n"
				+ "Pink Floyd Encyclopedia,Vernon Fitch,22.95,1894959248,MRB,2005\r\n"
				+ "\"Presley, Elvis - Careless Love: The Unmaking of Elvis Presley\",Peter Guralnick,5.95,0316332224,MRB,2005\r\n"
				+ "\"Presley, Elvis - Elvis + Marilyn: 2X Immortal\",,3.95,0847818829,MRB,2005\r\n"
				+ "\"Presley, Elvis - Untold Gold: The Stories Behind Elvis's #1 Hits\",Ace Collins,11.95,1556525656,MRB,2005\r\n"
				+ "Prisonaires: Just Walkin' In The Rain,Jay Warner,4.95,1580631401,MRB,2005\r\n"
				+ "Private Eyelashes - Radio's Lady Detectives,Jack French,14.95,0971457085,MRB,2005\r\n"
				+ "Psychedelic Rock Files,Jerry Lucky,14.95,1896522971,MRB,2005\r\n"
				+ "\"Radio Sound Effects - Who Did It, And How, In The Era of Live Broadcasting\",Robert L. Mott,30.95,9780786422661,MRB,2005\r\n"
				+ "Radiohead - CD-Sized Book,Mick St Michael,1,,MRB,2005\r\n"
				+ "Real Deal - How To Get Signed To A Record Label,Daylle Deanna Schwartz,13.95,0823084051,MRB,2005\r\n"
				+ "\"Red, White, and the Blues Harmonica\",David Harp,11.95,0825634113,MRB,2005\r\n"
				+ "\"Reed, Lou - Walk on the Wild Side\",Chris Roberts,11.95,0634080326,MRB,2005\r\n"
				+ "\"Reinhardt, Django\",Charles Delaunay,24.95,095062246X,MRB,2005\r\n"
				+ "Rock Around the Clock - The Record that Started the Rock Revolution,Jim Dawson,12.95,087930829X,MRB,2005\r\n"
				+ "Rock Photography - Showtime,Steve Gullick,3.95,,MRB,2005\r\n"
				+ "Rock Posters - Swag 2: Rock Posters of the '90s,Spencer Drate & Judith Salavetz,19.95,,MRB,2005\r\n"
				+ "Rock Posters - Swag: Rock Posters of the '90s,Spencer Drate,22.95,0810991152,MRB,2005\r\n"
				+ "Rockabilly - A Forty-Year Journey,Billy Poore,13.95,,MRB,2005\r\n"
				+ "Rolling Stones - A Life On The Road,Jools Holland & Dora Loewenstein,14.95,,MRB,2005\r\n"
				+ "Rough Mix,Jimmy Bowen & Jim Jerome,4.95,0684807645,MRB,2005\r\n"
				+ "Sex Pistols - The Filth And The Fury,Nat Jackley,4.95,0312264941,MRB,2005\r\n"
				+ "Shadow - Walter G. Gibson & The Shadow,Thomas J. Shimeld,30.95,9780786423613,MRB,2005\r\n"
				+ "\"Shepherd, Jean - Excelsior, You Fathead! Art & Enigma of Jean Shepherd\",Eugene B. Bergmann,19.95,1557836000,MRB,2005\r\n"
				+ "\"Sinatra, Frank\",Jessica Hodge,3.95,0785809945,MRB,2005\r\n"
				+ "Songs In The Key of Z - The Curious Universe of Outsider Music,Irwin Chusid,16.95,1556523726,MRB,2005\r\n"
				+ "Soulsville U.S.A. - The Story of Stax Records,Rob Bowman,12.95,0825672848,MRB,2005\r\n"
				+ "Sound of Detection - Ellery Queen's Adventures in Radio,Francis M. Nevins & Martin Grams Jr.,19.95,0970331029,MRB,2005\r\n"
				+ "\"Spaniels - Goodnight Sweetheart, Goodnight: Story of The Spaniels\",Richard G. Carter,5.95,0963572024,MRB,2005\r\n"
				+ "\"Springsteen, Bruce - It Ain't No Sin To Be Glad You're Alive: The Promise of Bruce Springsteen\",Eric Alterman,1.99,0316039179,MRB,2005\r\n"
				+ "\"Springsteen, Bruce - Springsteen\",Frank Moriarty,14.5,1567996523,MRB,2005\r\n"
				+ "Steely Dan - Complete Guide to Their Music,Brian Sweet,4.95,184449425X,MRB,2005\r\n"
				+ "Suspense - Twenty Years of Thrills and Chills,Martin Grams Jr.,24.95,1575026759,MRB,2005\r\n"
				+ "Swing - 1500 Recordings Reviewed & Rated,Scott Yanow,4.95,0879306009,MRB,2005\r\n"
				+ "Swinging Big Bands...Into The Millennium,Al Raymond,12.95,,MRB,2005\r\n"
				+ "Taboo Tunes - A History of Banned Bands & Censored Songs,Peter Blecha,11.95,0879307927,MRB,2005\r\n"
				+ "\"Tetley, Walter - For Corn's Sake\",Ben Ohmart & Charles Stumpf,19.95,1593930003,MRB,2005\r\n"
				+ "They Fought The Law - Rock Music Goes To Court,Stan Soocher,4.95,0128647319,MRB,2005\r\n"
				+ "Tombstone Blues - The Encyclopedia of Rock Obituaries,Nick Talevski,4.95,0711983097,MRB,2005\r\n"
				+ "Tombstone Tourist - Musicians,Scott Stanton,5.95,0743463307,MRB,2005\r\n"
				+ "Top 500 Metal Songs of All Time,Martin Popoff,14.5,1550225308,MRB,2005\r\n"
				+ "Turn! Turn! Turn! - The '60S Folk-Rock Revolution,Richie Unterberger,12.95,087930703X,MRB,2005\r\n"
				+ "\"Turner, Ike - King of Rhythm\",John Collis,-4.95,1904316247,MRB,2005\r\n"
				+ "Urban Legends of Rock & Roll,Dale Sherman,11.95,1896522785,MRB,2005\r\n"
				+ "Urban Spacemen And Wayfaring Strangers - Overlooked Innovators And Eccentric Visionaries of '60S Rock,Richie Unterberger,14.95,0879306165,MRB,2005\r\n"
				+ "VH1 - 100 Greatest Albums,,3.95,0743448766,MRB,2005\r\n"
				+ "VH1 - Behind the Music: The Day the Music Died,Martin Huxley & Quinton Skinner,4.95,0671039628,MRB,2005\r\n"
				+ "Way To Tin Pan Alley,Nicholas E. Tawa,4.95,0028725417,MRB,2005\r\n"
				+ "Who - Concert File,Joe McMichael & 'Irish' Jack Lyons,8.95,1844490092,MRB,2005\r\n"
				+ "Who Sang Our Songs? The Official Rhythm & Blues And Doo-Wop Songography,Douglas E. Friedman & Anthony J. Gribin,20,0971397902,MRB,2005\r\n"
				+ "\"Wills, Bob - The King of Western Swing: Bob Wills Remembered\",Rosetta Wills,5.95,0823077446,MRB,2005\r\n"
				+ "Yardbirds,Alan Clayson,16.95,,MRB,2005\r\n"
				+ "Yardbirds - Ultimate Rave-Up,Greg Russo,16.95,0964815788,MRB,2005\r\n"
				+ "\"Zappa, Frank - Cosmik Debris: The Collected History and Improvisations of Frank Zappa (Revised)\",Greg Russo,16.95,0964815702,MRB,2005\r\n"
				+ "\"Zappa, Frank - The Complete Guide to his Music\",Ben Watson,4.95,1844498654,MRB,2005\r\n"
				+ "Actors Studio - A History,Shelly Frome,35.95,9.78179E+12,MTV,2005\r\n"
				+ "\"African American Films Through 1959 - A Comprehensive, Illustrated Filmography\",Larry Richards,35.95,9780786422746,MTV,2005\r\n"
				+ "Alias Smith and Jones - The Story of Two Pretty Good Bad Men,Sandra K. Sagala & JoAnne M. Bagwell,16.95,1593930313,MTV,2005\r\n"
				+ "\"Allen, Woody - The Unruly Life of Woody Allen\",Marion Meade,4.95,0753811170,MTV,2005\r\n"
				+ "\"American Family On Television - A Chronology of 121 Shows, 1948 - 2004\",Marla Brooks,30.95,9780786420742,MTV,2005\r\n"
				+ "Analytical Guide To Television's Battlestar Galactica,John Kenneth Muir,26.96,9780786424559,MTV,2005\r\n"
				+ "\"Arbuckle, Roscoe Fatty - A Biography of The Silent Film Comedian, 1887 - 1933\",Stuart Oderman,30.95,9780786422777,MTV,2005\r\n"
				+ "Attack of The B Queens,,19.95,1887664084,MTV,2005\r\n"
				+ "\"Belzer, Richard - How To Be A Stand-Up Comic\",Richard Belzer & Larry Charles & Rick Newman,3.95,0806513195,MTV,2005\r\n"
				+ "\"Berle, Milton - An Autobiography\",Milton Berle & Haskel Frankel,13.5,1557835853,MTV,2005\r\n"
				+ "Billboard's Video Yearbook: 1993,Joel Whitburn,24,0898201039,MTV,2005\r\n"
				+ "Billboard's Video Yearbook: 1994,Joel Whitburn,10,089820111X,MTV,2005\r\n"
				+ "Biology of Science Fiction Cinema,Mark C. Glassy,35.95,9780786426041,MTV,2005\r\n"
				+ "Blood In The Moonlight - Michael Mann And Information Age Cinema,Mark E. Wildermuth,30.95,9780786420599,MTV,2005\r\n"
				+ "\"Bunuel, Luis - Bunuel\",John Baxter,4.95,078670506X,MTV,2005\r\n"
				+ "\"Butler, Daws - Characters Actor\",Ben Ohmart & Joe Bevilacqua,19.95,1593930151,MTV,2005\r\n"
				+ "\"Carpenter, John - Films of John Carpenter\",John Kenneth Muir,30.95,9780786422692,MTV,2005\r\n"
				+ "\"Carson, Johnny - Here's Johnny!\",Ed McMahon,4.95,,MTV,2005\r\n"
				+ "\"Carson, Johnny - Here's Johnny!: Thirty Years of America's Favorite Late-Night Entertainer (Revised Edition)\",Stephen Cox,5.95,1581822650,MTV,2005\r\n"
				+ "Casting Might - Have - Beens - A Film By Film Directory of Actors Considered For Roles Given To Others,Eila Mell,39.95,9780786420179,MTV,2005\r\n"
				+ "\"Chan, Charlie - Film Encyclopedia\",Howard M. Berlin,35.95,9780786424528,MTV,2005\r\n"
				+ "\"Chandler, Jeff - Film, Record, Radio, Television And Theater Performances\",Jeff Wells,39.95,9780786420018,MTV,2005\r\n"
				+ "\"Chaney, Jr., Lon - Midnight Marquee Actors Series: Lon Chaney, Jr.\",,19.95,1887664157,MTV,2005\r\n"
				+ "Children of Hollywood: Accounts of Growing Up as the Sons and Daughters of Stars,Michelle Vogel,30.95,9780786420469,MTV,2005\r\n"
				+ "Chronicles of Terror: Silent Screams,Steve Haberman,24.95,1887664394,MTV,2005\r\n"
				+ "Classic Scripts - Filet of Sohl: The Classic Scripts and Stories of Jerry Sohl,Jerry Sohl,12.95,0971457034,MTV,2005\r\n"
				+ "\"Corman, Roger: An Unauthorized Biography of The Godfather of Indie Filmmaking\",Beverly Gray,4.95,1580631460,MTV,2005\r\n"
				+ "\"Cushing, Peter - Autobiography and Past Forgetting\",Peter Cushing,16.95,1187664262,MTV,2005\r\n"
				+ "\"Day, Doris - Sentimental Journey\",Garry McGee,49.5,9780786419814,MTV,2005\r\n"
				+ "Dinosaur Filmography,Mark F. Berry,35.95,9780786424535,MTV,2005\r\n"
				+ "\"Disney, Walt - Remembering Walt: Favorite Memories of Walt Disney\",Amy Boothe Green & Howard E. Green,5.95,0786853794,MTV,2005\r\n"
				+ "\"Disney, Walt - Walt In Wonderland: Silent Films of Walt Disney\",,4.95,0801864291,MTV,2005\r\n"
				+ "\"Doctor Who Error Finder - Plot, Continuity And Production Mistakes In The Television Series And Films\",R.H. Langley,44.95,9780786419906,MTV,205\r\n"
				+ "Drums O' Terror: Voodoo In The Cinema,Bryan Senn,19.95,1841950931,MTV,2005\r\n"
				+ "Earth vs. the Sci-Fi Filmmakers - Twenty Interviews,Tom Weaver,39.95,9780786422104,MTV,2005\r\n"
				+ "\"Ellen, Vera: The Magic And The Mystery\",David Soren & Meredith Banasiak & Bob Johnston,24.95,1887664483,MTV,2005\r\n"
				+ "Encyclopedia of Ethnic Groups In Hollywood,James Robert Parish,19.95,0816046042,MTV,2005\r\n"
				+ "Encyclopedia of Fantastic Film,R.G. Young,11.95,1557832692,MTV,2005\r\n"
				+ "Eurospy Guide,Matt Blake & David Deal,19.95,1887664521,MTV,2005\r\n"
				+ "Exploring Space: 1999 - An Episode Guide and Complete History of the Mid-1970s Science Fiction Television Series,John Kenneth Muir,26.96,9780786422760,MTV,2005\r\n"
				+ "\"Fairbanks, Douglas - Making of A Screen Character (MOMA Art Film Library Series 2)\",Alistair Cooke,3.95,0870706845,MTV,2005\r\n"
				+ "Fantastic Journeys: Sci-Fi Memories,,19.95,1887664408,MTV,2005\r\n"
				+ "Fantasy of The 20th Century - An Illustrated History,Randy Broecker,9.95,1888054522,MTV,2005\r\n"
				+ "\"Fields, W.C.\",James Curtis,14.95,0823084426,MTV,2005\r\n"
				+ "\"First Hollywood Sound Shorts 1926-1931, The\",,,44.95,9780786443192,MTV,2005\r\n"
				+ "First World War and Popular Cinema,,5.95,,MTV,2005\r\n"
				+ "Forbidden Fruit: The Golden Age of The Exploitation Film,Felicia Feaster & Bret Wood,19.95,1887664246,MTV,2005\r\n"
				+ "\"Ford, John and the American West\",Peter Cowie,19.95,0810949768,MTV,2005\r\n"
				+ "Forgotten Horrors 1: Definitive Edition,George E. Turner & Michael H. Price,19.95,1887664203,MTV,2005\r\n"
				+ "Forgotten Horrors 2: Beyond The Horror Ban,Michael H. Price & George E. Turner,19.95,,MTV,2005\r\n"
				+ "Forgotten Horrors 3: Dr. Turner's House of Horrors,Michael H. Price & John Wooley & George E. Turner,14.95,1887664378,MTV,2005\r\n"
				+ "\"Fountainheads - Wright, Rand, The FBI And Hollywood\",Donald Leslie Johnson,66.95,9780786419586,MTV,2005\r\n"
				+ "Frankenstein - We Belong Dead: Frankenstein On Film,,19.95,1887664092,MTV,2005\r\n"
				+ "\"Frees, Paul - Welcome, Foolish Mortals...The Life And Voices of Paul Frees\",Ben Ohmart,19.95,1593930046,MTV,2005\r\n"
				+ "\"Frye, Dwight - Dwight Frye's Last Laugh\",Gregory William Mank & James T. Coughlin & Dwight D. Frye,19.95,1887664114,MTV,2005\r\n"
				+ "Gang Busters - The Crime Fighters of American Broadcasting,Martin Grams Jr.,19.95,0970331061,MTV,2005\r\n"
				+ "\"Garland, Judy - Beyond the Rainbow\",Sheridan Morley & Ruth Leon,5.95,1559705256,MTV,2005\r\n"
				+ "\"Garland, Judy - Portrait In Art & Anecdote\",John Fricke,11.95,0821228366,MTV,2005\r\n"
				+ "\"Girls - Greta Garbo, Marlene Dietrich and Tallulah Bankhead\",Diana McLellan,4.95,0312283202,MTV,2005\r\n"
				+ "\"Gish, Lillian - Her Legend, Her Life\",Charles Affron,4.95,0520234340,MTV,2005\r\n"
				+ "Gone with the Wind,Herb Bridges,5.95,067168387X,MTV,2005\r\n"
				+ "\"Greek Filmography, 1914 Through 1996\",Dimitris Koliodimos,85.5,9780786424214,MTV,2005\r\n"
				+ "\"Griffith, D.W. - American Film Master (MOMA Art Film Library Series 1)\",Iris Barry,3.95,0870706837,MTV,2005\r\n"
				+ "\"Hassall, Imogen - Tuesday's Child - The Life And Death of Imogen Hassall\",Dan Leissner,19.95,1887664475,MTV,2005\r\n"
				+ "Haunted Screen - Ghosts In Literature And Film,Lee Kovacs,30.95,9780786426058,MTV,2005\r\n"
				+ "Have Gun - Will Travel Companion,Martin Grams Jr. & Les Rayburn,24.95,0970331002,MTV,2005\r\n"
				+ "\"Hepburn, Katharine\",Sheridan Morley,4.95,1557833400,MTV,2005\r\n"
				+ "\"Hepburn, Katharine - The Private World of Katharine Hepburn\",John Bryson,4.95,0316113336,MTV,2005\r\n"
				+ "\"Hitchcock, Alfred - Hitchcock Becomes Hitchcock: The British Years\",Paul M. Jensen,14.95,1887664351,MTV,2005\r\n"
				+ "\"Hitchcock, Alfred Presents Companion\",Martin Grams Jr. & Patrik Wikstrom,19.95,0970331010,MTV,2005\r\n"
				+ "\"Hollywood Surf And Beach Movies - The First Wave, 1959 - 1969\",Thomas Lisanti,39.95,9780786421046,MTV,2005\r\n"
				+ "Hollywood's Golden Age,Edward Dmytryk,14.95,0971457042,MTV,2005\r\n"
				+ "\"Hollywood's Latin Lovers - Latino, Italian and French Men who Make the Screen Smolder\",Victoria Thomas,7.95,1883318416,MTV,2005\r\n"
				+ "\"Hollywood's Maddest Doctors: A Biography of Lionel Atwill, Colin Clive And George Zucco\",Gregory William Mank,19.95,,MTV,2005\r\n"
				+ "Human Monsters: The Definitive Edition,Michael H. Price & George E. Turner,19.95,1887664505,MTV,2005\r\n"
				+ "\"Image of Librarians In Cinema, 1917 - 1999\",Ray Tevis & Brenda Tevis,44.95,9780786421503,MTV,2005\r\n"
				+ "\"In A Door, Into A Fight, Out A Door, Into A Chase - Moviemaking Remembered By The Guy At The Door\",William Witney,30.95,9780786422586,MTV,2005\r\n"
				+ "Indian Cinema - The Bollywood Saga,Dinesh Raheja & Jitendra Kothari,24.95,8174362851,MTV,2005\r\n"
				+ "It Came From Bob's Basement: Exploring The Science Fiction And Monster Movie Archive of Bob Burns,Bob Burns,5.95,0811825728,MTV,2005\r\n"
				+ "Italian Horror,Jim Harper,19.95,1887664556,MTV,2005\r\n"
				+ "It's A Wonderful Life,Jimmy Hawkins,3.95,0740738410,MTV,2005\r\n"
				+ "It's Christmas Time At The Movies: An A-Z Guide To Our Favorite Holiday Films,,19.95,188766419X,MTV,2005\r\n"
				+ "Japanese Movie Posters,,4.95,0972312455,MTV,2005\r\n"
				+ "\"Karloff, Boris - A Gentleman's Life\",Scott Allen Nollen,19.95,1887664238,MTV,2005\r\n"
				+ "\"Kean, Jane - Funny Thing Happened on the Way to the Honeymooners...I Had a Life\",Jane Kean,12.95,0971457093,MTV,2005\r\n"
				+ "King Kong - History of a Movie Icon: From Fay Wray to Peter Jackson,Ray Morton,16.95,,MTV,2005\r\n"
				+ "King Kong - Spawn of Skull Island: The Making of King Kong,George E. Turner & Orville Goldner,29.95,1887664459,MTV,2005\r\n"
				+ "\"Kubrick, Stanley - A Life in Pictures\",Christiane Kubrick,12.95,0821228153,MTV,2005\r\n"
				+ "\"Kubrick, Stanley - Seven Films Analyzed\",Randy Rasmussen,-35.95,9780786421527,MTV,2005\r\n"
				+ "\"Lancaster, Burt - A Filmography And Biography\",Ed Andreychuk,35.95,9780786423392,MTV,2005\r\n"
				+ "\"Last of The Cowboy Heroes - The Westerns of Randolph Scott, Joel Mccrea, And Audie Murphy\",Robert Nott,35.95,9780786422616,MTV,2005\r\n"
				+ "\"Latin American Cinema - Essays On Modernity, Gender And National Identity\",,35.95,9780786420049,MTV,2005\r\n"
				+ "\"Latin American Films, 1932 - 1994 - A Critical Filmography\",Ronald Schwartz,44.95,9780786422265,MTV,2005\r\n"
				+ "\"Lloyd, Harold - Master Comedian\",Jeffrey Vance & Suzanne Lloyd,24.95,0810916746,MTV,2005\r\n"
				+ "Lone Ranger - I Was That Masked Man: The Lone Ranger Unmasked,Clayton Moore & Frank Thompson,14.95,0878332162,MTV,2005\r\n"
				+ "\"Lorre, Peter - Midnight Marquee Actors Series: Peter Lorre\",,19.95,1887664300,MTV,2005\r\n"
				+ "\"Lovecraft, H.P. in Popular Culture\",Don G. Smith,30.95,9780786420919,MTV,2005\r\n"
				+ "Mad About Movies (#3),,7.95,,MTV,2005\r\n"
				+ "Mad About Movies (#4),,7.95,,MTV,2005\r\n"
				+ "\"Marx, Harpo - Harpo Speaks!\",Harpo Marx & Rowland Barber,14.95,0879100362,MTV,2005\r\n"
				+ "\"McCambridge, Mercedes - A Biography And Career Record\",Ron Lackmann,39.95,9780786419791,MTV,2005\r\n"
				+ "\"McGowan, J.P. - Biography of A Hollywood Pioneer\",John J. McGowan,35.95,9780786419944,MTV,2005\r\n"
				+ "\"Melchior, Ib - Man of Imagination\",Robert Skotak,14.95,1887664416,MTV,2005\r\n"
				+ "Memories of Hammer,,24.95,1884664181,MTV,2005\r\n"
				+ "Midnight Marquee (Issue #71 / 72),,7.95,,MTV,2005\r\n"
				+ "Midnight Marquee (Issue #73 / 74),,7.95,,MTV,2005\r\n"
				+ "\"Milligan, Andy - The Ghastly One: The Sex-Gore Netherworld of Filmmaker Andy Milligan\",Jimmy McDonough,3.95,1556524951,MTV,2005\r\n"
				+ "Minds of Fear - A Dialogue with 30 Modern Masters of Horror,Calum Waddell,19.95,1887664572,MTV,2005\r\n"
				+ "Movie Mystique (#1),,4.95,,MTV,2005\r\n"
				+ "Movie Mystique (#2),,4.95,,MTV,2005\r\n"
				+ "Movies That Changed Us,Nick Clooney,3.95,0743410440,MTV,2005\r\n"
				+ "\"Naschy, Paul - Memoirs of A Wolfman\",,19.95,1887664386,MTV,2005\r\n"
				+ "\"Nolan, William F. - Have You Seen the Wind?\",William F. Nolan,12.95,0971457050,MTV,2005\r\n"
				+ "\"Of Gods And Monsters: A Critical Guide To Universal Studios' Science Fiction, Horror And Mystery Films, 1929-1939\",John T. Soister,44.95,9780786421534,MTV,2005\r\n"
				+ "One Hundred Violent Films that Changed Cinema,Neil Fulwood,11.95,0713488190,MTV,2005\r\n"
				+ "\"Performers' Television Credits, 1948-2000, Volume 2: G-M\",David M. Inman,109.95,,MTV,2005\r\n"
				+ "\"Performers' Television Credits, 1948-2000, Volumes 1-3\",David M. Inman,264.95,,MTV,2005\r\n"
				+ "\"Pitt, Ingrid - Darkness Before Dawn\",Ingrid Pitt,19.95,1887664548,MTV,2005\r\n"
				+ "\"Poverty Row Studios, 1929 - 1940 - An Illustrated History of 55 Independent Film Companies, With A Filmography For Each\",Michael R. Pitts,39.95,9780786423194,MTV,2005\r\n"
				+ "\"Price, Vincent - Midnight Marquee Actors Series: Vincent Price\",,19.95,1887664211,MTV,2005\r\n"
				+ "\"Reeves, George - Superman on Television: A Comprehensive Viewer's Guide to the Daring Exploits of Superman As Presented in the Original 1950s TV Series [Author-Signed Edition]\",Michael Bifulco,14.95,0961959630,MTV,2005\r\n"
				+ "\"Reference Guide To Television's Bonanza - Episodes, Personnel And Broadcast History\",Bruce R. Leiby & Linda F. Leiby,30.95,9780786422685,MTV,2005\r\n"
				+ "Retro Stud - Muscle Movie Posters From Around The World,David Chapman,12.95,1888054697,MTV,2005\r\n"
				+ "Rise And Fall of The Horror Film,Dr. David Soren,19.95,1887664173,MTV,2005\r\n"
				+ "\"RKO Features - A Complete Filmography of the Feature Films Released RKO Radio Pictures, 1929 - 1960\",James L. Neibaur,35.95,9780786421664,MTV,2005\r\n"
				+ "\"Rock, Chris - Rock This!\",Chris Rock,3.95,0786885602,MTV,2005\r\n"
				+ "\"Roeper, Richard - 10 Sure Signs A Movie Character Is Doomed\",Richard Roeper,3.95,078688830X,MTV,2005\r\n"
				+ "\"Rogers, Roy - The Ultimate Roy Rogers Collection Identification And Price Guide\",Ron Lenius,5.95,0873492269,MTV,2005\r\n"
				+ "\"Salmi, Albert - Spotlights & Shadows: The Albert Salmi Story\",Sandra Grabman,14.95,,MTV,2005\r\n"
				+ "Scenes for Actors and Voices,Daws Butler,12.95,0971457069,MTV,2005\r\n"
				+ "Science Fiction of The 20th Century - An Illustrated History,Frank M. Robinson,16.95,1888054298,MTV,2005\r\n"
				+ "\"Serial Film Stars - A Biographical Dictionary, 1912-1956\",Buck Rainey,85.5,9780786420100,MTV,2005\r\n"
				+ "Shooting Scripts - From Pulp Western To Film,Bob Herzberg,30.95,9780786421732,MTV,2005\r\n"
				+ "\"Silent Films, 1877 - 1996 - A Critical Guide To 646 Movies\",Robert K. Klepper,44.95,9780786421640,MTV,2005\r\n"
				+ "\"Sinatra, Frank - The Cinema of Sinatra: The Actor, On Screen And In Song\",Scott Allen Nollen,19.95,1887664513,MTV,2005\r\n"
				+ "Sinema - Erotic Adventures in Film,Douglas Brode,4.95,0806523131,MTV,2005\r\n"
				+ "\"Sinister Serials of Boris Karloff, Bela Lugosi And Lon Chaney, Jr.\",Leonard J. Kohl,17.95,1887664319,MTV,2005\r\n"
				+ "Sleazoid Express,,4.95,0743215834,MTV,2005\r\n"
				+ "Sopranos,Allen Rucker,8.95,0451202465,MTV,2005\r\n"
				+ "\"Sorkin, Aaron - Essays On The Politics, Poetics And Sleight of Hand In The Films And Television Series\",,30.95,9780786421206,MTV,1005\r\n"
				+ "\"Spaghetti Westerns - The Good, The Bad And The Violent - A Comprehensive, Illustrated Filmography of 558 Eurowesterns And Their Personnel, 1961 - 1977\",Thomas Weisser,35.95,9780786424429,MTV,2005\r\n"
				+ "Stay Tuned - Television's Unforgettable Moments,Joe Garner,5.95,0740726935,MTV,2005\r\n"
				+ "Strange Case of Dr. Mabuse,David Kalat,30.95,9780786423378,MTV,2005\r\n"
				+ "\"Strickfaden, Kenneth - Dr. Frankenstein's Electrician\",Harry Goldman,30.95,9780786420643,MTV,2005\r\n"
				+ "\"Three Stooges - Pop, You're Poifect!\",Patrick Regan,2.95,0740726889,MTV,2005\r\n"
				+ "\"Three Stooges - Why, I Oughta... Wish You a Happy Birthday\",Patrick Regan,2.95,0740726870,MTV,2005\r\n"
				+ "\"Tierney, Gene - A Biography\",Michelle Vogel,49.5,9780786420353,MTV,2005\r\n"
				+ "\"Titanic In Print And On Screen - An Annotated Guide To Books, Films, Television Shows And Other Media\",D. Brian Anderson,35.95,9780786417865,MTV,2005\r\n"
				+ "Trash - The Graphic Genius of Xploitation Movie Posters,Jacques Boyreau,14.95,,MTV,2005\r\n"
				+ "Truth And Lives On Film - The Legal Problems of Depicting Real Persons And Events In A Fictional Medium,John T. Aquino,35.95,9780786420445,MTV,2005\r\n"
				+ "TV A-Go-Go - Rock on TV from American Bandstand to American Idol,,14.95,0811834174,MTV,2005\r\n"
				+ "Ultraviolent Movies,Laurent Bouzereau,5.95,0806520450,MTV,2005\r\n"
				+ "Valentino Mystique - Death And Afterlife of The Silent Film Idol,Allan R. Ellenberger,35.95,9780786419500,MTV,2005\r\n"
				+ "\"Van Cleef, Lee - A Biographical, Film And Television Reference\",Mike Malloy,30.95,9780786422722,MTV,2005\r\n"
				+ "Videoscope - The Phantom of The Movies,Joe Kane,5.95,0812931491,MTV,2005\r\n"
				+ "\"Vixens, Floozies And Molls - 28 Actresses of Late 1920S And 1930S Hollywood\",Hans J. Wollstein,35.95,9780786422609,MTV,2005\r\n"
				+ "\"Wilder, Billy - On Sunset Boulevard: Life & Times of Billy Wilder\",Ed Sikov,5.95,0786885033,MTV,2005\r\n"
				+ "\"Williams, Guy - The Man Behind the Mask\",Antoinette Girgenti Lane,19.95,,MTV,2005\r\n"
				+ "Women Scientists In 50s Science Fiction Films,Bonnie Noonan,35.95,9780786421305,MTV,2005\r\n"
				+ "Zorro Television Companion,Gerry Dooley,39.95,9780786420582,MTV,2005\r\n"
				+ "America's Sideshows - Seeing is Believing,A.W. Stencell,19.95,1550225294,NEB,2005\r\n"
				+ "Beer - Beer Lover's Rating Guide,Bob Klein,3.95,0761113118,NEB,2005\r\n"
				+ "Beer - Man Show on Tap: A Guide to All Things Beer,Ray James,4.95,0689873719,NEB,2005\r\n"
				+ "\"Brooklyn - People and Places, Past and Present\",Grace Glueck & Paul Gardner,5.95,0810991780,NEB,2005\r\n"
				+ "\"But, Wait! There's More! - The Irresistible Appeal and Spiel of Ronco and Popeil\",Timothy Samuelson,5.95,0847824314,NEB,2005\r\n"
				+ "\"Celebrity Skin - Tattoos, Brands, And Body Adornments of The Stars\",Jim Gerard,6.95,1160253231,NEB,2005\r\n"
				+ "Cigar Bizarre: An Unusual History,Philip Collins,4.95,1575440679,NEB,2005\r\n"
				+ "Coney Island - Lost and Found,Charles Denson,22.95,1580084559,NEB,2005\r\n"
				+ "Encyclopedia of Kidnappings,Michael Newton,7.95,0816044872,NEB,2005\r\n"
				+ "\"From Other Worlds - Aliens, Abductions, And Ufos\",Hilary Evans,6.95,0762101083,NEB,2005\r\n"
				+ "\"Hollywood Divas: The Good, the Bad, and the Fabulous\",James Robert Parish,3.95,0071408193,NEB,2005\r\n"
				+ "MADvertising,David Shayne,16.95,0823030814,NEB,2005\r\n"
				+ "Magazine Covers,David Crowley,5.95,1840006986,NEB,2005\r\n"
				+ "Museum of Hoaxes,Alex Boese,3.95,0452284651,NEB,2005\r\n"
				+ "Pageant - Beauty Contest,Keith Lovegrove,5.95,3823855697,NEB,2005\r\n"
				+ "\"Perverse, Adverse & Rottenverse\",June Foray,9.95,1593930208,NEB,2005\r\n"
				+ "Retro Beach Party - A Sun Lover's Guide To Food And Fun,Linda Everett,12.95,1888054751,NEB,2005\r\n"
				+ "\"Shelf Space - Modern Package Design, 1945 To 1965\",Jerry Jankowski,4.95,0811817849,NEB,2005\r\n"
				+ "Slinky - It's Slinky: The Fun And Wonderful Toy,Lou Harry,7.95,0762403993,NEB,2005\r\n"
				+ "Swingin' Chicks of the '60s,Chris Strodder,7.95,0768322324,NEB,2005\r\n"
				+ "\"Warhol, Andy - Greetings from Andy (Warhol): Christmas at Tiffany's\",John Loring,7.95,,NEB,2005\r\n"
				+ "\"Warhol, Andy - Year in the Life of Andy Warhol\",David Dalton,9.95,0714843229,NEB,2005\r\n"
				+ "What's Your Poison? Addictive Advertising of the '40s-'60s,Kirven Blount,7.95,1933112026,NEB,2005\r\n"
				+ "Zippo - An American Legend,Avi R. Baer & Alexander Neumark,12.95,076240700X,NEB,2005\r\n"
				+ "Baseball - 100 Greatest Baseball Games of The 20th Century Ranked,Joseph J. Dittmar,26.95,0786409150,SSM,2005\r\n"
				+ "Baseball - 100 Greatest Baseball Players of The 20th Century Ranked,Mark McGuire & Michael Sean Gormley,31.95,0786409142,SSM,2005\r\n"
				+ "\"Baseball - 1903 World Series: The Boston Americans, the Pittsburg Pirates, and the First Championship of the United States\",Andy Dabilis & Nick Tsiotos,24.95,0786418400,SSM,2005\r\n"
				+ "Baseball - 1940 Cincinnati Reds: A World Championship and Baseball's Only In-Season Suicide,Brian Mulligan,24.95,0786420901,SSM,2005\r\n"
				+ "Baseball - 1964 Phillies: The Story of Baseball's Most Memorable Collapse,,26.96,9780786421176,SSM,2005\r\n"
				+ "Baseball - 25 Greatest Baseball Teams of The 20th Century Ranked,Chris Holaday & Marshall Adesman,29.95,0786409258,SSM,2005\r\n"
				+ "Baseball - A Picture Postcard History of Baseball,Ron Menchine,4.95,0930256212,SSM,2005\r\n"
				+ "\"Baseball - All-American Girls Professional Baseball League Record Book: Comprehensive Hitting, Fielding and Pitching Statistics\",W.C. Madden,39.95,078640597X,SSM,2005\r\n"
				+ "\"Baseball - And The Skipper Bats Cleanup: A History of the Baseball Player-Manager, with 42 Biographies of Men Who Filled the Dual Role\",Fred Stein,24.95,0786412283,SSM,2005\r\n"
				+ "\"Baseball - Anson, Cap: The Grand Old Man of Baseball\",David L. Fleitz,24.95,0786422386,SSM,2005\r\n"
				+ "Baseball - At Home And Away: 33 Years of Baseball Essays,John Kuenster,24.95,0786415592,SSM,2005\r\n"
				+ "\"Baseball - Baseball / Literature / Culture: Essays, 1995-2001\",,39.95,0786416432,SSM,2005\r\n"
				+ "\"Baseball - Baseball / Literature / Culture: Essays, 2002-2003\",,39.95,0786418516,SSM,2005\r\n"
				+ "\"Baseball - Baseball And Richmond: A History of the Professional Game, 1884-2000\",W. Harrison Daniel & Scott P. Mayer,24.95,0786414898,SSM,2005\r\n"
				+ "Baseball - Baseball Anthology,,9.95,0810991799,SSM,2005\r\n"
				+ "Baseball - Baseball as America: Seeing Ourselves Through Our National Game,,11.95,0792238982,SSM,2005\r\n"
				+ "Baseball - Baseball Cyclopedia,Ernest J. Lanigan,30.95,9780786418688,SSM,2005\r\n"
				+ "\"Baseball - Baseball Filmography, 1915 Through 2001, Second Edition\",Hal Erickson,44.95,0786412720,SSM,2005\r\n"
				+ "\"Baseball - Baseball Necrology, The - The Post Baseball Lives and Deaths of More Than 7,600 Major League Players and Others\",Bill Lee,35.95,9780786442393,SSM,2005\r\n"
				+ "\"Baseball - Baseball Players of The 1950s: A Biographical Dictionary of All 1,560 Major Leaguers\",Rich Marazzi & Len Fiorito,49.95,078641281X,SSM,2005\r\n"
				+ "Baseball - Baseball Scorekeeping: A Practical Guide to the Rules,Andres Wirkmaa,24.95,0786414480,SSM,2005\r\n"
				+ "Baseball - Baseball Scrapbook,Peter C. Bjarkman,19.95,1572153792,SSM,2005\r\n"
				+ "Baseball - Baseball With A Latin Beat: A History of the Latin American Game,Peter C. Bjarkman,29.95,0899509738,SSM,2005\r\n"
				+ "Baseball - Baseball's Golden Age: Photographs of Charles M. Conlan,Neal McCabe & Constance McCabe,5.95,0810991195,SSM,2005\r\n"
				+ "Baseball - Baseball's Retired Numbers: Major and Minor Leagues,Thomas W. Brucato,24.95,0786417625,SSM,2005\r\n"
				+ "Baseball - Baseball's Western Front: The Pacific Coast League During World War II,Donald R. Wells,24.95,0786419989,SSM,2005\r\n"
				+ "Baseball - Beating The Breaks: Major League Ballplayers Who Overcame Disabilities,Rick Swaine,24.95,0786418281,SSM,2005\r\n"
				+ "\"Baseball - Before They Were The Bombers: The New York Yankees' Early Years, 1903-1915\",Jim Reisler,24.95,0786422300,SSM,2005\r\n"
				+ "\"Baseball - Beisbol Cubano: A un Paso de las Grandes Ligas, 1878-1961\",Jorge S. Figueredo,64.95,0786419865,SSM,2005\r\n"
				+ "\"Baseball - Best Man Plays: Major League Baseball and the Black Athlete, 1901-2002\",Andrew O'Toole,24.95,0786414944,SSM,2005\r\n"
				+ "Baseball - Biographical Dictionary of Major League Baseball Managers,John C. Skipper,39.95,0786410213,SSM,2005\r\n"
				+ "Baseball - Biographical Dictionary of The Baseball Hall of Fame,John C. Skipper,39.95,0786406038,SSM,2005\r\n"
				+ "\"Baseball - Brooklyn Dodgers In The 1940s: How Robinson, MacPhail, Reiser and Rickey Changed Baseball\",Rudy Marzano,24.95,0786419873,SSM,2005\r\n"
				+ "Baseball - Brooklyn Dodgers Reader,Andrew Paul Mele,34.95,078641913X,SSM,2005\r\n"
				+ "Baseball - Business of Baseball,Albert Theodore Powers,24.95,078641426X,SSM,2005\r\n"
				+ "Baseball - Checks And Imbalances: Competitive Disparity in Major League Baseball,Scott Barzilla,24.95,0786412550,SSM,2005\r\n"
				+ "\"Baseball - Class At Bat, Gender On Deck And Race In The Hole: A Line-up of Essays on Twentieth Century Culture and America's Game\",Ron Briley,29.95,0786415908,SSM,2005\r\n"
				+ "Baseball - Classic Baseball,Dave Anderson,7.95,0810942585,SSM,2005\r\n"
				+ "Baseball - Close Shave: The Life and Times of Baseball's Sal Maglie,James D. Szalontai,27.95,0786411899,SSM,2005\r\n"
				+ "\"Baseball - College World Series: A Baseball History, 1947-2003\",W.C. Madden & Patrick J. Stewart,39.95,0786418427,SSM,2005\r\n"
				+ "\"Baseball - Collins, Dottie Wiltse: Strikeout Queen of the All-American Girls Professional Baseball League\",Carolyn M. Trombe,26.96,9780786421886,SSM,2005\r\n"
				+ "Baseball - Cool Papas And Double Duties: The All-Time Greats of the Negro Leagues,William F. McNeil,24.95,0786422297,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 1997 (Jackie Robinson)\",,29.95,0786408316,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 1998\",,29.95,0786409541,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 1999\",,29.95,0786408324,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 2000\",,29.95,0786411201,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 2001\",,29.95,0786413573,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 2002\",,29.95,0786415703,SSM,2005\r\n"
				+ "\"Baseball - Cooperstown Symposium On Baseball And American Culture, 2003-2004\",,29.95,0786421967,SSM,2005\r\n"
				+ "\"Baseball - Cuban Baseball: A Statistical History, 1878-1961\",Jorge S. Figueredo,67.95,078641250X,SSM,2005\r\n"
				+ "\"Baseball - Cubs Win The Pennant!: Charlie Grimm, the Billy Goat Curse, and the 1945 World Series Run\",John C. Skipper,24.95,0786418168,SSM,2005\r\n"
				+ "\"Baseball - Cultural Encyclopedia of Baseball, 2nd Edition\",Jonathan Fraser Light,64.95,0786420871,SSM,2005\r\n"
				+ "\"Baseball - Dahlen, Bad Bill: The Rollicking Life and Times of an Early Baseball Star\",Lyle Spatz,26.95,0786419784,SSM,2005\r\n"
				+ "\"Baseball - Dean of Umpires: A Biography of Bill McGowan, 1896-1954\",Bob Luke,24.95,0786421363,SSM,2005\r\n"
				+ "Baseball - Diamond Classics: Essays on 100 of the Best Baseball Books Ever Published,Mike Shannon,24.95,0786418532,SSM,2005\r\n"
				+ "\"Baseball - Early Latino Ballplayers In The United States: Major, Minor and Negro Leagues, 1901-1949\",Nick C. Wilson,29.95,078642012X,SSM,2005\r\n"
				+ "Baseball - Ebbets To Veeck To Busch: Eight Owners who Shaped Baseball,Burton A. Boxerman & Benita W. Boxerman,24.95,0786415622,SSM,2005\r\n"
				+ "\"Baseball - Ee-Yah: The Life and Times of Hughie Jennings, Baseball Hall of Famer\",Jack Smiles,-24.95,0786422025,SSM,2005\r\n"
				+ "Baseball - Ferrell Brothers of Baseball,Dick Thompson,24.95,0786420065,SSM,2005\r\n"
				+ "\"Baseball - Fine-Looking Lot of Ball-Tossers, A: The Remarkable Akrons of 1881\",Richard L. McBane,26.96,9780786420568,SSM,2005\r\n"
				+ "\"Baseball - For It's One, Two, Three, Four Strikes You're Out At The Owners' Ball Game: Players Versus Management in Baseball\",G. Richard McKelvey,24.95,0786411929,SSM,2005\r\n"
				+ "Baseball - For The Good of The Country: World War II Baseball in the Major and Minor Leagues,David Finoli,24.95,0786413700,SSM,2005\r\n"
				+ "Baseball - Ghosts In The Gallery At Cooperstown: Sixteen Little-Known Members of the Hall of Fame,David L. Fleitz,24.95,0786417498,SSM,2005\r\n"
				+ "\"Baseball - Giants And The Dodgers: Four Cities, Two Teams, One Rivalry\",Andrew Goldblatt,24.95,0786416408,SSM,2005\r\n"
				+ "Baseball - Golden Age of Baseball,Paul Adomites,19.95,0785383689,SSM,2005\r\n"
				+ "Baseball - Great Shutout Pitchers: Twenty Profiles of a Vanishing Breed,Joe MacKay,24.95,0786416769,SSM,2005\r\n"
				+ "Baseball - Greatest World Series Games: Baseball Historians Choose 26 Classics,Warren N. Wilbert,39.95,0786418230,SSM,2005\r\n"
				+ "\"Baseball - Hartnett, Gabby: The Life and Times of the Cubs' Greatest Catcher\",William F. McNeil,24.95,0786418508,SSM,2005\r\n"
				+ "Baseball - History of The Baseball Fan,Fred Stein,24.95,0786421487,SSM,2005\r\n"
				+ "\"Baseball - Hoyt, Waite: A Biography of the Yankees' Schoolboy Wonder\",William A. Cook,27.95,0786419601,SSM,2005\r\n"
				+ "Baseball - I Will Never Forget: Interviews with 39 Former Negro League Players,Brent Kelley,39.95,0786414812,SSM,2005\r\n"
				+ "\"Baseball - Independent Carolina Baseball League, 1936-1938: Baseball Outlaws\",R.G. (Hank) Utley & Scott Verner,28.8,9780786423187,SSM,2005\r\n"
				+ "Baseball - Integration of Baseball In Philadelphia,Christopher Threston,24.95,0786414235,SSM,2005\r\n"
				+ "\"Baseball - International League: Year-by-Year Statistics, 1884-1953\",Marshall D. Wright,49.95,078642267X,SSM,2005\r\n"
				+ "\"Baseball - Kansas City Athletics: A Baseball History, 1954-1967\",John E. Peterson,24.95,0786416106,SSM,2005\r\n"
				+ "\"Baseball - Lazzeri, Tony: A Baseball Biography\",Paul Votano,26.96,9780786420148,SSM,2005\r\n"
				+ "Baseball - League That Lasted: 1876 and the Founding of the National League of Professional Base Ball Clubs,Neil W. Macdonald,28.95,0786417552,SSM,2005\r\n"
				+ "\"Baseball - Lean Years of The Yankees, 1965-1975\",Robert W. Cohen,26.95,078641846X,SSM,2005\r\n"
				+ "\"Baseball - Long Before The Dodgers: Baseball in Brooklyn, 1855-1884\",James L. Terry,24.95,0786412291,SSM,2005\r\n"
				+ "Baseball - Louisville Grays Scandal of 1877: The Taint of Gambling at the Dawn of the National League,William A. Cook,24.95,0786421797,SSM,2005\r\n"
				+ "\"Baseball - Mack's, Connie '29 Triumph: The Rise and Fall of the Philadelphia Athletics Dynasty\",William C. Kashatus,26.96,9780786421657,SSM,2005\r\n"
				+ "Baseball - Major League Baseball In The 1970s: A Modern Game Emerges,Joseph G. Preston,24.95,0786415924,SSM,2005\r\n"
				+ "\"Baseball - Martin, Pepper: A Baseball Biography\",Thomas Barthel,24.95,0786416025,SSM,2005\r\n"
				+ "\"Baseball - Mccarthy, Joe: Architect of the Yankee Dynasty\",Alan H. Levy,29.95,078641961X,SSM,2005\r\n"
				+ "\"Baseball - Mexican League / La Liga Mexicana: Comprehensive Player Statistics, 1937-2001\",Pedro Treto Cisneros,44.95,0786413786,SSM,2005\r\n"
				+ "\"Baseball - Minor League Baseball Standings: All North American Leagues, Through 1999\",Benjamin Barrett Sumner,64.95,0786407816,SSM,1005\r\n"
				+ "\"Baseball - Minor League Milwaukee Brewers, 1859-1952\",Brian A. Podoll,24.95,0786414553,SSM,2005\r\n"
				+ "\"Baseball - Minor Leagues, Major Boom: Local Professional Baseball Revitalized\",Jon C. Stott,24.95,0786417595,SSM,2005\r\n"
				+ "\"Baseball - Most Valuable Players In Baseball, 1931-2001\",Timm Boyle,39.95,0786410299,SSM,2005\r\n"
				+ "\"Baseball - National Association of Base Ball Players, 1857-1870\",Marshall D. Wright,39.95,0786407794,SSM,2005\r\n"
				+ "Baseball - Negro Leagues Revisited: Conversations with 66 More Baseball Heroes,Brent Kelley,39.95,0786408758,SSM,2005\r\n"
				+ "\"Baseball - Negro Leagues, 1869-1960\",Leslie A. Heaphy,39.95,0786413808,SSM,2005\r\n"
				+ "\"Baseball - Nineteenth Century Baseball: Year-by-Year Statistics for the Major League Teams, 1871 through 1900\",Marshall D. Wright,29.95,0786418958,SSM,2005\r\n"
				+ "Baseball - Occasional Glory: The History of the Philadelphia Phillies,David M. Jordan,24.95,0786412607,SSM,2005\r\n"
				+ "Baseball - October Baseball: Ballplayers Discuss Postseason Play,Dan Zachofsky,24.95,0786421819,SSM,2005\r\n"
				+ "Baseball - Pastime In The Seventies: Oral Histories of 16 Major Leaguers,Bill Ballew,24.95,0786413476,SSM,2005\r\n"
				+ "Baseball - Pitched Battle: 35 of Baseball's Greatest Duels from the Mound,John Klima,24.95,0786412038,SSM,2005\r\n"
				+ "\"Baseball - Reiser, Pete: The Rough-and-Tumble Career of the Perfect Ballplayer\",Sidney Jacobson,24.95,0786418761,SSM,2005\r\n"
				+ "\"Baseball - Richter's History And Records of Base Ball, The American Nation's Chief Sport\",Francis C. Richter,30.95,9780786417278,SSM,2005\r\n"
				+ "\"Baseball - Rose, Pete: Baseball's All-Time Hit King\",William A. Cook,24.95,0786417331,SSM,2005\r\n"
				+ "Baseball - Santurce Crabbers: Sixty Seasons of Puerto Rican Winter League Baseball,Thomas E. Van Hyning,29.95,0786406623,SSM,2005\r\n"
				+ "Baseball - Scapegoats: Baseballers Whose Careers Are Marked by One Fateful Play,Christopher Bell,24.95,0786413816,SSM,2005\r\n"
				+ "Baseball - September Streak: The 1935 Chicago Cubs Chase the Pennant,Doug Feldmann,24.95,0786415916,SSM,2005\r\n"
				+ "\"Baseball - Single-Season Home Run Kings: Ruth, Maris, McGwire, Sosa, and Bonds, 2d ed.\",William F. McNeil,24.95,0786414413,SSM,2005\r\n"
				+ "\"Baseball - Sockalexis, Louis: The First Cleveland Indian\",David L. Fleitz,24.95,0786413832,SSM,2005\r\n"
				+ "\"Baseball - Southern Association In Baseball, 1885-1961\",Marshall D. Wright,39.95,0786412917,SSM,2005\r\n"
				+ "Baseball - Spring Training Handbook: A Comprehensive Guide to the Ballparks of the Grapefruit and Cactus Leagues,Joshua R. Pahigian,24.95,0786420863,SSM,2005\r\n"
				+ "Baseball - Stand And Deliver: A History of Pinch-Hitting,Paul Votano,24.95,0786415886,SSM,2005\r\n"
				+ "\"Baseball - State of Baseball Management: Decision-Making in the Best and Worst Teams, 1993-2003\",Scott Barzilla,24.95,0786418982,SSM,2005\r\n"
				+ "Baseball - Summer of '64: A Pennant Lost,William A. Cook,27.95,078641216X,SSM,2005\r\n"
				+ "\"Baseball - Sunday Baseball: The Major Leagues' Struggle to Play Baseball on the Lord's Day, 1876-1934\",Charlie Bevis,24.95,0786415649,SSM,2005\r\n"
				+ "Baseball - Sunday Coming: Black Baseball in Virginia,Darrell J. Howard,24.95,0786413514,SSM,2005\r\n"
				+ "\"Baseball - Tale of Four Cities, A: Nineteenth Century Baseball's Most Exciting Season, 1889, in Contemporary Accounts\",Jean-Pierre Caillault,24.95,0786416785,SSM,2005\r\n"
				+ "\"Baseball - Texas League In Baseball, 1888-1958\",Marshall D. Wright,44.95,0786418028,SSM,2005\r\n"
				+ "\"Baseball - Tinker, Evers, And Chance: A Triple Biography\",Gil Bogen,24.95,0786416815,SSM,2005\r\n"
				+ "Baseball - Voices From The Negro Leagues: Conversations with 52 Baseball Standouts of the Period 1924-1960,Brent Kelley,29.95,0786422793,SSM,2005\r\n"
				+ "\"Baseball - Wagner, Honus: The Life of Baseball's Flying Dutchman\",Arthur D. Hittner,24.95,0786418117,SSM,2005\r\n"
				+ "\"Baseball - Washington Senators, 1901-1971\",Tom Deveaux,29.95,0786423595,SSM,2005\r\n"
				+ "\"Baseball - What Makes An Elite Pitcher?: Young, Mathewson, Johnson, Alexander, Grove, Spahn, Seaver, Clemens, and Maddux\",Warren N. Wilbert,24.95,0786414561,SSM,2005\r\n"
				+ "Baseball - When Baseball Returned To Brooklyn: The Inaugural Season of the New York-Penn League Cyclones,Ed Shakespeare,24.95,0786414596,SSM,2005\r\n"
				+ "Baseball - When The Bucs Won It All: The 1979 World Champion Pittsburgh Pirates,Bill Ranier & David Finoli,24.95,0786420502,SSM,2005\r\n"
				+ "\"Baseball - Whole New Game, A: Off the Field Changes in Baseball, 1946-1960\",John P. Rossi,29.95,0786406518,SSM,2005\r\n"
				+ "Baseball - Women Characters In Baseball Literature: A Critical Study,Kathleen Sullivan,29.95,0786421703,SSM,2005\r\n"
				+ "Baseball - Women of The All-American Girls Professional Baseball League: A Biographical Dictionary,W.C. Madden,29.95,0786422637,SSM,2005\r\n"
				+ "\"Baseball - Wright, Harry: The Father of Professional Base Ball\",Christopher Devine,24.95,0786415614,SSM,2005\r\n"
				+ "Golf - Classic Golf: Photographs of Walter Iooss Jr.,,5.95,0810949830,SSM,2005\r\n"
				+ "Golf - Golf's Greatest Moments: Illustrated History by the Game's Finest Writers,,19.95,0810946319,SSM,2005\r\n"
				+ "Sports Bloopers,Mark Huebner & Brad Wilson,7.95,1552976270,SSM,2005\r\n"
				+ "\"Baseball - All Bat, No Glove: A History of the Designated Hitter\",G. Richard McKelvey,24.95,078641944X,SSN,2005\r\n"
				+ "Baseball - Baseball Players' Best Seasons: Team by Team Rankings,Michael S. Jones,39.95,0786410868,SSN,2005\r\n"
				+ "Baseball - Battle of Base-Ball,C.H. Claudy,30.95,9780786420209,SSN,2005\r\n"
				+ "\"Baseball - Big And Little Poison: Paul and Lloyd Waner, Baseball Brothers\",Clifton Blue Parker,24.95,0786414006,SSN,2005\r\n"
				+ "American Stock Car,William Burt,5.95,0760309779,TPA,2005\r\n"
				+ "Big Tractors - 100 Years of High-Powered Farm Machinery,Robert Pripps & Andrew Morland,16.95,0873497317,TPA,2005\r\n"
				+ "BMW Motorcycles - The Ultimate Riding Machines,Kevin Ash,9.95,1842222732,TPA,2005\r\n"
				+ "Cadillac Century,John Heilig,7.95,0785809171,TPA,2005\r\n"
				+ "Car - A History of the Automobile,Jonathan Glancey,12.95,1842229427,TPA,2005\r\n"
				+ "Cars,Leah Bendavid-Val,4.95,0792261690,TPA,2005\r\n"
				+ "Corvette,Mike Mueller,7.95,0760313865,TPA,2005\r\n"
				+ "\"Corvette Field Guide, 1953-Present\",Jerry Heasley,9.95,,TPA,2005\r\n"
				+ "\"Ford Muscle - Street, Stock, and Strip\",Bill Holder & Phil Kunz,19.95,0873498356,TPA,2005\r\n"
				+ "Harley-Davidson,Allan Girdler & Jeff Hackett,6.95,0760313741,TPA,2005\r\n"
				+ "Harley-Davidson,John Tipler,9.95,1571457836,TPA,2005\r\n"
				+ "Harley-Davidson - 100 Years of Harley-Davidson Advertising,Thomas C. Bolfert,5.95,0821228293,TPA,2005\r\n"
				+ "\"Harley-Davidson - Standard Catalog of Harley-Davidson Motorcycles, 1903-2003\",Doug Mitchel,19.95,0873497368,TPA,2005\r\n"
				+ "Harley-Davidson - Ultimate Harley-Davidson,Mac McDiarmid,19.95,1843091690,TPA,2005\r\n"
				+ "Lowriders,Robert Genat,4.95,0760318328,TPA,2005\r\n"
				+ "Mustang,David Newhardt,4.95,076031389X,TPA,2005\r\n"
				+ "Porsche - The Ultimate Guide,Scott Faragher,19.95,0873497201,TPA,2005\r\n"
				+ "Retro Ride,Tony Swan,14.95,188805462X,TPA,2005\r\n"
				+ "Sport Compacts,Alan Paradise,4.95,0760318336,TPA,2005\r\n"
				+ "Sports Car Classics,Lain Ayre,5.95,1859676561,TPA,2005\r\n"
				+ "Sports Car Icons,John Heilig & Dennis Adler & Randy Leffingwell,5.95,0760317771,TPA,2005\r\n"
				+ "\"Standard Catalog of Camaro, 1967-2002\",John Gunnell,19.95,0873494954,TPA,2005\r\n"
				+ "\"Standard Catalog of Corvette, 1953-2005 (2nd Edition)\",John Gunnell,19.95,0873499077,TPA,2005\r\n"
				+ "\"Standard Catalog of GTO, 1961-2004\",John Gunnell,19.95,0873496892,TPA,2005\r\n"
				+ "\"Standard Catalog of Pontiac, 1926-2002 (2nd Edition)\",John Gunnell,16.95,0873492633,TPA,2005\r\n"
				+ "Vintage Volkswagens,Flat 4 Project,7.95,0811825450,TPA,2005\r\n"
				+ "Viper,Matt Stone,6.95,0760316988,TPA,2005\r\n"
				+ "VW Beetle,Ryan Lee Price,8.95,1557884218,TPA,2005\r\n"
				+ "");
		System.out.println(book + " created successfully.");
		b11.close();
}
if(book.equals("books2006.csv")) {
	b12 = new PrintWriter(book);
		b12.println("\"Animated Film Encyclopedia - A Complete Guide To American Shorts, Features, And Sequences, 1900-1979 (Volumes 1 & 2)\",Graham Webb,66.95,9780786428595,CCB,2006\r\n"
			+ "Artburn,Robbie Conal,5.95,0971920613,CCB,2006\r\n"
			+ "Comics Buyer's Guide (12th Edition) - 2006 Comic Book Checklist & Price Guide (1961-Present),Maggie Thompson & Brent Frankenhoff & Peter Bickford & John Jackson Miller,16.95,0873499921,CCB,2006\r\n"
			+ "Superman - Superman Sunday Classics 1939-1943,Jerry Siegel & Joe Shuster,11.95,1402737866,CCB,2006\r\n"
			+ "\"Superman: The Dailies, 1939-1942\",Jerry Siegel & Joe Shuster,16.95,1402737858,CCB,2006\r\n"
			+ "Wonder Woman - Complete History,Les Daniels,14.95,0811842339,CCB,2006\r\n"
			+ "Worlds of Tomorrow - The Amazing Universe of Science Fiction Art,Forrest J. Ackerman & Brad Linaweaver,29.95,188805493X,CCB,2006\r\n"
			+ "Striking Images - Vintage Match-Book Cover Art,Monte Beauchamp,12.95,0811851435,HCB,2006\r\n"
			+ "AC / DC - Two Sides to Every Glory,Paul Stenning,14.95,1842403087,MRB,2006\r\n"
			+ "\"Archives of the Airwaves, Volume 1\",Roger C. Paulson,17.95,1593930372,MRB,2006\r\n"
			+ "\"Archives of the Airwaves, Volume 2\",Roger C. Paulson,17.95,1593930380,MRB,2006\r\n"
			+ "\"Archives of the Airwaves, Volume 3\",Roger C. Paulson,17.95,159393047X,MRB,2006\r\n"
			+ "\"Archives of the Airwaves, Volume 4\",Roger C. Paulson,17.95,1593930488,MRB,2006\r\n"
			+ "Baby Snooks Scripts,Philip Rapp,11.95,1932133801,MRB,2006\r\n"
			+ "Bickersons - Biography of Radio's Wittiest Program,Ben Ohmart,16.95,1593930089,MRB,2006\r\n"
			+ "\"Bickersons Scripts, Volume 2\",Philip Rapp,15.95,1593930070,MRB,2006\r\n"
			+ "Billboard - Billboard Illustrated Encyclopedia of Music,,19.95,0823078698,MRB,2006\r\n"
			+ "Black History - On This Day in Black Music History,Jay Warner,12.95,0634099264,MRB,2006\r\n"
			+ "\"British Beat - Then, Now and Rare, 1960-1969\",Terry Rawlings,19.95,0711990948,MRB,2006\r\n"
			+ "\"Clapton, Eric - Complete Guide to his Music\",Marc Roberty,4.95,1846090075,MRB,2006\r\n"
			+ "\"Columbo, Russ - A Prisoner of Love\",Tony Toran,24.95,1593930550,MRB,2006\r\n"
			+ "Comic Strips & Comic Books of Radio's Golden Age,Ron Lackmann,16.95,1593930216,MRB,1006\r\n"
			+ "\"Davis, Sammy Jr. - In Black and White\",Wil Haygood,5.95,037540354X,MRB,2006\r\n"
			+ "Deep Purple - Complete Deep Purple,Michael Heatley,24.95,1903111994,MRB,2006\r\n"
			+ "Donovan - Autobiography of Donovan: Hurdy Gurdy Man,Donovan Leitch,5.95,0312352122,MRB,2006\r\n"
			+ "\"Epstein, Brian - In My Life: Epstein, Brian Story\",Debbie Geller,4.95,031228862X,MRB,2006\r\n"
			+ "Esteban's Complete Guitar Course for Beginners,Esteban,19.95,1402732171,MRB,2006\r\n"
			+ "Grammys - Ultimate Unofficial Guide To Music's Highest Honor,Thomas O'Neil,4.95,0399524770,MRB,2006\r\n"
			+ "\"Harrison, George - George Harrison Encyclopedia\",Bill Harry,5.95,0753508222,MRB,2006\r\n"
			+ "Heavy Metal Thunder,James Sherry & Neil Aldis,15.95,0811853535,MRB,2006\r\n"
			+ "Hopalong Cassidy - Jingle of the Silver Spurs: Hopalong Cassidy Radio Program (1950-52),Bernard A. Drew,11.95,1593930267,MRB,2006\r\n"
			+ "Hot Wacks Supplement 6,,12.95,0968858708,MRB,2006\r\n"
			+ "Intimate Nights - Golden Age of New York Cabaret,James Gavin,15.95,0823088251,MRB,2006\r\n"
			+ "KISS - Convention Guide & Ticket: 18th Annual New York KISS Expo (2004),,3.95,,MRB,2006\r\n"
			+ "KISS - Convention Guide & Ticket: 19th Annual New York KISS Expo (2005),,3.95,,MRB,2006\r\n"
			+ "\"Lanza, Mario\",Roland L. Bessette,16.95,1574670441,MRB,2006\r\n"
			+ "Led Zeppelin - Concert File,Dave Lewis & Simon Pallett,9.95,1844496597,MRB,2006\r\n"
			+ "Let's Pretend and the Golden Age of Radio,Arthur Anderson,16.95,1593930194,MRB,2006\r\n"
			+ "Little Feat - Rock and Roll Doctor: Lowell George and Little Feat,Mark Brend,14.95,0879307269,MRB,2006\r\n"
			+ "Magical Mystery Tours: My Life With The Beatles,Tony Bramwell,5.95,031233043X,MRB,2006\r\n"
			+ "\"Marley, Bob - His Musical Legacy\",Jeremy Collingwood,19.95,1844033430,MRB,2006\r\n"
			+ "\"McCartney, Linda - Portrait\",Danny Fields,4.95,1580631045,MRB,2006\r\n"
			+ "\"McCartney, Paul - Paintings\",Paul McCartney,14.95,0821226738,MRB,2006\r\n"
			+ "\"Mercer, John Herndon - Portrait of Johnny: Life of John Herndon Mercer\",Gene Lees,14.95,9780634099298,MRB,2006\r\n"
			+ "Metallica - Complete Guide to their Music,Malcolm Dome & Mick Wall,4.95,1844499812,MRB,2006\r\n"
			+ "Metallica - Metallica,Ross Halfin,19.95,190339984X,MRB,2006\r\n"
			+ "New York Dolls - Too Much Too Soon,Nina Antonia,9.95,1844499847,MRB,2006\r\n"
			+ "Off The Record: Songwriters on Songwriting (Includes 2-CDs),Graham Nash,14.95,0740726781,MRB,2006\r\n"
			+ "\"Ono, Yoko - Woman: The Incredible Life of Yoko Ono\",Alan Clayson,5.95,184240220X,MRB,2006\r\n"
			+ "Philadelphia - City of Music,James Rosin,14.95,1933822007,MRB,2006\r\n"
			+ "\"Presley, Elvis - Rebel Years\",Lester Bangs,4.95,0393316254,RBM,2006\r\n"
			+ "Punk - The Heebie-Jeebies At CBGB's: A Secret History of Jewish Punk,Steven Lee Beeber,16.95,155652613X,MRB,26\r\n"
			+ "Radio - It's That Time Again: More New Stories of Old-Time Radio,,11.95,1591930062,MRB,2106\r\n"
			+ "Radio - It's That Time Again: The New Stories of Old-Time Radio,,11.95,0971457026,MRB,2006\r\n"
			+ "\"Radio Stars - An Illustrated Biographical Dictionary of 953 Performers, 1920 Through 1960\",Thomas A. DeLong,44.95,9780786428342,MRB,2006\r\n"
			+ "\"Radio, TV, Mother Earth & Me - Memories of a Hollywood Life\",Joel Rapp,15.95,1593930054,MRB,2006\r\n"
			+ "Ramones - Ramones,Chip Dayton (Photographer),14.95,1903399815,MRB,2006\r\n"
			+ "\"Reinhardt, Django - Know The Man, Play The Music [Includes Audio CD]\",Dave Gelly & Rod Fogg,19.95,0879308370,MRB,2006\r\n"
			+ "\"Rock Posters - Cult Rock Posters: Ten Years of Classic Posters From The Glam, Punk And New Wave Era\",Roger Crimlis & Alwyn W. Turner,24.95,0823077799,MRB,2006\r\n"
			+ "Rocklopedia Fakebandica,T. Mike Childs,5.95,031232944X,MRB,2006\r\n"
			+ "\"Rolling Stone 1,000 Covers - A History of The Most Influential Magazine In Pop Culture\",Jann S. Wenner,24.95,0810958651,MRB,2006\r\n"
			+ "Roxy Music - Both Ends Burning,Jonathan Rigby,22.95,1903111803,MRB,2006\r\n"
			+ "\"Simon, Paul - Definitive Biography\",Laura Jackson,5.95,0749923431,MRB,2006\r\n"
			+ "\"Sintra, Frank - Why Sinatra Matters\",Pete Hamill,4.95,0316738867,MRB,2006\r\n"
			+ "\"Springsteen, Bruce - Days of Hope and Dreams: Intimate Portrait of Bruce Springsteen\",Frank Stefanko,9.95,082308387X,MRB,2006\r\n"
			+ "Swing Book,Degen Penner,3.95,0316698024,MRB,2006\r\n"
			+ "\"Terror On The Air! - Horror Radio in America, 1931-1952\",Richard J. Hand,39.95,0786423676,MRB,2006\r\n"
			+ "This is Uncool - 500 Greatest Singles Since Punk and Disco,Garry Mulholland,14.95,1844031055,MRB,2006\r\n"
			+ "\"Van Halen, Eddie - Know the Man, Play the Music\",Malcolm Dome & Rod Fogg,19.95,0879308389,MRB,2006\r\n"
			+ "Vic & Sade - The Story of Vic & Sade,Bill Idelson,19.95,1593930615,MRB,2006\r\n"
			+ "\"Vinyl Hayride - Country Music Album Covers, 1947-1989\",Paul Kingsbury,9.95,0811835723,MRB,2006\r\n"
			+ "While My Guitar Gently Weeps - Music of George Harrison,Simon Leng,16.95,1423406095,MRB,2006\r\n"
			+ "\"Who - Anyway, Anyhow, Anywhere: Complete Chronicle of The Who, 1958-1978\",Andy Neill & Matt Kent,17.95,1402728387,MRB,2006\r\n"
			+ "Wireless Radio - A History,Lewis Coe,30.95,9780786426621,MRB,2006\r\n"
			+ "\"Wonder, Stevie - Musical Guide to the Classic Albums\",Steve Lodder,16.95,0879308214,MRB,2006\r\n"
			+ "All About Eve - All About All About Eve,Sam Staggs,5.95,0312273150,MTV,2006\r\n"
			+ "\"Analytical Guide To Television's One Step Beyond, 1959 - 1961\",John Kenneth Muir,26.96,9780786428496,MTV,2006\r\n"
			+ "\"Ashton, Roy - Greasepaint and Gore: Hammer Monsters of Roy Ashton\",Bruce Sachs & Russell Wall,16.95,0953192601,MTV,2006\r\n"
			+ "\"Barbeau, Adrienne - There Are Worse Things I Could Do\",Adrienne Barbeau,12.95,0786719303,MTV,2006\r\n"
			+ "\"Beatty, Warren - Sexiest Man Alive: A Biography of Warren Beatty\",Ellis Amburn,5.95,006018566X,MTV,2006\r\n"
			+ "\"Bergman, Ingmar - His Life And Films\",Jerry Vermilye,35.95,9780786429592,MTV,2006\r\n"
			+ "\"Bogart, Humphrey - Ultimate Bogart\",Ernest W. Cunningham,5.95,1580630936,MTV,2006\r\n"
			+ "\"Brando, Marlon - The Way It's Never Been Done Before: My Friendship With Marlon Brando\",George Englund,4.95,1160786302,MTV,2006\r\n"
			+ "\"Brown, Joe E. - Film Comedian and Baseball Buffoon\",Wes D. Gerhing,34.95,078642589X,MTV,2006\r\n"
			+ "\"Brunetti, Argentina - In Sicilian Company\",Argentina Brunetti,15.95,1593930402,MTV,2006\r\n"
			+ "Censored Screams - The British Ban On Hollywood Horror In The Thirties,Tom Johnson,30.95,9780786427314,MTV,2006\r\n"
			+ "\"Chaplin, Charlie - Comic Genius\",David Robinson,4.95,0810928842,MTV,2006\r\n"
			+ "Children of The Night - The Six Archetypal Characters of Classic Horror Films,Randy Rasmussen,30.95,9780786427253,MTV,2006\r\n"
			+ "Christmas Carol And Its Adaptations - A Critical Examination of Dickens's Story And Its Productions On Screen And Television,Fred Guida,26.96,9780786428403,MTV,2006\r\n"
			+ "\"Cohn, Harry - King Cohn: The Life And Times of Hollywood Mogul Harry Cohn\",Bob Thomas,4.95,1893224074,MTV,2006\r\n"
			+ "\"Count Dracula Goes To The Movies - Stoker's Novel Adapted, 1922 - 1995\",Lyndon W. Joslin,44.95,9780786426010,MTV,2006\r\n"
			+ "Cuban Filmography - 1897 Through 2001,Alfonso J. Garcia Osuna,-49.5,9780786427277,MTV,2006\r\n"
			+ "\"Cushing, Peter\",,19.95,188766453X,MTV,2006\r\n"
			+ "\"Davis, Joan - Hold That Joan: The Life, Laughs And Films of Joan Davis [Author-Signed Edition]\",Ben Ohmart,19.95,1593930461,MTV,2006\r\n"
			+ "\"Day, Doris - Que Sera, Sera: The Magic of Doris Day Through Television\",Pierre Patrick & Garry McGee,19.95,1593930569,MTV,2006\r\n"
			+ "\"Dressler, Marie - A Biography, with a Listing of Major Stage Performances, a Filmography and a Discography\",Matthew Kennedy,35.95,9780786428441,MTV,2006\r\n"
			+ "Drive-In Theaters - A History From Their Inception In 1933,Kerry Segrave,30.95,9780786426300,MTV,2006\r\n"
			+ "\"Ebert, Roger - I Hated, Hated, Hated This Movie\",Roger Ebert,3.95,0740706721,MTV,2006\r\n"
			+ "\"Fetchit, Stepin - Life & Times of Lincoln Perry\",Mel Watkins,24.95,0375423826,MTV,2006\r\n"
			+ "\"Film Noir - A Comprehensive, Illustrated Reference To Movies, Terms And Persons\",Michael L. Stephens,44.95,9780786426287,MTV,2006\r\n"
			+ "\"Films of The Eighties - A Complete, Qualitative Filmography To Over 3400 Feature-Length English Language Films, Theatrical And Video - Only, Released Between January 1, 1980, And December 31, 1989 (2-Volume Set)\",Robert A. Nowlan & Gwendolyn Wright Nowlan,85.5,9780786427376,MTV,2006\r\n"
			+ "Following The Fugitive - An Episode Guide And Handbook To The 1960S Television Series,Bill Deane,30.95,9780786426317,MTV,2006\r\n"
			+ "\"Ford, John Westerns - A Thematic Analysis, With A Filmography\",William Darby,35.95,9780786429547,MTV,2006\r\n"
			+ "Four-Star Movies - 101 Greatest Films of All Time,Gail Kinn & Jim Piazza,11.95,1579123155,MTV,2006\r\n"
			+ "\"Fox, William - Story of Early Hollywood 1915-1930\",Susan Fox & Donald G. Rosellini,24.95,1887664629,MTV,2006\r\n"
			+ "\"Francis, Kay\",Lynn Kear & John Rossman,30.95,0786423668,MTV,2006\r\n"
			+ "\"From Broadway to the Bowery - A History and Filmography of the Dead End Kids, Little Tough Guys, East Side Kids and Bowery Boys Films, with Cast Biographies [Author-Signed Edition]\",Leonard Getz,44.95,0786425350,MTV,2006\r\n"
			+ "\"Garfield, John - The Illustrated Career In Films And On Stage\",Patrick J. McGrath,-35.95,9780786428489,MTV,2006\r\n"
			+ "\"Garner, Peggy Ann - Plain Beautiful: Life of Peggy Ann Garner\",Sandra Grabman,16.95,1593930178,MTV,2006\r\n"
			+ "\"Golden Horrors - An Illustrated Critical Filmography of Terror Cinema, 1931 - 1939\",Bryan Senn,35.95,9780786427246,MTV,2006\r\n"
			+ "\"Harryhausen, Ray - Art of Ray Harryhausen\",Ray Harryhausen & Tony Dalton,39.95,0823084000,MTV,2006\r\n"
			+ "\"History And Critical Analysis of Blake's 7, The 1978 to 1981 British Television Space Adventure\",John Kenneth Muir,26.96,9780786426607,MTV,2006\r\n"
			+ "\"Hitchcock, Alfred - A-Z of Hitchcock\",Howard Maxford,19.95,0713487380,MTV,2006\r\n"
			+ "\"Hitchcock, Alfred - It's Only a Movie: Personal Biography\",Charlotte Chandler,12.95,1557836922,MTV,2406\r\n"
			+ "\"Hollywood Diary: Twelve Untold Tales - Gary Cooper, Our Gang, Zeppo Marx, and more!\",Richard Lamparski,16.95,1593930526,MTV,2006\r\n"
			+ "\"Hollywood War Films, 1937-1945: An Exhaustive Filmography of American Feature - Length Motion Pictures Relating To World War II\",Michael S. Shull & David Edward Wilt,66.95,9780786428540,MTV,2006\r\n"
			+ "\"Jarman, Derek - A Biography\",Tony Peake,5.95,1585673315,MTV,2006\r\n"
			+ "\"Jason, Sybil - My Fifteen Minutes\",Sybil Jason,13.95,1593930232,MTV,2006\r\n"
			+ "\"Kaufman, Andy - Revealed! Best Friend Tells All\",Bob Zmunda & Matthew Scott Hansen,4.95,0316610984,MTV,2006\r\n"
			+ "\"Kennedy, Edgar - Master of the Slow Burn\",Bill Cassara,16.95,1593930186,MTV,2006\r\n"
			+ "King Kong - Amazingly True Adventures of Merian C. Cooper & Ernest B. Schoedsack,Susan Svehla,8.95,1887664602,MTV,2006\r\n"
			+ "Ladies of The Western - Interviews With Fifty - One More Actresses From The Silent Era To The Television Westerns of The 1950S And 1960S,Michael G. Fitzgerald & Boyd Magers,35.95,9780786426560,MTV,2006\r\n"
			+ "Laurel & Hardy - Art of Laurel & Hardy,Kyp Harness,30.95,0786424400,MTV,2006\r\n"
			+ "\"Lewis, Herschell Gordon - Godfather of Gore - The Films\",Randy Palmer,35.95,9780786428502,MTV,2006\r\n"
			+ "\"Live From New York - Uncensored History of 'Saturday Night Live', An\",Tom Shales & James Andrew Miller,4.95,0316735655,MTV,2006\r\n"
			+ "\"Lugosi, Bela - His Life In Films, On Stage, And In The Hearts of Horror Lovers\",Gary Don Rhodes,30.95,9780786427659,MTV,2006\r\n"
			+ "Mad About Movies (#5),,7.95,,MTV,2006\r\n"
			+ "\"Martin, Dean - Memories Are Made of This: Dean Martin Through His Daughter's Eyes\",Deana Martin,5.95,140005043X,MTV,2006\r\n"
			+ "\"Marvin, Lee - His Films And Career\",Robert J. Lentz,35.95,9780786426065,MTV,2006\r\n"
			+ "Meat is Murder! An Illustrated Guide to Cannibal Culture,Mikita Brottman,-6.95,1840680407,MTV,2006\r\n"
			+ "\"Miller, Kenny - Hollywood Inside & Out: Miller, Kenny Story\",Kenny Miller & Donald Vaughan,16.95,1593930399,MTV,2006\r\n"
			+ "\"Moorehead, Agnes - I Love the Illusion: Life and Career of Agnes Moorehead\",Charles Tranberg,16.95,1593930291,MTV,2006\r\n"
			+ "\"Motion Picture Exhibition In Washington, D.C. - An Illustrated History of Parlors, Palaces And Multiplexes In The Metropolitan Area, 1894 - 1997\",Robert K. Headley,44.95,9780786428427,MTV,2006\r\n"
			+ "My Hit Parade...And A Few Misses,Russell Arms,13.95,1593930240,MTV,2006\r\n"
			+ "\"Names You Never Remember, with Faces You Never Forget - Interviews with the Movies' Character Actors\",Justin Humphreys,16.95,1593930410,MTV,2006\r\n"
			+ "\"Nicholson, Jack - Jack: Great Seducer\",Edward Douglas,5.95,0060520477,MTV,2006\r\n"
			+ "\"Peckinpah, Sam - Films of Sam Peckinpah\",Neil Fulwood,16.95,071348733X,MTV,2006\r\n"
			+ "Planet of The Apes As American Myth - Race And Politics In The Films And Television Series,Eric Greene,26.96,9780786426638,MTV,2006\r\n"
			+ "\"Police On Screen - Hollywood Cops, Detectives, Marshals And Rangers\",M. Ray Lott,35.95,0786425776,MTV,2006\r\n"
			+ "Rebels & Chicks - History of the Hollywood Teen Movie,Stephen Tropiano,14.95,0823097013,MTV,2006\r\n"
			+ "\"Reel Middle Ages - American, Western And Eastern European, Middle Eastern And Asian Films About Medieval Europe\",Kevin J. Harty,44.95,9780786426577,MTV,2006\r\n"
			+ "Republic Pictures Checklist,Len D. Martin,34.95,078642740X,MTV,2006\r\n"
			+ "\"Ripper, Michael - Unmasked\",Derek Pykett,19.95,1887664270,MTV,2006\r\n"
			+ "Rodeo and Hollywood - Rodeo Cowboys on Screen and Western Actors in the Arena,Jim Ryan,49.5,9780786424702,MTV,2006\r\n"
			+ "Science Fiction America - Essays On SF Cinema,,39.95,0786421495,MTV,2006\r\n"
			+ "\"Science Fiction Stars And Horror Heroes - Interviews with Actors, Directors, Producers and Writers of the 1940s-1960s\",Tom Weaver,21.95,0786428570,MTV,2006\r\n"
			+ "\"Science Fiction Television Series - Episode Guides, Histories, And Casts And Credits For 62 Prime-Time Shows, 1959 Through 1989 (Volumes 1 & 2)\",Mark Phillips & Frank Garcia,44.95,9780786428359,MTV,2006\r\n"
			+ "Selznick - The Man Who Produced Gone With The Wind,Bob Thomas,4.95,1893224244,MTV,2006\r\n"
			+ "Son of Guilty Pleasures of the Horror Film,,19.95,1887664041,MTV,2006\r\n"
			+ "\"Stewart, James - The Films and Career of James Stewart\",Tony Thomas,8.95,0806519533,MTV,2006\r\n"
			+ "Suicide in the Entertainment Industry,David K. Frasier,39.95,0786423331,MTV,2006\r\n"
			+ "They Started Talking,Frank Tuttle,16.95,1593930275,MTV,2006\r\n"
			+ "They'll Never Put That on the Air,Allan Neuwirth,16.95,1581154178,MTV,2006\r\n"
			+ "\"Three Stooges - One Fine Stooge: Fine, Larry Frizzy Life in Pictures\",Steve Cox & Jim Terry,24.95,1581823630,MTV,2006\r\n"
			+ "Twelve O'Clock High Logbook,Allan T. Duffin & Paul Matheis,-24.95,159393033X,MTV,2206\r\n"
			+ "Underdog - How Underdog was Born,Buck Biggers & Chet Stover,16.95,1593930259,MTV,2006\r\n"
			+ "\"Uneasy Dreams - The Golden Age of British Horror Films, 1956 - 1976\",Gary A. Smith,35.95,9780786426614,MTV,2006\r\n"
			+ "Virgin Film - Horror Films,James Marriott,5.95,0753509415,MTV,2006\r\n"
			+ "White Zombie - Anatomy of A Horror Film,Gary D. Rhodes,35.95,9780786427628,MTV,2006\r\n"
			+ "\"Women of The Western Frontier In Fact, Fiction And Film\",Ron Lackmann,35.95,9780786428458,MTV,2006\r\n"
			+ "A Hundred Years A Million Laughs - A Centennial Celebration of The Friars Club,Barry Dougherty,5.95,1578601614,NEB,2006\r\n"
			+ "Adulterers Anonymous,Marley Brant & Lydia Lunch & Exene Cervenka,4.95,0867194235,NEB,2006\r\n"
			+ "\"Burroughs, Edgar Rice - Burroughs Cyclopaedia\",Clark A. Brady,35.95,0786421231,NEB,2006\r\n"
			+ "Change Your Underwear Twice A Week - Lessons From The Golden Age Of Classroom Filmstrips,Danny Gregory,5.95,1579652638,NEB,2006\r\n"
			+ "Esquire - Things a Man Should Never Do Past 30,David Katz,7.95,1588164691,NEB,2006\r\n"
			+ "Firesign Theatre - Backwards into the Future: Recorded History of The Firesign Theatre,Frederick C. Wiebel Jr.,19.95,1593930437,NEB,2006\r\n"
			+ "Life at the Top - Inside New York's Grand Hotels,Ward Morehouse III,16.95,1593930348,NEB,2006\r\n"
			+ "Scandal! - Public Stories of Public Shame,Colin Wilson & Damon Wilson,4.95,1852279893,NEB,2006\r\n"
			+ "\"Sedgwick, Edie - Edie Factory Girl\",Nat Finkelstein & David Dalton,24.95,1576873463,NEB,2006\r\n"
			+ "Uleta Blues & Haikus,Paul Gleason,12.95,0894078146,NEB,2006\r\n"
			+ "\"Baseball - New York Giants Base Ball Club: The Growth of a Team and a Sport, 1870-1900\",James D. Hardy Jr.,26.95,0786427280,SSM,2006\r\n"
			+ "Baseball - Neyer / James Guide To Pitchers,Bill James & Rob Neyer,6.95,0743261585,SSM,2006\r\n"
			+ "Baseball - What Baseball Means to Me,,5.95,0446527491,SSM,2006\r\n"
			+ "Baseball - World Series: Opinonated Chronicle,Joseph Wallace,14.95,0810946394,SSM,2006\r\n"
			+ "Corvette - Definitive Guide To The All-American Sports Car,Andrew Montgomery,14.95,159223061X,TPA,2006\r\n"
			+ "Ferrari,Hartmut Lehbrink & Rainer W. Schlegelmilch,19.95,3895080764,TPA,2006\r\n"
			+ "Jaguar - Spirit of the Cat,Paul W. Cockerham,5.95,1577171225,TPA,2006\r\n"
			+ "Rolls-Royce Motor Car and the Bentley Since 1931,Anthony Bird & Ian Hallows & Brendan James,29.95,0713487496,TPA,2006\r\n"
			+ "Volkswagen - Beetle Mania or How I Learned to Stop Worrying and Love the Bug: History and Celebration of an Unlikely Icon,Alessandro Pasi,8.95,0312265247,TPA,2006\r\n"
			+ "");
		System.out.println(book + " created successfully.");
		b12.close();
}
if(book.equals("books2007.csv")) {
	b13 = new PrintWriter(book);
		b13.println("Blondie - The Bumstead Family History,Dean Young & Melena Ryzik,24.95,140160322X,CCB,2007\r\n"
			+ "Mad Magazine - Spy Vs. Spy: The Complete Casebook,Antonio Prohias,15.95,0823050211,BCC,2007\r\n"
			+ "Romance Pulp - 30 Postcards,,3.95,0811847225,CCB,2007\r\n"
			+ "Boys' Toys - Illustrated History of Little Things That Pleased Big Minds,Jed Novick,5.95,0452286840,HCB,2007\r\n"
			+ "Abba - Mamma Mia: How Can I Resist You? The Inside Story of Mamma Mia! And The Songs of ABBA,Benny Andersson & Bjorn Ulvaeus & Judy Craymer,24.95,0297844210,MRB,2007\r\n"
			+ "AC/DC - Let There Be Rock - The Story of AC/DC,Susan Masino,16.95,0825634695,MRB,2007\r\n"
			+ "After The Ball - Pop Music From Rag To Rock,Ian Whitcomb,13.95,087910063X,MRB,2007\r\n"
			+ "Alive At The Village Vanguard,Lorraine Gordon & Barry Singer,19.95,0634073990,MRB,2007\r\n"
			+ "American Troubadours - Groundbreaking Singer Songwriters of The 60s,Mark Brend,5.95,0879306416,MRB,2007\r\n"
			+ "Andrews Sisters - A Biography And Career Record,H. Arlo Nimmo,35.95,9780786432608,MRB,2007\r\n"
			+ "\"Barrett, Syd - Crazy Diamond\",Mike Watkinson & Pete Anderson,11.95,9781846097393,MRB,2007\r\n"
			+ "Beatles - Diary: An Intimate Day By Day History (Oversized Edition/Hardcover),Barry Miles,18.95,1572150106,MRB,2007\r\n"
			+ "Beatles - Fab Four FAQ,Stuart Shea & Robert Rodriguez,16.95,1423421388,MRB,2007\r\n"
			+ "Beatles - Meet The Beatles,Steven D. Stark,5.95,0060008938,MRB,2007\r\n"
			+ "\"Beatles - Meet The Beatles: A Cultural History of The Band That Shook Youth, Gender And The World\",Steven D. Stark,4.95,006000892X,MRB,2007\r\n"
			+ "Beatles - Press Reports,W. Fraser Sandercombe,15.95,9781894959612,MRB,2007\r\n"
			+ "Bebop - Third Ear: The Essential Listening Companion,Scott Yanow,5.95,0879306084,MRB,2007\r\n"
			+ "Billboard - Top 1000 Hits of the Rock Era 1955-2005,Joel Whitburn,11.95,1423409191,MRB,2007\r\n"
			+ "Billboard Pop Charts 1955-1959,Joel Whitburn,49.95,089820092X,MRB,2007\r\n"
			+ "\"Blakey, Art - Hard Bop Academy: Sidemen of Art Blakey And The Jazz Messengers\",Alan Goldsher,17.95,0634037935,MRB,2007\r\n"
			+ "Boogaloo - Quintessence of American Popular Music,Arthur Kempton,5.95,0375406123,MRB,2007\r\n"
			+ "\"Brown, James - I Feel Good: A Memoir of A Life of Soul\",James Brown & Marc Eliot,4.95,0451213939,MRB,2007\r\n"
			+ "Celtic Music - Third Ear: The Essential Listening Companion,,5.95,9780879306236,MRB,2007\r\n"
			+ "\"Charles, Ray - Birth of Soul\",Mike Evans,7.95,1846093414,MRB,2007\r\n"
			+ "Chess Records - Machers And Rockers,Rich Cohen,5.95,039305280X,MRB,2007\r\n"
			+ "\"Clark, Gene - Mr. Tambourine Man: The Life And Legacy of The Byrds' Gene Clark\",John Einarson,16.95,0879307935,MRB,2007\r\n"
			+ "Clash - Rock Retrospectives,Ray Lowry & Ben Myers,19.95,9781906283360,MRB,2007\r\n"
			+ "Classic Guitars Identification And Price Guide,Nick Freeth,16.99,0896895297,MRB,2007,\r\n"
			+ "Classic Rock Drummers (Includes Lessons CD),Ken Micallef & Donnie Marshall,16.95,0879309075,MRB,2007\r\n"
			+ "\"Collector's Guide To Heavy Metal, Volume 3: The Nineties [Includes Audio CD]\",Martin Popoff,24.95,9781894959629,MRB,2007\r\n"
			+ "\"Colonna, Jerry - Greetings, Gate! The Story of Professor Jerry Colonna\",Bob Colonna,21.95,1593930860,MRB,2007\r\n"
			+ "\"Copeland, Alan - Jukebox Saturday Nights\",Alan Copeland,24.95,1593931034,MRB,2007\r\n"
			+ "\"Coryell, Larry - Improvising: My Life In Music (Includes Audio CD)\",Larry Coryell,19.95,0879308265,MRB,2007\r\n"
			+ "Creedence Clearwater Revival - Bad Moon Rising: The Unauthorized History of Creedence Clearwater Revival,Hank Bordowitz,14.95,155652661X,MRB,2007\r\n"
			+ "Cure - Never Enough,Jeff Apter,7.95,0825673402,MRB,2007\r\n"
			+ "\"Darin, Bobby - Roman Candle\",David Evanier,4.95,1594860106,MRB,2007\r\n"
			+ "\"Davis, Miles - Running The Voodoo Down: The Electric Music of Miles Davis\",Philip Freeman,16.95,0879308281,MRB,2007\r\n"
			+ "\"Davis, Miles - The Miles Davis Reader: Interviews And Features From DownBeat Magazine\",,19.95,142343076X,MRB,2007\r\n"
			+ "Diary of A Mad Playwright,James Kirkwood,14.95,1557835675,MRB,2007\r\n"
			+ "Dirty Little Secrets Of The Record Business - Why So Much Music You Hear Sucks,Hank Bordowitz,19.95,1556526431,MRB,2007\r\n"
			+ "\"Discovering The Hudson - New York's Landmark Theatre From Broadway's Beginnings to Live Television, Jack Parr and Elvis\",Ward Morehouse III,16.95,1593931166,MRB,2007\r\n"
			+ "Duran Duran - Notorious: The Unauthorised Biography,Steve Malins,12.95,0233001751,MRB,2007\r\n"
			+ "Exploding,Stan Cornyn & Paul Scanlon,5.95,0380814773,MRB,2007\r\n"
			+ "Fleetwood Mac - Storms: My Life With Lindsey Buckingham And Fleetwood Mac,Carol Ann Harris,14.95,9781556527906,MRB,2007\r\n"
			+ "\"Franklin, Aretha - I Never Loved A Man The Way I Love You\",Matt Dobkin,4.95,0312318286,MRB,2007\r\n"
			+ "\"Garcia, Jerry - Jerry Garcia's Amazing Grace\",Deborah Koons Garcia,4.95,0060297107,MRB,2007\r\n"
			+ "\"Gershwin, George - The Memory of All That\",Joan Peyser,14.95,1423410254,MRB,2007\r\n"
			+ "Get Media Airplay,Rick Davis,9.95,1423413083,MRB,2007\r\n"
			+ "Getting Signed! - An Insider's Guide To The Record Industry,George Howard,17.95,0876390459,MRB,2007\r\n"
			+ "\"Gillespie, Dizzy - Dizzy: The Life And Times of John Birks Gillespie\",Donald L. Maggin,5.95,0060559217,MRB,2007\r\n"
			+ "\"Gillespie, Dizzy - Waiting For Dizzy: Fourteen Jazz Portraits\",Gene Lees,5.95,0815410379,MRB,2007\r\n"
			+ "\"Goodman, Steve - Facing The Music\",Clay Eals,23.95,1550227327,ABC,2007\r\n"
			+ "\"Green, Grant - Rediscovering The Forgotten Genius of Jazz Guitar\",Sharony Andrews Green,14.95,087930698X,MRB,2007\r\n"
			+ "\"Griffin, Merv - Merv: Making The Good Life Last\",Merv Griffin & David Bender,5.95,0743236823,MRB,2007\r\n"
			+ "Group Harmony: Echoes of the Rhythm & Blues Era,Todd R. Baptista,19.98,0977379833,MRB,2007\r\n"
			+ "\"Harrison, George - Harrison\",,9.95,0743235819,MRB,2007\r\n"
			+ "\"Harrison, George - I Me Mine\",George Harrison & Derek Taylor,14.95,0811859004,MRB,2007\r\n"
			+ "\"Hawes, Hampton - Raise Up Off Me: A Portrait of Hampton Hawes\",Hampton Hawes,4.95,1560253533,MRB,2007\r\n"
			+ "\"Hit Songs, 1900-1955: American Popular Music of The Pre-Rock Era\",Don Tyler,66.95,9780786429462,MRB,2007\r\n"
			+ "Hornes - An American Family,Gail Lumet Buckley,14.95,1557835640,MRB,2007\r\n"
			+ "House On Fire - The Rise And Fall of Philadelphia Soul,John A. Jackson,7.95,0195149726,MRB,2007\r\n"
			+ "How To Have Your Hit Song Published,Jay Warner,12.95,1423411994,MRB,2007\r\n"
			+ "\"Jansch, Bert - Dazzling Strange\",Colin Harper,12.95,0747587256,MRB,2007\r\n"
			+ "Kinks - A Very English Band,Neville Marten & Jeff Hudson,12.95,0825673518,MRB,2007\r\n"
			+ "Leaving The 20th Century - The Last Rites of Rock 'N' Roll,Dave Henderson & Howard Johnson,5.95,190279902X,MRB,2007\r\n"
			+ "\"Lerner, Al - Vamp 'Til Ready\",Al Lerner,12.95,1593930801,MRB,2007\r\n"
			+ "\"Lynne, Gloria - I Wish You Love: A Riveting Memoir From One of The Leading Ladies of Jazz\",Gloria Lynne & Karen Chilton,5.95,0312870310,MRB,2007\r\n"
			+ "\"Marley, Bob - Lyrical Genius\",Kwame Dawes,12.95,0825673526,MRB,2007\r\n"
			+ "\"McRae, Carmen - Miss Jazz\",Leslie Gourse,4.95,082307904X,MRB,2007\r\n"
			+ "\"Mellencamp, John - Born In A Small Town\",Heather Johnson,5.95,0825673364,MRB,2007\r\n"
			+ "Mom Have You Seen My Leather Pants?,Craig A. Williams,11.95,9780307342126,MRB,2007\r\n"
			+ "\"Morrison, Jim - Angels Dance And Angels Die\",,14.95,0825673410,MRB,2007\r\n"
			+ "\"Morrison, Jim - Jim Morrison: Life, Death, Legend\",Stephen Davis,4.95,159240099X,MRB,2007,\r\n"
			+ "\"Morrison, Van - Can You Feel The Silence?\",Clinton Heylin,22.95,1556525176,MRB,2007\r\n"
			+ "\"Morrow, Cousin Brucie - Doo Wop: The Music, The Times, The Era [Author-Signed Edition + Bonus CD]\",Bruce Morrow & Rich Maloof,19.95,9781402742767,MRB,2007\r\n"
			+ "Night Moves - Pop Music In The Late '70s,Don Breithaupt & Jeff Breithaupt,4.95,0312198213,MRB,2007\r\n"
			+ "\"On The Short Waves, 1923 - 1945 - Broadcast Listening In The Pioneer Days of Radio\",Jerome S. Berg,35.95,9780786430291,MRB,2007\r\n"
			+ "Original Marvelettes - Motown's Mystery Girl Group,Marc Taylor,12.95,0965232859,MRB,2007\r\n"
			+ "\"Page, Jimmy - Magus, Musician, Man: An Unauthorized Biography\",George Case,19.95,1423404076,MRB,2007\r\n"
			+ "\"Paxton, Tom - Honor of Your Company\",Milton Okun,13.95,1575601443,MRB,2007\r\n"
			+ "Pink Floyd - Early Years,Barry Miles,17.95,1846094445,MRB,2007\r\n"
			+ "\"Puente, Tito - King of Latin Music (Includes Bonus DVD)\",Jim Payne,17.95,1423413350,MRB,2007\r\n"
			+ "\"Radio Network Prime Time Programming, 1926 - 1967\",Mitchell E. Shapiro,66.95,9780786431090,MRB,2007\r\n"
			+ "Ramones - On The Road With The Ramones,Monte A. Melnick & Frank Meyer,19.95,9781847721037,MRB,2007\r\n"
			+ "\"Reed, Jimmy - Big Boss Man: The Life And Music of Jimmy Reed\",Will Romano,14.95,0879308788,MRB,2007\r\n"
			+ "\"Reinhardt, Django - Django: The Life And Music of A Gypsy Legend\",Michael Dregni,7.95,019516752X,MRB,2007\r\n"
			+ "Rockabilly Legends - They Called It Rockabilly Long Before They Called It Rock And Roll (Includes Bonus DVD),Jerry Naylor & Steve Halliday,26.95,142342042X,MRB,2007\r\n"
			+ "Rolling Stones - Origin of The Species,Alan Clayson,15.95,9781842403891,MRB,2007\r\n"
			+ "\"Rollins, Sonny - Definitive Musical Guide\",Peter Niklas Wilson,5.95,1893163067,MRB,2007\r\n"
			+ "Sex Pistols - 90 Days At EMI,Brian Southall,9.95,9781846097799,MRB,2007\r\n"
			+ "\"Shearing, George - Lullaby of Birdland: The Autobiography of George Shearing\",George Shearing & Alyn Shipton,5.95,0826417248,MRB,2007\r\n"
			+ "\"Sissle, Noble - Reminiscing With Noble Sissle And Eubie Blake\",Robert Kimball & William Bolcom,5.95,081541045X,MRB,2007\r\n"
			+ "Sound and the Fury: 40 Years of Classic Rock Journalism,Barney Hoskins & Barney Hoskyns,5.95,1582342822,MRB,2007\r\n"
			+ "\"Springfield, Dusty - The Complete Dusty Springfield [Revised And Expanded]\",Paul Howes,29.95,1905287399,MRB,2007\r\n"
			+ "Standells - Love That Dirty Water! The Standells And The Improbable Red Sox Victory Anthem,Chuck Burgess & Bill Nowlin,12.95,1579401465,MRB,2007\r\n"
			+ "\"Streisand, Barbra - Barbra: The Way She Is\",Christopher Andersen,7.95,0060562560,MRB,2007\r\n"
			+ "\"Swing City - Newark Nightlife, 1925-50\",Barbara J. Kukla,7.95,0813531160,MRB,2007\r\n"
			+ "Tennessee Music - Its People And Places,Peter Coats Zimmerman,5.95,0879305339,MRB,2007\r\n"
			+ "The Pilgrim Jubilees,Alan Young,5.95,1578064163,MRB,2007\r\n"
			+ "\"Thorgeson, Storm - Taken By Storm: The Album Art of Storm Thorgeson\",Storm Thorgeson & Peter Curzon,19.95,1846096677,DEF,2007\r\n"
			+ "Touch of Classic Soul 1 - Soul Singers of The Early 1970s,Marc Taylor,11.95,0965232840,MRB,2007\r\n"
			+ "\"Touch of Classic Soul 2, A - Soul Singers of The Late 1970s\",Marc Taylor,11.95,0965232875,MRB,2007\r\n"
			+ "Troggs - Rock's Wild Things - Troggs Files,Alan Clayson & Jacqueline Ryan,4.95,1900924196,MRB,2007\r\n"
			+ "\"Tubb, Ernest - The Texas Troubadour\",Ronnie Puch,5.95,0822321904,MRB,2007\r\n"
			+ "Unknown Legends of Rock'N'Roll (Includes Audio CD),Richie Unterberger,16.95,0879305347,MRB,2007\r\n"
			+ "Velvet Underground - The Rough Guide To The Velvet Underground,Peter Hogan,12.95,9781843535881,MRB,2007\r\n"
			+ "\"Waits, Tom - Many Lives of Tom Waits, The\",Patrick Humphries,19.95,184449585X,MRB,2007\r\n"
			+ "\"Wilson, Brian - Smile\",Domenic Priore,11.95,,MRB,2007\r\n"
			+ "\"Womack, Bobby - Midnight Mover: My Autobiography\",Bobby Womack,19.95,1844541487,MRB,2007\r\n"
			+ "Mas! Cine Mexicano - Sensational Mexican Movie Posters 1957-1990,Rogelio Agrasanchez Jr.,17.95,0811854493,MTV,2007\r\n"
			+ "101 Movies To Avoid,Allan Smithee,12.95,1905736061,MTV,2007\r\n"
			+ "\"Agar, John - On The Good Ship Hollywood\",John Agar & L.C. Van Savage,12.95,1593930682,MTV,2007\r\n"
			+ "\"Allen, Woody - Pocket Essential Woody Allen (3rd Edition)\",Martin Fitzgerald,2.95,1903047056,MTV,2007\r\n"
			+ "\"Alley, Kirstie - How To Lose Your Ass And Regain Your Life: Reluctant Confessions of a Big-Butted Star\",Kirstie Alley,5.95,159486232X,MTV,2007\r\n"
			+ "Amazing Transforming Superhero,Terrence Wandtke,30.95,9780786431892,MTV,2007\r\n"
			+ "\"Ameche, Don - The Kenosha Comeback Kid [Author-Signed]\",Ben Ohmart,19.95,1593930453,MTV,2007\r\n"
			+ "American Gothic - Sixty Years of Horror Cinema,Jonathan Rigby,19.95,1905287259,MTV,2007\r\n"
			+ "American Science Fiction Television Series of The 1950S - Episode Guides And Casts And Credits For Twenty Shows,Patrick Lucanio & Gary Coville,35.95,9780786431014,MTV,2007\r\n"
			+ "\"Autry, Gene - His Life And Career\",Don Cusic,44.95,9780786430611,MTV,2007\r\n"
			+ "Balboa Films - A History And Filmography of The Silent Film Studio,Jean-Jacques Jura & Rodney Norman Bardin II,35.95,9780786430987,MTV,2007\r\n"
			+ "\"Ball, Lucille - For The Love of Lucy - Complete Guide For Collectors And Fans\",Ric B. Wyman,14.95,0789200066,MTV,2007\r\n"
			+ "\"Barris, Chuck - Confessions of A Dangerous Mind: An Unauthorized Autobiography\",Chuck Barris,3.95,0786888083,MTV,2007\r\n"
			+ "\"Baxter, Dick - Standing Tall In The Shadows: Memoirs of A Hollywood Manager\",Dick Baxter,16.95,9781593931056,MTV,2007\r\n"
			+ "Beyond Horror Holocaust - A Deeper Shade Of Red,Chas. Balun & Paul Spinrad,19.95,1888214082,MTV,2007\r\n"
			+ "Black Comedians On Black Comedy,Darryl Littleton,24.95,1557836809,MTV,2007\r\n"
			+ "Booking Hawaii Five-O: An Episode Guide And Critical History of The 1968-1980 Television Detective Series,Karen Rhodes,30.95,9780786431083,MTV,2007\r\n"
			+ "\"Buchanan, Larry - Films Of\",Rob Craig,35.95,9780786429820,MTV,2007\r\n"
			+ "\"Burton, Tim - An Unauthorized Biography of The Filmmaker\",Ken Hanke,4.95,1580631622,MTV,2007\r\n"
			+ "\"Byrnes, Edd - Kookie No More\",Edd Byrnes & Marshall Terrill,4.95,1569800928,MTV,2007\r\n"
			+ "\"Caesar, Sid - Caesar's Hours: My Life In Comedy, With Love And Laughter\",Sid Caesar,5.95,1586482831,MTV,2007\r\n"
			+ "Celluloid Adventures,Nicholas Anez,19.95,9781887664714,MTV,2007\r\n"
			+ "\"Chaney, Lon - Films of Lon Chaney, The\",Michael F. Blake,5.95,1879511266,MTV,2007\r\n"
			+ "Chicago TV Horror Movie Shows - From Shock Theatre To Svengoolie,Ted Okuda & Mark Yurkiw,14.95,9781893121133,MTV,2007\r\n"
			+ "\"Christmas At The Movies - Images of Christmas In American, British And European Cinema\",Mark Connelly,6.95,1860643973,MTV,2007\r\n"
			+ "\"Classic Cliffhangers, Volume 1: 1914-1940\",Hank Davis,19.95,9781887664769,MTV,2007\r\n"
			+ "Columbia Checklist 1922-1988 (2-Volume Set),Len D. Martin,44.95,9780786431038,MTV,2007\r\n"
			+ "\"Cowell, Simon - I Don't Mean To Be Rude, But...\",Simon Cowell,4.95,0767917413,MTV,2007\r\n"
			+ "\"Craven, Wes - Screams & Nightmares: The Films of Wes Craven\",Brian J. Robb,5.95,1585670901,MTV,2007\r\n"
			+ "Critical History And Filmography of Toho's Godzilla Series,David Kalat,30.95,9780786430994,MTV,2007\r\n"
			+ "Critical History of Doctor Who On Television,John Kenneth Muir,26.96,9780786437160,MTV,2007\r\n"
			+ "\"Davis, Bette - Girl Who Walked Alone\",Charlotte Chandler,5.95,0743262085,MTV,2007\r\n"
			+ "\"Diller, Phyllis - Like A Lampshade In A Whorehouse: My Life In Comedy\",Phyllis Diller & Richard Buskin,5.95,1585424765,MTV,2007\r\n"
			+ "Dimensions Behind The Twilight Zone,Stewart T. Stanyard,19.95,1550227440,MTV,2007\r\n"
			+ "Dionne Quintuplets - The Films of The Dionne Quintuplets,Phil Talbot,11.95,9781593930974,MTV,2007\r\n"
			+ "Distant Technology - Science Fiction Film And The Machine Age,J.P. Telotte,5.95,0819563463,MTV,2007\r\n"
			+ "Doctor Who - Greatest Show In The Galaxy: The Discerning Fan's Guide,March Schuster & Tom Powers,30.95,9780786432769,MTV,2007\r\n"
			+ "\"Elliott, Wild Bill - A Complete Filmography\",Gene Blottner,49.5,9780786429868,MTV,2007\r\n"
			+ "English Gothic - A Century of Horror Cinema,Jonathan Rigby,19.95,1905287364,MTV,2007\r\n"
			+ "Escape! - How Animation Broke Into The Mainstream In The 1990s,G. Michael Dobbs,16.95,1593931107,MTV,2007,\r\n"
			+ "Eye On Science Fiction - 20 Interviews With Classic Sf And Horror Filmmakers,Tom Weaver,35.95,9780786430284,MTV,2007\r\n"
			+ "Film Clowns of The Depression,Wes D. Gehring,39.95,9780786428922,MTV,2007\r\n"
			+ "Film Noir Reader 1,Alain Silver & James Ursini,17.95,0879101970,MTV,2007\r\n"
			+ "Film Noir Reader 2,Alain Silver & James Ursini,13.95,0879102802,MTV,2007\r\n"
			+ "Film Noir Reader 3,Robert Porfirio & Alain Silver & James Ursini,15.95,0879109610,MTV,2007\r\n"
			+ "Film Noir Reader 4,Alain Silver & James Ursini,15.95,0879103051,MTV,2007\r\n"
			+ "\"Florence Lawrence, The Biograph Girl - America's First Movie Star\",Kelly R. Brown,30.95,9780786430895,MTV,2007\r\n"
			+ "Forgotten Horrors 4: Dreams That Money Can Buy,Michael H. Price & John Wooley,17.95,1887664734,MTV,2007\r\n"
			+ "\"Frankenheimer, John - Pictures About Extremes: The Films of John Frankenheimer\",Stephen B. Armstrong,31.95,9780786431458,MTV,2007\r\n"
			+ "Gangster Film: The Overlook Film Encyclopedia,,14.95,0879518812,MTV,2007\r\n"
			+ "Glamour Girls of The Sixties,Tom Lisanti,39.95,9780786431724,MTV,2007\r\n"
			+ "\"Glut, Don - I Was A Teenage Movie Maker: The Book\",Don Glut,26.96,9780786430413,MTV,2007\r\n"
			+ "\"Good Night, Whatever You Are!: My Journey with Zacherley, The Cool Ghoul\",Richard Scrivani,500,1933384034,MTV,2007\r\n"
			+ "\"Hanks, Tom - The Films of Tom Hanks\",Lee Pfeiffer & Michael Lewis,5.95,0806517174,MTV,2007\r\n"
			+ "\"Hepburn, Audrey - The Paramount Years\",Tony Nourmand,18.95,0811858022,MTV,2007\r\n"
			+ "\"Hilton, Daisy & Violet - Lives And Loves of Daisy And Violet Hilton\",Dean Jensen,16.95,1580087582,MTV,2007\r\n"
			+ "Hollywood Book of Love,James Robert Parish,4.95,0071402802,MTV,2007\r\n"
			+ "Horror Poster Art,Tony Nourmand & Graham Marsh,24.95,1845130103,MTV,2007\r\n"
			+ "How To Succeed In Hollywood Without Really Trying (P.S. You Can't!),Melville Shavelson,19.95,1593930666,MTV,2007\r\n"
			+ "How To Survive A Horror Movie,Seth Grahame-Smith,12.95,1594741794,MTV,2007\r\n"
			+ "\"Hunter, Tab - Confidential: The Making of a Movie Star\",Tab Hunter with Eddie Muller,6.95,1565124669,MTV,2007\r\n"
			+ "I Led Three Lives - The True Story of Herbert A Philbrick's Television Program,Martin Grams Jr.,19.95,1593930925,MTV,2007\r\n"
			+ "\"Incorrect Entertainment: Or, Trash From The Past: A History of Political Incorrectness And Bad Taste In 20th Century American Popular Culture\",Anthony Slide,16.95,9781593930936,MTV,2007\r\n"
			+ "\"Influence of Star Trek On Television, Film And Culture, The\",,31.95,9780786430345,MTV,2007\r\n"
			+ "It Came From The Drive-In,,3.95,0743493168,MTV,2007\r\n"
			+ "\"Japanese Science Fiction, Fantasy and Horror Films - A Critical Analysis and Filmography of 103 Features Released in the United States, 1950-1992\",Stuart Galbraith IV,30.95,0786421266,MTV,2007\r\n"
			+ "\"Kaufman, Lloyd - Make Your Own Damn Movie: Secrets of A Renegade Director\",Lloyd Kaufman & Adam Jahnke & Trent Haaga,5.95,0312288646,MTV,2007\r\n"
			+ "\"Keitel, Harvey - Movie Top Ten\",Jack Hunter,4.95,1871592879,MTV,2007\r\n"
			+ "\"Krofft, Sid And Marty - A Critical Study of Saturday Morning Children's Television, 1969 - 1993\",Hal Erickson,30.95,9780786430932,MTV,2007\r\n"
			+ "\"Kubrick, Stanley - And The Art of Adaptation - Three Novels, Three Films\",Greg Jenkins,30.95,9780786430970,MTV,2007\r\n"
			+ "\"Lee, Anna - Memoir of A Career On General Hospital In Film\",Anna Lee & Barbara Roisman Cooper,30.95,9780786431618,MTV,2007\r\n"
			+ "\"Letterman, David - The Letterman Wit: His Life And Humor\",Bill Adler,2.95,0786702109,MTV,2007\r\n"
			+ "\"Lugosi, Bela: Dreams and Nightmares\",Gary D. Rhodes & Richard Sheffield,24.95,,MTV,2007\r\n"
			+ "\"MacMurray, Fred - Biography\",Charles Tranberg,19.95,1593930992,MTV,2007\r\n"
			+ "\"Marx, Bill - Son of Harpo Speaks!\",Bill Marx,19.95,1593930623,MTV,2007\r\n"
			+ "\"Mercer, Jack - He Am What He Am! The Voice of Popeye\",Fred M. Grandinetti,16.95,1593930968,MTV,2007\r\n"
			+ "Mexican Cinema of Darkness,Doyle Greene,30.95,9780786429998,MTV,2007\r\n"
			+ "Midnight Marquee (Issue #75),,7.95,,MTV,2007\r\n"
			+ "\"Mikels, Ted V. - Independent Cinema of Ted V. Mikels\",Christopher Wayne Curry,44.95,9780786432370,MTV,2007\r\n"
			+ "Missing Reels - Lost Films of American And European Cinema,Harry Waldman,44.95,9780786437771,MTV,2007\r\n"
			+ "\"Mitchum, Robert - In His Own Words\",Jerry Roberts,18.95,0879102926,MTV,2007\r\n"
			+ "\"Moreland, Mantan - Mantan the Funnyman: The Life and Times of Mantan Moreland\",Michael H. Price,29.95,188766470X,MTV,2007\r\n"
			+ "\"Mummy In Fact, Fiction And Film\",Susan D. Cowie & Tom Johnson,35.95,9780786431144,MTV,2007\r\n"
			+ "Mummy Unwrapped - Scenes Left On Universal's Cutting Room Floor,Thomas M. Feramisco,35.95,9780786437344,MTV,2007\r\n"
			+ "New Queer Aesthetic On Television,James R. Keller & Leslie Stratnyer,29.95,0786423900,MTV,2007\r\n"
			+ "\"Payton, Barbara - Kiss Tomorrow Goodbye\",John O'Dowd,24.95,1593930631,MTV,2007\r\n"
			+ "\"Peckinpah, Sam - Portrait In Montage\",Garner Simmons,13.95,087910273X,MTV,2007\r\n"
			+ "\"Picerni, Paul - Steps To Stardom\",Paul Picerni & Tom Weaver,19.95,1593930828,MTV,2007\r\n"
			+ "Politics And The American Television Comedy,Doyle Greene,30.95,9780786432356,MTV,2007\r\n"
			+ "\"Presley, Elvis - Silver Screen Icon\",Steve Templeton,39.95,1570722323,MTV,2007\r\n"
			+ "Pulp Fiction - A Quentin Tarantino Screenplay,Quentin Tarantino,2.95,0786881046,MTV,2007\r\n"
			+ "Remington Steele - Steele Loved After All These Years: A Remington Steele Retrospective,Judith A. Moose,29.95,9781593930981,MTV,2007\r\n"
			+ "Rock & Roll Film Encyclopedia,John Kenneth Muir,16.95,,MTV,2007\r\n"
			+ "\"Route 66 - The Television Series, 1960-1964 [Author-Signed Edition]\",James Rosin,16.95,0972868429,MTV,2007\r\n"
			+ "\"Schwarzenegger, Arnold - Fantastic: The Life of Arnold Schwarzenegger\",Laurence Leamer,4.95,0312333382,MTV,2007\r\n"
			+ "\"Scott, Randolph - Films of Randolph Scott\",Robert Nott,35.95,9780786437597,MTV,2007\r\n"
			+ "\"Scott, Ridley - Ridley Scott Close Up: The Making of His Movies\",Paul M. Sammon,4.95,1560252030,MTV,2007\r\n"
			+ "\"Sellers, Peter - Mr. Strangelove: A Biography of Peter Sellers\",Ed Sikov,3.95,0786885815,MTV,2007\r\n"
			+ "\"Sennett, Mack - Keystone: The Life And Clowns of Mack Sennett\",Simon Louvish,7.95,057121276X,MTV,2007\r\n"
			+ "Serial Killer Cinema - An Analytical Filmography With An Introduction,Robert Cettl,44.95,9780786437313,MTV,2007\r\n"
			+ "Sherlock Holmes On Screen,Alan Barnes,24.95,1905287240,MTV,2007\r\n"
			+ "\"Sopranos, The - A Sitdown With The Sopranos\",,3.95,0312295286,MTV,2007\r\n"
			+ "\"Sothern, Ann - Cordially Yours, Ann Sothern\",Colin Briggs,19.95,1593930607,MTV,2007\r\n"
			+ "\"Sports Cinema - 100 Movies: The Best of Hollywood's Athletic Heroes, Losers, Myths And Misfits\",Randy Williams,16.95,0879103310,MTV,2007\r\n"
			+ "\"Stewart, James - Behind The Scenes of A Wonderful Life\",Lawrence J. Quirk,13.95,1557834164,MTV,2007\r\n"
			+ "Stoogeology - Essays On The Three Stooges,Peter Seely & Gail W. Pieper,30.95,9780786429202,MTV,2007\r\n"
			+ "Stop - Motion Filmography - A Critical Guide To 297 Features Using Puppet Animation,Neil Pettigrew,66.95,9780786431076,MTV,2007\r\n"
			+ "\"Superman On Film, Television, Radio And Broadway\",Christopher Wayne Curry & Bruce Scivally,44.95,9780786431663,MTV,2007\r\n"
			+ "Tales From The Cult Film Trenches,Louis Paul,30.95,9780786429943,MTV,2007\r\n"
			+ "Television Fright Films of The 1970s,David Deal,44.95,9780786429295,MTV,2007\r\n"
			+ "\"Television Specials - 3,201 Entertainment Spectaculars, 1939 Through 1993\",Vincent Terrace,44.95,9780786437733,MTV,2007\r\n"
			+ "Television Variety Shows - Histories and Episode Guides to 57 Programs,David M. Inman,67.95,0786421983,MTV,2007\r\n"
			+ "\"Television Western Players of The Fifties - A Biographical Encyclopedia of All Regular Cast Members In Western Series, 1949 - 1959\",Everett Aaker,44.95,9780786430871,MTV,2007\r\n"
			+ "\"The Incredible World of Spy-Fi - Wild And Crazy Spy Gadgets, Props And Artifacts From TV And The Movies\",Danny Biederman,7.95,081184224X,MTV,2007\r\n"
			+ "\"Trumbo, Dalton - Hollywood Rebel - A Critical Survey And Filmography\",Peter Hanson,35.95,9780786432462,MTV,2007\r\n"
			+ "\"Tsuburaya, Eiji - Master of Monsters\",August Ragone,29.95,0811860787,MTV,2007\r\n"
			+ "Twilight Zone - Unlocking The Door To A Television Classic,Martin Grams Jr.,24.95,9780970331090,MTV,2007\r\n"
			+ "\"Universal - International Westerns, 1947 - 1963 - The Complete Filmography\",Gene Blottner,35.95,9780786430888,MTV,2007\r\n"
			+ "\"Universal Horrors - The Studio's Classic Films, 1931-1946 (2nd Edition)\",Michael Brunas & John Brunas & Tom Weaver,49.5,9780786429745,MTV,2007\r\n"
			+ "\"Warners Wiseguys - All 112 Films That Robinson, Cagney And Bogart Made For The Studio\",Scott Allen Nollen,49.5,9780786432622,MTV,2007\r\n"
			+ "\"Wayne, John - The John Wayne Filmography\",Fred Landesman,39.95,9780786432523,MTV,2007\r\n"
			+ "\"Way-Out, Wonderful World of Horror, Fantasy And Sci-Fi Trivia\",Keith Hedges,12.95,1887664750,MTV,2007\r\n"
			+ "\"Welles, Orson - Stories of His Life\",Peter Conrad,5.95,057121164X,MTV,2007\r\n"
			+ "Who Framed Boris Karloff?,Dwight Kemper,17.95,1887664742,MTV,2007\r\n"
			+ "\"Wilder, Billy - Nobody's Perfect: A Personal Biography\",Charlotte Chandler,13.95,1557836329,MTV,2007\r\n"
			+ "Women Who Made Television Funny,David C. Tucker,35.95,9780786429004,MTV,2007\r\n"
			+ "\"Year of Fear, A - A Day-By-Day Guide To 366 Horror Films\",Bryan Senn,30.95,9780786431960,MTV,2007\r\n"
			+ "\"Amram, David - Offbeat: Collaborating With Kerouac\",David Amram,5.95,1560254602,NEB,2007\r\n"
			+ "Big Hair,James Innes-Smith,4.95,1582344493,NEB,2007\r\n"
			+ "Big Shots - The Men Behind The Booze,A.J. Baime & A. J. Baime,4.95,045120980X,NEB,2007\r\n"
			+ "\"Cocktail Shakers, Lava Lamps, And Tupperware - Celebration of Lifestyle Design From The Last Half of The 20th Century\",,6.95,1592530451,NEB,2007\r\n"
			+ "Encyclopedia of Guilty Pleasures - 1001 Things You Hate To Love,Sam Stall & Lou Harry & Julia Spalding,11.95,1931686548,NEB,2007\r\n"
			+ "Human Monsters - An Illustrated Encyclopedia of The World's Most Vicious Murderers,David Everitt,5.95,0809239949,NEB,2007\r\n"
			+ "\"Lee, Gypsy Rose - My G-String Mother: At Home And Backstage With Gypsy Rose Lee\",Erik Lee Preminger,5.95,1583940960,NEB,2007\r\n"
			+ "Pees On Earth,Erica Jong & Annie Sprinkle,24.95,157687317X,NEB,2007\r\n"
			+ "Photo Oops - Photographic Opportunities Gone Awry,Hal Buell,2.49,1579123775,NEB,2007\r\n"
			+ "Rapp Joke File,Philip Rapp,12.95,,NEB,2007\r\n"
			+ "Spy File - International Spy Museum,,4.95,1579123627,NEB,2007\r\n"
			+ "Tiki Road Trip - A Guide To Tiki Culture In North America,James Teitelbaum,14.95,1595800190,NEB,2007\r\n"
			+ "Up Against The Wall - International Poster Design,Russell Bestley & Ian Noble,11.95,2880465613,NEB,2007\r\n"
			+ "\"Warhol, Andy & Makos, Christopher - Warhol / Makos In Context\",Christopher Makos,39.95,9781576873311,NEB,2007\r\n"
			+ "\"Baby Snooks Scripts, Volume 2\",Philip Rapp,15.95,9781593930943,OTR,2007\r\n"
			+ "Radio Adventures of Sam Spade,Martin Grams Jr.,17.95,097033107X,OTR,2007\r\n"
			+ "Railroad Hour - A History of The Radio Series,Gerald Wilson & Martin Grams Jr.,21.95,9781593930646,OTR,2007\r\n"
			+ "Story of Twenty Questions - A Novelized Memoir,Robert VanDeventer,19.95,9781593930776,OTR,2007\r\n"
			+ "Tales Well Calculated To Keep You In Suspense,Darryl Shelton,19.95,9781593930882,OTR,2007\r\n"
			+ "\"Baseball - Aaron, Hank and the Home Run That Changed America\",Tom Stanton,4.95,0060722908,SSM,2007\r\n"
			+ "Baseball - Baseball Over The Air,Tony Silvia,35.95,9780786430666,SSM,2007\r\n"
			+ "\"Boxing - Ali, Muhammad: The Soul of A Butterfly\",Muhammad Ali & Hana Yasmeen Ali,4.95,0743255690,SSM,2007\r\n"
			+ "");
		System.out.println(book + " created successfully.");
		b13.close();
}
if(book.equals("books2008.csv")) {
	b14 = new PrintWriter(book);
		b14.println("Forbidden Animation - Censored Cartoons And Blacklisted Animators In America,Karl F. Cohen,24.95,0786420324,CCB,2008\r\n"
			+ "\"Reardon, Francis - Whistling Down the Halls: The Times and Cartoons of America's Original Pantomime Comic Strip Artist\",Laurence S. Cutler & Judy Goffman Cutler & Michael T. Reardon,20.95,1593931492,CCB,2008\r\n"
			+ "\"Firman, Pete - Tricks to Freak Out Your Friends\",Pete Firman,12.95,9781556526954,HCB,2008\r\n"
			+ "\"Great Monster Magazines - 1950s, 1960s, and 1970s\",Robert Michael Bobb Cotter,39.95,9780786433896,HCB,2008\r\n"
			+ "20th Century Rock And Roll - Progressive Rock,Jerry Lucky,11.95,1896522203,MRB,2008\r\n"
			+ "AC/DC - The Encyclopaedia,Malcolm Dome & Jerry Ewing,16.95,9781842404362,MRB,2008\r\n"
			+ "\"Airwaves of New York - Illustrated Histories of 156 Am Stations In The Metropolitan Area, 1921 - 1996\",Bill Jaker & Frank Sulek & Peter Kanze,30.95,9780786438723,MRB,2008\r\n"
			+ "\"Allman, Duane - Skydog: The Duane Allman Story (Updated and Expanded)\",Randy Poe,14.95,9780879309398,MRB,2008\r\n"
			+ "American Big Bands,William F. Lee,16.95,0634080547,MRB,2008\r\n"
			+ "\"Assoluta Voice In Opera, 1797-1847\",Geoffrey S. Riggs,44.95,9780786440771,MRB,2008\r\n"
			+ "\"Ballard, Florence - The Lost Supreme: The Life of Dreamgirl Florence Ballard\",Peter Benjaminson,19.95,9781556527050,MRB,2008\r\n"
			+ "\"Barnet, Charlie - An Illustrated Biography and Discography\",Don Mather,35.95,9780786437610,MRB,2008\r\n"
			+ "Beach Boys - Wouldn't it be Nice: Brian Wilson and the Making of the Beach Boys' Pet Sounds,Charles Granata,12.95,1556525079,MRB,2008\r\n"
			+ "Beatles - Beatles Gear,Andy Babiuk,24.95,0879307315,MRB,2008\r\n"
			+ "Beatles - Beatles On Television (Rex Collections),Jeff Bench & Ray Tedman,34.95,9781905287512,MRB,2008\r\n"
			+ "Beatles - The Unreleased Beatles: Music & Film,Richie Unterberger,26.95,9780879308926,MRB,2008\r\n"
			+ "Billboard - Hot Dance/Disco 1974-2003,Joel Whitburn,37.95,089820156X,RFG,2008\r\n"
			+ "Billboard - Top Country Songs 1944-2005,Joel Whitburn,49.95,0898201659,MRB,2008\r\n"
			+ "Billboard Top Adult Songs 1961-2006,Joel Whitburn,39.95,0898201691,MRB,2008\r\n"
			+ "Bjork - Wow And Flutter,Mark Pytlik,14.95,1550225561,MRB,2008\r\n"
			+ "\"Blues - NPR Curious Listener's Guide To Blues, The\",David Evans,5.95,039953072X,MRB,2008\r\n"
			+ "\"Bush, Kate - The Illustrated Collector's Guide To Kate Bush (2nd Edition)\",Robert Godwin,14.95,1894959450,MRB,2008\r\n"
			+ "Cartoon Music Book,,16.95,1556524730,MRB,2008\r\n"
			+ "\"Cassidy, David - Could it be Forever? My Story\",David Cassidy,20.95,9780755315796,MRB,2008\r\n"
			+ "Chasin' That Devil Music - Searching For The Blues [With Bonus CD],Gayle Dean Wardlow,14.95,9780879305529,MRB,2008\r\n"
			+ "\"Cline, Patsy - Honky Tonk Angel: The Intimate Story of Patsy Cline\",Ellis Nassour,14.95,9781556527470,MRB,2008\r\n"
			+ "\"Des Barres, Pamela - Let's Spend The Night Together\",Pamela Des Barres,14.95,9781556527890,MRB,2008\r\n"
			+ "\"Des Barres, Pamela - Take Another Little Piece of My Heart\",Pamela Des Barres,12.95,9781556528002,MRB,2008\r\n"
			+ "\"Dictionary of Music Titles - The Origins of The Names And Titles of 3,500 Musical Compositions\",Adrian Room,35.95,9780786438907,MRB,2008\r\n"
			+ "\"Doors, The - The Doors Revisited: The Fire Still Burns\",John Lydon & Keith Zimmerman & Kent Zimmerman & Doug Sundling,13.95,9780825673542,MRB,2008\r\n"
			+ "\"Dylan, Bob - Forever Young\",Douglas R. Gilbert,6.95,9780306815164,MRB,2008\r\n"
			+ "\"Dylan, Bob - Rolling Thunder Logbook\",Sam Shepard,5.95,1860746403,MRB,2008\r\n"
			+ "\"Dylan, Bob - The Songs He Didn't Write: Bob Dylan Under The Influence\",Derek Barker,18.95,9781842404249,MRB,2008\r\n"
			+ "\"Encyclopedia of American Radio, 1920 - 1960\",Luther F. Sies,175.5,9780786429424,MRB,2008\r\n"
			+ "\"Encyclopedia of Dead Rock Stars: Heroin, Handguns, and Ham Sandwiches\",Jeremy Simmonds,18.95,9781556527548,MRB,2008\r\n"
			+ "\"Falcone, Vincent - Frankly Just Between Us: My Life Conducting Sinatra's Music\",Vincent Falcone & Bob Popyk,19.95,063409498X,MRB,2008\r\n"
			+ "\"Flick, Vic - Guitarman: From James Bond to The Beatles and Beyond\",Vic Flick,16.95,1593933088,MRB,2008\r\n"
			+ "\"Florida's Famous & Forgotten: The Illustrated Encyclopedia of Florida's Rock, Soul & Dance, The First Thirty Years (1955-1985) [Volumes 1&2]\",Kurt K.O.T.O. Curtis,39.95,0976306247,MRB,2008\r\n"
			+ "\"Floyd, Pink - Mind Over Matter 4: The Images of Pink Floyd [Fourth Edition]\",Storm Thorgerson & Peter Curzon,24.95,9781846097638,MRB,2008\r\n"
			+ "\"Glass, Philip - Glass: A Portrait\",Robert Maycock,4.95,1860743471,MRB,2008\r\n"
			+ "Goldmine Records & Prices (4th Edition),Peter Lindblad,16.99,0896896196,MRB,2008\r\n"
			+ "\"Goldmine Standard Catalog of American Records, 1950-1975 (6th Edition)\",Tim Neely,33.99,0896896609,MRB,2008\r\n"
			+ "\"Grappelli, Stephane - Stephane Grappelli: With and Without Django\",Paul Balmer,6.95,1860744532,MRB,2008\r\n"
			+ "Great Radio Audience Participation Shows - Seventeen Programs From The 1940S And 1950S,Jim Cox,35.95,9780786440467,MRB,2008\r\n"
			+ "Great Radio Soap Operas,Jim Cox,35.95,9780786438655,MRB,2008\r\n"
			+ "Guerrilla Home Recording: How to Get Great Sound From Any Studio (Second Edition),Karl Coryat,19.95,9781423454465,MRB,2008\r\n"
			+ "Halfway To Paradise - The Birth of British Rock,Alwyn W. Turner,39.95,9781851775538,MRB,2008\r\n"
			+ "Heart - Heart in the Studio,Jake Brown,16.95,9781550228311,MRB,2008\r\n"
			+ "\"How To Get A Job In The Music Industry, 2nd Edition\",Keith Hatscheck,23.95,0876390726,MRB,2008\r\n"
			+ "I Hate New Music,David Thompson,19.39,9780879309350,MRB,2008\r\n"
			+ "Iron Maiden - 30 Years of the Beast,Paul Stenning,16.95,1842403613,MRB,2008\r\n"
			+ "\"James, Rick - The Confessions of Rick James: Memoirs of a Super Freak\",Rick James,15.95,9780979097638,MRB,2008\r\n"
			+ "\"James, Skip - I'd Rather Be the Devil: Skip James + the Blues\",Stephen Calt,13.95,9781556527463,MRB,2008\r\n"
			+ "Jazz Singers - The Ultimate Guide,Scott Yanow,17.95,9780879308254,MRB,2008\r\n"
			+ "Join Together: Forty Years of the Rock Music Festival,Marley Brant,19.95,9780879309268,MRB,2008\r\n"
			+ "Kinks - All Day And All of The Night,Doug Hinman,19.95,087930765X,MRB,2008\r\n"
			+ "\"Kooper, Al - Backstage Passes And Backstabbing Bastards: Memoirs of A Rock 'N' Rock Survivor [Second Edition]\",Al Kooper,16.95,9780879309220,MRB,2008\r\n"
			+ "\"Krasnow, Hecky - Rudolph, Frosty, and Captain Kangaroo: The Musical Life of Hecky Krasnow - Producer of the World's Most Beloved Children's Songs\",Judy Gail Krasnow,19.95,9781595800268,MRB,2008\r\n"
			+ "\"Last, James - My Autobiography\",James Last,24.95,9781844544349,MRB,2008\r\n"
			+ "Led Zeppelin: The Story of a Band and Their Music 1968-1980,Matteo Molinari & Jim Kamm & Keith Shadwick,22.95,0879308710,MRB,2008\r\n"
			+ "\"Loesser, Frank - Most Remarkable Fella, A: Frank Loesser And The Guys And Dolls In His Life\",Susan Loesser,11.95,0634009273,MRB,2008\r\n"
			+ "\"Lydon, John - Stories of Johnny\",,17.95,1842403605,MRB,2008\r\n"
			+ "\"McCartney, Paul - Each One Believing\",Bill Bernstein,9.95,0811845079,MRB,2008\r\n"
			+ "New York Dolls - Photographs By Bob Gruen,Bob Gruen (Photographs) & Legs McNeil (Commentary Collection) & Morrissey (Awd.),22.95,9780810972711,MRB,2008\r\n"
			+ "Notable Moments of Women in Music,Jay Warner,16.95,9781423429517,MRB,2008\r\n"
			+ "Official Book of Punk Rock Lists,Amy Wallace & Handsome Dick Manitoba,14.95,0879309199,MRB,2008\r\n"
			+ "On This Day in Music History,Jay Warner,16.95,0634066935,MRB,2008\r\n"
			+ "\"Opera Companies And Houses of The United States - A Comprehensive, Illustrated Reference\",Karyl Lynn Zietz,44.95,9780786438990,MRB,2008\r\n"
			+ "\"Opera Companies And Houses of Western Europe, Canada, Australia And New Zealand - A Comprehensive Illustrated Reference\",Karyl Lynn Zietz,44.95,9780786438983,MRB,2008\r\n"
			+ "\"Paul, Les - The Early Years of The Les Paul Legacy: 1915-1963\",Robb Lawrence,29.95,9780634048616,MRB,2008\r\n"
			+ "Pink Floyd - Black Strat: A History Of David Gilmour's Black Fender Stratocaster,Phil Taylor,26.95,9781423445593,MRB,2008\r\n"
			+ "\"Pop Surf Culture - Music, Design, Film And Fashion From The Bohemian Surf Boom\",Brian Chidester & Dominic Priore,29.95,1595800352,QAS,2008\r\n"
			+ "Pretty Vacant: A History of UK Punk,Phil Strongman,13.95,9781556527524,MRB,2008\r\n"
			+ "Punk Diary: The Ultimate Trainspotter's Guide to Underground Rock 1970-1982,George Gimarc,19.95,0879308486,MRB,2008\r\n"
			+ "\"Radio Drama - A Comprehensive Chronicle of American Network Programs, 1932 - 1962\",Martin Grams Jr.,66.95,9780786438716,MRB,2008\r\n"
			+ "Radio's Morning Show Personalities - Early Hour Broadcasters And Deejays From The 1920S To The 1990S,Philip A. Lieberman,34.65,9780786440603,MRB,2008\r\n"
			+ "Revolutions In Sound - Warner Bros. Records: The First Fifty Years,Warren Zanes,34.95,9780811866286,MRB,2008\r\n"
			+ "Roxy Music - Thrill of It All: The Story of Bryan Ferry & Roxy Music,David Buckley,14.95,1556525745,MRB,2008\r\n"
			+ "Sly & The Family Stone - I Want to Take You Higher: The Life and Times of Sly & The Family Stone,Jeff Kaliss,19.95,9780879309343,MRB,2008\r\n"
			+ "Songwriters - A Biographical Dictionary With Discographies,Nigel Harrison,66.95,9780786441020,MRB,2008\r\n"
			+ "Supremes - The Story of The Supremes,Daryl Easlea,16.95,9781851775521,MRB,2008\r\n"
			+ "Tell the Truth Until They Bleed: Coming Clean in the Dirty World of Blues and Rock 'n' Roll,Josh Alan Friedman,16.95,9780879309329,MRB,2008\r\n"
			+ "\"Torme, Mel - A Chronicle of His Recordings, Books & Films\",George Hulme,35.95,9780786437436,MRB,2008\r\n"
			+ "Touch Me I'm Sick: The 52 Creepiest Love Songs You've Ever Heard,Tom Reynolds,10.95,9781556527531,MRB,2008\r\n"
			+ "\"Waits, Tom - Wild Years: The Music and Myth of Tom Waits\",Jay S. Jacobs,17.95,9781550227161,MRB,2008\r\n"
			+ "Whitburn's Music Yearbook 2005-2006,Joel Whitburn,34.95,0898201705,MRB,2008\r\n"
			+ "\"Who - On Record: A Critical History, 1963-1998\",John Atkins,30.95,9780786440979,MRB,2008\r\n"
			+ "\"Williams, Hank - Lovesick Blues\",Paul Hemphill,5.99,0670034142,MRB,2008\r\n"
			+ "30 Years of British Television: Meet the People Behind Your Favorite British TV Characters!,Bret Norton & A.S Berman,16.95,9781593931438,MTV,2008\r\n"
			+ "A Western Filmmakers (Volumes 1 & 2),Henryk Hoffman,66.95,9780786437429,MTV,2008\r\n"
			+ "\"Allen, Irwin - Irwin Allen Television Productions, 1964-1970\",Jon Abbott,54.95,9780786427598,MTV,2008\r\n"
			+ "\"American Frontiersmen On Film And Television - Boone, Crockett, Bowie, Houston, Bridger And Carson\",Ed Andreychuk,44.95,9780786438648,MTV,2008\r\n"
			+ "\"American Silent Film Comedies - An Illustrated Encyclopedia of Persons, Studios And Terminology\",Blair Miller,30.95,9780786438839,MTV,2008\r\n"
			+ "Art Directors In Cinema - A Worldwide Biographical Dictionary,Michael L. Stephens,39.95,9780786437719,MTV,2008\r\n"
			+ "Avengers - On Location,Chris Bentley,19.95,190528747X,MTV,2008\r\n"
			+ "Bad Boys - The Actors of Film Noir,Karen Burroughs Hannsberry,66.95,9780786437399,MTV,2008\r\n"
			+ "\"Beatty, Warren - Warren Beatty: A Private Man\",Suzanne Finstad,6.95,1400046068,MTV,2008\r\n"
			+ "\"Bergman, Ingrid - Ingrid: A Personal Biography\",Charlotte Chandler,14.95,9781557837356,MTV,2008\r\n"
			+ "\"Bernhardt, Sarah - In The Theatre of Films And Sound Recordings\",David W. Menefee,30.95,9780786438822,MTV,2008\r\n"
			+ "\"Blaisdell, Paul - Monster Maker\",Randy Palmer,30.95,9780786440993,MTV,2008\r\n"
			+ "\"Booth, Shirley - Love is the Reason for it All: The Shirley Booth Story\",Jim Manago,20.95,1593931468,MTV,2008\r\n"
			+ "\"Browning, Tod - The Cinema of Tod Browning: Essays of the Macabre and Grotesque\",Bernd Herzogenrath,29.95,9780786434473,MTV,2008\r\n"
			+ "\"Bruce, Virginia: Under My Skin\",Scott O'Brien,24.95,1593933142,MTV,2008\r\n"
			+ "\"Burr, Raymond - Hiding in Plain Sight: The Secret Life of Raymond Burr\",Michael Seth Starr,13.95,9781423473718,MTV,2008\r\n"
			+ "\"Carradine, John - The Films\",Tom Weaver,35.95,9780786437788,MTV,2008\r\n"
			+ "Chaplin In The Sound Era - An Analysis of The Seven Talkies,Eric L. Flom,35.95,9780786440528,MTV,2008\r\n"
			+ "Cheap Scares - Low Budget Horror Filmmakers Share Their Secrets,Gregory Lamberson,34.95,9780786437061,MTV,2008\r\n"
			+ "\"Cheap Tricks And Class Acts - Special Effects, Makeup And Stunts From The Fantastic Fifties\",John J.J. Johnson,30.95,9780786440580,MTV,2008\r\n"
			+ "Chinese Filmography - 2444 Feature Films Produced By Studios In The People's Republic of China From 1949 Through 1995,Donald J. Marion,85.5,9780786440719,MTV,2008\r\n"
			+ "Cinema of Obsession - Erotic Fixation And Love Gone Wrong In The Movies,Dominique Mainon & James Ursini,19.95,0879103477,MTV,2008\r\n"
			+ "\"Classic Cliffhangers, Volume 2: 1941-1955\",Hank Davis,19.95,9781887664820,MTV,2008\r\n"
			+ "Cocteau and the Testament of Orpheus,Lucien Clergue,5.95,0670892580,MTV,2008\r\n"
			+ "\"Coen, Joel & Ethan\",Peter Korte & Georg Seesslen,18.95,9780879109639,MTV,2008\r\n"
			+ "\"Crabbe, Buster: A Biofilmography\",Jerry Vermilye,42.95,9780786436057,MTV,2008\r\n"
			+ "Creature Feature: Nature Turned Nasty in the Movies,William Schoell,42.95,9780786435562,MTV,2008\r\n"
			+ "\"Critical History of Television's The Twilight Zone, 1959 - 1964\",Don Presnell & Marty McGee,26.96,9780786438860,MTV,2008\r\n"
			+ "\"Dali, Salvador - Dali, Surrealism and Cinema\",Elliott H. King,13.95,9781904048909,MTV,2008\r\n"
			+ "\"Dictionary of Cinema Quotations From Filmmakers And Critics - Over 3400 Axioms, Criticisms, Opinions And Witticisms From 100 Years of The Cinema\",Stephen M. Ringler,35.95,9780786437634,MTV,2008\r\n"
			+ "Dictionary of Television And Audiovisual Terminology,Moshe Moshkovitz,30.95,9780786437542,MTV,2008\r\n"
			+ "Doctor Who - Calling the Shots: Directing the New Series of Doctor Who,Graeme Harper,22.95,1905287410,MTV,2008\r\n"
			+ "Doctor Who - Dalek I Loved You: A Memoir,Nick Griffiths,12.95,9780575082199,MTV,2008\r\n"
			+ "Don't Wear Silver in the Winter: Remembering My Mother,Janet Cantor Gari,12.95,1593933126,MTV,2008\r\n"
			+ "Encyclopedia of Superheroes On Film And Television,John Kenneth Muir,66.95,9780786437559,MTV,2008\r\n"
			+ "\"Epic Films - Casts, Credits And Commentary On Over 350 Historical Spectacle Movies, 2D Ed.\",Gary Allen Smith,35.95,9780786440818,MTV,2008\r\n"
			+ "\"Experimental Television, Test Films, Pilots And Trial Series, 1925 Through 1995 - Seven Decades of Small Screen Almosts\",Vincent Terrace,66.95,9780786440962,MTV,2008\r\n"
			+ "\"Fantastic Cinema Subject Guide - A Topical Index To 2,500 Horror, Science Fiction, And Fantasy Films\",Bryan Senn & John Johnson,44.95,9780786437665,MTV,2008\r\n"
			+ "\"Feature Films, 1940 - 1949 - A United States Filmography\",Alan G. Fetrow,66.95,9780786440511,MTV,2008\r\n"
			+ "\"Feature Films, 1950 - 1959 - A United States Filmography\",Alan G. Fetrow,66.95,9780786440504,MTV,2008\r\n"
			+ "Femme Noir - Bad Girls of Film,Karen Burroughs Hannsberry,66.95,9780786437399,MTV,2008\r\n"
			+ "\"Film And Television In - Jokes - Nearly 2,000 Intentional References, Parodies, Allusions, Personal Touches, Cameos, Spoofs And Homages\",Bill van Heerden,30.95,9780786438945,MTV,2008\r\n"
			+ "\"Flash Gordon Serials, The - 1936-1940: A Heavily Illustrated Guide\",Roy Kinnard & Tony Crnkovich & R.J. Vitone,44.95,9780786434701,MTV,2008\r\n"
			+ "\"Ford, Harrison - The Films\",Brad Duke,35.95,9780786440481,MTV,2008\r\n"
			+ "\"Francis, Kay - I Can't Wait To Be Forgotten [2nd Edition]\",Scott O'Brien,5.95,9781593931063,MTV,2008\r\n"
			+ "\"From Beautiful Downtown Burbank - A Critical History of Rowan And Martin's Laugh - In, 1968 - 1973\",Hal Erickson,30.95,9780786440498,MTV,2008\r\n"
			+ "From Silents To Sound - A Biographical Encyclopedia of Performers Who Made The Transition To Talking Pictures,Roy Liebman,35.95,9780786440627,MTV,2008\r\n"
			+ "\"Gangster Films - A Comprehensive, Illustrated Reference To People, Films And Terms\",Michael L. Stephens,30.95,9780786437702,MTV,2008\r\n"
			+ "\"Gish, Lillian - A Life On Stage And Screen\",Stuart Oderman,35.95,9780786440757,MTV,2008\r\n"
			+ "\"Googies: Coffeeshop to the Stars, Vol. 1\",Steve Hayes,19.95,1593933061,MTV,2008\r\n"
			+ "\"Googies: Coffeeshop to the Stars, Vol. 2 - The Golden Years\",Steve Hayes,16.95,159393307X,MTV,2008\r\n"
			+ "\"Halsey, Brett - Art or Instinct in the Movies\",John B. Murray,19.95,9781887664837,MTV,2008\r\n"
			+ "\"History of Television, 1880 To 1941\",Albert Abramson,44.95,9780786440863,MTV,2008\r\n"
			+ "Hollywood's Top Dog: The Dog Hero in Film,Deborah Painter,19.95,9781887664844,MTV,2008\r\n"
			+ "\"Holmes, John: A Life Measured in Inches\",Jennifer Sugar & Jill C. Nelson,24.95,9781593933029,MTV,2008\r\n"
			+ "\"Hood, Robin - A Cinematic History of The English Outlaw And His Scottish Counterparts\",Scott Allen Nollen,30.95,9780786437573,MTV,2008\r\n"
			+ "\"Horror 101: The A-List of Horror Films and Monster Movies, Volume 1\",,19.95,1887664793,MTV,2008\r\n"
			+ "Horror At The Drive - In - Essays In Popular Americana,Gary D. Rhodes,35.95,9780786437627,MTV,2008\r\n"
			+ "I Talked With A Zombie - Interviews With 23 Veterans of Horror And Sci-Fi Films And Television,Tom Weaver,39.95,9780786441181,MTV,2008\r\n"
			+ "\"Karloff, Boris - A Critical Account of His Screen, Stage, Radio, Television And Recording Work\",Scott Allen Nollen,30.95,9780786440733,MTV,2008\r\n"
			+ "King Kong Is Back!,,14.95,1932100644,MTV,2008\r\n"
			+ "\"Kipling, Rudyard And Sir Henry Rider Haggard On Screen, Stage, Radio And Television\",Philip Leibfried,35.95,9780786437467,MTV,2008\r\n"
			+ "Korean War Filmography - 91 English Language Features Through 2000,Robert J. Lentz,39.95,9780786438761,MTV,2008\r\n"
			+ "L.A. Noir - Nine Dark Visions of The City of Angels,William Hare,35.95,9780786437405,MTV,2008\r\n"
			+ "\"Langdon, Harry: His Life and Film [Second Edition]\",William Schelly,39.95,9780786436910,MTV,2008\r\n"
			+ "\"Laurie, Hugh - The House That Hugh Laurie Built: An Unauthorized Biography and Episode Guide\",Paul Challen,14.95,9781550228038,MTV,2008\r\n"
			+ "\"Lee, Christopher - Christopher Lee: The Authorised Screen History\",Alistair McAlpine & Cathy Giangrande & Jonathan Rigby,24.95,1905287488,MTV,2008\r\n"
			+ "\"Lemmon, Jack - A Twist of Lemmon: A Tribute to My Father\",Chris Lemmon,14.95,9781557837394,MTV,2008\r\n"
			+ "\"Lugosi, Bela\",Pauline Bartel & Gary Svehla & Susan Svehla,19.95,9781887664776,MTV,2008\r\n"
			+ "\"Lynch, David\",Colin Odell & Michelle Le Blanc,13.95,9781842432259,MTV,2008\r\n"
			+ "\"Media In The Movies - A Catalog of American Journalism Films, 1900 - 1996\",Larry Langman,39.95,9780786440917,MTV,2008\r\n"
			+ "Mexican Masked Wrestler And Monster Filmography,Robert Michael Bobb Cotter,35.95,9780786441044,MTV,2008\r\n"
			+ "Mickey Rooney as Archie Bunker and Other TV Casting Almosts,Ted Sennett & Eila Mell,24.95,159393145X,MTV,2008\r\n"
			+ "Monster Bash Magazine (Issue #6),,7,,MTV,2008\r\n"
			+ "Monster Bash Magazine (Issue #7),,7,,MTV,2008\r\n"
			+ "Monster Bash Magazine (Issue #8),,7,,MTV,2008\r\n"
			+ "\"Moran, Lois - A Beautiful Fairy Tale: The Life of Actress Lois Moran\",Richard Buller,22.95,0879103124,MTV,2008\r\n"
			+ "Movie and Television Locations - 113 Famous Sites in Los Angeles and San Diego,Leon Smith,30.95,9780786440825,MTV,2008\r\n"
			+ "Movies At Home - How Hollywood Came To Television,Kerry Segrave,30.95,9780786440801,MTV,2008\r\n"
			+ "Naked City - The Television Series,James Rosin,14.95,9780972868433,MTV,2008\r\n"
			+ "Nazi Propaganda Films - A History And Filmography,Rolf Giesen,35.95,9780786438709,MTV,2008\r\n"
			+ "\"Plagues, Apocalypses And Bug-Eyed Monsters - How Speculative Fiction Shows Us Our Nightmares\",Heather Urbanski,29.95,9780786429165,MTV,2008\r\n"
			+ "\"Pollack, Syndey - A Critical Filmography\",Janet L. Meyer,30.95,9780786437528,MTV,2008\r\n"
			+ "\"Quinn, Martin, Producer - A Behind-the-Scenes History of QM Productions and Its Founder\",Jonathan Etter,30.95,9780786438679,MTV,2008\r\n"
			+ "\"Reeves, George - Behind the Crimson Cape: The Cinema of George Reeves\",Jan Alan Henderson & Steve Randisi,33.95,0961959665,MTV,2008\r\n"
			+ "\"Reeves, George - Speeding Bullet: The Life and Bizarre Death of George Reeves (2nd Edition)\",Jan Alan Henderson,24.95,0961959665,MTV,2008\r\n"
			+ "Riding The Video Range - The Rise And Fall of The Western On Television,Gary A. Yoggy,44.95,9780786438969,MTV,2008\r\n"
			+ "\"Rigg, Diana - Biography\",Kathleen Tracy,12.95,193210027X,MTV,2008\r\n"
			+ "\"Robinson, Edward G - The Edward G. Robinson Encyclopedia\",Robert Beck,44.95,9780786438648,MTV,2008\r\n"
			+ "Samurai Films,Roland Thorne,14.95,9781842432556,MTV,2008\r\n"
			+ "\"Scheider, Roy - A Film Biography\",Diane C. Kachmar,35.95,9780786440597,MTV,2008\r\n"
			+ "Science Fiction Films,John Costello,7.98,9781903047446,MTV,2008\r\n"
			+ "Science Fiction Serials,Roy Kinnard,30.95,9780786437450,MTV,2008\r\n"
			+ "\"Scott, George C. - Rage And Glory: The Volatile Life And Career of George C. Scott\",David Sheward,24.95,9781557836700,MTV,2008\r\n"
			+ "Spaghetti & Stars,Grafiche Damiani,9.98,8890130415,MTV,2008\r\n"
			+ "\"Television Cartoon Shows - An Illustrated Encyclopedia, 1949-2003 (2 Volumes, 2nd Edition)\",Hal Erickson,104.95,9780786420995,MTV,2008\r\n"
			+ "\"Terror Television - American Series, 1970 - 1999\",John Kenneth Muir,44.95,9780786438846,MTV,2008\r\n"
			+ "\"Tourneur, Maurice - The Life And Films\",Harry Waldman,30.95,9780786440856,MTV,2008\r\n"
			+ "Twilight Zone - Trivia from The Twilight Zone,Fred Bronson & Bill DeVoe,12.95,1593931360,MTV,2008\r\n"
			+ "Wagon Train: The Television Series [Author-Signed Edition],James Rosin,19.95,9780972868440,MTV,2008\r\n"
			+ "\"Wampas Baby Stars - A Biographical Dictionary, 1922 - 1934\",Roy Liebman,35.95,9780786440610,MTV,2008\r\n"
			+ "\"Wells, H.G. - H.G. Wells On Film\",Don G. Smith,44.95,0786410582,MTV,2008\r\n"
			+ "\"Western Filmmakers - A Biographical Dictionary of Writers, Directors, Cinematographers, Composers, Actors And Actresses\",Henryk Hoffmann,66.95,9780786437429,MTV,2008\r\n"
			+ "Wild Beyond Belief!: Interviews with Exploitation Filmmakers of the 1960s and 1970s,Brian Albright,33.95,9780786436897,MTV,2008\r\n"
			+ "Window to The Future: The Golden Age of Television Marketing and Advertising,Steve Kosareff,6.95,0811846326,MTV,2008\r\n"
			+ "Women Pioneers In Television - Biographies of Fifteen Industry Leaders,Cary O'Dell,35.95,9780786440740,MTV,2008\r\n"
			+ "\"Wray, Fay - The Films of Fay Wray\",Roy Kinnard & Tony Crnkovich,30.95,9780786438754,MTV,2008\r\n"
			+ "\"Young, Alan - Mister Ed and Me and More! [Author-Signed Edition]\",Alan Young,17.95,9780979740404,MTV,2008\r\n"
			+ "You're Next: Loss of Identity in the Horror Film,Anthony Ambrogio,19.95,9781887664806,MTV,2008\r\n"
			+ "\"Burroughs, Edgar Rice: The Exhaustive Scholar's and Collector's Descriptive Bibliography of American Periodical, Hardcover, Paperback, and Reprint Editions\",Robert B. Zeuschner,29.95,9780786431137,NEB,2008\r\n"
			+ "Hairstyles Of The World,Pepin Press,5.95,9054960825,NEB,2008\r\n"
			+ "Home Away From Home: The World of Camper Vans and Motorhomes,Kate Trant & Lars Eriksen,6.95,1904772277,NEB,2008\r\n"
			+ "No Smoking,Luc Sante,8.95,2843236169,NEB,2008\r\n"
			+ "Trivia: Guinness World Record Holder,Wilson Casey,12.95,1593931379,NEB,2008\r\n"
			+ "Fibber McGee and Molly: On the Air 1935-1959,Clair Schulz,19.95,1593933053,OTR,2008\r\n"
			+ "Baseball - Baseball's Greatest Hit: The Story of Take Me Out to the Ball Game,Jennifer Trainer Thompson & Andy Strasberg & Bob Thompson & Tim Wiles,24.95,9781423431886,SSM,2008\r\n"
			+ "Bowling - Let's Go Bowling,Eric Dregni,6.95,9780760317945,SSM,2008\r\n"
			+ "");
		System.out.println(book + " created successfully.");
		b14.close();
}
if(book.equals("books2009.csv")) {
	b15 = new PrintWriter(book);
		b15.println("\"Doc Savage - A History of the Doc Savage Adventures in Pulps, Paperbacks, Comics, Fanzine, Radio and Film\",Robert Michael Bobb Carter,44.95,9780786441358,CCB,2009\r\n"
			+ "\"Kurtzman, Harvey - The Art of Harvey Kurtzman: The Mad Genius of Comics\",Nick Freeth & Denis Kitchen & Paul Buhle,29.95,9780810972964,CCB,2009\r\n"
			+ "Manga Madness,David Okum,16.95,1581805349,CCB,2009\r\n"
			+ "Manga Martial Arts,David Okum,16.95,1600610293,CBC,2009\r\n"
			+ "Manga Monster Madness,David Okum,16.95,158180606X,CCB,2009\r\n"
			+ "Underground Classics - The Transformation of Comics Into Comix,Karen Schoemer,24.95,9780810905986,CCB,2009\r\n"
			+ "Antique Trader Collectible Paperback Price Guide,Gary Lovisi,16.95,089689634X,HCB,2009\r\n"
			+ "Collector's Guide To Classic O-Gauge Trains,David Doyle,16.95,9780896894570,HCB,2009\r\n"
			+ "Collector's Guide To PEZ Identification & Price Guide [3rd Edition],Shawn Peterson,19.95,0896896358,HCB,2009\r\n"
			+ "Collector's Guide To Postwar Lionel Trains 1945-1969,David Doyle,16.95,0896895416,HCB,2009\r\n"
			+ "Collector's Guide To Prewar Lionel Trains 1900-1942,David Doyle,16.95,9780896894624,HCB,2009\r\n"
			+ "\"Dames, Dolls & Delinquents - A Collector's Guide To Sexy Pulp Fiction Paperbacks\",Gary Lovisi,19.95,0896899683,HCB,2009\r\n"
			+ "O'Brien's Collecting Toys [12th Edition],Karen O'Brien,24.95,0896893715,HCB,2009\r\n"
			+ "Petretti's Coca-Cola Collectibles Price Guide [12th Edition],Allan Petretti,39.95,0896896919,HCB,2009\r\n"
			+ "Standard Catalog Of American Flyer Trains,David Doyle,27.95,0896895157,BHC,2009\r\n"
			+ "Standard Catalog Of Lionel Train Sets 1945-1969,David Doyle,24.95,0896894444,HCB,2009\r\n"
			+ "Standard Catalog Of Lionel Trains 1900-1942 [2nd Edition],David Doyle,27.95,0896895998,HCB,2009\r\n"
			+ "Standard Catalog Of Lionel Trains 1970-2000,Justin Moen (Editor),27.95,0896895777,HCB,2009\r\n"
			+ "Warman's Coca-Cola Collectibles Identification & Price Guide,Allan Petretti,19.95,0898693111,HCB,2009\r\n"
			+ "Warman's Vietnam War Collectibles Identification & Price Guide,David Doyle,19.95,0896896048,HCB,2009\r\n"
			+ "Warman's World War II Collectibles,John F. Graf,19.95,0896895467,HCB,2009\r\n"
			+ "25 Albums That Rocked The World,Chris Charlesworth & Peter Doggett,3.95,9781847726261,MRB,2009\r\n"
			+ "America Dances - Historical Postcards From The Library of Congress,Pomegranate Publishing,3.95,0764936417,MRB,2009\r\n"
			+ "And Party Every Day - The Inside Story of Casablanca Records,Larry Harris with Curt Gooch and Jeff Suhs,19.95,9780879309824,MRB,2009\r\n"
			+ "\"Band, The - Across The Great Divide: The Band And America\",Barney Hoskyns,14.95,9781423414421,MRB,2009\r\n"
			+ "\"Band, The - This Wheel's On Fire: Levon Helm And The Story of The Band\",Levon Helm & Stephen Davis,14.95,9781556524059,MRB,2009\r\n"
			+ "Battle of The Band Names,Bart Bull,11.95,9780810996403,MRB,2009\r\n"
			+ "Beatles - Beatle Books: From Genesis To Revolution,W. Fraser Sandercombe,21.95,9781926592008,MRB,2009\r\n"
			+ "Beatles - Beatlemania Forever,W. Fraser Sandercombe,22.95,9781894959780,MRB,2009\r\n"
			+ "Beatles - Beatles: A Diary,Barry Miles,19.95,9781847720825,MRB,2009\r\n"
			+ "Beatles - Revolution In The Head: The Beatles' Records And The Sixties,Ian MacDonald,13.95,1556527330,MRB,2009\r\n"
			+ "Beatles - Revolver: The Secret History of The Beatles,Geoffrey Giuliano & Avalon Giuliano,19.95,1844541606,MRB,2009\r\n"
			+ "Beatles - The Lennon Prophecy: A New Examination of The Death Clues of The Beatles,Joseph Niezgoda,15.95,9780942257458,MRB,2009\r\n"
			+ "\"Bell, Benny - Grandpa Had A Long One: Personal Notes on the Life, Career & Legacy of Benny Bell\",Joel Samberg,16.95,1593934599,MRB,2009\r\n"
			+ "Billboard - Hot Country Songs 1944-2008,Joel Whitburn,49.95,0898201772,WWF,2009\r\n"
			+ "Billboard Hot Country Albums 1964-2007,Joel Whitburn,39.95,,MRB,2009\r\n"
			+ "Billboard's Rock Tracks 1981-2002,Joel Whitburn,39.95,0898201535,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #83 (Summer 2008)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #84 (Fall 2008)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #85 (Winter 2008/9)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #86 (Spring 2009)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #87 (Summer 2009)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "\"Blue Suede News, Issue #88 (Fall 2009)\",Marc Bristol & Gaby Maag-Bristol,4.5,,MRB,2009\r\n"
			+ "Book Of Rock Quotes,Michael Heatley,3.95,9781847724182,MRB,2009\r\n"
			+ "By The Time We Got To Woodstock,Bruce Pollock,16.95,9780879309794,MRB,2009\r\n"
			+ "\"Cochran, Eddie - Three Steps To Heaven: The Eddie Cochran Story\",Bobby Cochran & Susan Van Hecke,17.95,0634032526,MRB,2009\r\n"
			+ "\"Como, Perry - Perry Como: A Biography And Complete Career Record\",Malcolm Macfarlane & Ken Crossland,49.95,9780786437016,MRB,2009\r\n"
			+ "\"Cooper, Alice - Alice Cooper: Golf Monster\",Alice Cooper & Keith Zimmerman,4.95,9780307382658,MRB,2009\r\n"
			+ "\"Cooper, Alice - The Illustrated Collectors Guide To Alice Cooper [10th Anniversary Edition]\",Dale Sherman,22.95,9781894959933,MRB,2009\r\n"
			+ "\"Des Barres, Pamela - I'm With The Band: Confessions of A Groupie\",Pamela Des Barres,12.95,9781556525896,MRB,2009\r\n"
			+ "\"Domino, Fats - Blue Monday: Fats Domino And The Lost Dawn of Rock 'n' Roll\",Rick Coleman,6.95,0306815311,MRB,2009\r\n"
			+ "\"Doors, The\",The Doors & Ben Fong-Torres,14.95,140130303X,MRB,2009\r\n"
			+ "\"Dorsey, Tommy - Livin' In A Great Big Way\",,4.95,0306815028,MRB,2009\r\n"
			+ "\"Dylan, Bob - Bob Dylan: Alias Anything You Please (Rex Collections)\",Ty Silkman,24.95,9781905287833,MRB,2009\r\n"
			+ "\"Dylan, Bob - Intimate Insights From Friends And Fellow Musicians\",Katheleen Mackay,4.95,0825673305,MRB,2009\r\n"
			+ "\"Dylan, Bob - Like A Rolling Stone: Bob Dylan At The Crossroads\",Greil Marcus,5.95,158648382X,MRB,2009\r\n"
			+ "\"Dylan, Bob - Real Moments: Bob Dylan By Barry Feinstein\",Barry Feinstein,29.95,9781847721051,MRB,2009\r\n"
			+ "\"Dylan, Bob - Revolution In The Air: The Songs of Bob Dylan, 1957-1973\",Clinton Heylin,24.95,9781556528439,MRB,2009\r\n"
			+ "\"Eno, Brian - On Some Faraway Beach: The Life And Times of Brian Eno\",David Sheppard,22.95,9781556529429,MRB,2009\r\n"
			+ "\"Faithfull, Marianne - Memories, Dreams And Reflections\",Marianne Faithfull,14.95,9780007245819,MRB,2009\r\n"
			+ "\"Fong-Torres, Ben - Becoming Almost Famous: My Back Pages in Music, Writing, and Life\",Ben Fong-Torres,12.95,0879308803,MRB,2009\r\n"
			+ "\"Gershwin, George - The George Gershwin Reader\",Robert Wyatt & John Andrew Johnson,6.95,9780195327113,MRB,2009\r\n"
			+ "\"Go, Girl, Go! - The Women's Revolution In Music\",James L. Dickerson,4.95,082567316X,MRB,2009\r\n"
			+ "Goldmine Price Guide To 45 RPM Records [7th Edition],Martin Popoff,27.95,0896899586,MRB,2009\r\n"
			+ "Goldmine Promo Record & CD Price Guide [2nd Edition],Fred Heggeness,7.95,0873416341,MRB,2009\r\n"
			+ "Goldmine Record Album Price Guide (6th Edition),Tim Neely,21.99,1440203733,MRB,2009\r\n"
			+ "Goldmine Record Album Price Guide (6th Edition) [DVD-ROM Version],Tim Neely,16.99,1440203776,XYZ,2009\r\n"
			+ "Goldmine Record Album Price Guide [5th Edition],Tim Neely,19.95,0896895327,MRB,2009\r\n"
			+ "\"Grateful Dead - Relix, The Book: The Grateful Dead Experience\",Toni Brown (Editor) & Jorma Kaukonen (Foreword) & Dennis McNally (Afterword),24.95,9780879309862,MRB,2009\r\n"
			+ "Great Pretenders - My Strange Love Affair With '50s Pop Music,Karen Schoemer,4.95,0743272463,MRB,2009\r\n"
			+ "\"Green, Al - Take Me To The River: An Autobiography\",Al Green & Davin Seay,12.95,9781556528101,MRB,2009\r\n"
			+ "\"Hart, Mickey - Songcatchers\",Mickey Hart & K.M. Kostyal,6.95,079224107X,MRB,2009\r\n"
			+ "\"Helm, Levon - The Levon Helm Midnight Ramble\",Paul Laraia (Photography) & Levon Helm (Foreword),24.95,9780879309497,MRB,2009\r\n"
			+ "\"Hendrix, Jimi - My Son James\",James A. Hendrix with Jas Obrecht,14.95,0966785711,MRB,2009\r\n"
			+ "\"Hendrix, Jimi - The Jimi Hendrix Experience (Rex Collections)\",,16.95,1905287070,MRB,2009\r\n"
			+ "Hit Singles - Top 20 Charts From 1954 To The Present Day,Dave McAleer,18.95,0879308087,MRB,2009\r\n"
			+ "I'm The Greatest Star - Broadway's Top Musical Legends From 1900 To Today,Robert Viagas,24.95,9781557837271,MRB,2009\r\n"
			+ "\"Jethro Tull - Flying Colours - The Jethro Tull Reference Manual, Remastered [Revised Edition]\",Greg Russo,21.95,9780979184512,MRB,2009\r\n"
			+ "\"John, Elton - Elton: The Biography\",David Buckley,12.95,9781556527944,MRB,2009\r\n"
			+ "\"Jones, Tom - Arise Sir Tom Jones\",Gwen Russell,19.95,9781844543229,MRB,2009\r\n"
			+ "\"Judas Priest - Story Of Judas Priest, Defenders Of The Faith\",Neil Daniels,4.95,9780825636059,MRB,2009\r\n"
			+ "Keep Rockin' - The 50s & 60s Nostalgia Magazine #1 (February 2009),Liz Harris & Lou Holly,4.95,,MRB,2009\r\n"
			+ "Keep Rockin' - The 50s & 60s Nostalgia Magazine #2 (April 2009),Liz Harris & Lou Holly,4.95,,MRB,2009\r\n"
			+ "Keep Rockin' - The 50s & 60s Nostalgia Magazine #3 (June 2009),Liz Harris & Lou Holly,4.95,,MRB,2009\r\n"
			+ "Keep Rockin' - The 50s & 60s Nostalgia Magazine #4 (August 2009),Liz Harris & Lou Holly,4.95,,MRB,2009\r\n"
			+ "Keep Rockin' - The 50s & 60s Nostalgia Magazine #5 (October 2009),Liz Harris & Lou Holly,4.95,,MRB,2009\r\n"
			+ "\"Kessel, Barney - A Jazz Legend\",Maurice J. Summerfield,29.95,7.78187E+12,MRB,2009\r\n"
			+ "\"Kuti, Fela - Fela: This Bitch of A Life\",Carlos Moor,13.95,9781556528354,MRB,2009\r\n"
			+ "Led Zeppelin - Photographs By Neal Preston,Neal Preston & Cynthia Fox,24.95,9781847726490,MRB,2009\r\n"
			+ "\"Leibovitz, Annie - American Music\",Annie Leibovitz,24.95,0375505075,MRB,2009\r\n"
			+ "\"McCartney, Paul - McCartney\",Christopher Sandford,5.95,0786718714,MRB,2009\r\n"
			+ "\"Metheny, Pat - The Pat Metheny Interviews\",Richard Niles,14.95,9781423474692,MRB,2009\r\n"
			+ "\"Morrison, Van - Van Morrison: No Surrender\",Johnny Rogan,10.95,9780099431831,MRB,2009\r\n"
			+ "Morrissey - Meetings With Morrissey [Very Limited Supply],Len Brown,8.95,9781847723765,MRB,2009\r\n"
			+ "\"Morrow, Cousin Brucie - Doo Wop: The Music, The Times, The Era [Author-Signed Edition + Bonus CD] / Rock & Roll ...And The Beat Goes On [Includes Autographed Photo + Bonus CD]\",Bruce Morrow & Rich Maloof,39.95,,MRB,2009\r\n"
			+ "\"Morrow, Cousin Brucie - Rock & Roll ...And The Beat Goes On [Includes Autographed Photo + Bonus CD]\",Bruce Morrow & Rich Maloof,24.95,0982306431,MRB,2009\r\n"
			+ "\"Novarro, Ramon - Ramon Novarro\",Karen Schoemer,29.95,9780786446766,MRB,2009\r\n"
			+ "\"Official Heavy Metal Book Of Lists, The\",Eric Danville & Cliff Mott (Illustrations) & Lemmy (Foreword),13.95,9780879309831,MRB,2009\r\n"
			+ "\"Phelps, Dee Dee - Vinyl Highway: Singing As Dick And Dee Dee\",Dee Dee Phelps,14.95,9781934321751,MRB,2009\r\n"
			+ "Pink Floyd - Pink Floyd (Rex Collections),,22.95,9781905287826,MRB,2009\r\n"
			+ "Pink Floyd - Pink Floyd FAQ: Everything Left To Know...And More!,Stuart Shea,16.95,0743272463,MRB,2009\r\n"
			+ "\"Presley, Elvis - Elvis And The Memphis Mafia\",Alanna Nash & Billy Smith & Marty Lacker & Lamar Fike,12.95,9781845131289,MRB,2009\r\n"
			+ "\"Presley, Elvis - Elvis: Word For Word\",Jerry Osborne,4.95,0517227959,MRB,2009\r\n"
			+ "\"Richard, Cliff - My Life, My Way\",Cliff Richard,24.95,075531588X,MRB,2009\r\n"
			+ "Rockabilly #45 [Spring 2009],Orlando Rios,3.99,,MRB,2009\r\n"
			+ "Rockabilly #46 [Summer 2009],Orlando Rios,3.69,,MRB,2009\r\n"
			+ "\"Rolling Stones, The - Stones: A History In Cartoons\",,6.95,0750942487,MRB,2009\r\n"
			+ "\"Simone, Nina - Nina Simone: The Biography\",David Brun-Lambert,22.95,9781845134303,MRB,2009\r\n"
			+ "Sonic Boom - The History of Northwest Rock From Louie Louie To Smells Like Teen Spirit,Peter Blecha,14.95,9780879309466,MRB,2009\r\n"
			+ "\"Springsteen, Bruce - Tougher Than The Rest: 100 Best Bruce Springsteen Songs\",June Skinner Sawyer,4.95,0825634709,MRB,2009\r\n"
			+ "Steely Dan - Reelin' In The Years,Brian Sweet,5.95,9781846098819,MRB,2009\r\n"
			+ "\"Summers, Andy - One Train Later\",,5.95,0312359144,MRB,2009\r\n"
			+ "U2 - U2: A Diary,Matt McGee,24.95,9781847721082,MRB,2009\r\n"
			+ "\"Vaughan, Stevie Ray - Roadhouse Blues: Stevie Ray Vaughan And Texas R&B\",Hugh Gregory,15.95,0879307471,MRB,2009\r\n"
			+ "\"Whitburn, Joel - Across The Charts: The 1960s\",Joel Whitburn,39.95,0898201756,MRB,2009\r\n"
			+ "\"Your Pretty Face is Going to Hell - The Dangerous Glitter of David Bowie, Iggy Pop and Lou Reed\",Dave Thompson,14.95,9780879309855,MRB,2009\r\n"
			+ "\"Zombies, The - Time of the Season - The Zombies Collector's Guide [Revised Edition]\",Greg Russo,14.95,9780979184505,MRB,2009\r\n"
			+ "100 Great Film Performances You Should Remember - But Probably Don't,John Dileo,14.95,0879109726,MTV,2009\r\n"
			+ "Acting Foolish,Lewis J. Stadlen,16.95,1593933290,MTV,2009\r\n"
			+ "Adventures In Paradise - The Television Series,James Rosin,14.95,9780972868457,MTV,2009\r\n"
			+ "Adventures of Baron Munchhausen,Charles McKeown & Terry Gilliam,6.95,155783041X,MTV,2009\r\n"
			+ "Bela Lugosi And The House of Doom [Original Fiction],Dwight Kemper,19.95,9781887664936,MTV,2009\r\n"
			+ "\"Best, James - Best In Hollywood: The Good, The Bad, And The Beautiful\",James Best & Jim Clark,19.95,9781593934606,MTV,2009\r\n"
			+ "Biographical Dictionary of Silent Film Western Actors and Actresses,George A. Katchmer & Diana Serra Cary (Foreword),39.95,9780786446933,MTV,2009\r\n"
			+ "\"Brent, Evelyn - The Life And FIlms of Hollywood's Lady Crook\",Lynn Kear & James King,39.95,9780786443635,MTV,2009\r\n"
			+ "\"Burghoff, Gary - To M*A*S*H And Back: My Life In Poems And Songs (That Nobody Ever Wanted To Publish)\",Gary Burghoff,16.95,1593933436,MTV,2009\r\n"
			+ "\"Bushman, Francis X. - King of The Movies\",Lon Davis & Debra Davis,19.95,1593934521,MTV,2009\r\n"
			+ "\"Car 54, Where Are You?\",Martin Grams Jr.,16.95,1593933401,MTV,2009\r\n"
			+ "Cinema Retro Movie Classics Special Edition Where Eagles Dare Issue,Cinema Retro,9.95,,MTV,2009\r\n"
			+ "\"Cinema Retro Volume 5, #13 [UK Limited Edition]\",,9.95,,MTV,2009\r\n"
			+ "\"Cinema Retro Volume 5, #14 [UK Limited Edition]\",,9.95,,MTV,2009\r\n"
			+ "\"Cinema Retro Volume 5, #15 [UK Limited Edition]\",Cinema Retro,9.95,,MTV,2009\r\n"
			+ "\"Clarke, Robert - To B Or Not To B: A Film Actor's Odyssey\",Robert Clarke,19.95,9781887664028,MTV,2009\r\n"
			+ "\"Comedy-Horror Films: A Chronological History, 1914-2008\",Bruce G. Hallenback,34.95,9780786433322,MTV,2009\r\n"
			+ "Coming Soon - Film Trailers And The Selling of Hollywood Technology,Keith M. Johnston,34.95,9780786444328,MTV,2009\r\n"
			+ "Confessions of An Accidental Mouseketeer,Lonnie Burr,19.95,1593933266,MTV,2009\r\n"
			+ "\"Crawford, Joan - Not The Girl Next Door: A Personal Biography\",Charlotte Chandler,14.95,9781557837516,MTV,2009\r\n"
			+ "\"Cukor, George - On Cukor\",Gavin Lambert,19.95,0847822974,MTV,2009\r\n"
			+ "\"Cushing, Peter - Peter Cushing: The Gentle Man Of Horror And His 91 Films\",Deborah Del Vecchio & Tom Johnson & Barry Morse,35.95,9780786444953,MTV,2009\r\n"
			+ "\"DeCamp, Rosemary - Tigers In My Lap\",Rosemary DeCamp,19.95,9781887664424,MTV,2009\r\n"
			+ "\"Eastwood, Clint - Eastwood\",Robert Tanitch,4.99,0760774471,MTV,2009\r\n"
			+ "\"Encyclopedia of Television Law Shows - Factional and Fictional Shows About Judges, Lawyers and the Courtroom, 1948-2008\",Hal Erickson,39.95,9780786438280,MTV,2009\r\n"
			+ "Fear Itself - Horror On Screen And In Reality During The Depression And World War II,Melvin E. Matthews Jr.,29.95,9780786443130,MTV,2009\r\n"
			+ "Food in the Movies [2nd Edition],Steve Zimmerman,39.95,9780786445462,MTV,2009\r\n"
			+ "\"Foray, June - Did You Grow Up With Me, Too?: The Autobiography of June Foray\",June Foray & Mark Evanier & Earl Kress,19.95,1593934610,MTV,2009\r\n"
			+ "\"Fuller, Dolores - A Fuller Life: Hollywood, Ed Wood And Me\",Dolores Fuller & Stone Wallace & Philip Chamberlin,19.95,1593933045,MTV,2009\r\n"
			+ "Gangster Film Reader,,16.95,0879103329,MTV,2009\r\n"
			+ "\"Garbo, Greta - Garbo\",Scott Reisfield & Robert Dance,19.95,0847827240,MTV,2009\r\n"
			+ "\"Gielgud, John - An Actor and His Time\",John Gielgud,12.95,1557834156,MTV,2009\r\n"
			+ "Guilty Pleasures of The Horror Film,Gary Svehla & Susan Svehla,19.95,1887664033,MTV,2009\r\n"
			+ "\"Guinness, Alec - Alec Guinness: The Authorised Biography\",Piers Paul Read,6.99,0743207297,MTV,2009\r\n"
			+ "Hardcastle and McCormick: A Complete Viewer's Guide to the Classic 80s Action Series,Deb Ohlin & Cheri deFonteny & Lynn Walker,27.95,159393324X,MTV,2009\r\n"
			+ "\"History of Independent Cinema, The\",Phil Hall,18.95,1593933355,MTV,2009\r\n"
			+ "\"Hitchcock, Alfred - The Hitchcock Murders\",Peter Conrad,4.95,0571210600,MTV,2009\r\n"
			+ "\"Holden, William - A Biography\",Michelangelo Capua,30.95,9780786444403,MTV,2009\r\n"
			+ "Hollywood Musicals Year By Year [2nd Edition],Stanley Green,12.95,0634007653,MTV,2009\r\n"
			+ "\"Hollywood's Made-To-Order Punks - The Complete Film History of The Dead End Kids, Little Tough Guys, East Side Kids and Bowery Boys\",Richard Roat,19.95,159393467X,MTV,2009\r\n"
			+ "HorrorHound #19 [Sept/Oct 2009],Nathan Hanneman,5.99,,MTV,2009\r\n"
			+ "HorrorHound #20 [Nov/Dec 2009],Nathan Hanneman,5.99,,MTV,2009\r\n"
			+ "Just When You Thought It Was Safe: A Jaws Companion,Patrick Jankiewicz,16.95,1593933347,MTV,2009\r\n"
			+ "\"Karloff, Boris - Dear Boris: The Life of William Henry Pratt a.k.a. Boris Karloff\",Cynthia Lindsay,16.95,9780879101060,MTV,2009\r\n"
			+ "\"Keaton, Buster - Keaton: The Man Who Wouldn't Lie Down\",Tom Dardis,12.95,0879101172,MTV,2009\r\n"
			+ "\"Laemmle, Carla - Among The Rugged Peaks: An Intimate Biography of Carla Laemmle\",Rick Atkins,19.95,9781887664912,MTV,2009\r\n"
			+ "\"Laemmle, Carla - Growing Up With Monsters: My Time At Universal Studios In Rhymes\",Carla Laemmle,11.95,9781593933418,MTV,2009\r\n"
			+ "\"Laugh Makers, The - A Behind-The-Scenes Tribute To Bob Hope's Incredible Gag Writers\",Robert L. Mills & J.D.,17.95,1593933231,MTV,2009\r\n"
			+ "\"Leading Men of MGM, The\",Jane Ellen Wayne,4.95,0786717688,MTV,2009\r\n"
			+ "\"Lee, Bruce - Pocket Essentials\",Simon B. Kenny,8.95,9781842432877,MTV,2009\r\n"
			+ "\"Lee, Sondra - Sondra Lee: I've Slept With Everybody (A Memoir)\",Sondra Lee,16.95,1593934637,MTV,2009\r\n"
			+ "\"Leonard, Sheldon - And The Show Goes On\",Sheldon Leonard & Andy Griffith (Foreword),19.95,9780879101848,MTV,2009\r\n"
			+ "\"Little Shoppe of Horrors, Issue #20\",Richard Klemensen,7.95,,MTV,2009\r\n"
			+ "\"Little Shoppe of Horrors, Issue #21\",Richard Klemensen,7.95,,MTV,2009\r\n"
			+ "\"Little Shoppe of Horrors, Issue #22\",Richard Klemensen,7.95,,MTV,2009\r\n"
			+ "\"Little Shoppe of Horrors, Issue #23\",Richard Klemensen,7.95,,MTV,2009\r\n"
			+ "\"Lugosi, Bela And Boris Karloff - The Expanded Story of A Haunting Collaboration\",Gregory William Mank,64.95,9780786434800,MTV,2009\r\n"
			+ "\"Meyer, Russ - Big Bosoms And Square Jaws: The Biography of Russ Meyer, King of The Sex Film\",Jimmy McDonough,5.95,1400050448,MTV,2009\r\n"
			+ "Midnight Marquee (Issue #76),Gary Svehla & Susan Svehla,7.95,,MTV,2009\r\n"
			+ "Monster Bash Magazine (Issue #9),Ron Adams,7,,MTV,2009\r\n"
			+ "Monster Memories #10 (2002 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #11 (2003 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #12 (2004 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #13 (2005 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #14 (2006 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #15 (2007 Scary Monsters Magazine Yearbook),Dennis Druktenis,7.95,,MTV,2009\r\n"
			+ "Monster Memories #16 (2008 Scary Monsters Magazine Yearbook),Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Monster Memories #17 (2009 Scary Monsters Magazine Yearbook),Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Monster Memories #2 (1994 Scary Monsters Magazine Yearbook),Dennis Druktenis,5.95,,MTV,2009\r\n"
			+ "Monster Memories #3 (1995 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #5 (1997 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #6 (1998 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #7 (1999 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #8 (2000 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monster Memories #9 (2001 Scary Monsters Magazine Yearbook),Dennis Druktenis,6.95,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #21,Monsters From The Vault,6.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #22,Monsters From The Vault,6.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #23,Monsters From The Vault,6.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #24,Monsters From The Vault,6.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #25,Monsters From The Vault,7.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Magazine #26,Monsters From The Vault,7.98,,MTV,2009\r\n"
			+ "Monsters From The Vault Special Edition #1: Kharis Unearthed,Monsters From The Vault,8.98,,MTV,2009\r\n"
			+ "Mr. Skin's Skintastic Video Guide: The 501 Greatest Movies For Sex & Nudity On DVD,Mr. Skin,16.95,9780979369100,MTV,2009\r\n"
			+ "\"Murphy, Audie - The Films of Audie Murphy\",Bob Larkins & Boyd Magers,35.95,9780786445080,MTV,2009\r\n"
			+ "\"Nelson, Ed - Beyond Peyton Place - My Fifty Years on Stage, Screen, and Television\",Ed Nelson & Alvin M. Cotlar & M.D.,16.95,9781595712929,MTV,2009\r\n"
			+ "\"Nelson, Miriam - My Life Dancing With The Stars\",Miriam Nelson,19.95,1593933339,MTV,2009\r\n"
			+ "\"Obscene, Indecent, Immoral And Offensive - 100+ Years of Censored, Banned And Controversial Films\",Stephen Tropiano,16.95,9780879103590,MTV,2009\r\n"
			+ "\"Olson, Johnny - A Voice In Time: From the Birth of Modern Media to The Price Is Right\",Randy West,19.95,9781593934712,MTV,2009\r\n"
			+ "\"Peck, Gregory - Gregory Peck: A Charmed Life\",Lynn Haney,5.99,0786716568,MTV,2009\r\n"
			+ "\"Phantom Variations - The Adaptations of Gaston Leroux's Phantom of The Opera,1925 To Present\",Ann C. Hall,30.95,9780786442652,MTV,2009\r\n"
			+ "\"Pimps, Wimps, Studs, Thugs And Gentlemen - Essays On Media Images of Masculinity\",Karen Schoemer,39.95,9780786443055,MTV,2009\r\n"
			+ "Popcorn Prozac - Movies To Cure The Recession Depression!,,19.95,9781887664943,MTV,2009\r\n"
			+ "Queens of Screams - The New Blood,,14.95,1593934556,MTV,2009\r\n"
			+ "Quincy - Television Series,James Rosin,16.95,9781593934545,MTV,2009\r\n"
			+ "Ready For My Close-up! - Great Movie Speeches,Denny Martin Flinn (Editor),14.95,0879103507,MTV,2009\r\n"
			+ "\"Rogers, Roy\",Robert W. Phillips,35.95,9780786445899,MTV,2009\r\n"
			+ "\"Sangster, Jimmy - Do You Want It Good Or Tuesday?\",Jimmy Sangster,19.95,1887664130,MTV,2009\r\n"
			+ "Scarlet - The Film Magazine #3,,7.95,,MTV,2009\r\n"
			+ "Scary Monsters #71,Dennis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #63,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #64,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #65,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #66,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #67,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #68,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #69,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #70,Denis Druktenis,7.95,,MTV,2009\r\n"
			+ "Scary Monsters Magazine #72,Dennis Druktenis,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #10 [Limited Stock],Darryl Mayeski,5.95,,MTV,2009\r\n"
			+ "Screem Magazine #11,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #12,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #15,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #16,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #17,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #18,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "Screem Magazine #19,Darryl Mayeski,7.95,,MTV,2009\r\n"
			+ "\"Sellers, Peter - The Life And Death of Peter Sellers\",Roger Lewis,11.95,1557833575,MTV,2009\r\n"
			+ "Silent Mystery And Detective Movies - A Comprehensive Filmography,Ken Wlaschin,49.95,9780786443505,MTV,2009\r\n"
			+ "\"Stanley, John - I Was A TV Horror Host [Author-Signed Edition]\",John Stanley,16.95,9780940064119,MTV,2009\r\n"
			+ "Star Trek - Gender and Sexuality in Star Trek,David Greven,29.95,9780786444137,MTV,2009\r\n"
			+ "Superman - Flights of Fantasy: The Unauthorized But True Story of Radio & TV's Adventures of Superman,Michael J. Hayde,24.95,1593933444,MTV,2009\r\n"
			+ "Tarzan - Denny Miller: Didn't You Used to Be What's His Name? [Author-Signed Edition],Denny Miller,29.95,0975391704,MTV,2009\r\n"
			+ "Television Game Show Hosts,David Baber,29.95,9780786445738,MTV,2009\r\n"
			+ "\"Temple, Shirley - Pictorial History\",Rita Dubas,19.95,1557836728,MTV,2009\r\n"
			+ "\"Terrorism in American Cinema - An Analytical Filmography, 1960-2008\",Robert Cettl,42.95,9780786441556,MTV,2009\r\n"
			+ "\"Total Television Productions - Created And Produced By Total Television Productions: The Story of Underdog, Tennessee Tuxedo And The Rest\",Mark Arnold,24.95,1593933452,MTV,2009\r\n"
			+ "\"Van Dyke, Dick - Official Dick Van Dyke Show Book\",Vince Waldron & Dick Van Dyke (Foreword),12.95,1557834539,MTV,2009\r\n"
			+ "Videoscope #52 Fall 2004,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #53 Winter 2005,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #54 Spring 2005,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #55 Summer 2005,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #56 Fall 2005,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #57 Winter 2006,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #58 Spring 2006,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #59 Summer 2006,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #60 Fall 2006,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #61 Winter 2007,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #62 Spring 2007,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #63 Summer 2007,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #64 Fall 2007,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #65 Winter 2008,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #66 Spring 2008,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #67 Summer 2008,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #68 Fall 2008,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #69 Winter 2008,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #70 Spring 2009,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #71 Summer 2009,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "Videoscope #72 Fall 2009,The Phantom Of The Movies,4.95,,MTV,2009\r\n"
			+ "\"Washburn, Beverly - Reel Tears: The Beverly Washburn Story\",Beverly Washburn,16.95,1593933487,MTV,2009\r\n"
			+ "\"Westerners, The - Interviews with Actors, Directors, Writers and Producers\",C. Courtney Joyner & Miles Swarthout (Foreword),34.95,9780786443031,MTV,2009\r\n"
			+ "Whale's Dracula's Daughter: An Alternate History For Classic Film Monsters,Philip J. Riley,19.95,0593934750,MTV,2009\r\n"
			+ "What You See May Shock You - Postmodern Pulp Fiction [Original Fiction],Michael H. Price & Mark Evan Walker,19.95,1887664858,MTV,2009\r\n"
			+ "\"Wood, Ed - Ed Wood, Mad Genius: A Critical Study of The Films\",Rob Craig,35.95,9780786439553,MTV,2009\r\n"
			+ "\"Wood, Natalie - Natalie Wood: A Life\",Gavin Lambert,4.95,0823088294,MTV,2009\r\n"
			+ "\"Zimbalist, Efrem, Jr. - My Dinner of Herbs\",Efrem Zimbalist Jr.,19.95,9780879109882,MTV,2009\r\n"
			+ "Booked - The Last 150 Years Told Through Mug Shots,Giacomo Papi,5.95,1583227172,NEB,2009\r\n"
			+ "\"Bruce, Lenny - The Trials of Lenny Bruce [Includes Audio CD]\",Ronald K.L Collins & David M. Skover,7.95,1570719861,NEB,2009\r\n"
			+ "Remember The 80s - Now That's What I Call Nostalgia,Richard Evans,19.95,9781906032128,NEB,2009\r\n"
			+ "Science Fiction and Fantasy Artists of the Twentieth Century - A Biographical Dictionary,Jane Frank,119.95,9780786434237,NEB,2009\r\n"
			+ "\"Original Amos 'n' Andy, The - Freeman Gosden, Charles Correll and the 1928-1943 Radio Serial\",Elizabeth McLeod,34.95,9780786445844,OTR,2009\r\n"
			+ "Waging The War of the Worlds - A History of the 1938 Radio Broadcast and Resulting Panic [Includes Original Script],John Gosling & Howard Koch (Radio Script),39.95,9780786441051,OTR,2009\r\n"
			+ "Baseball - Baseball/Literature/Culture: Essays 2006/2007,,39.95,9780786436804,SSM,2009\r\n"
			+ "Baseball / Literature / Culture - Essays 2004-2005,Peter Carino (Editor),39.95,0786426187,SSM,2009\r\n"
			+ "2010 Collector Car Price Guide [Includes Bonus DVD],Ron Kowalke,18.95,0896899713,TPA,2009\r\n"
			+ "Chevrolet Pickups 1973-1998,John Gunnell,19.95,0896896145,TPA,2009\r\n"
			+ "\"Crash! Twisted Steel, Mangled Bumpers & Shattered Windshields From the 40s, 50s & 60s\",John Gunnell,9.95,0896894495,TPA,2009\r\n"
			+ "Indianapolis Motor Speedway - 100 Years Of Racing,Ralph Kramer & Mario Andretti (Foreword) & Tony George (Preface),24.95,0896898350,TPA,2009\r\n"
			+ "\"Legendary Model T Ford, The - The Ultimate History Of America's First Great Automobile\",Tom Collins,33.95,0896895602,TPA,2009\r\n"
			+ "");
		System.out.println(book + " created successfully.");
		b15.close();
}
if(book.equals("books2010.csv")) {
	b16 = new PrintWriter(book);
		b16.println("\"James, Tommy - Me, the Mob, and the Music: One Helluva Ride with Tommy James & The Shondells (Autographed by Tommy James)\",Tommy James & Martin Fitzpatrick,16.98,9781439128657,MRB,2010\r\n"
			+ "Supervillian Book: The Evil Side of Comics and Hollywood,Gina Misiroglu & Michael Eury,24.95,1578591783,CCB,\r\n"
			+ "Who's Who In Animated Cartoons: An International Guide to Film and Television's Award-Winning and Legendary Animators,Jeff Lenburg,15.95,155783671X,CCB,\r\n"
			+ "\"Amos, Tori - Images & Insights (Rare Photo Collection And Quotations)\",Michael Friel & Melody Mcdaniel & Cindy Palmano & Rankin,1,0825615674,MRB,\r\n"
			+ "\"Atkins, Chet - Me And My Guitars\",Chet Atkins & Michael Cochran,19.95,0634055658,MRB,\r\n"
			+ "\"Bacharach, Burt - Song By Song\",Serene Dominic,11.95,0825672805,MRB,\r\n"
			+ "Bay City Rollers - Bye Bye Baby: My Tragic Love Affair With The Bay City Rollers,Caroline Sullivan,2.95,1582340552,MRB,\r\n"
			+ "Beastie Boys - The Beastie Boys Companion - Two Decades of Commentary,John Rocco,1,0028653343,MRB,\r\n"
			+ "Beatles - Diary: An Intimate Day By Day History (Pocket Edition),Barry Miles,8.95,0711991960,MRB,\r\n"
			+ "Beatles - Dream Is Over,By Keith Badman,3.95,0711988021,MRB,\r\n"
			+ "Billboard's Top R&B And Hip-Hop Singles (1942 To 2004),,39.95,0898201608,MRB,\r\n"
			+ "Black Sabbath - The Ozzy Years (Embossed Cover And Rare Photo Collection),Robert V. Conte & C.J. Henderson,1.99,1890313998,MRB,\r\n"
			+ "Blues - From Robert Johnson To Robert Clay,Tony Russell,5.95,0028648862,MRB,\r\n"
			+ "\"Boone, Pat - April Love: The Early Days of Rock 'N' Roll - The Authorized Biography\",Paul Davis,4.95,,MRB,\r\n"
			+ "British Invasion - How The Beatles And Other UK Bands Conquered America,Bill Harry,6.95,,MRB,\r\n"
			+ "\"Brown, Ruth - Miss Rhythm: The Autobiography of Ruth Brown, Rhythm & Blues Legend\",Ruth Brown & Andrew Yule,9.95,0306808889,MRB,\r\n"
			+ "\"Buffet, Jimmy - Jimmy Buffet Scrapbook\",Mark Humphrey & Harris Lewine,3.95,0806514612,MRB,\r\n"
			+ "\"Carmichael, Hoagy - Stardust Melody: The Life And Music of Hoagy Carmichael\",Richard M. Sudhalter,5.95,0195168984,MRB,\r\n"
			+ "\"Cash, Johnny - Man In White: A Novel About The Apostle Paul\",Johnny Cash,19.95,159554237X,MRB,\r\n"
			+ "\"Cash, Johnny - Ring of Fire: Tribute (with Quotes From Johnny's Family, Friends And Colleagues And A Bonus CD Single)\",Brian Mansfield,3.95,1401601375,MRB,\r\n"
			+ "\"Cash, Johnny - The Life of An American Icon\",Stephen Miller,15.95,0711996261,MRB,\r\n"
			+ "Cher - The First Time,Cher as told to Jeff Coplon,4.95,0316848441,MRB,\r\n"
			+ "Chicago - Feelin' Stronger Every Day,Ben Joseph,1.99,1550822454,MRB,\r\n"
			+ "Chieftains - Authorized Biography,John Glatt,5.95,0306809222,MRB,\r\n"
			+ "\"Cole, Natalie - Angel On My Shoulder: An Autobiography\",Natalie Cole & Digby Diehl,4.95,0446527467,MRB,\r\n"
			+ "Complete Book of Doo Wop,Dr. Anthony J. Gribin & Dr. Matthew M. Schiff,24.95,0977379841,MRB,\r\n"
			+ "Cranberries,Michael Heatley,1,1578990203,MRB,\r\n"
			+ "\"Darin, Bobby - Me And Bobby D.: A Memoir\",Steve Karmen,16.95,0634048767,MRB,\r\n"
			+ "\"Davies, Ray - X-Ray\",Ray Davies,5.95,0879516119,MRB,\r\n"
			+ "Deep Purple - The Deep Purple Story: Smoke On The Water,Dave Thompson,12.95,,MRB,\r\n"
			+ "\"Dion, Celine - Falling Into You\",Barry Grills,1.49,155082189X,MRB,\r\n"
			+ "\"Dylan, Bob - Performing Artist, Volume 2 - The Middle Years: 1974-1986\",Paul Williams,1.99,0711935556,MRB,\r\n"
			+ "\"Dylan, Bob - Performing Artist, Volume 3 - Mind Out of Time: 1986-1990 & Beyond\",Paul Williams,15.98,1844492818,MRB,\r\n"
			+ "\"Freed, Alan Story - The Early Years of Rock & Roll\",John A. Jackson,6.95,0977379809,MRB,\r\n"
			+ "Garage Rock And Its Roots - Musical Rebels And The Drive For Individuality,Eric James Abbey & Michelangelo Capua,28.95,0786425644,MRB,\r\n"
			+ "\"Gillespie, Dizzy - Groovin' High: The Life of Dizzy Gillespie\",Alyn Shipton,6.95,0195144104,MRB,\r\n"
			+ "Girl Groups: Fabulous Females That Rocked The World,John Clemente,7.95,0873418166,MRB,\r\n"
			+ "Grateful Dead - Living With The Dead: Twenty Years On The Bus With Garcia And The Grateful Dead,Rock Scully & David Dalton,3.95,0316777129,MRB,\r\n"
			+ "Grateful Dead - Not Fade Away: The Online World Remembers Jerry Garcia,,1.95,1560251255,MRB,\r\n"
			+ "Great Rock Drummers of the Sixties (Revised),Bob Cianci,16.95,0634099256,MRB,\r\n"
			+ "\"Hampton, Lionel - Hamp\",Lionel Hampton & James Haskins,4.99,1567430198,MRB,\r\n"
			+ "\"Hendrix, Jimi - Voodoo Child: The Illustrated Legend of Jimi Hendrix (with CD)\",Illustrated By Bill Sienkiewicz & Created & Produed By Martin I. Green,8.95,0670867896,MRB,\r\n"
			+ "Hot Country Stars,Michael McCall & Janet Williams,1.99,1561737291,MRB,\r\n"
			+ "\"Jones, George - Why Baby Why\",Jim Brown,1.99,1550822438,MRB,\r\n"
			+ "Madonna - Complete Guide To Her Music,Rikky Rooksby,2.95,0711998833,MRB,\r\n"
			+ "Madonna - In Her Own Words,Mick St Michael,5.95,0711977348,MRB,\r\n"
			+ "Mamas & The Papas - California Dreamin',Doug Hall,1.99,1880522160,MRB,\r\n"
			+ "\"Manilow, Barry - Biography\",Patricia Butler,3.95,0711991979,MRB,\r\n"
			+ "\"Marley, Bob - Complete Guide To His Music\",Ian Mccann & Harry Hawke,2.95,0711998841,MRB,\r\n"
			+ "\"McCartney, Paul - I Saw Him Standing There\",Jorie B. Gracen,12.95,0823083691,MRB,\r\n"
			+ "\"McCartney, Paul - Now & Then\",Tony Barrow & Robin Bextor,19.95,0634069195,MRB,\r\n"
			+ "Monkees - Collectibles Price Guide,Marty Eck,13.95,0930625188,MRB,\r\n"
			+ "\"Music Hound - Soundtracks: The Essential Album Guide To Film, Television, And Stage Music\",Didieer C. Deutsh,4.95,,MRB,\r\n"
			+ "\"Music, Money, And Success - The Insider's Guide To Making Money In The Music Industry (3rd Ed.)\",Todd Brabec & Jeffrey Brabec,2.5,0825672821,MRB,\r\n"
			+ "Nirvana - Complete Guide To Their Music,James Hector,2.95,0711998876,MRB,\r\n"
			+ "\"Nirvana And The Grunge Revolution - Interviews (Also Pearl Jam, Alice In Chains, Soundgarden)\",Guitar World Magazine,10.95,079359006X,MRB,\r\n"
			+ "Nirvana Tribute - Nirvana / Hole,,1,,MRB,\r\n"
			+ "Pink Floyd - Interviews,Guitar World Magazine,9.95,0634032860,MRB,\r\n"
			+ "\"Pop, Iggy - Neighbourhood Threat: On Tour With Iggy Pop\",Alvin Gibbs,4.95,1899598170,MRB,\r\n"
			+ "\"Presley, Elvis - Elvis Film Encyclopedia: An Impartial Guide To The Films of Elvis\",Eric Braun,6.99,0879518146,MRB,\r\n"
			+ "\"Presley, Elvis - Elvis Inc.: The Fall And Rise of The Presley Empire\",Sean O'Neal,2.95,076151127X,MRB,\r\n"
			+ "\"Presley, Elvis - Sergeant Presley: Our Untold Story of Elvis' Missing Years\",Rex & Elisabeth Mansfield & Marshall & Zoe Terrill,5.95,1550225553,MRB,\r\n"
			+ "Queen - The Art of Queen - The Making of The Eye,,1.99,0765191261,MRB,\r\n"
			+ "R.E.M. - The Rolling Stone Files,,2.95,0786880546,MRB,\r\n"
			+ "Radiohead - Green Plastic Wateringcan,William Stone,1,188689454X,MRB,\r\n"
			+ "\"Redding, Otis - Otis!: Otis Redding Story\",Scott Freeman,5.95,0312302975,MRB,\r\n"
			+ "\"Rich, Buddy - Traps: The Drum Wonder - The Life of Buddy Rich\",Mel Torme,10.5,1888408022,MRB,\r\n"
			+ "Rock 'N' Roll Years 1960-2000 - The Photographer's Cut,,2.99,0953747913,MRB,\r\n"
			+ "\"Sinatra, Frank - My Father's Daughter: A Memoir\",Tina Sinatra (With Jeff Coplon),5.95,0684870762,MRB,\r\n"
			+ "\"Sinatra, Frank - Sessions With Sinatra: Frank Sinatra And The Art of Recording\",Charles Granata,13.95,1556523564,MRB,\r\n"
			+ "Soulful Divas,David Nathan,11.5,0823084302,MRB,\r\n"
			+ "\"Spector, Phil - Out of His Head\",Richard Williams,4.95,0711998647,MRB,\r\n"
			+ "\"Springsteen, Bruce - Talking: In His Own Words\",John W. Duffy,8.95,1844494039,MRB,\r\n"
			+ "Top 500 Heavy Metal Albums of All Time,Martin Popoff,14.95,,MRB,\r\n"
			+ "Turn On Your Mind - Four Decades of Great Psychedelic Rock,Jim Derogatis,12.95,0634055488,MRB,\r\n"
			+ "U2 - Complete Guide To Their Music,Bill Graham & Caroline Van Der Oosten De Boer,2.95,0711998868,MRB,\r\n"
			+ "\"Vaughan, Stevie Ray - In His Own Words\",Guitar World Magazine,10.5,0793580803,MRB,\r\n"
			+ "Yes - Close To The Edge: The Story of Yes,Chris Welch,3.95,0711995095,MRB,\r\n"
			+ "\"Brando, Marlon - A Biography\",Patricia Bosworth,4.95,,MTV,\r\n"
			+ "\"Brynner, Yul - A Biography\",Michelangelo Capua,30.95,0786424613,MTV,\r\n"
			+ "\"Ferrara, Abel - The King of New York\",Nick Johnstone,2.95,071197652X,MTV,\r\n"
			+ "Monster Bash Magazine (Issue #1),,7,,MTV,\r\n"
			+ "Monster Bash Magazine (Issue #2),,7,,MTV,\r\n"
			+ "Monster Bash Magazine (Issue #3),,7,,MTV,\r\n"
			+ "Monster Bash Magazine (Issue #4),,7,,MTV,\r\n"
			+ "Monster Bash Magazine (Issue #5),,7,,MTV,\r\n"
			+ "\"Presley, Elvis - Elvis Cinema and Popular Culture\",Douglas Brode,30.95,0786425261,MTV,\r\n"
			+ "\"Raimi, Sam - Unseen Force: The Films of Sam Raimi\",John Kenneth Muir,14.95,1557836078,MTV,\r\n"
			+ "\"Robinson, Bill - Mr. Bojangles\",Jim Haskins & N.R. Mitgang,7.95,,MTV,\r\n"
			+ "Superman - Art of Superman Returns,Daniel Wallace,29.95,0811853446,MTV,\r\n"
			+ "\"Tarantino, Quentin - The Cinema of Cool\",Jeff Dawson,10.95,1557832277,MTV,\r\n"
			+ "Tarzan - My Father,Johnny Weissmuller Jr. & William Reed & W. Craig Reed,19.95,1550225227,MTV,\r\n"
			+ "Wizardry of Oz - The Artistry And Magic of The 1939 Mgm Classic (Revised & Expanded Edition),Jay Scarfone & William Stillman,13.95,1557836248,MTV,\r\n"
			+ "\"Young, Alan - There's No Business Like Show Business... Was\",Alan Young,12.95,1593930534,MTV,\r\n"
			+ "Pop-Up Book of Nightmares,Gary Greenberg,4.95,031228263X,NEB,\r\n"
			+ "Racing - Legends of Stock Car Racing,Dr. John Craft,5.95,0760301441,SSM,\r\n"
			+ "");
		System.out.println(book + " created successfully.");
	b16.close();
}
*/
