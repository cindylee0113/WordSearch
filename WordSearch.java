//Cindy Lee Period 9

import java.util.*;
import java.io.*;

public class WordSearch {

    private ArrayList <String> track;
    private char [][] search;
    
    public WordSearch () {
	search = new char [10][10];
	track = new ArrayList <String> ();
    }

    public WordSearch (int rows, int cols) {
	search = new char [rows] [cols];
    }

    public String toString () {
	String s = "";
	for (int i = 0; i < search.length; i++) {
	    for (int j= 0; j< search[i].length; j++) {
		if (search [i][j] == '\0') {
		    s += " - ";
		}
		else{
		    s += ((Character)search [i][j]).toString() + " ";
		}
	    }
	    s+= "\n";
	}
	s += "\n  WORDS IN SEARCH: \n";
        for (int i = 0; i < track.size(); i++ ) {
            s += track.get (i) + "\n";
        }
	return s;
    }
	     

    public boolean addWordH (int row, int col, String s) {
	if ((row >= 0 && row < search.length) && (col >= 0 && col < search[row].length)) {
	    if (search[row].length - col >= s.length () ) {
		int i = 0;
		int clear = col;
		String u = s.toUpperCase ();
		while (i < s.length ()) {
		    if (search[row][col] != '\0') {
			if (search[row][col] != u.charAt (i)) {
			    return false;
			}
			else {
			    col++;
			    i++;
			}
		    }
		    else {
			col++;
			i++;
		    }
		}
	        int j = 0; 
		while (j < s.length ()){
		    if (search[row][clear] == '\0') {
			search[row][clear] = u.charAt (j);
			clear += 1;
			j++;
		    }
		    else {
			clear +=1;
			j++;
		    }
		}
		track.add (u);
	        return true;
	    }
	    else {
		return false;
	    }
	}
	else {
	    return false;
	}
    }

    public boolean addWordV (int row, int col, String s) {
	if ((row >=0 && row < search.length) && (col >= 0 && col < search[row].length)) {
	    if (search.length - row >= s.length()) {
		int i = 0;
	        int clear = row;
		String u = s.toUpperCase();
		while (i < s.length ()) {
		    if (search[row][col] != '\0') {
			if (search[row][col] != u.charAt (i)) {
			    return false;
			}
			else {
			    row++;
			    i++;
			}
		    }
		    else {
			row++;
			i++;
		    }
		}
		int j = 0;
                while (j < s.length ()){
                    if (search[clear][col] == '\0') {
                        search[clear][col] = u.charAt (j);
                        clear += 1;
                        j++;
                    }
                    else {
                        clear +=1;
                        j++;
                    }
                }
		track.add (u);
		return true;
	    }
	    else {
		return false ;
	    }
	}
	else {
	    return false;
	}
    }

    public boolean addWordD (int row, int col, String s) {
	if ((row >= 0 && col >=0) && (row < search.length && col < search[row].length)) {
	    if (search[row].length - col >= s.length () && search.length - row >= s.length() ){
		int i = 0;
		String u = s.toUpperCase ();
		int clear = row;
		int meh = col;
                while (i < s.length ()) {
                    if (search[row][col] != '\0') {
                        if (search[row][col] != u.charAt (i)) {
                            return false;
                        }
                        else {
                            row++;
			    col++;
                            i++;
                        }
                    }
                    else {
                        row++;
			col++;
                        i++;
                    }
                }
		int j = 0;
                while (j < s.length ()){
                    if (search[clear][meh] == '\0') {
                        search[clear][meh] = u.charAt (j);
                        clear += 1;
			meh+=1;
                        j++;
                    }
                    else {
                        clear +=1;
			meh++;
                        j++;
                    }
                }
		track.add (u);
		return true;
	    }
	    else {
		return false;
	    }
	}
	else {
	    return false; 
	}
    }

    public void fillGrid () {
	Random randy = new Random ();
	String alpha = "ABCDEFGHIJKLMNOPQRSTUVXWYZ";
	for (int r = 0; r < search.length; r++) {
	    for (int c = 0; c < search[r].length; c++) {
		if (search[r][c] == '\0') {
		    int n = randy.nextInt (alpha.length ());
		    search [r][c] = alpha.charAt (n);
		}
	    }
	}
    }

    public ArrayList<String> loadDictionary() {
	String s = "zzz";
	ArrayList<String> dictionary = new ArrayList<String>();
     
	try {
	    FileReader f = new FileReader("wordlist.txt");
	    BufferedReader b = new BufferedReader(f);
 
	    while( s != null ) {
		s = b.readLine();
		if ( s != null )
		    dictionary.add(s);
            }
        }
	catch (IOException e) {}
   
	return dictionary;
    }

    public static void main (String [] args) {

	WordSearch ws = new WordSearch();
        
	ArrayList <String> dictionary = new ArrayList <String> ();
	dictionary = ws.loadDictionary ();
	int what = 0;
	Random rand = new Random ();
	Random cand = new Random ();
	Random numb = new Random ();
	while (what < dictionary.size()) {
	    int number = numb.nextInt (dictionary.size());
	    int randy = rand.nextInt (10);
	    int candy = rand.nextInt (10);
	    boolean hori = ws.addWordH (randy,candy, dictionary.get(number));
	    if (hori == true) {
		dictionary.remove (number);
	    }
	    else {
		boolean verti = ws.addWordV (randy,candy, dictionary.get(number));
		if (verti == true) {
		    dictionary.remove (number);
		}
		else {
		    boolean diag = ws.addWordD (randy,candy, dictionary.get(number));
		    if (diag == true) {
			dictionary.remove (diag);
		    }
		    else {
			what++;
		    }
		}
	    }
	}

	/*Check
        //working horizontal words
        ws.addWordH(0, 0, "hello");
        ws.addWordH(2, 4, "batman");
        ws.addWordH(5, 1, "apple");
	
        //Horizontal index error checking
        ws.addWordH(-2, 4, "joker");
        ws.addWordH(10, 4, "unicorn");  
        ws.addWordH(3, -1, "cowboys");
        ws.addWordH(5, 8, "dogs");
	
        //horizontal collision checking
        ws.addWordH(5, 3, "plow");
        ws.addWordH(2, 0, "neato");
       
        //working vertical words
        ws.addWordV(1, 0, "nice");
        ws.addWordV(4, 9, "yankee");
        ws.addWordV(4, 4, "old");
	
        //Verical index error checking
        ws.addWordV(-2, 4, "joker");
        ws.addWordV(7, 4, "unicorn");   
        ws.addWordV(3, -1, "cowboys");
        ws.addWordV(5, 20, "dogs");
        
        //vertical collision checking
        ws.addWordV(0, 4, "ores");
        ws.addWordV(4, 9, "goober");
	
        //working diagonal words
        ws.addWordD(7, 0,  "cat");
        ws.addWordD(0, 0, "home");
        ws.addWordD(0, 3, "loam");
	/*
        //Diagonal index error checking
        ws.addWordD(-2, 0,  "cat");
        ws.addWordD(3, -1,  "whelm");
        ws.addWordD(7, 7,  "after");    

        //Diagonal collision checking
        ws.addWordD(0, 4, "ores");
        ws.addWordD(4, 4, "oats");*/

        System.out.println(ws);
        
        ws.fillGrid();
        System.out.println(ws);
    }
}


    