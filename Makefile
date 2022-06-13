default: cpsc2150/extendedConnectX/GameScreen.java cpsc2150/extendedConnectX/GameBoard.java clean
	javac cpsc2150/extendedConnectX/GameScreen.java cpsc2150/extendedConnectX/GameBoard.java
run:
	java cpsc2150/extendedConnectX/GameScreen cpsc2150/extendedConnectX/GameBoard
clean:
	rm -f cpsc2150/extendedConnectX/*.class
test:
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedConnectX/TestGameBoard.java cpsc2150/extendedConnectX/TestGameBoardMem.java cpsc2150/extendedConnectX/GameScreen.java cpsc2150/extendedConnectX/IGameBoard.java cpsc2150/extendedConnectX/GameBoard.java cpsc2150/extendedConnectX/GameBoardMem.java cpsc2150/extendedConnectX/AbsGameBoard.java
testGB:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.TestGameBoard
testGBMem:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedConnectX.TestGameBoardMem