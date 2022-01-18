package Devoir1;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.Stack;


public class Turtletest implements Turtle{
	private double x, y; 		//tortue en position(x, y)
	private double angle;		//degre a partir de l axe des x en directions anti-horaire
	private Stack<State> stack; //pile contenant les etats
	private double d, delta;	//d est la distance parcourue et delta est l angle de rotation
	private DecimalFormat df;
	public double minX, minY, maxX, maxY;

	
	/**
	 * fonction qui permet de dessiner une ligne (pas implementer car on a pas besoin de faire le dessin
	 * @param valeur x intiale
	 * @param valeur y initiale
	 * @param nouvelle valeur de x
	 * @param nouvelle valeur de y
	 * (x1,y1) position initiale, (x2,y2) nouvelle position
	 */
	
	public Turtletest(Point2D position, double angle_deg, double d, double delta){
		init(position, angle_deg);
		this.d = d;
		this.delta = delta;
		this.minX = position.getX();
		this.minY = position.getY();
		this.maxX = position.getX();
		this.maxY = position.getY();
		df = new DecimalFormat("#.0");
		
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		//fct qui permet de dessiner une ligne entre 2 points
		System.out.println(df.format(x2) + " " + df.format(y2) + " L");
	}
	

	public void draw() {
		double x1= x;
		double y1= y;
		
		//il aurrait ete possible de mettre x et y au lieu de x2 et y2 
		double x2= d*Math.cos(Math.toRadians(angle)) + x1;
		double y2= d*Math.sin(Math.toRadians(angle)) + y1;
		drawLine(x1, y1, x2, y2);
		
		x= x2;
		y= y2;
		
		if(x<minX) {
			minX = x;
		}
		if(y<minY) {
			minY = y;
		}
		if(x>maxX) {
			maxX = x;
		}
		if(y>maxY) {
			maxY = y;
		}
	}


	public void move() {

		x+= d*Math.cos(Math.toRadians(angle));
		y+= d*Math.sin(Math.toRadians(angle));
		
	}


	public void turnR() {
		angle += delta;
	}
	

	public void turnL() {
		angle -= delta;
	}

	/**
	 * sauvegarder (empile) l etat courant (position + angle) de la tortue. 
	 * L etat ne change pas.
	 */
	public void push() {
		stack.push(new State(x, y, angle));
		System.out.println("stroke");
		System.out.println(df.format(x) + " " + df.format(y) + " newpath M");

	}


	public void pop() {
		State s= stack.pop();
		x=s.getX();
		y=s.getY();
		angle=s.getAngle();
		System.out.println("stroke");
		System.out.println(df.format(x) + " " + df.format(y) + " newpath M");

	}
	
	/**
	 * ne rien faire. L etat ne change pas.
	 */
	public void stay() {}
	
	/**
	 * initializes the turtle state (and clears the state stack)
     * @param pos turtle position
     * @param angle_deg angle in degrees (90=up, 0=right)
	 */
	public void init(Point2D position, double angle_deg) {
		stack=new Stack<State>();
		x=position.getX();
		y=position.getY();
		angle=angle_deg;
		
		System.out.println("%!PS-Adobe-3.0 EPSF-3.0");
		System.out.println("%%Title: L-system");
		System.out.println("%%Creator: lindenmayer.EPSTurtle");
		System.out.println("%%BoundingBox: (atend)");
		System.out.println("%%EndComments");
		System.out.println("/M {moveto} bind def");
		System.out.println("/L {lineto} bind def");
		System.out.println("0.5 setlinewidth");
		System.out.println(position.getX() + " " + position.getY() + " newpath moveto");

		
	}
	
	/**
	 * position of the turtle
     * @return position as a 2D point
	 */
	public Point2D getPosition() {
		Point2D.Double pos = new Point2D.Double(this.x,this.y);
		return pos;
	}
	
	/**
	 * angle of the turtle s nose
     * @return angle in degrees
	 */
	public double getAngle() {
		return this.angle;
	}
	
	/**
	 * sets the unit step and turn
     * @param step length of an advance (move or draw)
     * @param delta unit angle change in degrees (for turnR and turnL)
	 */
	public void setUnits(double step, double delta) {
		this.d=step;
		this.delta=delta;
	}
	
	/**
	 * classe pour encapsuler un etat
	 */
	public class State {
		private double x, y, angle;

		
		public State(double x, double y, double angle) {
			super();
			this.x = x;
			this.y = y;
			this.angle = angle;
		}

		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}

		public double getAngle() {
			return angle;
		}
	}
}
