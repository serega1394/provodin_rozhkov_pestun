/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.CollisionGroup;
import com.sun.org.apache.bcel.internal.generic.L2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ve
 */
public class PRPArcanoid extends Game{
    
    PRPArcanoidShell _logic;
    
    Background _backGround = new ColorBackground(new Color(255, 187, 9));
    PlayField _playField = new PlayField(_backGround);
   
    List<SpriteGroup> _groupBalls;
    SpriteGroup _groupBricks;
    
    Map<String, BufferedImage> _images;
    
    private void addImage(String name){
        if(!_images.containsKey(name)){
            _images.put(name, getImage(name));
        }
    }
    
    public PRPArcanoidShell getLogic(){
        return _logic;
    }
    /////
    public static void main(String[] args) {
        GameLoader game = new GameLoader(); // Класс, управляющий инициализацией приложения
        game.setup(new PRPArcanoid(), new Dimension(640,480), false);//Инициализация графического движка
        game.start();
    }
 
    @Override
    public void initResources() {
        _images = new HashMap();
        _groupBalls = new LinkedList();
        
        
        
        _groupBricks = new SpriteGroup("groupBricks");
        _playField.addGroup(_groupBricks);
        
        
        
        _logic = new PRPArcanoidShell(this);
        _logic.initResources();
        
//        for(int i=0;i<_groupBalls.size();++i){
//            _playField.addGroup(_groupBalls.get(i));
//        }
    }
 
    @Override
    public void render(Graphics2D g) {
        //Рендеринг графики
        
        _playField.render(g);
    }
 
    @Override
    public void update(long elapsedTime) {
        //Обновления переменных и ресурсов
        _logic.update(elapsedTime);
        _playField.update(elapsedTime);
    }
    
    
    
    public void addSprite(PRPSprite sprite, String image){
        addImage(image);
        sprite.getSpriteGTGE().setImage(_images.get(image));
        
        if(sprite instanceof PRPBall){
            SpriteGroup tmpSG = new SpriteGroup("groupBalls" + _groupBalls.size());
            tmpSG.add(sprite.getSpriteGTGE());
            _groupBalls.add(tmpSG);
            
            _playField.addGroup(tmpSG);
            _playField.addCollisionGroup(tmpSG, _groupBricks, new CollisionGroup() {

                @Override
                public void collided(Sprite ball, Sprite brick) {
                    revertPosition1();
                    _logic.collided(((PRPSpriteGTGE)ball).getSpriteLogic(), ((PRPSpriteGTGE)brick).getSpriteLogic(),collisionSide);
                }
            });
            
            // коллизии нового шарика со всеми предыдущими
            for(int i=0;i<_groupBalls.size()-1;++i){
               _playField.addCollisionGroup(tmpSG, _groupBalls.get(i), new CollisionGroup() {

                @Override
                public void collided(Sprite ball1, Sprite ball2) {
                    revertPosition1();
                    revertPosition2();
                    _logic.collided(((PRPSpriteGTGE)ball1).getSpriteLogic(), ((PRPSpriteGTGE)ball2).getSpriteLogic(),collisionSide);
                }
              });
            }
        }
        else if(sprite.getClass() == PRPBrick.class){
            _groupBricks.add(sprite.getSpriteGTGE());
        }
        else if(sprite instanceof PRPPlatform){
            _groupBricks.add(sprite.getSpriteGTGE());
        }
        
    }
    
    public void removeSprite(Sprite sprite){
        // тут лучше через setActive(false) но пока оставлю так
        for(int i=0;i<_groupBalls.size();++i){
            if(_groupBalls.get(i).getActiveSprite()==sprite){
                _groupBalls.get(i).setActive(false);
            }
        }
        _groupBricks.remove(sprite);
    }
}
