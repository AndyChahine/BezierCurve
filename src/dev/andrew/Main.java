package dev.andrew;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		Window window = new Window(800, 600, "Bezier Curves");
		Graphics2D g = window.getGraphics();
		ArrayList<Point> points = new ArrayList<Point>();
		Point start = new Point(100, 400);
		Point end = new Point(600, 500);
		int currentControlIndex = 0;
		points.add(start);
		points.add(end);
		float inc = 0.001f;
		
		while(true) {
			window.clear();
			
			if(Mouse.getMouse(MouseEvent.BUTTON1)) {
				points.get(currentControlIndex).setLocation(Mouse.getMouseX(), Mouse.getMouseY());
			}
			
			if(Mouse.getMouseDown(MouseEvent.BUTTON2)) {
				Point p = new Point(Mouse.getMouseX(), Mouse.getMouseY());
				currentControlIndex = points.size() - 1;
				points.add(points.size() - 1, p);
			}
			
			if(Mouse.isScrolling()) {
				if(Mouse.getScrollAmount() > 0) {
					currentControlIndex++;
				}else {
					currentControlIndex--;
				}
				
				if(currentControlIndex >= points.size()) {
					currentControlIndex = 0;
				}else if(currentControlIndex < 0) {
					currentControlIndex = points.size() - 1;
				}
			}
			
			float t = 0;
			for(int k = 0; k <= 1 / inc; k++) {
				float sumX = 0;
				float sumY = 0;
				int n = points.size() - 1;
				for(int i = 0; i < n + 1; i++) {
					long nF = factorial(n); // n!
					long iF = factorial(i); // i!
					long niF = factorial(n - i); // (n - i)!
					long biCo = nF / (iF * niF); // n! / i!(n - i)!
					float poly = (float) (biCo * Math.pow(t, i) * Math.pow(1 - t, n - i));
					sumX += poly * points.get(i).x;
					sumY += poly * points.get(i).y;
				}
				
				g.setColor(Color.WHITE);
				g.fillOval((int)sumX, (int)sumY, 2, 2);
				
				for(int i = 0; i < points.size(); i++) {
					if(i == currentControlIndex && points.size() > 2) {
						g.setColor(Color.RED);
					}else {
						g.setColor(Color.GREEN);
					}
					
					g.fillOval(points.get(i).x - 3, points.get(i).y - 3, 6, 6);
				}
				
				t += inc;
			}
			
			Mouse.update();
			window.render();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static long factorial(int n) {
		long fact = 1;
		for(int i = 2; i <= n; i++) {
			fact = fact * i;
		}
		
		return fact;
	}
}
