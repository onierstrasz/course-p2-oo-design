package noStatePattern;
import java.io.*;
public class Controller {
	private BufferedReader in;
	private PrintWriter  out;
	private int state;

	private static final int AWAITING_COMBINATION = 0;
	private static final int CLOSED = 1;
	private static final int LOCKED = 2;
	private static final int OPENED = 3;
	private static final String[] states = {
		"AWAITING_COMBINATION",
		"CLOSED",
		"LOCKED",
		"OPENED"
	};

	public Controller(InputStream is, OutputStream os) throws IOException{
		in = new BufferedReader(new InputStreamReader(is));
		out = new PrintWriter(os);
		this.state = CLOSED;
		out.println("Initial state is: " + states[state] );
	}

	/**
	 * Illustrates what state transitions look like when you
	 * do not use the state pattern.
	 */
	public void run() throws IOException {
		this.prompt();
		String line;
		while ((line = in.readLine()) != null) {
			if (line.equals("open")){
				changeState(CLOSED, OPENED);
			}
			if (line.equals("close")){
				changeState(OPENED, CLOSED);
			}
			if (line.equals("lock")){
				changeState(CLOSED, LOCKED);
			}
			if (line.equals("unlock")){
				changeState(LOCKED, AWAITING_COMBINATION);
			}
			if (line.equals("combination")){
				changeState(AWAITING_COMBINATION, CLOSED);
			}
			if (line.equals("error")){
				changeState(AWAITING_COMBINATION, LOCKED);
			}
			this.prompt();
		}
	}

	private void prompt() {
		out.print("Command(close, open, lock, unlock, combination, error): ");
		out.flush();
	}

	private void setState(int state) {
		this.state = state;
		out.print("Set state to :" + states[state] );
		out.flush();
	}

	private void changeState(int fromState, int toState) {
		if (state == fromState) {
			state = toState;
			out.println("Set state to: " + states[state] );
		} else {
			out.println("Invalid action for this state!");
		}
		out.flush();
	}

}
