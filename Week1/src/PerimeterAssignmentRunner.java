/**
 * @author Tara G.
 */

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
    	int points = 0;
    	for (Point p : s.getPoints())
    		points++;
        // Put code here
        return points;
    }

    public double getAverageLength(Shape s) {
    	// Put code here
        return (double)(getPerimeter(s)/getNumPoints(s));
    }

    public double getLargestSide(Shape s) {
    	Point lastPoint = s.getLastPoint();
        double largestSide = 0.0;
        
        for(Point p : s.getPoints()){
            double dist = lastPoint.distance(p);
            if(dist > largestSide) {
                largestSide = dist;
            }
            lastPoint = p;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
    	Point lastPoint = s.getLastPoint();
        int lastPointX = lastPoint.getX();
        double largestX = (double) lastPointX;
        
        for(Point p : s.getPoints()){
            int newX = p.getX();
            if(newX > largestX) {
                largestX = (double) newX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
    	DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        FileResource largestFile = null;

        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
    	DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        File largestFile = null;

        for(File f : dr.selectedFiles()){
            FileResource file = new FileResource(f);
            Shape shape = new Shape(file);
            double perim = getPerimeter(shape);
            if(perim > largestPerim) {
                largestPerim = perim;
                largestFile = f;
            }
        }

        return largestFile.getName();
        
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest4.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points:" + getNumPoints(s));
        System.out.println("Average Length:" + getAverageLength(s));
        System.out.println("Largest Side: " + getLargestSide(s));
        System.out.println("Largest X: " + getLargestX(s) );
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }
    
    public void testPerimeterMultipleFiles() {
    	double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
    	String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
