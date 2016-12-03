import java.util.Scanner;
public class RandomMazeGenerator
{
    private String[][] board;
    private int difficulty;
    public RandomMazeGenerator(int r, int c, int difficulty)
    {
        board = new String[r][c];
        fillBoard();
        this.difficulty = difficulty;
    }

    public RandomMazeGenerator()
    {
        board = new String[5][5];
        fillBoard();
        this.difficulty = 5;
    }

    public String[][] getBoard()
    {
        return board;
    }

    public int getDifficulty()
    {
        return difficulty;
    }

    /**
     * WARNING: THIS WILL RESET THE BOARD
     */
    public void setBoard(int r, int c)
    {
        board = new String[r][c];
    }

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public void generateBoard()
    {
        clearBoard();
        int row = 0;
        int column = 0;
        int standard = Math.max(board.length,board[0].length);
        for(int i = 0; i < difficulty * (standard / 4); i++)
        {
            row=0;
            column=0;
            do{
                row = (int)(Math.random() * board.length);
                column = (int)(Math.random() * board[0].length);
            }while(row == 0 && column == 0);
            while(board[row][column].equals(" "))
            {
                board[row][column] = (int)(Math.random() * difficulty + 1) + "";
            }
        }
        do{
            row = (int)(Math.random() * board.length);
            column = (int)(Math.random() * board[0].length);
        }while(row == 0 && column == 0);
        board[row][column] = "+";
        board[0][0] = "@";
    }

    public void clearBoard()
    {
        board = new String[board.length][board[0].length];
        fillBoard();
    }

    //FILLS ENTIRE THING WITH SPACES FOR AESTHETICS
    public void fillBoard()
    {
        for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
                board[r][c] = " ";
    }
}
