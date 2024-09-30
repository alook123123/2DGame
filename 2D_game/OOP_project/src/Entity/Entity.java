package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public float worldX, worldY;
    public float speed;

    //Player Image move
    public BufferedImage left1, left2, left3, right1, right2, right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

     //Player Image stand 
    public BufferedImage stand1, stand2, stand3, stand4, stand5;
    public int spriteStandCounter = 0;
    public int spriteStandNum = 1;



}
