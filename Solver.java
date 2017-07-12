import edu.princeton.cs.algs4.MinPQ;
import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver
{
    public static final Comparator<SearchNode> BY_PRIORITY = new ByPriority();
    private boolean solved;
    private MinPQ<SearchNode> pq = new MinPQ<SearchNode>(BY_PRIORITY);
    private MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(BY_PRIORITY);
        
    private static class ByPriority implements Comparator<SearchNode>
    {
        public int compare(SearchNode a, SearchNode b)
        {
            return a.priority-b.priority;
        }
    }
    
    private class SearchNode
    {
        private Board currentBoard;
        private int moves;
        private SearchNode previousBoard;
        private int priority;
        
        public SearchNode(Board insertedBoard, int movesMade, SearchNode oldBoard, int p)
        {
            currentBoard = insertedBoard;
            moves = movesMade;
            previousBoard = oldBoard;
            priority = p;
        }
    }
    
    public Solver(Board initial)
    {
        Board twin = initial.twin();
       // MinPQ<SearchNode> pq = new MinPQ<SearchNode>(BY_PRIORITY);
        //MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(BY_PRIORITY);
        SearchNode pqBoard;
        SearchNode twinPQBoard;
        Iterable<Board> pqIterator;
        Iterable<Board> twinPQIterator;
        
        pq.insert(new SearchNode(initial, 0, null,0));
        //pq.insert(new SearchNode(initial, 1, null,0));
        //pq.insert(new SearchNode(initial, 10, null,-1));
        twinPQ.insert(new SearchNode(twin,0,null,0));
            
        while(!(pq.min().currentBoard.isGoal()) || !(twinPQ.min().currentBoard.isGoal()))
        {
            pqBoard = pq.delMin();
            twinPQBoard = pq.delMin();
            
            pqIterator = pqBoard.currentBoard.neighbors();
            twinPQIterator = twinPQBoard.currentBoard.neighbors();
            
            for(Board i : pqIterator)
            {
                if(!(i.equals(pqBoard.currentBoard)))
                {
                    pq.insert(new SearchNode(i, pqBoard.moves+1, pqBoard,(i.manhattan()+pqBoard.moves+1)));
                }
            }
            
            for(Board o : twinPQIterator)
            {
                if(!(o.equals(twinPQBoard.currentBoard)))
                {
                    twinPQ.insert(new SearchNode(o, twinPQBoard.moves+1, twinPQBoard,(o.manhattan()+twinPQBoard.moves+1)));
                }
            }
        }
        
        if(pq.min().currentBoard.isGoal())
        {
            solved = true;
        }
        
        if(twinPQ.min().currentBoard.isGoal())
        {
            solved = false;
        }
        
        
       // SearchNode test = pq.delMin();
        //System.out.println(test.moves);
        
    }
    
    public boolean isSolvable()
    {
        return solved;
    }
    
    public int moves()
    {
        if(isSolvable())
        {
            return pq.min().moves;
        }
        return -1;
    }
    
    public Iterable<Board> solution()
    {
        Stack<Board> solutions = new Stack<Board>();
        MinPQ<SearchNode> boards = pq;
        
        for(int i = 0; i <= pq.min().moves; i++)
        {
            solutions.push(boards.min().currentBoard);
            boards.insert(boards.delMin().previousBoard);
        }
        
        return solutions;
    }
    
//    private int priority(Board a)
//    {
//        return (a.moves+a.currentBoard.manhattan());
//    }
    
     public static void main(String[] args)
    {
//        int[][] blocks = new int[3][3];
//        
//        Board test = new Board(blocks);
//        Solver solve = new Solver(test);
         
          // create initial board from file
         In in = new In(args[0]);
         int n = in.readInt();
         int[][] blocks = new int[n][n];
         for (int i = 0; i < n; i++)
             for (int j = 0; j < n; j++)
             blocks[i][j] = in.readInt();
         Board initial = new Board(blocks);
         
         // solve the puzzle
         Solver solver = new Solver(initial);
         
         // print solution to standard output
         if (!solver.isSolvable())
             StdOut.println("No solution possible");
         else {
             StdOut.println("Minimum number of moves = " + solver.moves());
             for (Board board : solver.solution())
                 StdOut.println(board);
         }
    }
}