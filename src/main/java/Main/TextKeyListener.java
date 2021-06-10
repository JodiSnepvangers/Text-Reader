package Main;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextKeyListener implements KeyListener {
    TextHandler textHandler;
    Frame frame;
    public TextKeyListener(TextHandler textHandler){
        this.textHandler = textHandler;
        this.frame = textHandler.frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TextHandler.undecorated = false;
    }

    @Override
    public void keyPressed(KeyEvent event) {
            if(event.getKeyCode() == 40){
                frame.buttonDecreaseFont();
            } else if(event.getKeyCode() == 38){
                frame.buttonIncreaseFont();
            } else if(event.getKeyCode() == 39){
                frame.buttonNextPage();
            } else if(event.getKeyCode() == 37){
                frame.buttonPreviousPage();
            } else if(event.getKeyCode() == 10){
                frame.requestFocusInWindow();
            } else if(event.getKeyCode() == 144){
                textHandler.textField.setCaretPosition(5);
            } else if(event.getKeyCode() == 16){
                frame.buttonPreviousWord();
            } else if(event.getKeyCode() == 32){
                frame.buttonNextWord();
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
