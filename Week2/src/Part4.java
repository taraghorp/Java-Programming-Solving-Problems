/**
 * @author Tara G.
 */

import edu.duke.*;

public class Part4 {
	
    public void printUrls(String url) {
        URLResource myurl = new URLResource(url);
        for(String word : myurl.words()) {
            if(word.toLowerCase().indexOf("youtube.com") != -1) {
                int quoteIndex = word.indexOf("\"");
                int lastQouteIndex = word.indexOf("\"", quoteIndex+1);
                System.out.println(word.substring(quoteIndex+1, lastQouteIndex));
                
            }
        }
    }
    
    public String mystery(String dna) {
    	  int pos = dna.indexOf("T");
    	  int count = 0;
    	  int startPos = 0;
    	  String newDna = "";
    	  if (pos == -1) {
    	    return dna;
    	  }
    	  while (count < 3) {
    	    count += 1;
    	    newDna = newDna + dna.substring(startPos,pos);
    	    startPos = pos+1;
    	    pos = dna.indexOf("T", startPos);
    	    if (pos == -1) {
    	      break;
    	    }
    	  }
    	  newDna = newDna + dna.substring(startPos);
    	  return newDna;
    	}
    
    public void testUrl() {
        printUrls("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    
    public static void main(String[] args) {
        Part4 url = new Part4();
        url.testUrl();
        System.out.println(url.mystery("ABCTDEFTHIJTSDTASD"));
    }
}
