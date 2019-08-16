package com.frank.game;

import java.awt.*;

/**
 * 炮弹
 * @author: Guozhong Xu
 * @date: Create in 13:30 2019/8/16
 * @version: 1.0
 */
public class Shell extends GameObject {

    /**
     * 角度
     */
    private double degree;

    public Shell(int x, int y, int speed) {
        super(x, y, speed);
        width = 10;
        height = 10;
        degree = Math.random()*Math.PI*2;
    }

    @Override
    public void drawSelf(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval((int)x,(int)y, width, height);

        //炮弹沿着任意角度去飞
        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);


        if(x<=10||x>=Constant.GAME_WIDTH-width){
            degree  = Math.PI - degree;
        }

        if(y<=30||y>=Constant.GAME_HEIGHT-height){
            degree  = - degree;
        }

        g.setColor(c);
    }
}
