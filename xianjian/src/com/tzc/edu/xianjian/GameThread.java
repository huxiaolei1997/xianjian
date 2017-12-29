package com.tzc.edu.xianjian;

import java.awt.Graphics;


public class GameThread extends Thread{
	String threadName;
	Graphics graphics;
	public GameThread(String threadName) {
		System.out.println(" Making a new Thread:" + threadName);
		this.threadName = threadName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			String relativePath = System.getProperty("user.dir") + "\\images";
			String[] imageUrl = new String[]{"\\XiaoJi\\0.png", "\\XiaoJi\\1.png"};
			for (int i = 0; i < imageUrl.length; i++) {
				// ImageIcon icon2 = new ImageIcon(relativePath + imageUrl[i]);
				// Image image2 = icon2.getImage();
				// graphics.drawImage(image2, 100, 100, this);
			}
		}
	}
	

}
