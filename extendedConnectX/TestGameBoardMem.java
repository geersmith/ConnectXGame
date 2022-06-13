package cpsc2150.extendedConnectX;
import org.junit.*;

//import cpsc2150.extendedConnectX.GameBoardMem;

import static org.junit.Assert.*;
import java.util.Random;

public class TestGameBoardMem {
    final private int rows = 9;
    final private int cols = 9;
    final private int winNum = 6;
    final private int bottomRow = 1;
    final private int leftColumn = 0;
    final private int minimum = 0;

    private IGameBoard MakeBoard(int rows, int cols, int winNum){
        return new GameBoardMem(rows, cols, winNum);
    }
//Constructor tests

//    @Test
//    public void makeBoardMiddle(){
//        IGameBoard gb = MakeBoard(rows, cols, winNum);
//        char[][] xpect;
//        xpect = new char[rows][cols];
//        int halfCol = cols/2;
//        for(int r = 0; r < rows; r++){
//            gb.placeToken('X', halfCol + 1);
//            xpect[row][halfCol + 1] = 'X';
//        }
//        assertEquals(gb.toString(), compare2String(xpect));
//
//    }

//    @Test
//    public void makeBoardBottom(){
//        IGameBoard gb = MakeBoard(rows, cols, winNum);
//        char[][] xpect;
//        xpect = new char[rows][cols];
//        for(int c = 0; c < cols; c++){
//            gb.placeToken('X', c);
//            xpect[rows-1][c] = 'X';
//        }
//        assertEquals(gb.toString(), compare2String(xpect));
//    }

//    @Test
//    public void makeBoardWhole(){
//        IGameBoard gb = MakeBoard(rows, cols, winNum);
//        char[][] xpect;
//        xpect = new char[rows][cols];
//        char playerMove = 'Y';
//        for(int r = 0; r<rows; r++){
//            for(int c = 0; c <cols; c++){
//                if(row%2!==0){
//                    token = 'X';
//                }
//                else{
//                    token = 'Y';
//                }
//                gb.placeToken(playerMove, c);
//                xpect[r][c] = playerMove;
//            }
//        }
//        assertEquals(gb.toString(), compare2String(xpect));
//    }

    //Check if free tests
    @Test
    public void testCheckIfFreeTopLeft(){
        //full board
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++) {
                gb.placeToken('X', minimum);
            }
        }
        assertFalse(gb.checkIfFree(minimum));

    }

    @Test
    public void testCheckIfFreeWholeBoardFull(){
        //full board
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int j = 0; j < cols; j++){
            for(int g = 0; g < rows; g++){
                gb.placeToken('X', g);
            }
        }
        for(int r = 0; r < rows; r++){
            assertFalse(gb.checkIfFree(r));

        }
    }

    @Test
    public void testCheckIfFreeEmpty(){
        //full empty board
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int j = 0; j < cols; j++){
            for(int g = 0; g < rows; g++){
                gb.placeToken(' ', g);
            }
        }
        for(int r = 0; r < rows; r++){
            assertTrue(gb.checkIfFree(r));
        }

    }

    //Check Horiz wins tests
    @Test
    public void checkHorizWinEntireWidth(){
        int widthNum = cols;
        IGameBoard gb = MakeBoard(rows, cols, widthNum);
        for(int c = 0; c < cols; c++){
            for(int r = 0; r < rows; r++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(cols-1,rows-1);
        assertTrue(gb.checkHorizWin(pos,'Y'));
    }
    //
    @Test
    public void checkHorizWinOneLess(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int c = 0; c < winNum - 2; c++){
            for(int r = 0; r < rows; r++){
                gb.placeToken('Y', cols);
            }
        }
        for(int c = 0; c < cols; c++){
            for(int r = 0; r < rows; r++){
                BoardPosition pos = new BoardPosition(r,c);
                assertFalse(gb.checkHorizWin(pos, 'Y'));
            }
        }
    }

    //Check vert win tests
    @Test
    public void checkVertWinRightColumn(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('X', cols-1);
            }
        }
        for(int r = 0; r < rows; r++){
            BoardPosition pos = new BoardPosition(r, cols-1);
            assertTrue(gb.checkVertWin(pos, 'X'));
        }

    }

    @Test
    public void checkVertWinZeroColumn(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('X', leftColumn);
            }
        }
        for(int r = 0; r < rows; r++){
            BoardPosition pos = new BoardPosition(r, leftColumn);
            assertTrue(gb.checkVertWin(pos, 'X'));
        }

    }

    @Test
    public void checkVertWinBlankBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken(' ', c);
            }
        }
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                BoardPosition pos = new BoardPosition(r,c);
                assertFalse(gb.checkVertWin(pos, 'X'));

            }
        }

    }

    @Test
    public void checkVertWinOneLess(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < winNum-2; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('X', c);
            }
        }
        for(int r = 0; r < rows; r++){
            BoardPosition pos = new BoardPosition(0, r);
            assertFalse(gb.checkVertWin(pos, 'X'));
        }

    }

    //Check Diag win test

    @Test
    public void checkDiagWinBottomLeft2Right(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(minimum,minimum);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinBottomRight2Left(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1, minimum);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinTopRight2Left(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-1);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinTopLeft2Right(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(minimum, cols-1);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinLoss(){
        int smallWinNum = winNum - 3;
        IGameBoard gb = MakeBoard(rows, cols, smallWinNum);
        //2 in a row which isn't enough
        gb.placeToken('Y', winNum-2);
        gb.placeToken('Y', winNum-1);
        gb.placeToken('Y', winNum-1);
        BoardPosition pos = new BoardPosition(rows-7, cols-5);
        assertFalse(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinFromMiddle(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows/2,cols/2);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    @Test
    public void checkDiagWinMaxWinNum(){
        int maxWinNum = rows;
        IGameBoard gb = MakeBoard(rows, cols, maxWinNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < rows; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(minimum,cols-1);
        assertTrue(gb.checkDiagWin(pos, 'Y'));

    }

    //Check Tie tests
    @Test
    public void checkTieFullBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('Y', c);
            }
        }

        assertFalse(gb.checkTie());
    }

    @Test
    public void checkTieFullBoardMinusOne(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        Random rando;
        rando = new Random();
        int randomColumn = rando.nextInt(cols);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(r!=rows-1 && c != randomColumn){
                    gb.placeToken('Y', c);
                }
            }
        }
        assertFalse(gb.checkTie());
    }

    @Test
    public void checkTieNoTopRow(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows-1; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('Y', c);
            }
        }
        assertFalse(gb.checkTie());
    }

    @Test
    public void checkTieBlankBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken(' ', c);
            }
        }
        assertFalse(gb.checkTie());

    }

    //WhatsAtPos tests
    @Test
    public void whatsAtPosMiddleRow(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        int halfCol = cols/2;
        for(int r = 0; r < rows; r++){
            gb.placeToken('X', halfCol + 1);
        }
        BoardPosition pos = new BoardPosition(rows-1,halfCol+1);
        Assert.assertEquals(gb.whatsAtPos(pos), 'X');
    }
    @Test
    public void whatsAtPosTopRight(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-1);
        Assert.assertEquals(gb.whatsAtPos(pos), 'Y');

    }

    @Test
    public void whatsAtPosBottomLeft(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('Y', c);
            }
        }
        BoardPosition pos = new BoardPosition(0,0);
        Assert.assertEquals(gb.whatsAtPos(pos), 'Y');

    }

    @Test
    public void whatsAtPosBlankSpace(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken(' ', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-3);
        Assert.assertEquals(gb.whatsAtPos(pos), ' ');

    }

    @Test
    public void whatsAtPosDifToken(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r<rows; r++){
            for(int c = 0; c < cols; c++){
                if(r == rows-1 && c == 0){
                    gb.placeToken('X', c);
                }
                else{
                    gb.placeToken('Y', c);
                }
            }
        }
        BoardPosition pos = new BoardPosition(rows-1, 0);
        Assert.assertEquals(gb.whatsAtPos(pos), 'Y');

    }

    //Is player at pos tests
    @Test
    public void isPlayerAtPosBlankSpace(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken(' ', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-1);
        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void isPlayerAtPosFullBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('X', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-1);
        assertTrue(gb.isPlayerAtPos(pos, 'X'));

    }

    @Test
    public void isPlayerAtPosBottomLeft(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gb.placeToken('X', c);
            }
        }
        BoardPosition pos = new BoardPosition(minimum,minimum);
        assertTrue(gb.isPlayerAtPos(pos,'X'));

    }

    @Test
    public void isPlayerAtPosMiddleRow(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gb.placeToken('X', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows/2,cols/2);
        assertTrue(gb.isPlayerAtPos(pos,'X'));

    }

    @Test
    public void isPlayerAtPosTopRight(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gb.placeToken('X', c);
            }
        }
        BoardPosition pos = new BoardPosition(rows-1,cols-1);
        assertTrue(gb.isPlayerAtPos(pos, 'X'));

    }

    //PlaceToken tests
    @Test
    public void placeTokenEmptyBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                BoardPosition pos = new BoardPosition(r,c);
                Assert.assertEquals(gb.whatsAtPos(pos), ' ');
            }
        }

    }

    @Test
    public void placeTokenFullBoard(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('E', c);
            }
        }
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                BoardPosition pos = new BoardPosition(r,c);
                Assert.assertEquals(gb.whatsAtPos(pos), 'E');
            }
        }


    }

    @Test
    public void placeTokenBottomRow(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                gb.placeToken('E', c);
                BoardPosition pos = new BoardPosition(r,c);

            }
        }

        for(int c = 0; c<cols; c++){
            BoardPosition pos = new BoardPosition(bottomRow,c);
            Assert.assertEquals(gb.whatsAtPos(pos), 'E');
        }

    }

    @Test
    public void placeTokenRightColumn(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gb.placeToken('E', cols - 1);
            }
        }
        for(int r = 0; r < rows; r++){
            BoardPosition pos = new BoardPosition(r, cols -1);
            Assert.assertEquals(gb.whatsAtPos(pos), 'E');
        }

    }

    @Test
    public void placeTokenLeftColumn(){
        IGameBoard gb = MakeBoard(rows, cols, winNum);
        for(int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                gb.placeToken('E', leftColumn);
            }
        }
        for(int r = 0; r < rows; r++){
            BoardPosition pos = new BoardPosition(r, leftColumn);
            Assert.assertEquals(gb.whatsAtPos(pos), 'E');
        }
        System.out.println(gb.toString());

    }




}