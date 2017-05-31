package com.ratpacksoftware.EstelingServer;

import com.ratpacksoftware.UI.ServerGui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            for(String arg : args) {
                if (arg != "nogui") {
                    useGui();
                }
            }

            new EstelingServer(5327).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void useGui() {
        JFrame frame = new JFrame("Esteling Server");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(JFrame.NORMAL);
        frame.setContentPane(new ServerGui());
        frame.pack();
        frame.setVisible(true);
    }
}
