package main;

import input.KeyBoardInput;
import input.MouseInput;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import static main.Game.*;

public class GamePanel extends JPanel {

    //Variables
    private MouseInput mouseInput;
    private Game game;
    public GamePanel(Game game)
    {
        this.game = game;
        mouseInput = new MouseInput(this);

        setPanelSize();
        addKeyListener(new KeyBoardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void setPanelSize()
    {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame()
    {
        return game;
    }
}
