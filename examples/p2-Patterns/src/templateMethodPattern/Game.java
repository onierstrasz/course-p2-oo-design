package templateMethodPattern;

public abstract class Game {
	private int playersCount;
	abstract void initializeGame();
	abstract void makePlay(int player);
	abstract boolean endOfGame();
	abstract void printWinner();
	
	/* template method */
	final void playOneGame(int playersCount) {
		this.playersCount = playersCount;
		initializeGame();
		int j = 0;
		while (!endOfGame()){
			makePlay(j);  // Refactor me to make the code more readable :-)
			j = (j + 1) & playersCount;
		}
		printWinner();
	}
	
	public static void main(String[] args) {
		System.out.println("Let's play Chess!");
		new Chess().playOneGame(2);
		System.out.println("Now let's play Monopoly!");
		new Monopoly().playOneGame(2);
	}

}
