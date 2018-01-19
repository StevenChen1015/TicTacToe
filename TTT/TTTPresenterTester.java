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
		return o == null ? null :
			!o.getClass().isArray() ? o.toString() :
			o instanceof Object[] ? Arrays.toString((Object[])o) :
			o instanceof int[] ? Arrays.toString((int[])o) :
			o instanceof double[] ? Arrays.toString((double[])o) :
			o instanceof float[] ? Arrays.toString((float[])o) :
			o instanceof char[] ? Arrays.toString((char[])o) :
			o instanceof long[] ? Arrays.toString((long[])o) :
			o instanceof byte[] ? Arrays.toString((byte[])o) :
			o instanceof short[] ? Arrays.toString((short[])o) :
			o instanceof boolean[] ? Arrays.toString((boolean[])o) :
			o.toString();
	}
	private static void assertEquals(Object lhs, Object rhs) {
		assertEquals(lhs, rhs, "");
	}
	private static void assertEquals(Object lhs, Object rhs, String msg) {
		assert Objects.deepEquals(lhs, rhs) :
			msg
			+ "\nlhs: " + deepToString(lhs)
			+ "\nrhs: " + deepToString(rhs);
	}

	public void test_init() {
		TTTPresenter p = new TTTPresenterImpl();
		assertEquals(new int[] {0,0}, p.getScore());
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
		assertEquals(new int[] {0,0}, p.getScore());
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
		assertEquals(new int[] {0,0}, p.getScore());
		assertEquals(new Player[9], p.getBoard());
		assertEquals(Player.X, p.getWhosMove());
		assertEquals(null, p.getWin());
		assertEquals(null, p.getWinner());
		assertEquals(false, p.isRoundOver());
		assertEquals(false, p.isDraw());
	}

	// TODO add your own tests

}
