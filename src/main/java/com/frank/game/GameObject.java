package com.frank.game;

import java.awt.*;

/**
 * @author: Guozhong Xu
 * @date: Create in 12:17 2019/8/16
 * @version: 1.0
 */
public class GameObject {
    Image img;
    double x, y;
    double speed;
    int width, height;


    public GameObject(Image img, int x, int y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public GameObject(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }


    public void drawSelf(Graphics g) {
        g.drawImage(this.img, (int) x, (int) y, null);
    }


    /**
     * 物体碰撞检测
     *
     * @return
     */
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
