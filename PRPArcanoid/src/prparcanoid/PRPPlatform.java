/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import java.awt.event.KeyEvent;

/**
 *
 * @author ve
 */
public class PRPPlatform extends PRPBrick{
    private PRPPlatform() {
        super(0,0,null);
        setType(SPRITE_TYPE.PLATFORM);
    }
    public PRPPlatform(double x, double y,PRPArcanoid mainGTGE){
        super(x,y,mainGTGE);
        setType(SPRITE_TYPE.PLATFORM);
    }
    
    @Override
    public void update(long elapsedTime){
        if(_mainGTGE.keyDown(KeyEvent.VK_LEFT) && getX()>0){
            move(-5,0);
        }
        else if(_mainGTGE.keyDown(KeyEvent.VK_RIGHT) && getX()<(640-getWidth())){
            move(5,0);
        }
    }
    
    public static int getWidth(){
        return 100;
    }
    
    public static  int getHeight(){
        return 10;
    }
}
