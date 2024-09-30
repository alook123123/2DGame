package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Graphics2D;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();

    }

    

    public void getTileImage()
    { 

        try {
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile4.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile5.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile6.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile7.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile8.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/tiles/tile9.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
      
    }


    public void loadMap()
    {
        try {
            
            InputStream is = getClass().getResourceAsStream("/res/Maps/Map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow)
            {
                String line = br.readLine();

                while (col < gp.maxScreenCol)
                {
                    String number[] = line.split(" ");
                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxScreenCol)
                {
                    col =0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2)        
        {

            int col = 0;
            int row = 0;
            int x =0;
            int y =0;


            while (col < gp.maxScreenCol && row < gp.maxScreenRow)
            {

                int tileNum = mapTileNum[col][row];

                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
                col++;
                x+=gp.tileSize;
                
                if (col == gp.maxScreenCol)
                {
                    col = 0;
                    x  = 0;
                    row++;
                    y += gp.tileSize;
                }
            }
        }

}
