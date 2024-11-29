package utilz;

import main.Game;

//import static utilz.Constants.UI.Buttons.B_HEIGHT_DEFAULT;

//import javax.print.attribute.standard.OutputBin;
//import java.security.PublicKey;

public class Constants {

    public static class UI{
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140; //HOW WIDTH OF THE BUTTON
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (Game.SCALE * B_HEIGHT_DEFAULT);
        }
    }
    public static class Directions
    {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }



    public static class PlayerConstant
    {

        //the order is depended on the player sprite matrix
        public static final int RUNNING = 0;
        public static final int IDLE = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;

        //Get the player sprite pos
        public static int GetSpriteAmount(int player_action)
        {
            switch (player_action)
            {
                case RUNNING:
                    return 3;
                case IDLE:
                    return 5;
                case JUMPING:
                    return 3;
                case FALLING:
                    return 3;
                default:
                    return 0;
            }
        }

    }
}
