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
public class PRPSpriteGTGE extends Sprite{

    PRPSprite _spriteLogic;

    public PRPSpriteGTGE(PRPSprite spriteLogic) {
        _spriteLogic=spriteLogic;
    }
    
    public PRPSprite getSpriteLogic(){
        return _spriteLogic;
    }
}
