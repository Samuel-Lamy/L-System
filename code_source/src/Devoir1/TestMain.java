package Devoir1;

import java.awt.geom.Point2D;
import java.io.*;
import java.text.DecimalFormat;

public class TestMain {

	
	
	public static void main(String[] args) throws IOException{
		DecimalFormat df = new DecimalFormat("#");
		String file = args[0];
		int n = Integer.parseInt(args[1]);
		
		
		
		LSystem test = new LSystem(file);
		test.applyRules(n);
		Point2D point = new Point2D.Double(test.getParametres(2),test.getParametres(3));
		
		Turtletest testTurtle = new Turtletest(point, test.getParametres(4), test.getParametres(0), test.getParametres(1));
		
		for (int i = 0; i<test.getAxiom().size(); i++) {
			test.tell(testTurtle, test.getAxiom().get(i));

		}
		
		System.out.println("stroke");
		System.out.println("%%Trailer");
		System.out.println("%%BoundingBox: " + df.format(testTurtle.minX-1) + " " + df.format(testTurtle.minY-1) + " " + df.format(testTurtle.maxX+1) + " " + df.format(testTurtle.maxY+1));
		System.out.println("%%EOF");
		
		

	}
	

}
