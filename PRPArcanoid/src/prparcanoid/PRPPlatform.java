/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author ve
 */
public class PRPPlatform extends PRPBrick{
    private PRPPlatform() {
        super(0,0,null);
    }
    public PRPPlatform(double x, double y,PRPArcanoid mainGTGE){
        super(x,y,mainGTGE);
    }
    
    @Override
    public void update(long elapsedTime){
        if(_mainGTGE.keyDown(KeyEvent.VK_LEFT) && getX()>0){
            move(-5,0);
        }
        else if(_mainGTGE.keyDown(KeyEvent.VK_RIGHT) && getX()<(640-getWidth())){
            move(5,0);
        }
        
        if(_mainGTGE.keyPressed(KeyEvent.VK_SPACE)){
            PRPSprite tmpSprite=_mainGTGE.getLogic().addSprite(new PRPBall(getX()+getWidth()/2, getY()-20, _mainGTGE), "Ball.png");
            tmpSprite.setHorizontalSpeed((new Random()).nextDouble()/3-0.25);
            tmpSprite.setVerticalSpeed(-(new Random()).nextDouble()/3);
        }
    }
    
    public static int getWidth(){
        return 100;
    }
    
    public static  int getHeight(){
        return 10;
    }
}
