package com.company;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.regex.*;

public class everyThing
{
    public everyThing()
    {
        JFrame frame = new JFrame();
        frame.setBounds(100,100,800,400);
        frame.setResizable(false);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        firstFrame first = new firstFrame();

        JPanel holding = first.getHolding();
        contentPane.add(holding, BorderLayout.EAST);

        JLabel gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon("C:\\Users\\messe\\IdeaProjects\\creating iteration 2 before board\\src\\com\\company\\zm2001_box_front.png"));
        contentPane.add(gameImage, BorderLayout.WEST);
        JButton play = first.getNewGame();
        play.addActionListener(ae -> go2Frame(contentPane));


        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible( true);
    }

    private void go2Frame(Container contentPane) {
        System.out.println("hey");
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        System.out.println("hey");
        secondFrame second = new secondFrame();
        JPanel holding2 = second.getHolding2();
        contentPane.add(holding2, BorderLayout.EAST);
    }
}
