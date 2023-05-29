import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final JButton player1Button;
    private final JButton player2Button;
    private final JTextArea displayArea;
    private final JTextField pileField;
    private final JTextField sticksField;
    private final JLabel turnLabel;

    private int[] piles;
    private boolean player1Turn;

    public Main() {
        setTitle("Kushal's Nim Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1Button = new JButton("Player 1");
        player2Button = new JButton("Player 2");
        displayArea = new JTextArea(10, 20);
        pileField = new JTextField(10);
        sticksField = new JTextField(10);
        turnLabel = new JLabel("Player 1's Turn");

        player1Button.addActionListener(e -> player1Turn());

        player2Button.addActionListener(e -> player2Turn());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(player1Button);
        buttonPanel.add(player2Button);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(new JLabel("Pile:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(pileField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Sticks:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(sticksField, gbc);

        JPanel turnPanel = new JPanel();
        turnPanel.add(turnLabel);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(turnPanel, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        initializeGame();
        updateDisplay();
    }

    private void initializeGame() {
        piles = Methods.setup();
        player1Turn = true;
    }

    private void player1Turn() {
        int pile = Integer.parseInt(pileField.getText());
        int sticks = Integer.parseInt(sticksField.getText());

        Methods.Player1turn(piles, pile, sticks);
        player1Turn = false;
        updateDisplay();
        updateTurnLabel();
        checkWinner();

        // Clear input fields
        pileField.setText("");
        sticksField.setText("");
    }

    private void player2Turn() {
        int pile = Integer.parseInt(pileField.getText());
        int sticks = Integer.parseInt(sticksField.getText());

        Methods.Player2turn(piles, pile, sticks);
        player1Turn = true;
        updateDisplay();
        updateTurnLabel();
        checkWinner();

        // Clear input fields
        pileField.setText("");
        sticksField.setText("");
    }

    private void updateDisplay() {
        displayArea.setText("Pile 1 = " + piles[0] + ", Pile 2 = " + piles[1] + ", Pile 3 = " + piles[2] + ", Pile 4 = " + piles[3]);
    }

    private void updateTurnLabel() {
        String turn = player1Turn ? "Player 1's Turn" : "Player 2's Turn";
        turnLabel.setText(turn);
    }

    private void checkWinner() {
        if (!Methods.Check(piles)) {
            String winner = player1Turn ? "Player 1" : "Player 2";
            displayArea.append("\n" + winner + " wins!");
            player1Button.setEnabled(false);
            player2Button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}