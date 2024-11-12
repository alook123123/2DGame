package entities;

import main.Game;
import utilz.LoadSave;

import static utilz.HelpMethods.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstant.*;


public class Player extends Entity{


    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 20;
    private int playerAction = IDLE;
    private boolean left, right, jump;
    private boolean moving = false;
    private float playerSpeed = 2;
    private int[][] lvlData;

    //Jumping , Gravity
    private float airSpeed = 0;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed =  -2.25f * Game.SCALE;
    private float fallSpeedAfterCollison  = 0.5f *Game.SCALE;
    private boolean inAir = false;

    // Draw Player hitbox Pos
    private float xDrawOffSet = 30 * Game.SCALE;

    private float yDrawOffSet = 32 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x,y,width, height);
        loadAnimations();

        //Hitbox width, height scale
        initHitbox(x,y,22 * Game.SCALE, 16* Game.SCALE);


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
        int starAni = playerAction;

        if (moving)
        {
            playerAction = RUNNING;
        } else
        {
            playerAction = IDLE;
        }

        if(inAir)
        {
            if( airSpeed < 0 )
            {
                playerAction = JUMPING;
            } else
            {
                playerAction = FALLING;
            }
        }

        if (starAni != playerAction)
        {
            resetAniTick();
        }
    }

    private void resetAniTick()
    {
        aniTick = 0;
        aniIndex = 0;

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

        if (jump )
        {
            jump();
        }

        // if change this code line to the top of the jump line it will block the jump
        // and you can only jump when moving
        if (!left && !right && !inAir) return;



        float xSpeed = 0;

        if ( left )
        {
            xSpeed -= playerSpeed;

        }

        if (right)
        {
                xSpeed += playerSpeed;
        }

        if (!inAir)
        {
            if(!IsEntityOnFloor(hitbox,lvlData))
            {
                inAir = true;
            }
        }

        if (inAir)
        {
            if (canMovehere(hitbox.x, hitbox.y + airSpeed,hitbox.width, hitbox.height, lvlData))
            {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else
            {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
                if (airSpeed > 0f)
                    resetInAir();
                 else
                    airSpeed = fallSpeedAfterCollison;
                updateXPos(xSpeed);
            }
        } else
            {
                updateXPos(xSpeed);
            }
        moving = true;
    }

    private void jump()
    {
        if(inAir) return;
        inAir = true;
        airSpeed = jumpSpeed;

    }

    private void resetInAir()
    {
        inAir = false;
        airSpeed = 0f;
    }

    private void updateXPos(float xSpeed)
    {
        if (canMovehere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
        {
            hitbox.x +=xSpeed;
        } else
        {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    private void loadAnimations()
    {

            BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            //Player sprite matrix
            animations = new BufferedImage[5][4];
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
        if (!IsEntityOnFloor(hitbox, lvlData))
        {
            inAir = true;
        }
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

    public void setJump(boolean jump)
    {
        this.jump = jump;
    }
}
