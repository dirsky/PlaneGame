package com.frank.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * @author: Guozhong Xu
 * @date: Create in 10:20 2019/8/16
 * @version: 1.0
 */
public class MyGameFrame extends Frame {

    /**
     * 炮弹数组
     */
    private Shell[] shells = new Shell[50];
    /**
     * 爆炸类
     */
    private Explode explode;
    /**
     * 游戏持续的时间(秒)统计
     */
    private Date startTime = new Date();
    private Date endTime;
    private int period;

    /**
     * 资源加载
     */
    private Image bg = GameUtil.getImage("./images/bg.jpg");
    private Image planeImg = GameUtil.getImage("./images/plane.png");
    private Plane plane = new Plane(planeImg,30,30,10);

    /**
     * 系统首次自动调用
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        // 背景图片
        g.drawImage(bg, 0, 0, null);

        // 飞机出现
        plane.drawSelf(g);

        // 画炮弹
        for (Shell s : shells) {
            s.drawSelf(g);

            boolean dead = s.getRectangle().intersects(plane.getRectangle());
            if (dead) {
                plane.setLive(false);

                if (explode==null) {
                    explode = new Explode(plane.x, plane.y);
                    endTime = new Date();
                    period = (int)((endTime.getTime()-startTime.getTime())/1000);
                }
                explode.draw(g);

            }

            //计时功能，给出提示
            if(!plane.isLive()){
                g.setColor(Color.red);
                Font font =  new Font("宋体", Font.BOLD, 50);
                g.setFont(font);
                g.drawString("时间：" + period + "秒",
                        Constant.GAME_WIDTH / 2 - 100, Constant.GAME_HEIGHT / 2);
            }

        }
    }


    /**
     * 按键监控
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println(e.getKeyCode());
            plane.startChangDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println(e.getKeyCode());
            plane.stopChangDirection(e);
        }
    }


    /**
     * 重复绘制画布
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void launchGame() {
        this.setTitle("Frank Game");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        this.setLocation(300,100);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new PaintThread().start();
        this.addKeyListener(new KeyMonitor());

        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell(200,200,2);
        }
    }


    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchGame();
    }

    // 解决闪烁
    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
