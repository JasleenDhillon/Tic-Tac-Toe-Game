package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {

	private enum GameStatus {
		XWon, OWon, Tie, Incomplete;

	}

	private int BOARD_SIZE = 3;
	private boolean crossturn = true;
	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

	public TTT() {
		super.setTitle("Tic Tac Toe");
		super.setResizable(false);
		super.setSize(800, 800);

		GridLayout layout = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(layout);
		Font font = new Font("Comic sans ms", 1, 130);

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton btn = new JButton("");
				btn.setFont(font);
				btn.addActionListener(this);
				buttons[i][j] = btn;
				super.add(btn);
			}
		}
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton cb = (JButton) e.getSource();

		makeMove(cb);

		GameStatus gs = getGameStatus();

		if (gs == GameStatus.Incomplete) {
			return;
		}
		DeclareWinner(gs);

		int choice = JOptionPane.showConfirmDialog(this, "Restart?");
		if (choice == JOptionPane.YES_OPTION) {
			for (int row = 0; row < buttons.length; row++) {
				for (int col = 0; col < buttons[row].length; col++) {
					buttons[row][col].setText("");
				}
			}
		} else {
			super.dispose();
		}
	}

	private void makeMove(JButton cb) {
		String text = cb.getText();
		if (text.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
			return;
		}
		if (this.crossturn) {
			cb.setText("X");
		} else {
			cb.setText("O");
		}

		crossturn = !crossturn;
	}

	private GameStatus getGameStatus() {
		String text1 = "", text2 = "", text3 = "";
		// rows
		for (int row = 0; row < buttons.length; row++) {
			text1 = buttons[row][0].getText();
			text2 = buttons[row][1].getText();
			text3 = buttons[row][2].getText();

			if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
				if (text1.equals("X")) {
					return GameStatus.XWon;
				} else {
					return GameStatus.OWon;
				}

			}
		}
		// cols
		for (int col = 0; col < buttons[0].length; col++) {
			text1 = buttons[0][col].getText();
			text2 = buttons[1][col].getText();
			text3 = buttons[2][col].getText();

			if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
				if (text1.equals("X")) {
					return GameStatus.XWon;
				} else {
					return GameStatus.OWon;
				}

			}
		}

		// diag1
		text1 = buttons[0][0].getText();
		text2 = buttons[1][1].getText();
		text3 = buttons[2][2].getText();

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			} else {
				return GameStatus.OWon;
			}

		}

		// diag2
		text1 = buttons[0][2].getText();
		text2 = buttons[1][1].getText();
		text3 = buttons[2][0].getText();

		if (text1.equals(text2) && text2.equals(text3) && !text1.equals("")) {
			if (text1.equals("X")) {
				return GameStatus.XWon;
			} else {
				return GameStatus.OWon;
			}

		}

		// incomplete
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				if (buttons[row][col].getText().length() == 0) {
					return GameStatus.Incomplete;
				}
			}
		}

		// tie
		return GameStatus.Tie;

	}

	private void DeclareWinner(GameStatus gs) {
		if (gs == GameStatus.XWon) {
			JOptionPane.showMessageDialog(this, "X Won");
		}

		else if (gs == GameStatus.OWon) {
			JOptionPane.showMessageDialog(this, "O Won");
		}

		else if (gs == GameStatus.Tie) {
			JOptionPane.showMessageDialog(this, "It's A Tie");
		}
	}
}