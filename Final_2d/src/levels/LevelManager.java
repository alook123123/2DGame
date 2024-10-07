package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;


    public LevelManager(Game game)
    {
        this.game = game;
        //levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutSideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutSideSprites()
    {
        levelSprite = new BufferedImage[64];
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);

        for (int j = 0; j < 4; j++) // Map Sprite Height
        {
            for (int i = 0; i < 12; i++) //Map Sprite width
            {
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32,32);
            }
        }
    }

    public void draw(Graphics g)
    {
        for(int j = 0; j < Game.TILE_IN_HEIGHT; j++)
        {
            for ( int i = 0; i < Game.TILE_IN_WIDTH; i++)
            {
                int index = levelOne.getSpriteIndex(i,j);
                g.drawImage(levelSprite[index],Game.TILE_SIZE*i,Game.TILE_SIZE*j,Game.TILE_SIZE,Game.TILE_SIZE,null);
            }
        }
    }


    public void update()
    {

    }

    public Level getCurrentLvl()
    {
        return levelOne;
    }
}
