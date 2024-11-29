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
    private boolean left, right, jump, down, up;
    private boolean moving = false;
    private float playerSpeed = 1.0f * Game.SCALE;
    private int[][] lvlData;

    //Jumping , Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed =  -2.25f * Game.SCALE;
    private float fallSpeedAfterCollison  = 0.5f *Game.SCALE;
    private boolean inAir = false;
    private int jumpCount = 0;
    private int maxJumps = 2;

    //testing only varialbles
    private int i=0;

    // Draw Player hitbox Pos
    private float xDrawOffSet = 30 * Game.SCALE;

    private float yDrawOffSet = 32 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x,y,width, height);
        loadAnimations();

        //Hitbox width, height scale
        initHitbox(x,y,(int) (22 * Game.SCALE), (int) (16* Game.SCALE));


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

        if (jump)
        {
            jump();
        }

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
                if (airSpeed > 0)
                {
                    resetInAir();
                } else
                {
                    airSpeed = fallSpeedAfterCollison;
                }

                updateXPos(xSpeed);
            }

        } else
        {
            updateXPos(xSpeed);
        }
        moving = true;
    }

    private void jump() //help to jump again
    {
        if(jumpCount >= maxJumps) return;

        inAir = true;
        airSpeed = jumpSpeed;
        jumpCount = jumpCount + 1;
        setJump(false);


    }

    private void resetInAir()
    {
        inAir = false;
        jumpCount = 0;
        airSpeed = 0;
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
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
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

    public void resetDirBooleans()
    {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
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

    public void setJump(boolean jump) {
        if (jump && jumpCount < maxJumps)
            this.jump = jump;
        else
            this.jump = false;
    }
}
