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
    JFrame frame;
    Container contentPane;
    firstFrame first;
    secondFrame second;
    JLabel gameImage;
    public everyThing()
    {
        frame = new JFrame();
        frame.setBounds(100,100,800,400);
        frame.setResizable(false);
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        first = new firstFrame();

        JPanel holding = first.getHolding();
        contentPane.add(holding, BorderLayout.EAST);

        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon("C:\\Users\\messe\\IdeaProjects\\creating iteration 2 before board\\src\\com\\company\\zm2001_box_front.png"));
        contentPane.add(gameImage, BorderLayout.WEST);
        JButton play = first.getNewGame();
        play.addActionListener(ae -> go2Frame());




        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible( true);
    }

    private void go2Frame() {
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        second = new secondFrame();
        JPanel holding2 = second.getHolding2();
        contentPane.add(holding2, BorderLayout.EAST);
        contentPane.add(gameImage,BorderLayout.WEST);
        JButton next = second.getNext();
        JButton previous = second.getPrevious();
        previous.addActionListener(ae -> go1Frame());
        frame.repaint();
        frame.revalidate();


    }

    public void go1Frame()
    {
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        JPanel holding = first.getHolding();
        contentPane.add(holding, BorderLayout.EAST);
        contentPane.add(gameImage, BorderLayout.WEST);
        frame.repaint();
        frame.revalidate();

    }
}
