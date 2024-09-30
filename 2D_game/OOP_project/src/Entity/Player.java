package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Keyhandler;

public class Player extends Entity{
        GamePanel gp;
        Keyhandler keyH;

        public final int screenX;
        public final int screenY;

        public Player(Keyhandler keyH, GamePanel gp)
        {
            this.gp = gp;
            this.keyH = keyH;

            screenY = gp.screenHeight/2 - (gp.tileSize/2);
            screenX = gp.screenWidth/2 - (gp.tileSize/2);

            setDefaultValues();
            getPlayerImage();
        }
    
        public void setDefaultValues()
        {
            //position
            worldX = gp.tileSize * 1;
            worldY = gp.tileSize * 5;


            speed = 1;
            direction = "stand";
        }

        //Move_animation
        public void getPlayerImage()
        {
          
            try {
                
                left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_left_1.png"));
                left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_left_2.png"));
                left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_left_3.png"));
                right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_right_1.png"));
                right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_right_2.png"));
                right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Player_move_right_3.png"));
                stand1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Stand_1.png"));
                stand2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Stand_2.png"));
                stand3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Stand_3.png"));
                stand4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Stand_4.png"));
                stand5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/Stand_5.png"));

                

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //Update
        public void update()
        {
            getKey();

            standAni();

            moveAni();
            
        }

        public void getKey()
        {
            //Press key to move
            
            if ( keyH.leftPress == true)
            {
                direction = "left";
                worldX -= (speed * 0.2);
            } else if ( keyH.rightPress == true)
                {
                    direction = "right";
                    worldX += (speed * 0.2);
                } else direction ="stand";
    
            //jump
            

        }

        public void draw(Graphics2D g2)
        {

            BufferedImage image = null;
            
            switch (direction) {
                case "left":
                    if ( spriteNum == 1)
                    {
                        image = left1;
                    }

                    if ( spriteNum == 2)
                    {
                        image = left2;
                    }

                    if ( spriteNum == 3)
                    {
                        image = left3;
                    }
                    break;
            
                case "right":
                    if (spriteNum == 1)
                    {
                        image = right1;
                    }
                    
                    if (spriteNum == 2)
                    {
                        image = right2;
                    }

                    if (spriteNum == 3)
                    {
                        image = right3;
                    }

                    break;
                default :
                    
                    if (spriteStandNum == 1)
                    {
                        image = stand1;
                    }

                    if (spriteStandNum == 2)
                    {
                        image = stand2;
                    }

                    if (spriteStandNum == 3)
                    {
                        image = stand3;
                    }

                    if (spriteStandNum == 4)
                    {
                        image = stand4;
                    }

                    if (spriteStandNum == 5)
                    {
                        image = stand5;
                    }
                    break;    
            }

            g2.drawImage(image, (int)worldX, (int)worldY, 100, 100, null);
        }

        public void moveAni()
        {
            //Animation
            if (keyH.leftPress == true || keyH.rightPress == true)
            {
                spriteCounter++;
                if (spriteCounter > 55)     //image change every 15 frame
                {
                    if (spriteNum == 1 )
                    {
                        spriteNum = 2;
                    } else if (spriteNum ==2)
                            {
                                spriteNum = 3;
                            } else if (spriteNum == 3)
                                    {
                                        spriteNum =1;
                                    }
                    //reset
                    spriteCounter = 0;        
                }
            }
        }

        public void standAni()
        {
            //Animation
            spriteStandCounter++;
            if (spriteStandCounter > 100)
            {
                if (spriteStandNum == 1)
                {
                    spriteStandNum = 2;
                } else if (spriteStandNum == 2)
                        {
                            spriteStandNum = 3;

                        } else if (spriteStandNum == 3)
                            {
                                spriteStandNum = 4;

                            } else if (spriteStandNum == 4)
                                    {
                                        spriteStandNum = 5;

                                    } else if (spriteStandNum == 5)
                                        {
                                            spriteStandNum = 1;
                                        }
                spriteStandCounter = 0;
            }
        }
    
}

