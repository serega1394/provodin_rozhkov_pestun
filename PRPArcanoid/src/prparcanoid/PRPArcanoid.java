/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prparcanoid;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author ve
 */
public class PRPArcanoid extends Game{
    public static void main(String[] args) {
        GameLoader game = new GameLoader(); // Класс, управляющий инициализацией приложения
        game.setup(new PRPArcanoid(), new Dimension(640,480), false);//Инициализация графического движка
        game.start();
    }
 
    @Override
    public void initResources() {
        //Инициализация игровых переменных и ресурсов
    }
 
    @Override
    public void render(Graphics2D g) {
        //Рендеринг графики
    }
 
    @Override
    public void update(long elapsedTime) {
        //Обновления переменных и ресурсов
    }
}
