package ddw.com;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Area extends Actor{
	
	public Area(int x, int y) {
		super(x, y);
		
		init();
	}
	
//	private void initArea() {
//		ImageIcon iicon = new ImageIcon("src/resources/area.png");
//		Image image = iicon.getImage();
//		setImage(image);
//	}
	
	@Override
	public void init() {
		ImageIcon iicon = new ImageIcon("src/resources/area.png");
		Image image = iicon.getImage();
		setImage(image);
	}

}
