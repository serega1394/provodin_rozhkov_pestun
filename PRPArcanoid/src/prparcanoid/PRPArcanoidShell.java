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
    
    List<PRPSprite> _onRemove;
    //PRPSprite _ball;

    public PRPArcanoidShell(PRPArcanoid mainGTGE) {
        _mainGTGE=mainGTGE;
        _bricks=new LinkedList();
        _balls=new LinkedList();
        _onRemove = new LinkedList();
    }
    
    public void initResources() {
        addSprite(new PRPBall(140, 140, _mainGTGE), "Ball.png");
        addSprite(new PRPBall(400, 400, _mainGTGE), "Ball.png");
        
        addSprite(new PRPPlatform(640/2, 480-30, _mainGTGE), "platform.png");
        
        for(int i=0; i<5;++i){
            for(int j=0;j<8;++j){
                addSprite(new PRPBrick(100+j*(PRPBrick.getWidth()), 140+i*(PRPBrick.getHeight()), _mainGTGE), "Brick.png");
            }
        }
        //_sprites.get(0).setVerticalSpeed(-0.1);
        _balls.get(0).setPos(640/2, 480-30-20);
        _balls.get(0).setHorizontalSpeed(0.3);
        _balls.get(0).setVerticalSpeed(-0.1);
        
        _balls.get(1).setHorizontalSpeed(-0.3);
        _balls.get(1).setVerticalSpeed(-0.1);
    }
    
    public void update(long elapsedTime){
        for(int i=0;i<_onRemove.size();++i){
            removeSprite(_onRemove.get(i));
        }
        _onRemove = new LinkedList();
        
        for(int i=0;i<_bricks.size();++i){
            _bricks.get(i).update(elapsedTime);
        }
        for(int i=0;i<_balls.size();++i){
            _balls.get(i).update(elapsedTime);
        }
    }
    
    
    public PRPSprite addSprite(PRPSprite tmpSprite, String image){
        if(tmpSprite instanceof PRPBall){
            _balls.add(tmpSprite);
        }
        else if(tmpSprite.getClass() == PRPBrick.class){
            _bricks.add(tmpSprite);
        }
        else if(tmpSprite instanceof PRPPlatform){
            _bricks.add(tmpSprite);
        }
        
        _mainGTGE.addSprite(tmpSprite, image);
        return tmpSprite;
    }
    
    private void removeSprite(PRPSprite sprite){
        _bricks.remove(sprite);
        _balls.remove(sprite);
        _mainGTGE.removeSprite(sprite.getSpriteGTGE());
    }
    
    public void addToRemoveQueue(PRPSprite sprite){
        _onRemove.add(sprite);
    }
    public void collided(PRPSprite sprite1,PRPSprite sprite2, int collisionSide){
        if(sprite1 instanceof PRPBall && sprite2.getClass() == PRPBrick.class){
            sprite1.collided(collisionSide);
            removeSprite(sprite2);
        }
        else if(sprite1 instanceof PRPBall && sprite2 instanceof PRPPlatform){
            sprite1.collided(collisionSide);
        }
        else if(sprite1 instanceof PRPBall && sprite2 instanceof PRPBall){
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
