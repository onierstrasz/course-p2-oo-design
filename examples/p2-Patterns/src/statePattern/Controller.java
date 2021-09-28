package statePattern;
import java.io.*;

public class Controller {
	private BufferedReader in;
	private PrintWriter out;
	private State state;

	public Controller (InputStream is, OutputStream os) throws IOException {
		in = new BufferedReader(new InputStreamReader(is));
		out = new PrintWriter(os);
		state = Closed.enter(this);
	}

	public void changeState(State state){
		this.state = state;
		System.out.println("Changed state to: " + state);
		out.flush();
	}

	public void invalidStateChange() {
		out.println("Invalid action for this state!");
	}

	private void prompt() {
		showCurrentState();
		out.print("Command (open, close, lock, unlock, combination, error): " );
		out.flush();
	}

	private void exitMessage() {
		showCurrentState();
		out.print("You are now leaving the garage sector!" );
		out.flush();
	}

	private void showCurrentState() {
		out.println("Current State is: " + state);
		out.flush();
	}

	public void run() throws IOException {
		String line;
		prompt();
		while(!((line = in.readLine()).equals("exit"))){
			if(line.equals("close")) state.close();
			if(line.equals("combination")) state.combinationEntered();
			if(line.equals("lock")) state.lock();
			if(line.equals("error")) state.errorEntered();
			if(line.equals("open")) state.open();
			if(line.equals("unlock")) state.startUnlock();
			prompt();
		}
		exitMessage();
	}

}
