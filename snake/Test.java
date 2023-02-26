package snake;

import java.awt.*;
import java.util.*;
/*
class GamePanel extends Panel {
    
    public void gameStart() { // 该方法用来启动计时器
        Timer t = new Timer(); // 建立计时器对象
        myTimerTask game = new myTimerTask(1); // 建立计时任务对象
        myTimerTask game2 = new myTimerTask(2);
        t.scheduleAtFixedRate(game, 0, 1000); // 设定帧速率并启动计时任务
        t.scheduleAtFixedRate(game2, 1, 2000);
    }

    class myTimerTask extends TimerTask {// 建立内部类，继承TimerTask类
        int x;

        myTimerTask(int x) {
            super();
            this.x = x;
        }

        public void run() { // 重载run()方法，将游戏循环放置其中
            while (true) {
                System.out.println(x);
                break;
            }
        }
    }
}

public class Test {
    public static void main(String[] args) {
        GamePanel gp = new GamePanel();
        gp.gameStart();
    }
}
// 可以直接继承Thread也可以实现runable，两种都行，用runnable好一点，把自己作为参数传进Thread
*/