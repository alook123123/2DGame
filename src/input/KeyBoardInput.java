package input;

import main.GamePanel;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyBoardInput implements KeyListener {

        private final GamePanel gamePanel;

        public KeyBoardInput(GamePanel gamePanel)
        {
            this.gamePanel = gamePanel;
        }
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            switch(e.getKeyCode())
            {
                case KeyEvent.VK_D:
                    gamePanel.getGame().getPlayer().setRight(true);
                    break;
                case KeyEvent.VK_A:
                    gamePanel.getGame().getPlayer().setLeft(true);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePanel.getGame().getPlayer().setJump(true);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_D:
                    gamePanel.getGame().getPlayer().setRight(false);
                    break;
                case KeyEvent.VK_A:
                    gamePanel.getGame().getPlayer().setLeft(false);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePanel.getGame().getPlayer().setJump(false);
                    break;
            }
        }
}
