package ddw.com;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends Actor{
	
	public Player(int x, int y) {
		super(x, y);
		init();
	}
	
//	private void initPlayer() {
//		ImageIcon iicon = new ImageIcon("src/resources/sokoban.png");
//		Image image = iicon.getImage();
//		setImage(image);
//	}
	
	public void move (int x, int y) {
		int dx = x() + x;
		int dy = y() + y;
		
		setX(dx);
		setY(dy);
	}
	
	@Override
	public void init() {
		ImageIcon iicon = new ImageIcon("src/resources/sokoban.png");
		Image image = iicon.getImage();
		setImage(image);
	}
	

	public void init(String direction) {
		ImageIcon iicon = null;
		if(direction.equals("top")) {
			iicon = new ImageIcon("src/resources/sokoban_back.png");
		}else if(direction.equals("down")) {
			iicon = new ImageIcon("src/resources/sokoban.png");
		}else if(direction.equals("left")) {
			iicon = new ImageIcon("src/resources/sokoban_left.png");
		}else if(direction.equals("right")) {
			iicon = new ImageIcon("src/resources/sokoban_right.png");
		}
		Image image = iicon.getImage();
		setImage(image);
	}
}
