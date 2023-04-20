import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons;
    private JLabel label;
    private String currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        label = new JLabel("X's turn");
        currentPlayer = "X";
        gameOver = false;

        frame.add(panel, BorderLayout.CENTER);
        frame.add(label, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int index = -1;
        for (int i = 0; i < 9; i++) {
            if (button == buttons[i]) {
                index = i;
                break;
            }
        }
        if (gameOver || buttons[index].getText().length() > 0) {
            return;
        }
        buttons[index].setText(currentPlayer);
        buttons[index].setFont(new Font("Arial", Font.PLAIN, 40));
        if (checkForWin()) {
            label.setText(currentPlayer + " wins!");
            gameOver = true;
            return;
        }
        if (checkForDraw()) {
            label.setText("It's a draw!");
            gameOver = true;
            return;
        }
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        label.setText(currentPlayer + "'s turn");
    }

    private boolean checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }
        for (int i = 0; i < 3; i++) {
            if (board[i*3].equals(currentPlayer) && board[i*3+1].equals(currentPlayer) && board[i*3+2].equals(currentPlayer)) {
                return true;
            }
            if (board[i].equals(currentPlayer) && board[i+3].equals(currentPlayer) && board[i+6].equals(currentPlayer)) {
                return true;
            }
        }
        if (board[0].equals(currentPlayer) && board[4].equals(currentPlayer) && board[8].equals(currentPlayer)) {
            return true;
        }
        if (board[2].equals(currentPlayer) && board[4].equals(currentPlayer) && board[6].equals(currentPlayer)) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().length() == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}