package com.tzc.edu.xianjian;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * 本实例类表示游戏项目的自定义画板类，也就是界面
 * @author xiaolei_hu
 * 
 */
public class GamePanel extends JPanel{
	private int index;
	private static final long serialVersionUID = 8056346602752451376L;

	public void paint(Graphics graphics) {
		super.paint(graphics);
		// 获取存放图片的文件夹的路径
		String relativePath = System.getProperty("user.dir") + "\\images";
		
		ImageIcon[] imgs = new ImageIcon[]{
					new ImageIcon(relativePath + "\\AWangShen\\0.png"),
					new ImageIcon(relativePath + "\\AWangShen\\1.png"),
					new ImageIcon(relativePath + "\\AWangShen\\2.png"),
					new ImageIcon(relativePath + "\\AWangShen\\3.png"),
					new ImageIcon(relativePath + "\\AWangShen\\4.png"),
					new ImageIcon(relativePath + "\\AWangShen\\5.png"),
					new ImageIcon(relativePath + "\\AWangShen\\6.png"),
					new ImageIcon(relativePath + "\\AWangShen\\7.png"),
					new ImageIcon(relativePath + "\\AWangShen\\8.png"),
					new ImageIcon(relativePath + "\\AWangShen\\9.png"),
					new ImageIcon(relativePath + "\\AWangShen\\10.png"),
					new ImageIcon(relativePath + "\\AWangShen\\11.png"),
					new ImageIcon(relativePath + "\\AWangShen\\12.png"),
					new ImageIcon(relativePath + "\\AWangShen\\13.png"),
					new ImageIcon(relativePath + "\\AWangShen\\14.png"),
					new ImageIcon(relativePath + "\\AWangShen\\15.png"),
					new ImageIcon(relativePath + "\\AWangShen\\16.png")
		};
		
		// 画背景和静态人物
		ImageIcon backGround = new ImageIcon(relativePath + "\\LiJiaCun\\0.png");
	
		//ImageIcon aWangShen = new ImageIcon(relativePath + "\\AWangShen\\0.png");
		ImageIcon aZhu = new ImageIcon(relativePath + "\\AZhu\\0.png");
		ImageIcon wangCaiSao = new ImageIcon(relativePath + "\\WangCaiSao\\0.png");
		ImageIcon xiaoHai = new ImageIcon(relativePath + "\\XiaoHai\\0.png");
		ImageIcon muJi = new ImageIcon(relativePath + "\\Muji\\0.png");
		ImageIcon xiaoJi = new ImageIcon(relativePath + "\\XiaoJi\\0.png");
		ImageIcon xiaoXiaoJi = new ImageIcon(relativePath + "\\XiaoXiaoJi\\0.png");
		Image imageBackImage = backGround.getImage();
		//Image aWangShenImage = aWangShen.getImage();
		Image aZhuImage = aZhu.getImage();
		Image wangCaiSaoImage = wangCaiSao.getImage();
		Image xiaoHaiImage = xiaoHai.getImage();
		Image muJiImage = muJi.getImage();
		Image xiaoJiImage = xiaoJi.getImage();
		Image xiaoxiaoJiImage = xiaoXiaoJi.getImage();
		
		// 画背景界面
		graphics.drawImage(imageBackImage, -200, -200, this);
		graphics.drawImage(imgs[index%imgs.length].getImage(), 300, 300, this);
		graphics.drawImage(aZhuImage, 350, 350, this);
		graphics.drawImage(wangCaiSaoImage, 470, 360, this);
		graphics.drawImage(xiaoHaiImage, 600, 500, this);
		graphics.drawImage(muJiImage, 800, 390, this);
		graphics.drawImage(xiaoJiImage, 450, 330, this);
		graphics.drawImage(xiaoxiaoJiImage, 500, 500, this);
		index++;
	}

}
