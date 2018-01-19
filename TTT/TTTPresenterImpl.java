import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TTTPresenterImpl implements TTTPresenter {

	private final static Logger LOG = Logger.getLogger(TTTPresenterImpl.class.getName());

	private final static String PROPERTY_NAME = TTTPresenter.class.getName();
	private final PropertyChangeSupport listeners;
	private TTTModel model;

	public TTTPresenterImpl() {
		listeners = new PropertyChangeSupport(this);
		model = new TTTModel();
	}

	/**
	 * A player takes a square. Ignores illegal moves. Determines if the round is
	 * over, sets fields appropriately, updates score. Fires an event.
	 */
	public void move(Player p, int pos) {
		if ((!isRoundOver()) && (model.board[pos] == null) && (model.whosMove == p)) {
			model.board[pos] = p;
			String lastChanges = toString();
			model.whosMove = getWhosMove().getNext();
			listeners.firePropertyChange(PROPERTY_NAME, this, lastChanges);
		}
	}

	/** Gets the score for all players over all rounds. */
	public int[] getScore() {
		return model.score;
	}

	/** Gets the current play board. */
	public Player[] getBoard() {
		return model.board;
	}

	/** Returns the player that should move this turn; null if round is over. */
	public Player getWhosMove() {
		return model.whosMove;
	}

	/** Gets the winning scenario of the current round; null if no winner. */
	public Win getWin() {
		for (Win winner : Win.values()) {
			if (winner.isWin(model.board)) {
				model.win = winner;
				return winner;
			}
		}
		return null;
	}

	/** Gets the winner of the current round; null if there is no winner. */
	public Player getWinner() {
		if (getWin() != null) {
			return model.winner = model.win.getWinner(model.board);
		}
		return null;
	}

	/** Gets if the round is over. */
	public boolean isRoundOver() {
		if ((model.winner != null) || isDraw()) {
			return true;
		}
		return false;
	}

	/** Gets if the round is a draw. */
	public boolean isDraw() {
		boolean notDraw = Stream.of(model.board).anyMatch(a -> a == null);
		if (!notDraw && (getWin() == null)) {
			return true;
		}
		return false;
	}

	/**
	 * A player indicates that they are ready for the next round. When all players
	 * are ready, the next round begins. Fires an event when next round begins.
	 */
	public void nextRound(Player p) {
		if (Player.X == p) {
			model.readyForNextRound[0] = true;
		} else if (Player.O == p) {
			model.readyForNextRound[1] = true;
		}
		if (Stream.of(model.readyForNextRound).allMatch(a -> a != null)
				&& (Stream.of(model.readyForNextRound).allMatch(a -> a == true))) {
			if (!isDraw()) {
				if (model.winner == Player.X) {
					model.score[0]++;
				} else {
					model.score[1]++;
				}
			}
			model = new TTTModel((getWinner() != null ? model.winner : model.movesFirst), getScore());
			String lastChanges = toString();
			listeners.firePropertyChange(PROPERTY_NAME, this, lastChanges);
		}
	}

	/**
	 * Adds a listener to receive game state changes. Fires an event on add. The
	 * "source" on fired events should be this TTTPresenter The "property name" of
	 * fired events should be "TTTPresenter"
	 */
	public void addListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(PROPERTY_NAME, l);
	}

	/** A String representation of this object. */
	public String toString() {
		return this.getClass().getName() + ':' + Objects.toString(model);
	}
}
