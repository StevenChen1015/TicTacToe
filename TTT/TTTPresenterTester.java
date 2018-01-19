import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.stream.*;

public class TTTPresenterTester {

	public static void main(String[] args) {
		new TestRunner(TTTPresenterTester.class).run();
	}

	/** Does a toString of objects, and of arrays */
	public static String deepToString(Object o) {
		return o == null ? null
		: !o.getClass().isArray() ? o.toString()
		: o instanceof Object[] ? Arrays.toString((Object[]) o)
		: o instanceof int[] ? Arrays.toString((int[]) o)
		: o instanceof double[] ? Arrays.toString((double[]) o)
		: o instanceof float[] ? Arrays.toString((float[]) o)
		: o instanceof char[] ? Arrays.toString((char[]) o)
		: o instanceof long[] ? Arrays.toString((long[]) o)
		: o instanceof byte[]
		? Arrays.toString((byte[]) o)
		: o instanceof short[]
		? Arrays.toString((short[]) o)
		: o instanceof boolean[]
		? Arrays.toString(
		(boolean[]) o)
		: o.toString();
	}

	private static void assertEquals(Object lhs, Object rhs) {
		assertEquals(lhs, rhs, "");
	}

	private static void assertEquals(Object lhs, Object rhs, String msg) {
		assert Objects.deepEquals(lhs, rhs) : msg + "\nlhs: " + deepToString(lhs) + "\nrhs: " + deepToString(rhs);
	}

	public void test_init() {
		TTTPresenter p = new TTTPresenterImpl();
		assertEquals(new int[] { 0, 0 }, p.getScore());
		assertEquals(new Player[9], p.getBoard());
		assertEquals(Player.X, p.getWhosMove());
		assertEquals(null, p.getWin());
		assertEquals(null, p.getWinner());
		assertEquals(false, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}

	public void test_move_Legal() {
		TTTPresenter p = new TTTPresenterImpl();
		p.move(Player.X, 4); // legal: the middle

		Player[] expected = new Player[9];
		expected[4] = Player.X;
		assertEquals(new int[] { 0, 0 }, p.getScore());
		assertEquals(expected, p.getBoard());
		assertEquals(Player.O, p.getWhosMove());
		assertEquals(null, p.getWin());
		assertEquals(null, p.getWinner());
		assertEquals(false, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}

	public void test_move_illegal_notYourTurn() {
		TTTPresenter p = new TTTPresenterImpl();
		p.move(Player.O, 4); // illegal, X's turn, not O's
		// no change to state
		assertEquals(new int[] { 0, 0 }, p.getScore());
		assertEquals(new Player[9], p.getBoard());
		assertEquals(Player.X, p.getWhosMove());
		assertEquals(null, p.getWin());
		assertEquals(null, p.getWinner());
		assertEquals(false, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}
	// TODO add your own tests
	public void test_move_legal_xWins() {
		TTTPresenter p = new TTTPresenterImpl();
		p.move(Player.X, 0); // legal: the middle
		p.move(Player.O, 3);
		p.move(Player.X, 1);
		p.move(Player.O, 4);
		p.move(Player.X, 2);
		Player[] expected = new Player[9];
		expected[0] = Player.X;
		expected[3] = Player.O;
		expected[1] = Player.X;
		expected[4] = Player.O;
		expected[2] = Player.X;
		assertEquals(new int[] { 0, 0 }, p.getScore());
		assertEquals(expected, p.getBoard());
		assertEquals(Win.ROW_N, p.getWin());
		assertEquals(Player.X, p.getWinner());
		assertEquals(true, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}

	public void test_move_legal_oWins() {
		TTTPresenter p = new TTTPresenterImpl();
		p.move(Player.X, 1);
		p.move(Player.O, 2);
		p.move(Player.X, 5);
		p.move(Player.O, 4);
		p.move(Player.X, 7);
		p.move(Player.O, 6);
		Player[] expected = new Player[9];
		expected[1] = Player.X;
		expected[2] = Player.O;
		expected[5] = Player.X;
		expected[4] = Player.O;
		expected[7] = Player.X;
		expected[6] = Player.O;
		assertEquals(new int[] { 0, 0 }, p.getScore());
		assertEquals(expected, p.getBoard());
		assertEquals(Win.DIAG_NE_SW, p.getWin());
		assertEquals(Player.O, p.getWinner());
		assertEquals(true, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}
	//TODO
	public void test_draw() {
		
		
	}
}
