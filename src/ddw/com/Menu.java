package ddw.com;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu extends JPanel {
	
	ImageIcon bg = new ImageIcon("src/resources/bg.gif");
	ImageIcon bgBig = new ImageIcon("src/resources/bg_big.jpg");
	
	public Menu() {
		setLayout(null);
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Sokoban.frame.remove(Menu.this);
				Board board = new Board();
				Sokoban.frame.add(board);
				board.requestFocus();
				Sokoban.frame.validate();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(bgBig.getImage(), 0, 0, this);
		
		g.drawImage(bg.getImage(), 200, 73,600,600, this);
	}
}
