/**
 * @author Tara G.
 */

public class Part1 {
	
    public String findSimpleGene(String dna) {
        String result = "";
        int start = dna.indexOf("ATG");
        if(start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAA", start);
        if(stop == -1) {
            return "";
        }
        
        if((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        } else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
		String ap = "CCAATGCAGCGATAC";
		String apa = "CTAATCCGGATCCGA";
		String app = "CCAGCATGCCAGTCAGCTAACAG";
		String appa = "CCAGCATGCCAGTAGCTAACAG";
		
		System.out.println("The string is: " + a); System.out.println("The Gene is: " + findSimpleGene(a));
		System.out.println("The string is: " + ap); System.out.println("The Gene is: " + findSimpleGene(ap));
		System.out.println("The string is: " + apa); System.out.println("The Gene is: " + findSimpleGene(apa));
		System.out.println("The string is: " + app); System.out.println("The Gene is: " + findSimpleGene(app));
		System.out.println("The string is: " + appa); System.out.println("The Gene is: " + findSimpleGene(appa));
    }
    
    public static void main (String[] args) {
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }
}