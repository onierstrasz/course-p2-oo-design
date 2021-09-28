
This is the RMI version of the TicTacToe game.

To run it, take the following steps:

- Build the jar file (Ant target "jar" within eclipse)
- Start the server (server.command)
- Start the client (client.command)

If you start the server on a separate machine, you should configure the client command to connect to the server's machine.

If there is already a server running on the machine asterix, you can simply run clientAsterix.command to start a client for that host.  Multiple clients on different machines can connect to the same server host.

If you have problems starting the client, check that your firewall is turned off.
If you have problems connecting to a given host, check that the port used in the server and client command is actually open.

$Id: README.txt 16561 2008-02-29 12:50:42Z oscar $
