/**
 * @author Tara G.
 */

import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

public class PerimeterRunner {
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

    public void testPerimeter () {
        FileResource fr = new FileResource("example1.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("example1 perimeter = " + length);
        
        fr = new FileResource("example2.txt");
        s = new Shape(fr);
        length = getPerimeter(s);
        System.out.println("example2 perimeter = " + length);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
