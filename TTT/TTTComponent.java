import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

@SuppressWarnings("serial")
public class TTTComponent extends JComponent {

	private final TTTPresenter game;
	private final Player me;
	private static JButton buttons[] = new JButton[9];
	
	public TTTComponent(TTTPresenter game, Player player) {
		this.game = Objects.requireNonNull(game, "game presenter must be non-null");
		this.me = Objects.requireNonNull(player, "player must be non-null");
		JLabel status = new JLabel("You are " + player.name() + game.getWhosMove() +"'s move." + "Score:" + game.getScore());
		this.setLayout(new BorderLayout());
		this.add(status, BorderLayout.NORTH);
		
		JPanel j = new JPanel();
		j.setLayout(new GridLayout(3, 3));
		this.add(j, BorderLayout.CENTER);
		
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setText("");
			j.add(buttons[i]);
		}
	}
}
