package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



import Entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    // SCREEN SETTING
    final int orgTileSize = 16;
    final int scale = 3;

    public final int tileSize = orgTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;


    //tile
    TileManager tileM = new TileManager(this);

    Keyhandler keyH = new Keyhandler();
    Thread gameThread;
    Player player = new Player(keyH, this);
    

    //Set player defult position 
    int playerX = 100;
    int playerY = 100;

    //Player Speed
    int playerSpeed = 1;

    //player Animation
    
    public GamePanel (){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground( Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //background
        

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void run()
    {
        double drawInterval = 100000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) 
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if (delta >= 1)
            {
                //Update 
                update();

                //Draw: 
                repaint();
                delta--;
            }
        }
    }

    public void update()
    {
       player.update();
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);
        
        g2.dispose();
    }
}
 