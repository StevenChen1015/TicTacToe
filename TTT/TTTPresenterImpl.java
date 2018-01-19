import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TTTPresenterImpl implements TTTPresenter {

	private final static Logger LOG = Logger.getLogger(TTTPresenterImpl.class.getName());

	private final static String PROPERTY_NAME = TTTPresenter.class.getName();
	private final PropertyChangeSupport listeners;
	private TTTModel model;

	public TTTPresenterImpl() {
		listeners = null;
		model = null;
	}

	/** A player takes a square. Ignores illegal moves. Detemines if the round
		is over, sets fields appropriately, updates score. Fires an event.*/
	public void move(Player p, int pos) {

	}

	/** Gets the score for all players over all rounds. */
	public int[] getScore() {
		return null;
	}

	/** Gets the current play board. */
	public Player[] getBoard() {
		return null;
	}

	/** Returns the player that should move this turn; null if round is over.*/
	public Player getWhosMove() {
		return null;
	}

	/** Gets the winning scenario of the current round; null if no winner. */
	public Win getWin() {
		return null;
	}

	/** Gets the winner of the current round; null if there is no winner. */
	public Player getWinner() {
		return null;
	}

	/** Gets if the round is over. */
	public boolean isRoundOver() {
		return false;
	}
	
	/** Gets if the round is a draw. */
	public boolean isDraw() {
		return false;
	}

	/** A player indicates that they are ready for the next round.
		When all players are ready, the next round begins.
		Fires an event when next round begins.*/
	public void nextRound(Player p) {

	}

	/** Adds a listener to receive game state changes.
		Fires an event on add.
		The "source" on fired events should be this TTTPresenter
		The "property name" of fired events should be "TTTPresenter"
	*/
	public void addListener(PropertyChangeListener l) {

	}

	/** A String representation of this object. */
	public String toString() {
		return this.getClass().getName() + ':' + Objects.toString(model);
	}
}
