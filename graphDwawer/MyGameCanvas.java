package test;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 
 * 基于Java AWT Canvas 创建的游戏画布框架 用来绘制整个游戏的画布。
 *
 * 
 * 
 */

public class MyGameCanvas extends Canvas {
	/**
	 * 
	 * 双缓冲的画布
	 * 
	 */

	Image iBuffer;

	/**
	 * 
	 * 双缓冲的画笔
	 * 
	 */

	Graphics gBuffer;

	/**
	 * 
	 * 构造器 初始化画板
	 *
	 * 
	 * 
	 */

	public MyGameCanvas() {
		new PaintThread().start();

	}

	/**
	 * 
	 * 定义一个重绘窗口的线程类，是一个内部类。 通过线程中的循环来控制动画的间隔时间。
	 * 
	 */

	class PaintThread extends Thread {
		@Override

		public void run() {
			while (true) {
				try {
					repaint();// 重绘画布

					Thread.sleep(30);// 间隔时间

				} catch (InterruptedException ex) {
					System.out.println(ex);

				}

			}

		}

	}

	/**
	 * 
	 * 绘图方法，继承后可以重写这个方法来绘制场景及游戏元素，就可以不用。 我们制作游戏动画绘制场景主要是利用这个方法来实现。
	 * 
	 * 可以避免过于重写继承来的方法。也为后继加入双缓冲做准备。
	 *
	 * 
	 * 
	 * @param src
	 * 
	 */

	public void draw(Graphics g) {
	}

	/**
	 * 
	 * 重写这个方法并加入了双缓冲！
	 *
	 * 
	 * 
	 * @param g
	 * 
	 */

	@Override

	public void paint(Graphics g) {
		if (iBuffer == null) {
			iBuffer = createImage(this.getSize().width, this.getSize().height);

			gBuffer = iBuffer.getGraphics();

		}

		gBuffer.setColor(getBackground());

		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);

		draw(gBuffer);

		g.drawImage(iBuffer, 0, 0, this);

	}

	/**
	 * 
	 * 双缓冲重写
	 *
	 * 
	 * 
	 * @param g
	 * 
	 */

	@Override
	public void update(Graphics g) {
		paint(g);

	}

}
