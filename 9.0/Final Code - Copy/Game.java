import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;

public class Game {
    private JFrame frame;
    private Container contentPane;
    private firstPanel first;
    private secondPanel second;
    private thirdPanel third;
    private JLabel gameImage;
    private boardPanel board;
    private JLabel lblTimeLeft;
    private Timer timer;
    private player[] players;
    //Non swing variables or logic variables
    private boolean turn_over = true; // to control turn
    private boolean set_timer = false; // to know when to set_timer
    private boolean game_over = false; // to know when to end game
    private ArrayList<Integer> moves; // To store moves of the players
    private int minutes = 1;
    private int seconds;
    private HashMap<Integer,Integer> moving;

    public Game() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 400);
        frame.setResizable(false);
        contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        first = new firstPanel();

        JPanel holding = first.getHolding();
        contentPane.add(holding, BorderLayout.EAST);

        gameImage = new JLabel();
        gameImage.setIcon(new ImageIcon(getClass().getResource("image/zm2001_box_front.png")));
        contentPane.add(gameImage, BorderLayout.WEST);
        JButton play = first.getNewGame();
        play.addActionListener(ae -> from1to2());

        moving = new HashMap<Integer,Integer>();
        /**
         *
         *
         *
         */


        frame.setPreferredSize(new Dimension(900, 800));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void from1to2() {
        /*BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        */
        //removing the previous panel
        JPanel holding = first.getHolding();
        holding.setVisible(false);

        //adding new panel
        second = new secondPanel();
        JPanel holding2 = second.getHolding2();
        contentPane.add(holding2, BorderLayout.EAST);

        //getting next and adding action listner
        JButton next = second.getNext();
        next.addActionListener(ae -> from2to3());

        //getting previous and adding action listner
        JButton previous = second.getPrevious();
        previous.addActionListener(ae -> from2to1());

        //repainting and revalidating
        frame.repaint();
        frame.revalidate();
    }

    public void from2to3() {
        /*BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        */

        //removing the elements of previous panel.
        JPanel holding2 = second.getHolding2();
        holding2.setVisible(false);

        //making the thirdPanel and adding it to the main panel.
        third = new thirdPanel();
        JPanel holding3 = third.getHolding3();
        contentPane.add(holding3, BorderLayout.EAST);

        //getting next and previous button and adding action listener
        JButton next = third.getNext();
        next.addActionListener(ae -> Board());
        JButton previous = third.getPrevious();
        previous.addActionListener(ae -> from3to2());

        //repainting and revalidating
        frame.repaint();
        frame.revalidate();
    }

    public void from3to2() {
        /*BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        */
        JPanel holding3 = third.getHolding3();
        holding3.setVisible(false);


        JPanel holding2 = second.getHolding2();
        holding2.setVisible(true);

        frame.repaint();
        frame.revalidate();
    }

    public void from2to1() {
        /*BorderLayout layout = (BorderLayout)contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        */

        JPanel holding = first.getHolding();
        JPanel holding2 = second.getHolding2();

        holding2.setVisible(false);
        holding.setVisible(true);

        frame.repaint();
        frame.revalidate();
    }

    public void Board() {

        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        contentPane.remove(layout.getLayoutComponent(BorderLayout.EAST));
        contentPane.remove(layout.getLayoutComponent(BorderLayout.WEST));
        board = new boardPanel();
        JPanel boardPanel = board.getHolding5();
        boardPanel.setBounds(0, 0, 70, 700);
        contentPane.add(boardPanel, BorderLayout.CENTER);


        /**
         * Creating a Panel for to ring a bell for a turn and enter the no. of moves
         *
         */

        JPanel bidPanel = board.getbidPanel();
        bidPanel.setVisible(true);
        players = new player[4];
        bidPanel.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < players.length; i++) {
            players[i] = new player();
            players[i].setColor(i);
            int finalI = i;
            players[i].getBell().addActionListener(e -> enterInput(finalI));
            bidPanel.add(players[i].getBell(), BorderLayout.CENTER);
        }
        contentPane.add(bidPanel, BorderLayout.EAST);


        frame.repaint();
        frame.revalidate();


    }

    public void enterInput(int number) {
        int i = -1;
        while (i < 0) {
            String input = JOptionPane.showInputDialog("Enter the no. of moves");
            if (input.length() > 0) {
                try{
                    int inp;
                    inp = Integer.parseInt(input);
                    i++;
                    moving.put(number, inp);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        if(!set_timer){
            set_timer =true;
            lblTimeLeft = new JLabel(String.format("Time left:%02d:00", minutes));
            lblTimeLeft.setHorizontalAlignment(SwingConstants.CENTER);
            lblTimeLeft.setForeground(Color.BLACK);
            seconds = 0;
            timer = new Timer(1000, e-> {
                if(seconds >= minutes*60){
                    timer.stop();
                    for(int x =0; x< players.length;x++)
                    {
                        players[x].getBell().setEnabled(false);
                    }
                    set_timer = false;

                    JOptionPane.showMessageDialog(null, "Player 1 make you move!");
                }
                else{
                    seconds+=1;
                    lblTimeLeft.setBackground(Color.BLACK);
                    lblTimeLeft.setText("Time left:" + (String.format("%02d", (minutes * 60 - seconds) / 60)) + ":" + (String.format("%02d", (minutes * 60 - seconds) % 60)));
                }

            });
            timer.setRepeats(true);
            timer.start();
            frame.setPreferredSize(new Dimension(800,800));
            lblTimeLeft.setBounds(590, 64, 136, 14);
            contentPane.add(lblTimeLeft,BorderLayout.NORTH);
            frame.revalidate();
            frame.repaint();
        }


    }
}
