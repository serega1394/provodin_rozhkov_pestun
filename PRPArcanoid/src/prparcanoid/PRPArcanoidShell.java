/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.object.collision.CollisionGroup;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ve
 */
public class PRPArcanoidShell {
    PRPArcanoid _mainGTGE;
    
    List<PRPSprite> _bricks;
    List<PRPSprite> _balls;
    //PRPSprite _ball;

    public PRPArcanoidShell(PRPArcanoid mainGTGE) {
        _mainGTGE=mainGTGE;
        _bricks=new LinkedList();
        _balls=new LinkedList();
    }
    
    public void initResources() {
        addSprite(PRPSprite.SPRITE_TYPE.BALL, "Ball.png",140,140);
        addSprite(PRPSprite.SPRITE_TYPE.BALL, "Ball.png",150,130);
        
        addSprite(PRPSprite.SPRITE_TYPE.PLATFORM, "platform.png", 640/2, 480-30);
        
        for(int i=0; i<4;++i){
            for(int j=0;j<7;++j){
                addSprite(PRPSprite.SPRITE_TYPE.BRICK, "Brick.png", 100+j*(PRPBrick.getWidth()), 140+i*(PRPBrick.getHeight()));
            }
        }
        //_sprites.get(0).setVerticalSpeed(-0.1);
        _balls.get(0).setPos(640/2, 480-30-20);
        _balls.get(0).setHorizontalSpeed(0.5);
        _balls.get(0).setVerticalSpeed(-0.2);
        
        _balls.get(1).setHorizontalSpeed(-0.5);
        _balls.get(1).setVerticalSpeed(-0.2);
    }
    
    public void update(long elapsedTime){
        for(int i=0;i<_bricks.size();++i){
            _bricks.get(i).update(elapsedTime);
        }
        for(int i=0;i<_balls.size();++i){
            _balls.get(i).update(elapsedTime);
        }
    }
    
    
    private void addSprite(PRPSprite.SPRITE_TYPE type, String image, double x,double y){
        PRPSprite tmpSprite=null;
        if(type == PRPSprite.SPRITE_TYPE.BALL){
            tmpSprite = new PRPBall(x,y,_mainGTGE);
            _balls.add(tmpSprite);
        }
        else if(type == PRPSprite.SPRITE_TYPE.BRICK){
            tmpSprite = new PRPBrick(x,y,_mainGTGE);
            _bricks.add(tmpSprite);
        }
        else if(type == PRPSprite.SPRITE_TYPE.PLATFORM){
            tmpSprite = new PRPPlatform(x,y,_mainGTGE);
            _bricks.add(tmpSprite);
        }
        
        _mainGTGE.addSprite(type, tmpSprite.getSpriteGTGE(), image);
    }
    
    private void removeSprite(PRPSprite sprite){
        _bricks.remove(sprite);
        _balls.remove(sprite);
        _mainGTGE.removeSprite(sprite.getSpriteGTGE());
    }
    
    public void collided(PRPSprite sprite1,PRPSprite sprite2, int collisionSide){
        if(sprite1.getType()==PRPSprite.SPRITE_TYPE.BALL 
                && sprite2.getType() == PRPSprite.SPRITE_TYPE.BRICK){
            sprite1.collided(collisionSide);
            removeSprite(sprite2);
        }
        else if(sprite1.getType()==PRPSprite.SPRITE_TYPE.BALL 
                && sprite2.getType() == PRPSprite.SPRITE_TYPE.PLATFORM){
            sprite1.collided(collisionSide);
        }
        else if(sprite1.getType()==PRPSprite.SPRITE_TYPE.BALL 
                && sprite2.getType() == PRPSprite.SPRITE_TYPE.BALL){
            /////
            int collisionSide2=0;
            if(collisionSide == CollisionGroup.BOTTOM_TOP_COLLISION ){
                collisionSide2=CollisionGroup.TOP_BOTTOM_COLLISION;
            }
            else if(collisionSide == CollisionGroup.TOP_BOTTOM_COLLISION ){
                collisionSide2=CollisionGroup.BOTTOM_TOP_COLLISION;
            }
            else if(collisionSide == CollisionGroup.LEFT_RIGHT_COLLISION ){
                collisionSide2=CollisionGroup.RIGHT_LEFT_COLLISION;
            }
            else if(collisionSide == CollisionGroup.RIGHT_LEFT_COLLISION ){
                collisionSide2=CollisionGroup.LEFT_RIGHT_COLLISION;
            }
            //////
            sprite1.collided(collisionSide);
            sprite2.collided(collisionSide2);
        }
    }
}
