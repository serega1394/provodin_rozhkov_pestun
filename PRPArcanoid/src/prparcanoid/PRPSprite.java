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
    
    PRPSpriteGTGE _spriteGTGE;
    PRPArcanoid _mainGTGE;
    
    public PRPSprite(double x, double y,PRPArcanoid mainGTGE){
        _spriteGTGE = new PRPSpriteGTGE(this);
        _mainGTGE = mainGTGE;
        setPos(x, y);
    }
    
    public abstract void update(long elapsedTime);
    
    
    
    public Sprite getSpriteGTGE(){
        return _spriteGTGE;
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
    
    public void setVerticalSpeed(double vSpeed){
        _spriteGTGE.setVerticalSpeed(vSpeed);
    }
    
    public void setHorizontalSpeed(double hSpeed){
        _spriteGTGE.setHorizontalSpeed(hSpeed);
    }
    
    public double  getVerticalSpeed(){
        return _spriteGTGE.getVerticalSpeed();
    }
    
    public double  getHorizontalSpeed(){
        return _spriteGTGE.getHorizontalSpeed();
    }
    //////////////////
    
    
    ////// физика
    
    public abstract  void collided(int collisionSide);
    
}
