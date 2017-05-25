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
        int r = 0;
        int c = 0;
        for(int i = 0; i < dim; i++)
        {
            for(int j = 0; j < dim; j++)
            {
                if(board[i][j] != 0)
                {
                    val = board[i][j];
                    c = (val%dim)-1;
                    r = val/dim;

                    if(c<0)
                    {
                        c = dim;
                        r -= 1;
                    }

                    manhattan += (Math.abs(r-i))+(Math.abs(c-j));
                } 
            }
        }
        return manhattan;
    }
    
    public boolean isGoal()
    {
        if(hamming() == 0)
        {
            return true;
        }
        return false;
    }
    
    public Board twin()
    {
        int temp = 0;
        Board twin = new Board(board);
        if(board[0][0] != 0 && board[0][1] != 0)
        {
           // twin.board = board;
            temp = twin.board[0][0];
            twin.board[0][0] = twin.board[0][1];
            twin.board[0][1] = temp;
        }
        else
        {
           // twin.board = board;
            temp = twin.board[1][0];
            twin.board[1][0] = twin.board[1][1];
            twin.board[1][1] = temp;
        }
        return twin;
    }
    
    public boolean equals(Object y)
    {
        Board yBoard = (Board) y;
        return java.util.Arrays.deepEquals(board,yBoard.board);
    }
    
    public Iterable<Board> neighbors()
    {
        
    }
}