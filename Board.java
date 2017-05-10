public class Board {
    int[][] board;
    
    public Board(int[][] blocks)
    {
        board = blocks;
    }
    
    public int dimensions()
    {
        return board.length;
    }
    
    public int hamming()
    {
        int dim = dimensions();
        int hamming = 0;
        for(int i = 0; i < dim; i++)
        {
            for(int j = 0; j < dim; j++)
            {
                if(board[i][j] != (j+i+1) && board[i][j] != 0)
                {
                    hamming+= 1; 
                }
            }
        }   
        return hamming;
    }
    
    public int manhattan()
    {
        int dim = dimensions();
        int manhattan = 0;
        int val = 0;
        for(int i = 0; i < dim; i++)
        {
            for(int j = 0; j < dim; j++)
            {
                val = board[i][j];
                manhattan += ()
            }
        }
    }
}