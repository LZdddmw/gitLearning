package livegame;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.*;

public class UI{
	
	private JFrame frame = new JFrame(); 
	private JPanel panel = new JPanel();
	private JMenuBar bar = new JMenuBar();
	private JMenu m1 = new JMenu("初始形状");
	private JMenu m2 = new JMenu("状态");
	private JMenuItem m11 = new JMenuItem("十字");
	private JMenuItem m12 = new JMenuItem("矩形");
	private JMenuItem m13 = new JMenuItem("随机形状");
	private JMenuItem m21 = new JMenuItem("开始");
	private JMenuItem m22 = new JMenuItem("暂停");
	private ChessBoard chessBoard;
	private GridLayout grid = new GridLayout(ChessBoard.SIZE,ChessBoard.SIZE,1,1);
	private JPanel [][]jps = new JPanel[ChessBoard.SIZE][ChessBoard.SIZE];

	protected ChessBoard getChessBoard() {
		return this.chessBoard;
	}
	
	protected void setChessBord(ChessBoard chessBoard) {
		this.chessBoard=chessBoard;
	}
	
	protected JFrame getFrame(){
		return this.frame;
	}
	
	protected JPanel getPanel() {
		return this.panel;
	}
	
	protected JPanel[][] getJps() {
		return this.jps;
	}
	
	protected JMenuItem getM21() {
		return this.m21;
	}
	
	protected JMenuItem getM22() {
		return this.m22;
	}
	
	protected void playUI() {
		frame.setSize(1000,720);
		frame.setTitle("生命游戏");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		m1.add(m11);
		m1.add(m12);
		m1.add(m13);
		/*
		 * 
		 * 
		 .....可扩展初始形状......
		 */
		m2.add(m21);
		m2.add(m22);
		bar.add(m1);
		bar.add(m2);
		
		panel.setLayout(grid);
		for(int i = 0;i < ChessBoard.SIZE;i++)
			for(int j = 0;j  < ChessBoard.SIZE;j++) {
				jps[i][j] = new JPanel();
				jps[i][j].setBackground(new Color(72,209,204));
				panel.add(jps[i][j]);
			}
		
		m11.addActionListener(new ShapeListener());
		
		m12.addActionListener(new ShapeListener());
		
		m13.addActionListener(new ShapeListener());
		
		m21.addActionListener(new StateListener());
		
		m22.addActionListener(new StateListener());
		m22.setEnabled(false);
		
		frame.add(panel);
		frame.setJMenuBar(bar);
		frame.setVisible(true);
	}
}

//初始化形状菜单的监听器
class ShapeListener implements ActionListener{
	
	public void actionPerformed(ActionEvent event) {
		try {
			String shape = event.getActionCommand();
			int index=0;
			switch (shape) {
			case "十字":
				index=0;
				break;
			case "矩形":
				index=1;
				break;
			case "随机形状":
				index=2;
				break;
			default:
				break;
			}
			//此处根据index的值用ChessBoard类的对象调用某一个init()方法，初始化棋盘
			InitShape.initShape(index);//InitShape类中的ChessBoard对象调用init()函数初始化形状
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

class InitShape{
	static ChessBoard board;

	static protected void initShape(int index){
		switch (index) {
		case 0:
			board.init1();
			break;
		case 1:
			board.init2();
			break;
		case 2:
			board.init3();
			break;
		default:
			break;
		}
	}
}


//状态菜单的监听器
class StateListener implements ActionListener{
	
	public void actionPerformed(ActionEvent event) {
		try {
			String state = event.getActionCommand();
			int index=0;
			switch (state) {
			case "开始":
				index=0;
				break;
			case "暂停":
				index=1;
			default:
				break;
			}
			//此处根据index的值用ChessBoard类的对象调用某一个init()方法，初始化棋盘
			InitState.initState(index);//InitShape类中的ChessBoard对象调用init()函数初始化形状
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

class InitState{
	static UI ui;
	static Timer timer;
	static Time time;
	static protected void initState(int index){
		
		switch (index) {
		case 0:
			//结合Timer使生命游戏开始走动
			//Timer做好后再写
			timer=new Timer();
			time=new Time();
			time.setChessBoard(ui.getChessBoard());
			//timer.scheduleAtFixedRate(time,0,1000);
			timer.schedule(time,0, 1000);
			ui.getM21().setEnabled(false);
			ui.getM22().setEnabled(true);
			break;
		case 1:
			//结合Timer使生命游戏暂停
			//Timer做好后再写
			timer.cancel();
			ui.getM21().setEnabled(true);
			ui.getM22().setEnabled(false);
			break;
		default:
			break;
		}
	}
}