package snakes;

public class Ladder extends Square {

	protected int transport;

	private boolean myInvariant() {
		return isValidTransport(transport);
	}

	private boolean isValidTransport(int transport) {
		return transport != 0 && game.isValidPosition(position + transport);
	}

	public Ladder(int transport, Game game, int position) {
		super(game, position); // NB: superclass will initialize and validate its state
		assert isValidTransport(transport);
		this.transport = transport;
		assert myInvariant(); // NB: only validate my own state
	}
	
	@Override
	protected String squareLabel() {
		return position + "->" + this.destination().position();
	}

	@Override
	public ISquare landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	protected ISquare destination() {
		return game.getSquare(position+transport);
	}
}
