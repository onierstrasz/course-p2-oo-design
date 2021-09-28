package templateMethodPattern;

public class Chess extends Game {
	boolean gameOver = false;

	@Override
	boolean endOfGame() {
		return gameOver;
	}

	@Override
	void initializeGame() {
		// TODO Auto-generated method stub

	}

	@Override
	void makePlay(int player) {
		System.out.println("Player " + player + " plays");
		gameOver = true;
	}

	@Override
	void printWinner() {
		System.out.println("Chess is not working yet");
	}

}
