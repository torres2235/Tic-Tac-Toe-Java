import java.awt.*;
import java.awt.event.*;
import java.util.*;

//javax.swing allows us to build our GUI
import javax.swing.*;

public class TicTacToe implements ActionListener{

    //will help us randomly choose which player starts
    Random random = new Random();

    JFrame frame = new JFrame();

    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];

    JPanel reset_button_panel = new JPanel();
    JButton reset_button = new JButton();

    boolean player1_turn; //if player1_turn = true, player1's turn; if false, player2's turn

    //constructor for TicTacToe
    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Serif", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Helvetica", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setOpaque(true);
            //buttons[i].setBorderPainted(false);
        }

        reset_button_panel.setLayout(new GridLayout(0,1));
        reset_button_panel.setBounds(0, 0, 800, 50);

        //reset_button.setText("Reset");
        reset_button.addActionListener(this);
        reset_button_panel.add(reset_button);

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        frame.add(reset_button_panel, BorderLayout.SOUTH);

        firstTurn();
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        for(int i = 0; i < 9; i++) {
            if(e.getSource() == buttons[i]) {
                if(player1_turn == true) {
                    if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");

                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                    }
                } else {
                    if(buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");

                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }

        }

        if(e.getSource() == reset_button) {
            for(int i = 0; i < 9; i++) {
                buttons[i].setEnabled(true);
                buttons[i].setBackground(Color.white);
                buttons[i].setText("");
                firstTurn();
            }
        }
    }

    //method that decides which player goes first (X or O)
    public void firstTurn() {

        //makes this method sleep to allow "Tic-Tac-Toe" to display for 2000ms before starting game
        /*
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */

        reset_button.setText("Reset");

        if(random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    //method that checks which player wins
    public void check() {
        //---------------------check X win condtions --------------------
        if((buttons[0].getText() == "X") && (buttons[1].getText() == "X") && (buttons[2].getText() == "X")) {
            xWins(0,1,2);
        } //checks top row

        if((buttons[0].getText() == "X") && (buttons[3].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(0,3,6);
        } //checks left col

        if((buttons[0].getText() == "X") && (buttons[4].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(0,4,8);
        } //checks diagonal \

        if((buttons[1].getText() == "X") && (buttons[4].getText() == "X") && (buttons[7].getText() == "X")) {
            xWins(1,4,7);
        } //checks middle col

        if((buttons[2].getText() == "X") && (buttons[5].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(2,5,8);
        } //checks right col

        if((buttons[2].getText() == "X") && (buttons[4].getText() == "X") && (buttons[6].getText() == "X")) {
            xWins(2,4,6);
        } //checks diagonal /

        if((buttons[3].getText() == "X") && (buttons[4].getText() == "X") && (buttons[5].getText() == "X")) {
            xWins(3,4,5);
        } //checks middle row

        if((buttons[6].getText() == "X") && (buttons[7].getText() == "X") && (buttons[8].getText() == "X")) {
            xWins(6,7,8);
        } //checks bottom row

        //---------------------check O win condtions --------------------
        if((buttons[0].getText() == "O") && (buttons[1].getText() == "O") && (buttons[2].getText() == "O")) {
            oWins(0,1,2);
        } //checks top row

        if((buttons[0].getText() == "O") && (buttons[3].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(0,3,6);
        } //checks left col

        if((buttons[0].getText() == "O") && (buttons[4].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(0,4,8);
        } //checks diagonal \

        if((buttons[1].getText() == "O") && (buttons[4].getText() == "O") && (buttons[7].getText() == "O")) {
            oWins(1,4,7);
        } //checks middle col

        if((buttons[2].getText() == "O") && (buttons[5].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(2,5,8);
        } //checks right col

        if((buttons[2].getText() == "O") && (buttons[4].getText() == "O") && (buttons[6].getText() == "O")) {
            oWins(2,4,6);
        } //checks diagonal /

        if((buttons[3].getText() == "O") && (buttons[4].getText() == "O") && (buttons[5].getText() == "O")) {
            oWins(3,4,5);
        } //checks middle row

        if((buttons[6].getText() == "O") && (buttons[7].getText() == "O") && (buttons[8].getText() == "O")) {
            oWins(6,7,8);
        } //checks bottom row
    }

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++) {
            if(buttons[i] == buttons[a]  || buttons[i] == buttons[b] || buttons[i] == buttons[c]) {
                continue;
            } else {
                buttons[i].setEnabled(false);
            }
        }
        reset_button.setText("Rematch?");
        textfield.setText("X wins");
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++) {
            if (buttons[i] == buttons[a] || buttons[i] == buttons[b] || buttons[i] == buttons[c]) {
                continue;
            } else {
                buttons[i].setEnabled(false);
            }
        }

        reset_button.setText("Rematch?");
        textfield.setText("O wins");
    }
}
