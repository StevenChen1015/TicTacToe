import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Objects;

import javax.swing.*;

@SuppressWarnings("serial")
public class TTTComponent extends JComponent {

	private final TTTPresenter game;
	private final Player me;
	public final JButton buttons[] = new JButton[9];

	public TTTComponent(TTTPresenter game, Player player) {
		this.game = Objects.requireNonNull(game, "game presenter must be non-null");
		this.me = Objects.requireNonNull(player, "player must be non-null");

		// MESSAGES
		JPanel m = new JPanel();
		m.setLayout(new GridLayout(1, 3));
		JLabel name = new JLabel("You are " + player.name());
		JLabel move = new JLabel(game.getWhosMove() + "'s move.");
		JLabel score = new JLabel("Score: X:" + game.getScore()[0] + " O:" + game.getScore()[1]);
		m.add(name);
		m.add(move);
		m.add(score);
		this.setLayout(new BorderLayout());
		this.add(m, BorderLayout.NORTH);
		// BUTTONS
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3));
		this.add(p, BorderLayout.CENTER);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < game.getBoard().length; j++) {
						if (e.getSource() == buttons[j] && (me == game.getWhosMove())) {
							game.move(me, j);
						}
					}
				}
			});
			p.add(buttons[i]);
		}
		// add a listener to receive game state changes
		game.addListener((a) -> {
			game.getWinner();
			for (int i = 0; i < buttons.length; i++) {
				if (game.getBoard()[i] == null) {
					buttons[i].setText("");
					if (game.isRoundOver()) {
						buttons[i].setEnabled(false);
					} else {
						buttons[i].setEnabled(true);
					}
				} else {
					buttons[i].setText("" + game.getBoard()[i]);
					buttons[i].setEnabled(false);
				}
			}
			// Display game status after round over
			if (game.isRoundOver()) {
				if (game.isDraw() == true) {
					SwingUtilities.invokeLater(() -> {
						JOptionPane.showMessageDialog(this, "draw");
						game.nextRound(me);
					});
				} else {
					SwingUtilities.invokeLater(() -> {
						JOptionPane.showMessageDialog(this, game.getWinner().name() + " wins!");
						game.nextRound(me);
					});
				}
			} else {
				move.setText(game.getWhosMove() + "'s move.");
				score.setText("Score: X:" + game.getScore()[0] + " O:" + game.getScore()[1]);
			}
		});
	}
}