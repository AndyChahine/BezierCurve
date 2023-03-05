package dev.andrew;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class Main {

	// B(t) = (1 - t) * P1 + (P2 * t)
	// B(t) = (1 - t) * Q1 + (Q2 * t)
	// B(t) = (1 - t) * (P0 * (1 - t) + P1 * t) + t * (P1 * (1 - t) + P2 * t)
	
	public static void main(String[] args) {
		Window window = new Window(800, 600, "Bezier Curves");
		Graphics2D g = window.getGraphics();
		Point p0 = new Point(100, 400);
		Point p3 = new Point(600, 500);
		Point p2 = new Point(350, 75);
		Point p1 = new Point(250, 50);
		float inc = 0.001f;
		
		while(true) {
			window.clear();
			
			if(Mouse.getMouse(MouseEvent.BUTTON1)) {
				p1.setLocation(Mouse.getMouseX(), Mouse.getMouseY());
			}
			
			if(Mouse.getMouse(MouseEvent.BUTTON3)) {
				p2.setLocation(Mouse.getMouseX(), Mouse.getMouseY());
			}
			
			g.setColor(Color.WHITE);
			g.fillOval(p0.x, p0.y, 2, 2);
			g.fillOval(p2.x, p2.y, 2, 2);
			g.fillOval(p1.x, p1.y, 2, 2);
			g.fillOval(p3.x, p3.y, 2, 2);
			
			float t = 0;
			for(int i = 0; i <= 1 / inc; i++) {
				// quadratic curve
//				float bx = (1 - t) * (p0.x * (1 - t) + p1.x * t) + t * (p1.x * (1 - t) + p2.x * t);
//				float by = (1 - t) * (p0.y * (1 - t) + p1.y * t) + t * (p1.y * (1 - t) + p2.y * t);
				
				// cubic curve
				float bx = ((1 - t) * (1 - t) * (1 - t)) * p0.x + 3 * t * ((1 - t) * (1 - t)) * p1.x + 3 * (t * t) * (1 - t) * p2.x + (t * t * t) * p3.x;
				float by = ((1 - t) * (1 - t) * (1 - t)) * p0.y + 3 * t * ((1 - t) * (1 - t)) * p1.y + 3 * (t * t) * (1 - t) * p2.y + (t * t * t) * p3.y;
				g.fillOval((int)bx, (int)by, 2, 2);
				
				t += inc;
			}
			
			window.render();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
