package ddw.com;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Sokoban {
	private final int OFFSET = 30;
	public static JFrame frame;
	
	public Sokoban() {
		initUI();
	}
	
	private void initUI() {
		frame = new JFrame();
//		Board board = new Board();
//		add(board);
		Menu menu = new Menu();
		frame.add(menu);
		frame.setTitle("Sokoban");
		
		frame.setSize(1000, 700);
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
//		EventQueue.invokeLater(() -> {
//			Sokoban game = new Sokoban();
//			game.setVisible(true);
//		});
		new Sokoban();
	}
}
