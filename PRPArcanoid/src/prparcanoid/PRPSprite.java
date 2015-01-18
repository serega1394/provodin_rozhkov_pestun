/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.object.Sprite;
import java.awt.Graphics2D;

/**
 *
 * @author ve
 */
public abstract class PRPSprite {
    
    Sprite _spriteGTGE;
    
    /////////////
    enum SPRITE_TYPE{
        BALL,
        ROCKET,
        BRICK
    }
    
    SPRITE_TYPE _type;
    
    public PRPSprite(double x, double y){
        _spriteGTGE = new Sprite();
        setPos(x, y);
    }
    
    public abstract void update(long elapsedTime);
    
    
    
    public Sprite getSpriteGTGE(){
        return _spriteGTGE;
    }
    
    public SPRITE_TYPE getType(){
        return _type;
    }
    
    protected void setType(SPRITE_TYPE type){
        _type=type;
    }
    
    
    /// ДВИЖЕНИЕ
    public void setPos(double x,double y){
        _spriteGTGE.setLocation(x, y);
    }
    
    public void setX(double x){
        _spriteGTGE.setX(x);
    }
    
    public void setY(double y){
        _spriteGTGE.setY(y);
    }
    
    public double getX(){
        return _spriteGTGE.getX();
    }
    
    public double getY(){
        return _spriteGTGE.getY();
    }
    
    public void move(double x,double y){
        _spriteGTGE.move(x, y);
    }
    //////////////////
    
    
    
    
}
