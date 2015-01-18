/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.object.Sprite;

/**
 *
 * @author ve
 */
public class PRPBall extends PRPSprite{

    public PRPBall() {
        super(0,0);
        setType(SPRITE_TYPE.BALL);
    }
    public PRPBall(double x, double y){
        super(x,y);
        setType(SPRITE_TYPE.BALL);
    }
    
    @Override
    public void update(long elapsedTime){
        //move(1,1);
    }
}
