/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.object.collision.CollisionGroup;

/**
 *
 * @author ve
 */
public class PRPBrick extends PRPSprite{

    private PRPBrick() {
        super(0,0, null);
    }
    public PRPBrick(double x, double y,PRPArcanoid mainGTGE){
        super(x,y, mainGTGE);
    }
    
    @Override
    public void update(long elapsedTime){
        
    }
    
    
    public static int getWidth(){
        return 50;
    }
    
    public static int getHeight(){
        return 20;
    }
    
        
    @Override
    public void collided(int collisionSide){
        
    }
}