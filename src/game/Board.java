package game;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel{
	
	private final int OFFSET = 30;
	private final int SPACE = 20;
	private final int LEFT_COLLISION = 1;
	private final int RIGHT_COLLISION = 2;
	private final int TOP_COLLISION = 3;
	private final int BOTTOM_COLLISION = 4;
	
	private ArrayList<Wall> walls;
	private ArrayList<Box> boxes;
	private ArrayList<Area> areas;
	
	private Player player;
	private int w = 0;
	private int h = 0;
	
	private boolean isCompleted = false;

	public static void main(String[] args) {
		new Board();
	}

}
