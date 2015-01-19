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
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ve
 */
public class PRPArcanoid extends Game{
    
    PRPArcanoidShell _logic;
    
    Background _backGround = new ColorBackground(new Color(255, 187, 9));
    PlayField _playField = new PlayField(_backGround);
   
    SpriteGroup _groupBalls;
    SpriteGroup _groupBricks;
    
    Map<String, BufferedImage> _images;
    
    private void addImage(String name){
        if(!_images.containsKey(name)){
            _images.put(name, getImage(name));
        }
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
        
        _groupBalls = new SpriteGroup("groupBalls");
        _groupBricks = new SpriteGroup("groupBricks");
        
        _playField.addGroup(_groupBalls);
        _playField.addGroup(_groupBricks);
        
        _playField.addCollisionGroup(_groupBalls, _groupBricks, new CollisionGroup() {

            @Override
            public void collided(Sprite ball, Sprite brick) {
                revertPosition1();
                _logic.collided(((PRPSpriteGTGE)ball).getSpriteLogic(), ((PRPSpriteGTGE)brick).getSpriteLogic(),collisionSide);
            }
        });
        
        _logic = new PRPArcanoidShell(this);
        _logic.initResources();
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
    
    
    
    public void addSprite(PRPSprite.SPRITE_TYPE type, Sprite sprite, String image){
        addImage(image);
        sprite.setImage(_images.get(image));
        
        if(type == PRPSprite.SPRITE_TYPE.BALL){
            _groupBalls.add(sprite);
        }
        else if(type == PRPSprite.SPRITE_TYPE.BRICK){
            _groupBricks.add(sprite);
        }
        else if(type == PRPSprite.SPRITE_TYPE.PLATFORM){
            _groupBricks.add(sprite);
        }
        
    }
    
    public void removeSprite(Sprite sprite){
        // тут лучше через setActive(false) но пока оставлю так
        _groupBalls.remove(sprite);
        _groupBricks.remove(sprite);
    }
}
