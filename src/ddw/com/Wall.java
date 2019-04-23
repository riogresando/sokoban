package ddw.com;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Actor{
	
//	private Image image;
	
	public Wall(int x, int y) {
		super(x, y);
		init();
	}
	
//	private void initWall() {
//		ImageIcon iicon = new ImageIcon("src/resources/wall.png");
//		image = iicon.getImage();
//		setImage(image);
//	}
	
	@Override
	public void init() {
		ImageIcon iicon = new ImageIcon("src/resources/wall.png");
		Image image = iicon.getImage();
		setImage(image);
		
	}

}
