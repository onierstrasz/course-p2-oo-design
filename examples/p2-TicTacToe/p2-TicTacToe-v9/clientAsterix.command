#! /bin/sh
# client --- start the TicTacToe GUI

# Be sure to start the server first!

# NB: If you are having problems connecting to the server,
# make sure your firewall is turned off!

cd `dirname "$0"`

# Configure these for your installation
# host=`hostname` # Set this to the server's hostname
# port=7777

host=asterix.unibe.ch
port=2010

jar=TicTacToeGUI.jar

echo starting game client ...
java -cp ${jar} \
	-Djava.security.policy=policy.all \
	tictactoe.gui.GameConsole ${host}:${port}

exit
