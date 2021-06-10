package Main;



import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

public class Frame extends JFrame {
    @Override
    public String getTitle() {
        return "Text Reader " + Main.programVersion;
    }

    JButton resetEnter = new JButton("Start Text Reader");
    JButton increaseFont = new JButton("Increase font size");
    JButton decreaseFont = new JButton("Decrease font size");
    JButton nextPage = new JButton("Next paragraph");
    JButton prevPage = new JButton("Previous paragraph");
    JButton nextWord = new JButton("Next word");
    JButton prevWord = new JButton("Previous word");
    JButton toggleBorder = new JButton("Toggle");

    ImageIcon icon = new ImageIcon(getClass().getResource("/CatIcon.png"));
    JLabel kittyIcon = new JLabel(icon);

    ImageIcon icon2 = new ImageIcon(getClass().getResource("/title.png"));
    JLabel title = new JLabel(icon2);

    TextHandler textHandler;

    public Frame(TextHandler textHandler, boolean undecorated){
        //initialize variables:
        this.textHandler = textHandler;
        nextPage.setEnabled(false);
        prevPage.setEnabled(false);
        nextWord.setEnabled(false);
        prevWord.setEnabled(false);

        if(textHandler.begunDisplaying){
            if(textHandler.currentTextIndex != 0)prevPage.setEnabled(true);
            if((textHandler.currentTextIndex + 1) < textHandler.stringList.size())nextPage.setEnabled(true);
            resetEnter.setText("Reset Text Reader");
            nextWord.setEnabled(true);
            prevWord.setEnabled(true);
        }

        //Frame Parameters:
        if(undecorated){
            setSize(1485, 860);
        } else {
            setSize(1500, 900);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setUndecorated(undecorated);
        buttonRow();

        title.setBounds(1070, 0, 160, 45);
        title.setBackground(Color.pink);
        title.setVisible(true);
        add(title);

        kittyIcon.setBounds(1000, 0, 70, 45);
        kittyIcon.setBackground(Color.pink);
        kittyIcon.setVisible(true);
        kittyIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {

            }

            @Override
            public void mousePressed(MouseEvent event) {
                try {
                    // Open an audio input stream.
                    URL url = this.getClass().getClassLoader().getResource("cat.wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    // Get a sound clip resource.
                    Clip clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input stream.
                    clip.open(audioIn);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(kittyIcon);

        toggleBorder.setEnabled(true);
        toggleBorder.setBounds(1400, 7, 80, 30);
        buttonSetup(toggleBorder);
        toggleBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textHandler.undecorated = !textHandler.undecorated;
                textHandler.refreshFrame(getLocation());
                dispose();
            }
        });
        add(toggleBorder);
        }


    public void buttonIncreaseFont(){
        textHandler.increaseFont();
        if(textHandler.currentFontSize > textHandler.minFontSize){
            decreaseFont.setEnabled(true);
        }
        if(textHandler.currentFontSize >= textHandler.maxFontSize){
            increaseFont.setEnabled(false);
        }
    }
    public void buttonDecreaseFont(){
        textHandler.decreaseFont();
        if(textHandler.currentFontSize <= textHandler.minFontSize){
            decreaseFont.setEnabled(false);
        }
        if(textHandler.currentFontSize < textHandler.maxFontSize){
            increaseFont.setEnabled(true);
        }
    }
    public void buttonNextPage(){
        textHandler.nextPage();
        if((textHandler.currentTextIndex + 1) >= textHandler.stringList.size()){
            nextPage.setEnabled(false);
        }
        if(textHandler.currentTextIndex > 0){
            prevPage.setEnabled(true);
        }
    }
    public void buttonPreviousPage(){
        textHandler.prevPage();
        if((textHandler.currentTextIndex + 1) < textHandler.stringList.size()){
            nextPage.setEnabled(true);
        }
        if(textHandler.currentTextIndex <= 0){
            prevPage.setEnabled(false);
        }
    }
    public void buttonNextWord(){
        textHandler.nextWord();
    }

    public void buttonPreviousWord(){
        textHandler.previousWord();
    }

    private void buttonSetup(JButton tempVar){
        tempVar.setFocusable(false);
        tempVar.setFont(new Font("Serif", Font.PLAIN, 18));
        tempVar.setVisible(true);
    }

    private void buttonRow(){
        //Button Parameters:
        int sizeX = 206;
        int sizeY = 50;
        int positionX = 5;
        int positionXOffset = 211;
        int positionY = 805;


        JButton tempVar = resetEnter;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textHandler.begunDisplaying){
                    Main.reset();
                } else {
                    resetEnter.setText("Reset Text Reader");
                    textHandler.initializeScreen();
                    nextPage.setEnabled(true);

                    nextWord.setEnabled(true);
                    prevWord.setEnabled(true);
                }
            }
        });
        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = decreaseFont;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonDecreaseFont();
            }
        });

        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = increaseFont;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonIncreaseFont();
            }

        });

        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = prevPage;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPreviousPage();
            }
        });

        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = nextPage;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNextPage();
            }
        });
        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = prevWord;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPreviousWord();
            }
        });
        add(tempVar);
        positionX = positionX + positionXOffset;

        tempVar = nextWord;
        tempVar.setBounds(positionX, positionY, sizeX, sizeY);
        buttonSetup(tempVar);
        tempVar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNextWord();
            }
        });
        add(tempVar);
    }
}
