import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.Arrays;
public class DisplayRandomMaze extends JFrame implements ActionListener, KeyListener  {
    private static final long serialVersionUID = 1L;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    private JTextField text1,text2;
    private JTextArea text3;
    private JLabel label1, label2; 
    private JButton button1,button2;
    private GridButton gridButton;
    private String[][] board;
    private Mover move;
    public DisplayRandomMaze()
    {
        gridButton = new GridButton(10,10);
        for(int r = 0; r < gridButton.getButtonArray().length; r++)
            for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
            {
                gridButton.getButtonArray()[r][c].setOpaque(true);
                gridButton.getButtonArray()[r][c].setBorderPainted(false);
                gridButton.getButtonArray()[r][c].setBackground(Color.getHSBColor(217, 0, 74));
                gridButton.getButtonArray()[r][c].setText(" ");
                if(System.getProperty("os.name").equals("Mac OS X"))
                    gridButton.getButtonArray()[r][c].setPreferredSize(new Dimension(85,40));
                else
                    gridButton.getButtonArray()[r][c].setPreferredSize(new Dimension(65,25));
            }
        board = new String[10][10];
        for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
                board[r][c] = " ";
    }

    public void addComponentsToPane(Container display)
    {
        display.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipady = 25;
        //Text
        int row = 0;
        int column = 0;
        label1 = new JLabel("Input a Size (5-10) (Format : \"row,column\")");
        if (shouldFill) 
        {
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        if (shouldWeightX) 
        {
            c.weightx = 0.5;
        }
        c.gridwidth = 10;
        c.gridx = column;
        c.gridy = row;
        display.add(label1,c);
        row++;
        //text1
        text1 = new JTextField(20);
        text1.setBackground(Color.getHSBColor(217, 0, 10));
        text1.addActionListener(this);
        text1.setOpaque(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.gridx = column;
        c.gridy = row;
        display.add(text1,c);
        row++;
        //label2
        label2 = new JLabel("Input a Difficulty (1-9)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.gridx = column;
        c.gridy = row;
        display.add(label2,c);
        row++;
        //text2
        text2 = new JTextField(20);
        text2.setBackground(Color.getHSBColor(217, 0, 10));
        text2.addActionListener(this);
        text2.setOpaque(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.gridx = column;
        c.gridy = row;
        display.add(text2,c);
        row++;
        //button1
        button1 = new JButton("Generate Maze");
        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setBackground(Color.getHSBColor(217, 0, 1));
        button1.setOpaque(true);
        button1.setBorderPainted(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.gridx = column;
        c.gridy = row;
        display.add(button1, c);
        row++;
        //gridButton
        c.ipady = 10;
        c.gridwidth = 1;
        for(int r = 0; r < gridButton.getButtonArray().length; r++)
        {
            for(int col = 0; col < gridButton.getButtonArray()[0].length; col++)
            {
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                c.gridx = column + col;
                c.gridy = row;
                gridButton.getButtonArray()[r][col].addActionListener(this);
                gridButton.getButtonArray()[r][col].setFocusable(false);
                display.add(gridButton.getButtonArray()[r][col], c);
            }
            row++;
        }
        row++;
        //button2
        button2 = new JButton("Wait");
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setBackground(Color.getHSBColor(217, 0, 1));
        button2.setOpaque(true);
        button2.setBorderPainted(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 25;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.gridx = column;
        c.gridy = row;
        display.add(button2, c);
        //display
        display.setBackground(Color.getHSBColor(217, 0, 74));
        move = new Mover(gridButton, board);
    }

    private void createAndShowGUI() 
    {
        //Create and set up the window.
        JFrame frame = new JFrame("Random Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(500,350));
        frame.setFocusable(true);
        frame.addKeyListener(this);
        frame.requestFocus();
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button1) || e.getSource().equals(text1) || e.getSource().equals(text2))
        {
            boolean invalid = false;
            RandomMazeGenerator maze = new RandomMazeGenerator(0,0,0);
            String[] input = text1.getText().split(",");
            String input2 = text2.getText();
            try{
                try{
                    int row = Integer.parseInt(input[0]);
                    int column = Integer.parseInt(input[1]);
                    if(row < 5 || row > 10 || column < 5 || column > 10)
                    {
                        text1.setText("Invalid");
                        invalid = true;
                        text1.selectAll();
                        return;
                    }
                    else
                    {
                        try{
                            int difficulty = Integer.parseInt(input2);
                            if(difficulty < 1 || difficulty > 9)
                            {
                                text2.setText("Invalid");
                                invalid = true;
                                text2.selectAll();
                                return;
                            }
                            maze.setBoard(row,column);
                            maze.setDifficulty(difficulty);
                            maze.generateBoard();
                        }catch(NumberFormatException invalid3)
                        {
                            text2.setText("Invalid");
                            invalid = true;
                            text2.selectAll();
                            return;
                        }
                    }
                }catch(NumberFormatException invalid3)
                {
                    if(input[0].equals(""))
                    {
                        maze.setBoard(5,5);
                        maze.setDifficulty(5);
                        maze.generateBoard();
                        text1.setText("5,5");
                        text2.setText("5");
                    }
                    else
                    {
                        text1.setText("Invalid");
                        invalid = true;
                        text1.selectAll();
                        return;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException invalid2)
            {
                text1.setText("Invalid");
                invalid = true;
                text1.selectAll();
                return;
            }
            board = maze.getBoard();
            try{
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        gridButton.getButtonArray()[r][c].setBackground(Color.getHSBColor(217, 0, 74));
                        gridButton.setText(r,c," ");
                    }
                for(int r = 0 ; r < board.length; r++)
                {
                    for(int c = 0; c < board[0].length; c++)
                    {
                        gridButton.getButtonArray()[r][c].setBackground(Color.getHSBColor(217, 0, 1));
                        gridButton.getButtonArray()[r][c].setOpaque(true);
                        gridButton.getButtonArray()[r][c].setBorderPainted(false);
                        gridButton.setText(r,c,board[r][c]);
                    }
                }
            }catch(ArrayIndexOutOfBoundsException invalid4)
            {};
            text1.setFocusable(false);
            text2.setFocusable(false);
            text1.setFocusable(true);
            text2.setFocusable(true);
        }
        move = new Mover(gridButton, board);
        ifState:
        if(e.getSource().equals(button2))
        {
            for(int r1 = 0; r1 < board.length; r1++)
                for(int c1 = 0; c1 < board.length; c1++)
                    if(board[r1][c1].equals("Win!") || board[r1][c1].equals("Lose!"))
                        break ifState;
            gridButton = move.moveWait();
        }
        int ir = 0;
        int ic = 0;
        outerloop:
        for(int r = 0; r < gridButton.getButtonArray().length; r++)
        {
            for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
            {
                outerloop2:
                for(int r1 = 0; r1 < board.length; r1++)
                    for(int c1 = 0; c1 < board.length; c1++)
                        if(board[r1][c1].equals("@"))
                        {
                            ir = r1;
                            ic = c1;
                            break outerloop2;
                        }
                try{
                    if(gridButton.getButtonArray()[r][c].getText().equals("@"))
                    {   
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                    }
                }
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r+1][c].getText().equals("@"))
                    {
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                    } 
                }
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r-1][c].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true); 
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r+1][c+1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r-1][c+1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c,true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r][c+1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r][c-1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r+1][c-1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
                try{
                    if(gridButton.getButtonArray()[r-1][c-1].getText().equals("@"))
                        if(e.getSource().equals(gridButton.getButtonArray()[r][c]))
                        {
                            gridButton = move.move(ir, ic, r, c, true);
                            gridButton = move.moveWait();
                            break outerloop;
                        }
                } 
                catch(ArrayIndexOutOfBoundsException invalid5)
                {};
            }
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        out:
        switch( keyCode ) { 
            case KeyEvent.VK_NUMPAD8:
            {
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r+1][c].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD2:
            {
                //Down
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r-1][c].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD6:
            {
                //Right
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r][c-1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD4:
            {
                //Left
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r][c+1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD7:
            {
                //UpLeft
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r+1][c+1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD9:
            {
                //UpRight
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r+1][c-1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD1:
            {
                //DownLeft
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r-1][c+1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD3:
            {
                //DownRight
                int ir = 0;
                int ic = 0;
                outerloop:
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                {
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        outerloop2:
                        for(int r1 = 0; r1 < board.length; r1++)
                            for(int c1 = 0; c1 < board.length; c1++)
                                if(board[r1][c1].equals("@"))
                                {
                                    ir = r1;
                                    ic = c1;
                                    break outerloop2;
                                }
                        try{
                            if(gridButton.getButtonArray()[r-1][c-1].getText().equals("@"))
                            {
                                gridButton = move.move(ir, ic, r, c, true); 
                                gridButton = move.moveWait();
                                break outerloop;
                            }
                        } 
                        catch(ArrayIndexOutOfBoundsException invalid5)
                        {};
                    }
                }
                break;
            }
            case KeyEvent.VK_NUMPAD5:
            {
                //Wait
                for(int r1 = 0; r1 < board.length; r1++)
                    for(int c1 = 0; c1 < board.length; c1++)
                        if(board[r1][c1].equals("Win!") || board[r1][c1].equals("Lose!"))
                            break out;
                gridButton = move.moveWait();
                break;
            }
            case KeyEvent.VK_ENTER:
            {
                boolean invalid = false;
            RandomMazeGenerator maze = new RandomMazeGenerator(0,0,0);
            String[] input = text1.getText().split(",");
            String input2 = text2.getText();
            try{
                try{
                    int row = Integer.parseInt(input[0]);
                    int column = Integer.parseInt(input[1]);
                    if(row < 5 || row > 10 || column < 5 || column > 10)
                    {
                        text1.setText("Invalid");
                        invalid = true;
                        text1.selectAll();
                        return;
                    }
                    else
                    {
                        try{
                            int difficulty = Integer.parseInt(input2);
                            if(difficulty < 1 || difficulty > 9)
                            {
                                text2.setText("Invalid");
                                invalid = true;
                                text2.selectAll();
                                return;
                            }
                            maze.setBoard(row,column);
                            maze.setDifficulty(difficulty);
                            maze.generateBoard();
                        }catch(NumberFormatException invalid3)
                        {
                            text2.setText("Invalid");
                            invalid = true;
                            text2.selectAll();
                            return;
                        }
                    }
                }catch(NumberFormatException invalid3)
                {
                    if(input[0].equals(""))
                    {
                        maze.setBoard(5,5);
                        maze.setDifficulty(5);
                        maze.generateBoard();
                        text1.setText("5,5");
                        text2.setText("5");
                    }
                    else
                    {
                        text1.setText("Invalid");
                        invalid = true;
                        text1.selectAll();
                        return;
                    }
                }
            }catch(ArrayIndexOutOfBoundsException invalid2)
            {
                text1.setText("Invalid");
                invalid = true;
                text1.selectAll();
                return;
            }
            board = maze.getBoard();
            try{
                for(int r = 0; r < gridButton.getButtonArray().length; r++)
                    for(int c = 0; c < gridButton.getButtonArray()[0].length; c++)
                    {
                        gridButton.getButtonArray()[r][c].setBackground(Color.getHSBColor(217, 0, 74));
                        gridButton.setText(r,c," ");
                    }
                for(int r = 0 ; r < board.length; r++)
                {
                    for(int c = 0; c < board[0].length; c++)
                    {
                        gridButton.getButtonArray()[r][c].setBackground(Color.getHSBColor(217, 0, 1));
                        gridButton.getButtonArray()[r][c].setOpaque(true);
                        gridButton.getButtonArray()[r][c].setBorderPainted(false);
                        gridButton.setText(r,c,board[r][c]);
                    }
                }
            }catch(ArrayIndexOutOfBoundsException invalid4)
            {};
            }
        }
    } 

    public void keyReleased(KeyEvent e)
    {
        //Do nothing
    }

    public void keyTyped(KeyEvent e)
    {
        //Do nothing
    }

    public static void main(String []args)
    {
        final DisplayRandomMaze display = new DisplayRandomMaze();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    display.createAndShowGUI();
                }
            });
    }
}