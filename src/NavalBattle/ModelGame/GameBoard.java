package NavalBattle.ModelGame;

public class GameBoard {
    private int[][] gameBoard;

    public GameBoard() {
        gameBoard = new int[10][10];
    }
    public void resetBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }
}