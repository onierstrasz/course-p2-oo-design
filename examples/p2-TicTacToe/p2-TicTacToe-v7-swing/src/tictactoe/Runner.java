package tictactoe;

/**
 * Helper class to find winning scores for BoardGame.
 * @author $Author: oscar $
 * @version $Id: Runner.java 16615 2008-03-01 17:08:43Z oscar $
 */
public class Runner {

	BoardGame game;
	// Home col and row:
	int homeCol;
	int homeRow;
	// Current col & row:
	int col = 0;
	int row = 0;

	/**
	 * This class does not have any interesting
	 * contracts.  We do not bother to define
	 * a separate test() method, since it will
	 * be thoroughly tested by the unit tests
	 * for the BoardGame implementations.
	 */
	public Runner(BoardGame myGame, int myCol, int myRow)
	{
		game = myGame;
		homeCol = myCol;
		homeRow = myRow;
	}

	/**
	 * A Runner runs in some direction (dcol,drow)
	 * as far as it can, as long as the pieces on the
	 * Board are the same as the home square.
	 * Then it runs in the opposite direction, and
	 * returns the total score.
	 */
	public int run(int dcol, int drow)
	{
		int score = 1;
		this.goHome();
		score += this.forwardRun(dcol, drow);
		this.goHome();
		dcol = -dcol;
		drow = -drow;
		score += this.forwardRun(dcol, drow);
		return score;
	}
	
	protected void goHome() {
		col= homeCol;
		row = homeRow;
	}
	
	/**
	 * Recursively move forward as long as we are
	 * in a run.  Return the length of the run.
	 */
	protected int forwardRun(int dcol, int drow)
	{
		this.move(dcol, drow);
		if (this.samePlayer())
			return 1 + this.forwardRun(dcol, drow);
		else
			return 0;
	}
	
	protected void move(int dcol, int drow) {
		col = col + dcol;
		row = row + drow;
	}

	/**
	 * The player at the current location is the
	 * same as that of our home square.
	 */
	protected boolean samePlayer() {
		if (game.inRange(col,row)) {
			Player home = game.get(homeCol, homeRow);
			Player here = game.get(col, row);
			return home == here;
		} else {
			// Oops -- we walked off the edge of the board!
			return false;
		}
	}
}
