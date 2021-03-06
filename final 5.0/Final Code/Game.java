import java.awt.*;
import javax.swing.*;

public class Game
{
    private JFrame frame;
    private Container contentPane;
    private firstPanel first;
    private secondPanel second;
    private thirdPanel third;
    private JLabel gameImage;
    private boardPanel board;


    public Game()
    {
        frame = new JFrame();
        frame.setBounds(100,100,800,400);
        frame.setResizable(false);
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        first = new firstPanel();

        JPanel holding = first.getHolding();
        contentPane.add(holding, BorderLayout.EAST);

        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon("image/zm2001_box_front.png"));
        contentPane.add(gameImage, BorderLayout.WEST);
        JButton play = first.getNewGame();
        play.addActionListener(ae -> go2Frame());


        /**
         *
         *
         *
         */


        frame.setPreferredSize(new Dimension(800,800));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible( true);
    }

    public void go2Frame() {
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        second = new secondPanel();
        JPanel holding2 = second.getHolding2();
        contentPane.add(holding2, BorderLayout.EAST);
        contentPane.add(gameImage,BorderLayout.WEST);
        JButton next = second.getNext();
        next.addActionListener(ae->go3Frame());
        JButton previous = second.getPrevious();
        previous.addActionListener(ae -> go1Frame());
        frame.repaint();
        frame.revalidate();
    }

    public void go3Frame() {
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        third = new thirdPanel();
        JPanel holding3 = third.getHolding3();
        contentPane.add(holding3, BorderLayout.EAST);
        contentPane.add(gameImage, BorderLayout.WEST);
        JButton next = third.getNext();
        next.addActionListener(ae->Board());
        JButton previous = third.getPrevious();
        previous.addActionListener( ae -> goBack2Frame());
        frame.repaint();
        frame.revalidate();
    }

    public void goBack2Frame() {
        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        JPanel holding2 = second.getHolding2();
        contentPane.add(holding2,BorderLayout.EAST);
        contentPane.add(gameImage,BorderLayout.WEST);
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
    public void Board()
    {

        BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        board = new boardPanel();
        JPanel boardPanel = board.getHolding5();
        boardPanel.setBounds(0,0,70,700);
        contentPane.add(boardPanel,BorderLayout.CENTER);


        /**
         * Creating a Panel for to ring a bell for a turn and enter the no. of moves
         *
         */

        JPanel bidPanel = new JPanel();
        bidPanel.setVisible(true);
        player [] players = new player[4];
        bidPanel.setLayout(new GridLayout(4,1));
        JPanel time = new JPanel();
        time.setBounds(0,0,1,1);
        JLabel label =new JLabel("Time");
        time.add(label);
        for(int i =0; i<players.length; i++){
            players[i]=new player();
            players[i].setColor(i);
            bidPanel.add(players[i].getBell(),BorderLayout.CENTER);
        }
        contentPane.add(time, BorderLayout.NORTH);
        contentPane.add(bidPanel, BorderLayout.EAST);




        frame.repaint();
        frame.revalidate();




    }
}
