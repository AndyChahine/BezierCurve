package dev.andrew;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window {

	private JFrame frame;
	private Canvas canvas;
	private Graphics2D g;
	private BufferStrategy bs;

	public Window(int width, int height, String title) {
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.addMouseListener(new Mouse());
		canvas.addMouseMotionListener(new Mouse());
		canvas.addMouseWheelListener(new Mouse());
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		g = (Graphics2D) bs.getDrawGraphics();
	}
	
	public void clear() {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void render() {
		bs.show();
	}
	
	public Graphics2D getGraphics() {
		return g;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public int getWidth() {
		return canvas.getWidth();
	}
	
	public int getHeight() {
		return canvas.getHeight();
	}
}
