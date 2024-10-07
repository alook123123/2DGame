package entities;

import main.Game;
import utilz.LoadSave;

import static utilz.HelpMethods.canMovehere;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstant.*;

public class Player extends Entity{


    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean left, right;
    private boolean moving = false;
    private float playerSpeed = 2;
    private int[][] lvlData;
    // Draw Player hitbox Pos
    private float xDrawOffSet = 30 * Game.SCALE;

    private float yDrawOffSet = 32 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x,y,width, height);
        loadAnimations();

        //Hitbox width, height scale
        initHitbox(x,y,22 * Game.SCALE, 15* Game.SCALE);
    }


    public void update()
    {
        upDatePos();
        //updateHitbox();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g)
    {
        g.drawImage(animations[aniIndex][playerAction], (int)(hitbox.x - xDrawOffSet) ,(int)(hitbox.y - yDrawOffSet),width,height,null);
        drawHitbox(g);

    }



    private void setAnimation()
    {
        if (moving)
        {
            playerAction = RUNNING;
        } else
            playerAction = IDLE;
    }

    private void updateAnimationTick()
    {
        aniTick++;
        if (aniTick >=  aniSpeed)
        {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction))
            {
                aniIndex = 0;
            }
        }
    }

    private void upDatePos()
    {

        moving = false;
        if (!left && !right) return;

        float xSpeed = 0, ySpeed = 0;

        if ( left && !right)
        {
            xSpeed = -playerSpeed;

        } else if (right && !left)
            {
                xSpeed = playerSpeed;
            }

//        if (canMovehere(x + xSpeed, y + ySpeed, width, height, lvlData))
//        {
//            this.x +=xSpeed;
//            this.y +=ySpeed;
//            moving = true;
//        }

        if (canMovehere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData))
        {
            hitbox.x +=xSpeed;
            hitbox.y +=ySpeed;
            moving = true;
        }
    }

    private void loadAnimations()
    {

            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            //Player sprite matrix
            animations = new BufferedImage[6][3];
            for (int j = 0; j< animations.length; j++)
            {
                for (int i = 0; i< animations[j].length; i++)
                {
                    animations[j][i] = img.getSubimage(i*128, j*128, 128, 128 );
                }
            }

    }

    public void loadLvlData(int[][] lvlData)
    {
        this.lvlData = lvlData;
    }

    public void resetDirBoolean()
    {
        left = false;
        right =false;
    }
    public void setLeft(boolean left)
    {
        this.left = left;
    }
    public boolean isleft()
    {
        return left;
    }

    public void setRight(boolean right)
    {
        this.right = right;
    }
    public boolean isRight()
    {
        return right;
    }
}
