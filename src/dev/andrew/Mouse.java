package dev.andrew;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private static boolean[] mouseButtons = new boolean[5];
	private static boolean[] lastMouse = new boolean[5];
	
	private static int mouseX;
	private static int mouseY;
	
	private static float scrollAmount;
	
	private static boolean scrolling;
	
	public static void update(){
		scrollAmount = 0;
		scrolling = false;
		for(int i = 0; i < 5; i++){
			lastMouse[i] = getMouse(i);
		}
	}
	
	public static boolean getMouse(int mouseButton){
		return mouseButtons[mouseButton];
	}
	
	public static boolean getMouseDown(int mouseButton){
		return getMouse(mouseButton) && !lastMouse[mouseButton];
	}
	
	public static boolean getMouseUp(int mouseButton){
		return !getMouse(mouseButton) && lastMouse[mouseButton];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int code = e.getButton();
		if(code >= 0 && code <= mouseButtons.length) {
			mouseButtons[code] = true;
		}
		
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int code = e.getButton();
		if(code >= 0 && code <= mouseButtons.length) {
			mouseButtons[code] = false;
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scrollAmount = e.getUnitsToScroll();
		scrolling = true;
	}
	
	public static boolean isScrolling() {
		return scrolling;
	}
	
	public static float getScrollAmount() {
		return scrollAmount;
	}
	
	public static int getMouseX() {
		return mouseX;
	}
	
	public static int getMouseY() {
		return mouseY;
	}
}
