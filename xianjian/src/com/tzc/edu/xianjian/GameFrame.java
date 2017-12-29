package com.tzc.edu.xianjian;


import javax.swing.JFrame;
/*
 * 表示游戏项目的窗口
 * @author xiaolei_hu
 */
public class GameFrame extends JFrame{
	
	private static final long serialVersionUID = -5173627318538987627L;
	
//	public GameFrame() {
//		
//		 //定时器，延迟200ms执行
////		Timer timer = new Timer(200, new ActionListener() {
////			
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				gamePanel.repaint();
////			}
////		});
////		timer.start();
//	}
	public static void main(String[] args) {	
		// 创建一个窗口实例
		JFrame jFrame = new JFrame();
		// 设置窗口的大小
		jFrame.setSize(1024, 768);
		// 设置窗口的名称
		jFrame.setTitle("仙剑游戏");
		jFrame.setLocationRelativeTo(null);
		// 设置窗口默认关闭选项
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel1 gamePanel = new GamePanel1();
		//GameFrame gameFrame = new GameFrame();
		gamePanel.addKeyListener(gamePanel);
		jFrame.addKeyListener(gamePanel);
		Thread xiancheng = new Thread(gamePanel);
		xiancheng.start();
		jFrame.add(gamePanel);
		// 声明监听事件的有效性
		jFrame.setVisible(true);
	}

}
