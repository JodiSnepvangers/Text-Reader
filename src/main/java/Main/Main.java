package Main;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    static TextHandler f;
    static String programVersion = "V1.1.2";

    public static void main(String[] args) {
        //System.setProperty("FrameBackground", "0X000000");
        //System.setProperty("TextBackground", "0X282828");
        //System.setProperty("TextColor", "0XC8C8C8");
        //System.setProperty("ButtonColor", "0X3D0000");
        //System.setProperty("ButtonTextColor", "0X989898");
        //System.setProperty("ButtonTextDisable", "0X300000");
        //System.setProperty("ButtonPressColor", "0X200000");
        //System.setProperty("windowBorder", "0X200000");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        UIManager.put("control", new Color(50, 50, 50));
        UIManager.put("nimbusFocus", new Color(20, 20, 20));
        UIManager.put("nimbusLightBackground", new Color(20, 20, 20));
        UIManager.put("textForeground", new Color(200, 200, 200));
        UIManager.put("nimbusBase", new Color(0, 0, 0));
        UIManager.put("InternalFrameTitlePane.background", new Color(255, 0, 0));

        f = new TextHandler();

    }

    public static void reset() {
        f.jFrame.dispose();
        f = new TextHandler();
        TextHandler.undecorated = false;
    }
}
