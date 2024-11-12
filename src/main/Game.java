package main;

import entities.Player;
import levels.LevelManager;


import java.awt.Graphics;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelmanager;


    public final static int TIlE_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILE_IN_WIDTH = 26;
    public final static int TILE_IN_HEIGHT = 14;
    public final static int TILE_SIZE = (int)(TIlE_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILE_SIZE * TILE_IN_WIDTH;
    public final static int GAME_HEIGHT = TILE_SIZE * TILE_IN_HEIGHT;




    public Game()
    {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();

    }

    private void initClasses()
    {
        levelmanager = new LevelManager(this);
        player = new Player(200, 200, (int)SCALE*128, (int)SCALE*128);
        player.loadLvlData(levelmanager.getCurrentLvl().getLeLevelData());

    }


    private  void startGameLoop()
    {
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void update()
    {
        gamePanel.updateGame();
        player.update();
        levelmanager.update();
    }

    public void render(Graphics g)
    {

        levelmanager.draw(g);
        player.render(g);

    }

    @Override
    public void run() {

            double timePerFrame = 1000000000.0 / FPS_SET;
            double timePerUpdate = 1000000000.0 / UPS_SET;

            int frame = 0;
            int updates = 0;
            long previousTime = System.nanoTime();

            double deltaU = 0;
            double deltaF = 0;

            long lastCheck =  System.currentTimeMillis();
            while (true)
            {

                long currentTime = System.nanoTime();

                deltaU +=(currentTime - previousTime)/ timePerUpdate;
                deltaF += (currentTime - previousTime)/ timePerFrame;
                previousTime = currentTime;

                if (deltaU >= 1)
                {
                    update();
                    updates++;
                    deltaU--;

                }

                if (deltaF >= 1)
                {
                    gamePanel.repaint();
                    deltaF--;
                    frame++;
                }


                if (System.currentTimeMillis() - lastCheck >=1000)
                {
                    lastCheck = System.currentTimeMillis();
                    System.out.println("FBS " + frame + "| UPS " + updates);
                    frame = 0;
                    updates = 0;
                }
            }
    }


    public  void windowFocusLost()
    {
        player.resetDirBoolean();
    }

    public Player getPlayer()
    {
        return  player;
    }


    //temp
    public GameWindow getGameWindow() {
        return gameWindow;
    }

    //t
    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }
}
