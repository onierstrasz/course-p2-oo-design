package tictactoe.gui;

import java.awt.*;
import java.awt.event.*;
import tictactoe.*;

/**
 * Top-level frame with buttons to start TicTacToe or Gomoku.
 * @author $Author: oscar $
 * @version $Id: GameConsole.java 16608 2008-03-01 15:38:08Z oscar $
 */
@SuppressWarnings("serial")
public class GameConsole extends Frame {
	GameFactory tictactoeFactory, gomokuFactory;
	
	public GameConsole(String title) throws HeadlessException {
		super(title);
		tictactoeFactory = new TicTacToeFactory();
		gomokuFactory = new GomokuFactory();
		setLayout(new FlowLayout());
		add(makeTicTacToe());
		add(makeGomoku());
		this.setVisible(true);
		this.pack();
	}
	
	public static void main(String[] args) {
		new GameConsole("TicTacToe/Gomoku Console");
	}
	
	protected Button makeTicTacToe() {
		Button button = new Button("Start TicTacToe");
		/*
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new GameGUI(tictactoeFactory.getGame())).setVisible(true);
			}
		});
		*/
		button.addActionListener(e -> (new GameGUI(tictactoeFactory.getGame())).setVisible(true));
		return button;
	}

	protected Button makeGomoku() {
		Button button = new Button("Start Gomoku");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new GameGUI(gomokuFactory.getGame())).setVisible(true);
			}
		});
		return button;
	}
}
