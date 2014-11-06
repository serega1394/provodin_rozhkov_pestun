/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arcanoidgame;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.object.collision.PreciseCollisionGroup;
import com.golden.gamedev.util.ImageUtil;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author sergey
 */
public class ArcanoidGame extends Game{
    
    final int H = 600;
    final int W = 800;
    
    Background back = new ColorBackground(new Color(255, 200, 0));
    
    PlayField pf = new PlayField(back);
    
    SpriteGroup spr_group1;
    SpriteGroup spr_group2;
    BufferedImage spr_buffer;
    Sprite spr_player;
    Sprite spr_ball;   
    
    SpriteGroup brick_gr;
    Sprite spr_brick1;
    Sprite spr_brick2;
    
    SpriteGroup gr_bublic;
    SpriteGroup gr_litte_ball;
    Sprite spr_bublic;
    Sprite spr_little_ball;
    
    PreciseCollisionGroup pcg = new PreciseCollisionGroup() {

        @Override
        public void collided(Sprite sprite, Sprite sprite1) {
            
            if (CollisionManager.isPixelCollide(sprite.getX(), sprite.getY(), sprite.getImage(), sprite1.getX(), sprite1.getY(), sprite1.getImage())){
                Point2D centerBublic = new Point2D.Double(sprite.getX()+sprite.getWidth()/2, sprite.getY()+sprite.getHeight()/2);
                Point2D centerBall = new Point2D.Double(sprite1.getX()+sprite1.getWidth()/2, sprite1.getY()+sprite1.getHeight()/2); 
                
                sprite1.setSpeed(-sprite1.getVerticalSpeed(), -sprite1.getHorizontalSpeed());
              
                Point2D pNormal = new Point2D.Double(centerBublic.getX()-centerBall.getX(), centerBublic.getY() - centerBall.getY());
                VVector2D normal = new VVector2D(pNormal);
                VVector2D speed = new VVector2D(sprite1.getHorizontalSpeed(), sprite1.getVerticalSpeed());
                VVector2D refl = VVector2D.reflect(speed, normal);
                
               // sprite1.setSpeed(refl.x(),refl.y());             
            }
        }
    };

    private ArcanoidGame() {
 
    }

    public void initResources() {
        // initialization of game variables
        
        back.setSize(W, H);
        back.setClip(0, 0, W, H);
        
        // Создаем группу спрайтов
        spr_group1 = new SpriteGroup("grp_player");
        spr_group2 = new SpriteGroup("grp_ball");
       //Загружаем картинку
        spr_buffer = getImage("board.png");
       // Создаем спрайт игрока
        spr_player = new Sprite(spr_buffer, 200,H-50);
        spr_buffer = getImage("ball.png");
        spr_ball = new Sprite(spr_buffer, 380, 380);
       // Добавляем к группе спрайт доски
        spr_group1.add(spr_player); 
        spr_group2.add(spr_ball);
       // Помещаем группу спрайтов на игровую площадку
        pf.addGroup(spr_group1);
        pf.addGroup(spr_group2);
        spr_ball.setSpeed(0.1, 0.1);
        
        brick_gr = new SpriteGroup("bricks");
        
        spr_buffer = getImage("brickH.png");
        spr_brick1 = new Sprite(spr_buffer, 100,220);
        brick_gr.add(spr_brick1);
        
        spr_buffer = getImage("brickV.png");
        spr_brick2 = new Sprite(spr_buffer,220,100);
        brick_gr.add(spr_brick2);
        pf.addGroup(brick_gr);       
        
        gr_bublic = new SpriteGroup("bublik");
        //gr_litte_ball = new SpriteGroup("little_ball");
        spr_buffer = getImage("bublik.png");  
        spr_bublic = new Sprite(spr_buffer, 170, 170);
        gr_bublic.add(spr_bublic);
        pf.addGroup(gr_bublic);
        
        
        
        
        pf.addCollisionGroup(gr_bublic, spr_group2, pcg);
    }

    public void update(long elapsedTime) {
        pf.update(elapsedTime);
       // if (keyDown(KeyEvent.VK_UP)) spr_player.moveY(-3); 
       // if (keyDown(KeyEvent.VK_DOWN)) spr_player.moveY(3); 
        
        if (keyDown(KeyEvent.VK_SPACE)){
            if (spr_ball.getVerticalSpeed() == 0 && spr_ball.getHorizontalSpeed() == 0){
                spr_ball.setSpeed(0.01, 0.01);
            }
            else{
                spr_ball.setSpeed(0, 0);
            }
        }
        
        if (keyDown(KeyEvent.VK_LEFT)){
            if (spr_player.getX() >= 3){
                spr_player.moveX(-3); 
            }
        }
            
        if (keyDown(KeyEvent.VK_RIGHT))  {     
            if (spr_player.getX() <= W-3-spr_player.getWidth()){
                 spr_player.moveX(3);
            }
        }
       
        if (spr_ball.getY() <= 0){
            spr_ball.setSpeed(spr_ball.getHorizontalSpeed(), -spr_ball.getVerticalSpeed());
        }
        
        if (spr_ball.getY() >= H-spr_ball.getHeight()){
            
        }
        
        if (spr_ball.getX() <= 0 || spr_ball.getX() >= W-spr_ball.getWidth()){
            spr_ball.setSpeed(-spr_ball.getHorizontalSpeed(), spr_ball.getVerticalSpeed());
        }
    }

    public void render(Graphics2D g) {
        pf.render(g);
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new ArcanoidGame(), new Dimension(800,600),false);
        game.start();
    } 
}