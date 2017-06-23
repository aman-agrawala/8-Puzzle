//import java.util.Stack;
import edu.princeton.cs.algs4.Stack;

public class Board {
    int[][] board;
    int zeroR;
    int zeroC;
    
    public Board(int[][] blocks)
    {
        board = blocks;
        zeroR = -1;
        zeroC = -1;
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
                
                if(board[i][j] == 0)
                {
                    zeroR = i;
                    zeroC = j;
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
                
                if(board[i][j] == 0)
                {
                    zeroR = i;
                    zeroC = j;
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
        Stack<Board> neighbors = new Stack<Board>();
        int[][] neighbor = board;
        if(zeroR == -1 && zeroC == -1)
        {
            hamming();
        }
        
        for(int i = 0; i<4; i++)
        {
            int zeroRMove = 0;
            int zeroCMove = 0;
            
            if(i == 0) // top
            {
                zeroRMove = -1;
                zeroCMove = 0;
            }
            else if(i == 1) // right
            {
                zeroRMove = 0;
                zeroCMove = 1;
            }
            else if(i == 2) // bottom
            {
                zeroRMove = 1;
                zeroCMove = 0;
            }
            else if(i == 3) // left
            {
                zeroRMove = 0;
                zeroCMove = -1;
            }
            
            try // first try the top most point
            {
                neighbor = board;
                int temp = neighbor[zeroR][zeroC];
                neighbor[zeroR][zeroC] = neighbor[zeroR+zeroRMove][zeroC+zeroCMove];
                neighbor[zeroR+zeroRMove][zeroC+zeroCMove] = temp;
                neighbors.push(new Board(neighbor));
            }
            catch (ArrayIndexOutOfBoundsException E)
            {
                System.out.println("No " + i + " value");
            }
        }
        return neighbors;
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        int n = dimensions();
        s.append(dimensions() + "\n");
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    
    public static void main(String[] args)
    {
        int[][] blocks = new int[3][3];
        
        Board test = new Board(blocks);
    }
}