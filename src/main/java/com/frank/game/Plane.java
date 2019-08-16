package com.frank.game;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author: Guozhong Xu
 * @date: Create in 12:22 2019/8/16
 * @version: 1.0
 */
public class Plane extends GameObject {

    private boolean up, down, left, right;

    public void setLive(boolean live) {
        isLive = live;
    }

    public boolean isLive() {
        return isLive;
    }

    private boolean isLive=true;

    public Plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
        width = 30;
        height = 30;
    }

    @Override
    public void drawSelf(Graphics g) {

        if (isLive) {
            g.drawImage(img, (int) x, (int) y, null);
            if (up) {
                y -= speed;
            } else if (down) {
                y += speed;
            } else if (left) {
                x -= speed;
            } else if (right) {
                x += speed;
            }
        } else {
            System.out.println("plane dead can not move ");
        }
    }


    /**
     * 按下按键
     * @param event
     */
    public void startChangDirection(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            default:
                break;
        }
    }


    /**
     * 松开按键
     * @param event
     */
    public void stopChangDirection(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            default:
                break;
        }
    }
}
