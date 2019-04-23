package ddw.com;

import java.awt.Image;

public class Actor {
	
	private final int SPACE = 25;
	
	private int x;
	private int y;
	private Image image;
	
	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image img) {
		image = img;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isLeftCollision(Actor actor) {
		return x() - SPACE == actor.x && y() == actor.y();
	}
	
	public boolean isRightCollision(Actor actor) {
		return x() + SPACE == actor.x && y() == actor.y();
	}
	
	public boolean isTopCollision(Actor actor) {
		return x() == actor.x && y() - SPACE == actor.y();
	}
	
	public boolean isBottomCollision(Actor actor) {
		return x() == actor.x && y() + SPACE == actor.y();
	}
	
	public void init() {
		setImage(null);
	}

}
