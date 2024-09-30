package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyhandler implements KeyListener{

    public boolean leftPress, rightPress;


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {


        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_D)
        {
            rightPress = true;
        }

        if (code == KeyEvent.VK_A)
        {
            leftPress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_D)
        {
            rightPress = false;
        }

        if (code == KeyEvent.VK_A)
        {
            leftPress = false;
        }
    }
    
}
