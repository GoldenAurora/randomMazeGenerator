import javax.swing.JButton;
public class GridButton
{
    private JButton[][] buttonArray;
    public GridButton(int rows, int columns)
    {
        buttonArray = new JButton[rows][columns];
        for(int r = 0; r < buttonArray.length; r++)
            for(int c = 0; c < buttonArray[0].length; c++)
            {
                buttonArray[r][c] = new JButton();
            }
    }
    public JButton[][] getButtonArray()
    {
        return buttonArray;
    }
    public void setText(int r, int c, String text)
    {
        buttonArray[r][c].setText(text);
    }
    public void set(String[][] str)
    {
        for(int r = 0; r < str.length; r++)
            for(int c = 0; c < str[0].length; c++)    
                buttonArray[r][c].setText(new String(str[r][c]));
    }
}
