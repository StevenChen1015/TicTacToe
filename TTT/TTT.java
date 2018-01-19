/**
* Tic Tac Toe Game
* A player may only take a square on their turn 
* A player may only take an unoccupied square 
* Taking a square ends a turn 
* Players alternate taking turns until the round is over 
* For: Langara College, CPSC 1181-003 Fall 2017, Instructor: Jeremy Hilliker.
*
* @author Steven Chen @ langara
* @version 2017-11-12
*/
import java.awt.Dimension;
import javax.swing.JFrame;

public class TTT {

	public static void main(String[] args) {
		TTTPresenter p = new TTTPresenterImpl();
		for (Player player : Player.values()) {
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setContentPane(new TTTComponent(p, player));
			f.setMinimumSize(new Dimension(400, 400));
			f.setVisible(true);
		}
	}
}
