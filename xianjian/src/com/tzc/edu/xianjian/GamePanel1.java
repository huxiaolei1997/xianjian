package com.tzc.edu.xianjian;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/*
 * 这是一个新的自定义画板类
 * @author xiaolei_hu
 */

// 在Java中，遵循单继承，多实现，一般继承的以类居多，实现的以接口居多
public class GamePanel1 extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = -8606114648782059805L;
	// 定义李家村图片的相关内容
	Image ljcImage;
	// 定义李家村市场图片相关内容
	Image[] ljcscImages;
	// 定义传送阵图片相关内容
	//Image chuanSongZhen;
	// 定义李家村的数据地图
	BufferedImage ljcDataMap;
	// 定义李家村市场的数据地图
	BufferedImage ljcscDataMap;
	// 定义李家村的位置
	int ljcX;
	int ljcY;
	// 定义李家村市场的位置
	int ljcscX;
	int ljcscY;
	// 定义传送阵位置
	//int cszX;
	//int cszY;
	// 用来表示数组图片的当前下标
	int awsIndex;
	int wcsIndex;
	int azIndex;
	int xhIndex;
	int mjIndex;
	int xjIndex;
	int xxjIndex;
	int ljcscIndex;
	// 图片数组，用来存放人物的图片
	Image[] wcsImages;
	Image[] awsImages;
	Image[] azImages;
	Image[] xhImages;
	Image[] mjImages;
	Image[] xxjImages;
	Image[] xjImages;
	// 存放李逍遥相关的图片
	Image[] lxyUpImages;
	Image[] lxyDownImages;
	Image[] lxyLeftImages;
	Image[] lxyRightImages;
	
	int lxyX;
	int lxyY;
	int lxyIndex;
	int lxyDir;
	// 用来表示李逍遥的移动速度
	int lxySpeed;
	// 用来表示李逍遥图片的当前内容，当李逍遥移动时，需要改变该值
	Image lxyImages;
	// 定义李逍遥对话相关内容
	Image ltImage;
	int ltX;
	int ltY;
	String[] ltMessage;
	int ltIndex;
	// 用来表示聊天相关内容是否出现的状态值
	boolean isLTShow;
	int mapId;
	// 构造方法，主要用来初始化
	public GamePanel1() {
		
		try {
			// 绝对路径，以盘符或者根目录开始的路径
			// 相对路径，在工程中，最好用相对路径
			ljcImage = ImageIO.read(new File("./images/LiJiaCun/0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			chuanSongZhen = ImageIO.read(new File("./images/LiJiaCun/chuansongzhen.gif"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		// 初始化李家村的位置和李家村市场的位置
		this.ljcX = this.ljcscX =  -200;
		this.ljcY = this.ljcscY = -200;
		// 初始化传送阵位置
		// cszX = 0;
		// cszY = 600;
		// 初始存放人物图片的数组
		wcsImages = new Image[13];
	    awsImages = new Image[17];
	    azImages = new Image[6];
	    xhImages = new Image[4];
	    mjImages = new Image[6];
	    xjImages = new Image[2];
	    xxjImages = new Image[2];
	    lxyUpImages = new Image[8];
	    lxyDownImages = new Image[8];
	    lxyLeftImages = new Image[8];
	    lxyRightImages = new Image[8];
	    ljcscImages = new Image[3];

	    draw(lxyUpImages, "LiXiaoYao_Up");
	    draw(lxyDownImages, "LiXiaoYao_Down");
	    draw(lxyLeftImages, "LiXiaoYao_Left");
	    draw(lxyRightImages, "LiXiaoYao_Right");
	    draw(ljcscImages, "LiJiaCunShiChang");
	    this.lxyX = 800;
	    this.lxyY = 600;
	    this.lxyIndex = 0;
	    this.lxySpeed = 5;
	    this.lxyImages = lxyDownImages[lxyIndex];
	    // this.lxyDir = 2;
	    draw(wcsImages, "WangCaiSao");
	    draw(awsImages, "AWangShen");
	    draw(azImages, "AZhu");
	    draw(xhImages, "XiaoHai");
	    draw(mjImages, "Muji");
	    draw(xjImages, "XiaoJi");
	    draw(xxjImages, "XiaoXiaoJi");
	    
	    // 初始化聊天所需的相关内容
	    try {
			ltImage = ImageIO.read(new File("./images/LiaoTian/0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	    ltMessage = new String[]{"hello, how are you", "I'm fine, thank you", "Ok, Bye!", "Bye!"};
	    isLTShow = false;
	    lxyDir = KeyEvent.VK_DOWN;
	    ltIndex = 0;
	    mapId = 1;
	    // 初始化障碍物功能所需的相关内容
	    try {
			ljcDataMap = ImageIO.read(new File("./images/LiJiaCun/RedMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			ljcscDataMap = ImageIO.read(new File("./images/LiJiaCunShiChang/RedMap.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		// 绘画李家村场景
			
			ljcX = (this.getWidth() - lxyImages.getWidth(null)) / 2 - lxyX;
			ljcY = (this.getHeight() - lxyImages.getHeight(null)) / 2 - lxyY;
//			System.out.println("李逍遥坐标：" + lxyX + ", " + lxyY);
//			System.out.println("李家村坐标：" + ljcX + ", " + ljcY);
			// 判断上边界
			if (ljcY >= 0) {
				ljcY = 0;
			}
			// 判断左边界
			if (ljcX >= -10) {
				ljcX = 0;
			}
			// 判断下边界
			if (ljcY <= -672) {
				ljcY = -672;
				//System.out.println(lxyX + ", " + lxyY);
			}
			// 判断右边界
			if (ljcX <= -896) {
				ljcX = -896;
			}
			
			if (mapId == 1) {	
				// 绘画游戏界面
				g.drawImage(ljcImage, ljcX, ljcY, this);
				// 画阿旺婶
				g.drawImage(awsImages[awsIndex], 660 + ljcX, 660 + ljcY, this);
	
				// 画旺财嫂
				g.drawImage(wcsImages[wcsIndex], 800 + ljcX, 550 + ljcY, this);
				g.drawImage(azImages[azIndex], 500 + ljcX, 500 + ljcY, this);
				g.drawImage(xhImages[xhIndex], 1000 + ljcX, 550 + ljcY, this);
				g.drawImage(mjImages[mjIndex], 530 + ljcX, 780 + ljcY, this);
				g.drawImage(xjImages[xjIndex], 560 + ljcX, 630 + ljcY, this);
				g.drawImage(xxjImages[xxjIndex], 590 + ljcX, 660 + ljcY, this);
				//g.drawImage(chuanSongZhen, cszX + ljcX, cszY + ljcY, this);
				g.drawImage(lxyImages, lxyX + ljcX, lxyY + ljcY, this);
	
				// 窗口显示出来之后，this.getWidth和this.getHeight才能获取到正确的宽高
				if (isLTShow) {
					ltX = (this.getWidth() - ltImage.getWidth(null)) / 2;
					ltY = this.getHeight() - ltImage.getHeight(null);
					g.drawImage(ltImage, ltX, ltY, this);
					g.setColor(Color.BLUE);
					g.setFont(new Font("微软雅黑", Font.BOLD, 20));
					g.drawString(ltMessage[ltIndex], ltX + 20, ltY + 30);
				}
		} else if (mapId == 2) {
			g.drawImage(ljcscImages[ljcscIndex], ljcX, ljcY, this);
			g.drawImage(lxyImages, lxyX + ljcX, lxyY + ljcY, this);
		}		
	}

	@Override
	public void run() {
		// 实现配角的运动效果
		// 需要不断的循环播放图片，也就是不断的循环修改图片数组对应的下标
		while (true) {
			if (mapId == 1) {
				awsIndex++;
				wcsIndex++;
				azIndex++;
				xhIndex++;
				mjIndex++;
				xjIndex++;
				xxjIndex++;
				if (awsIndex >= awsImages.length) {
					awsIndex = 0;
				}
				
				if (wcsIndex >= wcsImages.length) {
					wcsIndex = 0;;
				}
				
				if (azIndex >= azImages.length) {
					azIndex = 0;
				}
				if (xhIndex >= xhImages.length) {
					xhIndex = 0;
				}
				if (mjIndex >= mjImages.length) {
					mjIndex = 0;
				}
				if (xjIndex >= xjImages.length) {
					xjIndex = 0;
				}
				if (xxjIndex >= xxjImages.length) {
					xxjIndex = 0;
				}
				try {
					Thread.sleep(130);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 重新调用执行你的绘画方法paint()
				repaint();
			} else if (mapId == 2) {
				ljcscIndex++;
				if (ljcscIndex >= ljcscImages.length) {
					ljcscIndex = 0;
				}
				try {
					Thread.sleep(130);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			
		}
	}
	
	// images表示存放人物图片的数组，pictureDirectoryName表示存放人物图片的文件夹名
	public void draw(Image[] images, String pictureDirectoryName) {
	    for (int i = 0; i < images.length; i++) {
	    	try {
	    		images[i] = ImageIO.read(new File("./images/" + pictureDirectoryName + "/" + i + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
			if (isLTShow) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_SPACE) {
					ltIndex++;
					if(ltIndex >= ltMessage.length) {
						ltIndex = 0;
						isLTShow = false;
					}
					repaint();
				}
			} else {
				int keyCode = e.getKeyCode();
				// 向上走
				if (keyCode == KeyEvent.VK_UP) {
					
					ljcY += lxySpeed;
					lxyY -= lxySpeed;
					if (lxyY <= 0) {
						lxyY = 0;
						
					}
					System.out.println("李逍遥坐标：" + lxyX + ", " + lxyY);
					//System.out.println(lxyY);
					//System.out.println("此时李家村的纵坐标为" + ljcY + "此时李逍遥的横纵坐标是" + lxyX + ", " + lxyY);
					lxyIndex++;
					if (lxyIndex >= lxyUpImages.length) {
						lxyIndex = 0;
					}
					lxyImages = lxyUpImages[lxyIndex];
					lxyDir = KeyEvent.VK_UP;
					int x = lxyX + lxyImages.getWidth(null) / 2;
					int y = lxyY + lxyImages.getHeight(null);
					if (mapId == 1) {
						if (ljcDataMap.getRGB(x, y) == -521461) {
							lxyY += lxySpeed;
							//System.out.println("李逍遥被困住了");
						}
					} else if (mapId == 2) {
						if (ljcscDataMap.getRGB(x, y) == -65536) {
							lxyY += lxySpeed;
							System.out.println("李逍遥被困住了");
						}
					}
					
					repaint();
				} else if (keyCode == KeyEvent.VK_DOWN) { // 向下走
					
					ljcY -= lxySpeed;
					lxyY += lxySpeed;
					//System.out.println(lxyY);
					if (lxyY  >= ljcImage.getHeight(null) - lxyImages.getHeight(null) - 5) {
						lxyY = ljcImage.getHeight(null) - lxyImages.getHeight(null) - 5;
					}
					lxyIndex++;
					if (lxyIndex >= lxyDownImages.length) {
						lxyIndex = 0;
					}
					lxyImages = lxyDownImages[lxyIndex];
					lxyDir = KeyEvent.VK_DOWN;
					int x = lxyX + lxyImages.getWidth(null) / 2;
					int y = lxyY + lxyImages.getHeight(null);
					if (mapId == 1) {
						if (ljcDataMap.getRGB(x, y) == -521461) {
							lxyY -= lxySpeed;
						}
					} else if (mapId == 2) {
						if (ljcscDataMap.getRGB(x, y) == -65536) {
							lxyY -= lxySpeed;
						}
					}
					
					repaint();
				} else if (keyCode == KeyEvent.VK_LEFT) { // 向左走
					
					ljcX += lxySpeed;
					lxyX -= lxySpeed;
					if (lxyX <= 0) {
						lxyX = 0;
					}
					lxyIndex++;
					if (lxyIndex >= lxyLeftImages.length) {
						lxyIndex = 0;
					}
					lxyImages = lxyLeftImages[lxyIndex];
					lxyDir = KeyEvent.VK_LEFT;
					int x = lxyX + lxyImages.getWidth(null) / 2;
					int y = lxyY + lxyImages.getHeight(null);
					if (mapId == 1) {
						if (ljcDataMap.getRGB(x, y) == -521461) {
							lxyX += lxySpeed;
						}
					} else if (mapId == 2) {
						if (ljcscDataMap.getRGB(x, y) == -65536) {
							lxyX += lxySpeed;
						}
					}
					
					repaint();
				} else if (keyCode == KeyEvent.VK_RIGHT) { // 向右走
					ljcX -= lxySpeed;
					lxyX += lxySpeed;
					if (lxyX >= ljcImage.getWidth(null) - lxyImages.getWidth(null) - 5) {
						lxyX = ljcImage.getWidth(null) - lxyImages.getWidth(null) - 5;
					}
					lxyIndex++;
					if (lxyIndex >= lxyRightImages.length) {
						lxyIndex = 0;
					}
					lxyImages = lxyRightImages[lxyIndex];
					lxyDir = KeyEvent.VK_RIGHT;
					int x = lxyX + lxyImages.getWidth(null) / 2;
					int y = lxyY + lxyImages.getHeight(null);
					if (mapId == 1) {
						if (ljcDataMap.getRGB(x, y) == -521461) {
							lxyX -= lxySpeed;
						}
					} else if (mapId == 2) {
						if (ljcscDataMap.getRGB(x, y) == -65536) {
							lxyX -= lxySpeed;
						}
					}
					
					repaint();
				} else if (keyCode == KeyEvent.VK_SPACE) {
					// 用来控制对话框的消失与控制
					int x = lxyX;
					int y = lxyY + lxyImages.getHeight(null);
					int x2 = awsImages[awsIndex].getWidth(null) + 660;
					int y2 = awsImages[awsIndex].getHeight(null) + 660;
					int x1 = x2 - 20;
					int y1 = y2 - 20;
					if (lxyDir == KeyEvent.VK_LEFT && x > x1 && x < x2 && y > y1 && y < y2 ) {
					// 这个用来控制对话框的出现
					isLTShow = true;
					repaint();
					} 
				} else if (keyCode == KeyEvent.VK_ENTER) {
					if (lxyDir == KeyEvent.VK_LEFT && lxyX >= 0 && lxyX <= 100 && lxyY >= 660 && lxyY <= 700 && mapId == 1) {
						mapId = 2;
						//System.out.println(lxyX + ", " + lxyY);
					} else {
						mapId = 1;
					}
					repaint();
				}
			}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("键盘按键被松开");
	}
}
