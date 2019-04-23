package ddw.com;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel{
	
	private final int OFFSET = 40;
	private final int SPACE = 25;
	private final int LEFT_COLLISION = 1;
	private final int RIGHT_COLLISION = 2;
	private final int TOP_COLLISION = 3;
	private final int BOTTOM_COLLISION = 4;

	private static enum STATE{ MENU, GAME };
	private static STATE currState = STATE.GAME;
	private int level = 1;
	private String lepel = Level.get(level);
	private int[] completeLevel = new int[41];
	
	private ArrayList<Wall> walls;
	private ArrayList<Baggage> baggs;
	private ArrayList<Area> areas;
	
	private Player soko;
	private int w = 0;
	private int h = 0;
	private int coorX;
	private int coorY;
	private int count = 0;
	
	private JLabel exit;
	private JLabel lv;
	
	private boolean isCompleted = false;
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		setFocusable(true);
		initWorld();
	}
	
	public int getBoardWidth() {
		return this.w;
	}
	
	public int getBoardHeight() {
		return this.h;
	}
	
	private void initWorld() {
		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();
		
		int x = OFFSET;
		int y = OFFSET;
		
		Wall wall;
		Baggage b;
		Area a;
		
		for(int i = 0; i < lepel.length(); i++) {
			char item = lepel.charAt(i);
			switch (item) {
				case '\n':
					y += SPACE;
					if(this.w < x) {
						this.w = x;
					}
					x = OFFSET;
					break;
				case 'X':
					wall = new Wall(x, y);
					walls.add(wall);
					x += SPACE;
					break;
				case '*':
					b = new Baggage(x, y);
					baggs.add(b);
					x += SPACE;
					break;
				case '.':
					a = new Area(x, y);
					areas.add(a);
					x += SPACE;
					break;
				case '@':
					soko = new Player(x, y);
					x += SPACE;
					break;
				case ' ':
					x += SPACE;
					break;
				default:
					break;
			}
			h = y;
		}
	}
	
	private void buildWorld(Graphics g) {
		g.setColor(new Color(250,240,170));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		ArrayList<Actor> world = new ArrayList<>();
		
		world.addAll(walls);
		world.addAll(areas);
		world.addAll(baggs);
		world.add(soko);
		
		for(int i = 0; i < world.size(); i++) {
			Actor item = world.get(i);
			
			if(item instanceof Player || item instanceof Baggage) {
				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			}else {
				g.drawImage(item.getImage(), item.x(), item.y(), this);
			}
			
			if(isCompleted) {
				levelUp();
			}
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(770, 0, 300, 1000);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("Level: " + this.level, 800, 100);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Exit", 800, 500);
		
//		g.drawString("X: " + coorX + " Y: " + coorY, 800, 300);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		int k = 0;
		for (int i = 400; i < 450; i+=15) {
			for (int j = 790; j < 990; j+=20) {
				if(completeLevel[k] == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLACK);	
				}
				g.drawString("" + ++k , j, i);
			}
		}
		
//		exit = new JLabel("Exit");
//		exit.setFont(new Font("Arial", Font.BOLD, 20));
//		exit.setBounds(800, 700, 500, 50);
//		exit.setForeground(new Color(250,240,170));
//		exit.setForeground(Color.BLACK);
//		exit.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.exit(0);
//			}
//			@Override
//			public void mouseEntered(MouseEvent e) {}
//			@Override
//			public void mouseExited(MouseEvent e) {}
//			@Override
//			public void mousePressed(MouseEvent e) {}
//			@Override
//			public void mouseReleased(MouseEvent e) {}
//		});
//		add(exit);
	}
	
	private MouseMotionListener mouseMotionListener = new MouseMotionListener() {
		public void mouseMoved(MouseEvent e) {
			coorX = e.getX();
			coorY = e.getY();
			repaint();
		}
		public void mouseDragged(MouseEvent e) {}
	};
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(currState == STATE.MENU) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		} else if(currState == STATE.GAME) {
			buildWorld(g);
		}
	}
	
	private MouseListener mouseListener = new MouseListener() {
		public void mouseReleased(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseClicked(MouseEvent arg0) {
			if(coorX >= 800 && coorX <= 835) {
				if(coorY >= 480 && coorY <= 500) {
					System.exit(0);
				}
			}
		}
	};
	
	private class TAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(isCompleted) {
				return;
			}
			
			int key = e.getKeyCode();
			
			switch (key) {
				case KeyEvent.VK_LEFT:
					if(checkWallCollision(soko, LEFT_COLLISION)) {
						return;
					}
					if(checkBagCollision(LEFT_COLLISION)) {
						return;
					}
					soko.init("left");
					soko.move(-SPACE, 0);
					break;
				case KeyEvent.VK_RIGHT:
					if(checkWallCollision(soko, RIGHT_COLLISION)) {
						return;
					}
					if(checkBagCollision(RIGHT_COLLISION)) {
						return;
					}
					soko.init("right");
					soko.move(SPACE, 0);
					break;
				case KeyEvent.VK_UP:
					if(checkWallCollision(soko, TOP_COLLISION)) {
						return;
					}
					if(checkBagCollision(TOP_COLLISION)) {
						return;
					}
					soko.init("top");
					soko.move(0, -SPACE);
					break;
				case KeyEvent.VK_DOWN:
					if(checkWallCollision(soko, BOTTOM_COLLISION)) {
						return;
					}
					if(checkBagCollision(BOTTOM_COLLISION)) {
						return;
					}
					soko.init("down");
					soko.move(0, SPACE);
					break;
				case KeyEvent.VK_R:
					restartLevel();
					break;
				case KeyEvent.VK_L:
					levelUp();
					break;
				default:
					break;
			}
			repaint();
		}
	}
	
	private boolean checkWallCollision(Actor actor, int type) {
		switch (type) {
		case LEFT_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if(actor.isLeftCollision(wall)) {
					return true;
				}
			}
			return false;
		case RIGHT_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if(actor.isRightCollision(wall)) {
					return true;
				}
			}
			return false;
		case TOP_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if(actor.isTopCollision(wall)) {
					return true;
				}
			}
			return false;
		case BOTTOM_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if(actor.isBottomCollision(wall)) {
					return true;
				}
			}
			return false;
		
		default:
			break;
		}
		return false;
	}
	
	private boolean checkBagCollision(int type) {
		switch (type) {
		case LEFT_COLLISION:
			for(int i = 0; i < baggs.size(); i++) {
				Baggage bag = baggs.get(i);
				if(soko.isLeftCollision(bag)) {
					for(int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if(!bag.equals(item)) {
							if(bag.isLeftCollision(item)) {
								return true;
							}
						}
						if(checkWallCollision(bag, LEFT_COLLISION)) {
							return true;
						}
					}
					bag.move(-SPACE, 0);
					isCompleted();
				}
			}
			return false;
		case RIGHT_COLLISION:
			for(int i = 0; i < baggs.size(); i++) {
				Baggage bag = baggs.get(i);
				if(soko.isRightCollision(bag)) {
					for(int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if(!bag.equals(item)) {
							if(bag.isRightCollision(item)) {
								return true;
							}
						}
						if(checkWallCollision(bag, RIGHT_COLLISION)) {
							return true;
						}
					}
					bag.move(SPACE, 0);
					isCompleted();
				}
			}
			return false;
		case TOP_COLLISION:
			for(int i = 0; i < baggs.size(); i++) {
				Baggage bag = baggs.get(i);
				if(soko.isTopCollision(bag)) {
					for(int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if(!bag.equals(item)) {
							if(bag.isTopCollision(item)) {
								return true;
							}
						}
						if(checkWallCollision(bag, TOP_COLLISION)) {
							return true;
						}
					}
					bag.move(0, -SPACE);
					isCompleted();
				}
			}
			return false;
		case BOTTOM_COLLISION:
			for(int i = 0; i < baggs.size(); i++) {
				Baggage bag = baggs.get(i);
				if(soko.isBottomCollision(bag)) {
					for(int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if(!bag.equals(item)) {
							if(bag.isBottomCollision(item)) {
								return true;
							}
						}
						if(checkWallCollision(bag, BOTTOM_COLLISION)) {
							return true;
						}
					}
					bag.move(0, SPACE);
					isCompleted();
				}
			}
			return false;

		default:
			break;
		}
		return false;
	}
	
	public void isCompleted() {
		int nOfBags = baggs.size();
		int finishedBags = 0;
		
		for(int i = 0; i < nOfBags; i++) {
			Baggage bag = baggs.get(i);
			for(int j = 0; j < nOfBags; j++) {
				Area area = areas.get(j);
				if(bag.x() == area.x() && bag.y() == area.y()) {
					finishedBags += 1;
				}
			}
		}
		
//		System.out.println(finishedBags);
		
		if(finishedBags == nOfBags) {
			isCompleted = true;
			repaint();
		}
	}
	
	public void restartLevel() {
		areas.clear();
		baggs.clear();
		walls.clear();
		
		initWorld();
		
		if(isCompleted) {
			isCompleted = false;
		}
	}
	
	public void levelUp() {
		areas.clear();
		baggs.clear();
		walls.clear();
		
		int tempLevel = level;
		
		count++;
		completeLevel[--tempLevel] = 1;
		
		if(count != 40) {
			if(completeLevel[++tempLevel] == 0) {
				if(level > 39) {
					level = 1;
					lepel = Level.get(level);
				} else {
					lepel = Level.get(++level);
				}
			} else {
				level += 2;
				lepel = Level.get(level);
			}
			initWorld();
		} else {
			System.exit(0);
		}
//		System.out.println(tempLevel);
//		System.out.println(level + ": " + completeLevel[level]);
		
		if(isCompleted) {
			isCompleted = false;
		}
	}
}
