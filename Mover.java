import java.util.Arrays;
import java.util.ArrayList;
public class Mover
{
    private GridButton board;
    private String[][] boardStr;
    private int direction;
    public Mover(GridButton grid, String[][] boardStr)
    {
        this.board = grid;
        this.boardStr = boardStr;
        direction = -1;
    }

    public GridButton moveWait()
    {
        if(countElements() == 0)
            return board;
        for(int r = 0; r < boardStr.length; r++)
        {
            for(int c = 0; c < boardStr[0].length; c++)
            {
                if(!(boardStr[r][c].equals("Win!")) && !(boardStr[r][c].equals("Lose!")) && !(boardStr[r][c].equals(" ")) && !boardStr[r][c].equals("@") && !boardStr[r][c].equals("+"))
                {
                    int num = Integer.parseInt(boardStr[r][c]);
                    switch(num)
                    {
                        case 1:
                        {
                            break;
                        }
                        //Shyam
                        case 2:
                        {
                            
                            if(r == 0 || !canMove(r+direction,c))
                            {
                                direction*=-1;
                            }
                            move(r,c,r+direction,c,false);
                            break;
                        }
                        //Jonathan or Dish
                        case 3:
                        {
                            move(r,c,r,c-1,false);
                            if((c==0) || (canMove(r,c-1)==false))
                            {
                                move(r,c,r,c+boardStr.length-1,false);
                            }
                            break;
                        }
                        case 4:
                        {
                            int rr = r;
                            int cc = c;
                            if(c == boardStr[r].length - 1)
                            {
                                cc = 0;
                            }
                            if(r == boardStr.length - 1)
                            {
                                rr = 0;
                            }
                            if(r == 0)
                            {
                                rr = boardStr.length - 1;
                            }
                            if(c == 0)
                            {
                                cc = boardStr.length - 1;
                            }
                            move(r,c,rr-1,cc+1,false);
                        }
                        case 5:
                        {
                            int dir = (int)(Math.random() * 4 + 1);
                            switch(dir){
                                case 1:
                                {
                                    move(r,c,r+1,c-1,false);
                                    break;
                                }
                                case 2:
                                {
                                    move(r,c,r-1,c+1,false);
                                    break;
                                }
                                case 3:
                                {
                                    move(r,c,r+1,c+1,false);
                                    break;
                                }
                                case 4:
                                {
                                    move(r,c,r-1,c-1,false);
                                    break;
                                }
                            }
                            break;
                        }
                        case 6:
                        {
                            int dir = (int)(Math.random() * 5 + 1);
                            switch(dir){
                                case 1:
                                {
                                    move(r,c,r+1,c,false);
                                    break;
                                }
                                case 2:
                                {
                                    move(r,c,r-1,c,false);
                                    break;
                                }
                                case 3:
                                {
                                    move(r,c,r,c+1,false);
                                    break;
                                }
                                case 4:
                                {
                                    move(r,c,r,c-1,false);
                                    break;
                                }
                            }
                            break;
                        }
                        case 7:
                        {
                            int dir = (int)(Math.random() * 8 + 1);
                            switch(dir){
                                case 1:
                                {
                                    move(r,c,r+1,c,false);
                                    break;
                                }
                                case 2:
                                {
                                    move(r,c,r-1,c,false);
                                    break;
                                }
                                case 3:
                                {
                                    move(r,c,r,c+1,false);
                                    break;
                                }
                                case 4:
                                {
                                    move(r,c,r,c-1,false);
                                    break;
                                }
                                case 5:
                                {
                                    move(r,c,r+1,c-1,false);
                                    break;
                                }
                                case 6:
                                {
                                    move(r,c,r-1,c-1,false);
                                    break;
                                }
                                case 7:
                                {
                                    move(r,c,r+1,c+1,false);
                                    break;
                                }
                                case 8:
                                {
                                    move(r,c,r-1,c+1,false);
                                    break;
                                }
                            }
                            break;
                        }
                        case 8:
                        {
                            int r1 = (int)(Math.random() * boardStr.length);
                            int c1 = (int)(Math.random() * boardStr[0].length);
                            move8(r,c,r1,c1);
                            break;
                        }
                        case 9:
                        {
                            int r1 = 0;
                            int c1 = 0;
                            do{
                                r1 = (int)(Math.random() * boardStr.length);
                                c1 = (int)(Math.random() * boardStr[0].length);
                            }while(r != r1 && c != c1);
                            move(r,c,r1,c1,false);
                            break;
                        }
                        default:
                        {break;}
                    }
                }
            }
        }
        return board;
    }

    public boolean canMove(int r, int c)
    {
        boolean canMove = false;
        try{
            if(boardStr[r][c].equals(" "))
                canMove = true;
        }catch(ArrayIndexOutOfBoundsException e)
        {};
        return canMove;
    }

    public GridButton move(int ir, int ic, int r, int c, boolean player)
    {
        if(countElements() == 0)
            return board;
        if(!player)
        {
            try{
                if(boardStr[r][c].equals("@"))
                {
                    boardStr[ir][ic] = "Lose!";
                    boardStr[r][c] = boardStr[ir][ic];
                    boardStr[ir][ic] = " ";
                    board.set(boardStr);
                }
                else
                {
                    if((canMove(r,c) || boardStr[ir][ic].equals("9")) && !boardStr[r][c].equals("+"))
                    {
                        boardStr[r][c] = boardStr[ir][ic];
                        boardStr[ir][ic] = " ";
                        board.set(boardStr);
                    }
                }
            }catch(ArrayIndexOutOfBoundsException e)
            {
                if((canMove(r,c) || boardStr[ir][ic].equals("9")) && !boardStr[r][c].equals("+"))
                {
                    boardStr[r][c] = boardStr[ir][ic];
                    boardStr[ir][ic] = " ";
                    board.set(boardStr);
                }
            }
        }
        else if(player && (boardStr[r][c].equals("+") || boardStr[r][c].equals(" ")))
        {
            if(boardStr[r][c].equals("+"))
            {
                boardStr[ir][ic] = "Win!";
            }
            boardStr[r][c] = boardStr[ir][ic];
            boardStr[ir][ic] = " ";
            board.set(boardStr);
        }
        return board;
    }

    public GridButton move8(int ir, int ic, int r, int c)
    {
        if(countElements() == 0)
            return board;
        if(canMove(r,c))
        {
            boardStr[r][c] = boardStr[ir][ic];
            boardStr[ir][ic] = " ";
            board.set(boardStr);
        }
        return board;
    }

    public int countElements()
    {
        int sum = 0;
        try{
            for(String[] row: boardStr)
                for(String column: row)
                {
                    if(!(column.equals(" ")))
                        sum++;
                }
        }catch(NullPointerException e)
        {};
        return sum;
    }
}
