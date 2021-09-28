#! /bin/sh
# server --- start the rmi registry and the TicTacToe game server

# NB: If you are having problems connecting to the server,
# make sure your firewall is turned off!

cd `dirname "$0"`

# Configure these for your installation
host=`hostname`
port=7777

case `hostname` in
asterix )
	host=asterix.unibe.ch
	port=2010 ;;
esac

# There should always be a copy available here:
jar=TicTacToeGUI.jar
codebase="http://scg.unibe.ch/download/p2/p2-tictactoe/${jar}"

echo $codebase

reg=.killregistry
if test ! -f ${reg}
then	
	echo starting rmi registry ...
	rmiregistry ${port} &
	echo kill -9 $! > ${reg}
fi

srv=.killserver
if test -f ${srv}
then	
	cat ${srv}
	sh ${srv}
	rm -f ${srv}
fi

echo starting game server ...
java -cp ${jar} \
	-Djava.security.policy=policy.all \
	-Djava.rmi.server.codebase="${codebase}" \
	tictactoe.server.GameServer ${host}:${port} \
	&

echo kill -9 $! > ${srv}

sleep 5

exit
