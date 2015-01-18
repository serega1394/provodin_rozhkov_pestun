/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ve
 */
public class PRPArcanoidShell {
    PRPArcanoid _mainGTGE;
    
    List<PRPSprite> _sprites;
    //PRPSprite _ball;

    public PRPArcanoidShell(PRPArcanoid mainGTGE) {
        _mainGTGE=mainGTGE;
        _sprites=new LinkedList();
    }
    
    public void initResources() {
        addSprite(PRPSprite.SPRITE_TYPE.BALL, "db.jpg");
    }
    
    public void update(long elapsedTime){
        for(int i=0;i<_sprites.size();++i){
            _sprites.get(i).update(elapsedTime);
        }
    }
    
    
    private void addSprite(PRPSprite.SPRITE_TYPE type, String image){
        if(type == PRPSprite.SPRITE_TYPE.BALL){
            _sprites.add(new PRPBall(100,100));
        }
        
        _mainGTGE.addSprite(type, _sprites.get(_sprites.size()-1).getSpriteGTGE(), image);
    }
}
