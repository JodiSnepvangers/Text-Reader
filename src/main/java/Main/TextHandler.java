package Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextHandler {

    public boolean undecorated = false;
    Frame frame = new Frame(this, false);
    JFrame jFrame = frame;
    JTextArea textField = new JTextArea("Please copy paste text here. \nUse left and right arrow keys to scroll forward or backward. \nUse up and down arrow keys to adjust font size. \nPress spacebar to highlight the next word, and shift to go back. \nPress the button down below to start viewing.");
    TextKeyListener keyListener = new TextKeyListener(this);

    boolean begunDisplaying = false;

    int beginnerOffset = 0;

    int currentTextIndex = 0;
    int currentFontSize = 18;

    int maxFontSize = 80;
    int minFontSize = 12;



    String fontText = "Serif";


    public TextHandler(){
        jFrame.add(textField);
        Font font = new Font(fontText, Font.PLAIN, currentFontSize);
        textField.setFont(font);
        textField.setBounds(5, 40, 1475, 760);
        textField.setLineWrap(true);
        textField.setWrapStyleWord(true);
        textField.setSelectionColor(Color.darkGray);

        jFrame.setVisible(true);


    }


    List<String> stringList = new ArrayList<>();

    public void initializeList(String text){
        text = text.replaceAll("\n", ".");
        String[] sSentence = text.split("\\.");
        stringList.addAll(Arrays.asList(sSentence));
        stringList.removeAll(Arrays.asList("", null));
        stringList.removeAll(Arrays.asList(" ", null));
        stringList.removeAll(Arrays.asList("\n", null));
        for(int i = 0; i < stringList.size(); i++){
            String string = " " + stringList.get(i);
            stringList.set(i, string);
        }
    }

    public void updateTextPane() {
        if (currentTextIndex >= stringList.size()) return;
        String pageCounter = (currentTextIndex + 1) + "/" + stringList.size() + ":";
        String content = stringList.get(currentTextIndex).replace("\n", "");
        content = content.replaceFirst("  ", " ");
        textField.setText(pageCounter + content);
        beginnerOffset = pageCounter.length() + 1;
        textField.setCaretPosition(beginnerOffset);
        if((currentTextIndex + 1) >= stringList.size()){
            frame.nextPage.setEnabled(false);
        } else {
            frame.nextPage.setEnabled(true);
        }
        if(currentTextIndex == 0){
            frame.prevPage.setEnabled(false);
        } else {
            frame.prevPage.setEnabled(true);
        }
    }

    public void nextPage(){
        //scroll forward
        if((currentTextIndex + 1) >= stringList.size()){
            frame.nextWord.setEnabled(false);
            return;
        }
        currentTextIndex++;
        updateTextPane();
    }

    public void prevPage(){
        //scroll backwards
        if(currentTextIndex <= 0){
            frame.prevWord.setEnabled(false);
            return;
        }
        currentTextIndex--;
        updateTextPane();
    }

    public void increaseFont() {
        if(currentFontSize >= maxFontSize)return;
        currentFontSize++;
        Font font = new Font(fontText, Font.PLAIN, currentFontSize);
        textField.setFont(font);
    }

    public void decreaseFont(){
        if(currentFontSize <= minFontSize)return;
        currentFontSize--;
        Font font = new Font(fontText, Font.PLAIN, currentFontSize);
        textField.setFont(font);
    }

    public void initializeScreen(){
        if(begunDisplaying == false){
            initializeList(textField.getText());
            updateTextPane();
            begunDisplaying = true;

            jFrame.addKeyListener(keyListener);
            textField.addKeyListener(keyListener);
            textField.setEditable(false);

        }
    }

    public void refreshFrame(Point point){
        frame = new Frame(this, undecorated);
        frame.setLocation(point);
        jFrame = frame;
        jFrame.add(textField);
        jFrame.setVisible(true);
    }

    public void nextWord(){
        if(textField.getText().length() == textField.getCaretPosition()){
            nextPage();
        }
        if(textField.getCaretPosition() <= beginnerOffset)textField.setCaretPosition(beginnerOffset);
        String currentText = textField.getText();
        int startIndex = currentText.lastIndexOf(" ", textField.getCaretPosition()) + 1;
        int endIndex = currentText.indexOf(" ", textField.getCaretPosition() + 1);
        if(endIndex == -1)endIndex = currentText.length();
        textField.select(startIndex, endIndex);
    }

    public void previousWord(){
        String currentText = textField.getText();
        int endIndex = currentText.lastIndexOf(" ", textField.getCaretPosition()-1);
        int startIndex = currentText.lastIndexOf(" ", endIndex-1);
        if(endIndex == -1)endIndex = currentText.length();
        if(startIndex == -1)
        if(startIndex <= beginnerOffset || endIndex <= beginnerOffset){
            startIndex = beginnerOffset - 1;
        }

        textField.select(startIndex + 1, endIndex);
    }
}
