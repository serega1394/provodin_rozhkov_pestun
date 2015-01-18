/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

/**
 *
 * @author ve
 */
public class PRPBrick extends PRPSprite{

    public PRPBrick() {
        super(0,0);
        setType(SPRITE_TYPE.BRICK);
    }
    public PRPBrick(double x, double y){
        super(x,y);
        setType(SPRITE_TYPE.BRICK);
    }
    
    @Override
    public void update(long elapsedTime){
        
    }
}