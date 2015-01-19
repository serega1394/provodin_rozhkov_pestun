/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;
import java.awt.event.KeyEvent;

/**
 *
 * @author ve
 */
public class PRPBall extends PRPSprite{

    private PRPBall() {
        super(0,0, null);
        setType(SPRITE_TYPE.BALL);
    }
    public PRPBall(double x, double y,PRPArcanoid mainGTGE){
        super(x,y, mainGTGE);
        setType(SPRITE_TYPE.BALL);
    }
    
    public static int getWidth(){
        return 20;
    }
    
    public static int getHeight(){
        return 20;
    }
    
    @Override
    public void update(long elapsedTime){
        if(getX()+getHorizontalSpeed()>640){
            setHorizontalSpeed(-getHorizontalSpeed());
        }
        else if(getX()+getHorizontalSpeed()<0){
            setHorizontalSpeed(-getHorizontalSpeed());
        }
        
        if(getY()+getVerticalSpeed()>480){
            setVerticalSpeed(-getVerticalSpeed());
        }
        else if(getY()+getVerticalSpeed()<0){
            setVerticalSpeed(-getVerticalSpeed());
        }
    }
    
    @Override
    public void collided(int collisionSide){
        if(collisionSide == CollisionGroup.BOTTOM_TOP_COLLISION){
            setVerticalSpeed(-getVerticalSpeed());
        }
        else if(collisionSide == CollisionGroup.RIGHT_LEFT_COLLISION){
            setHorizontalSpeed(-getHorizontalSpeed());
        }
        else if(collisionSide == CollisionGroup.TOP_BOTTOM_COLLISION){
            setVerticalSpeed(-getVerticalSpeed());
        }
        else if(collisionSide == CollisionGroup.LEFT_RIGHT_COLLISION){
            setHorizontalSpeed(-getHorizontalSpeed());
        }
    }
}
