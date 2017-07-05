import edu.princeton.cs.algs4.MinPQ;

public class Solver
{
    private class SearchNode
    {
        private Board currentBoard;
        private int moves;
        private Board previousBoard;
        
        private SearchNode(Board insertedBoard, int movesMade, Board oldBoard)
        {
            currentBoard = insertedBoard;
            moves = movesMade;
            previousBoard = oldBoard;
        }
    }
    
    public Solver(Board initial)
    {
        Board twin = initial.twin();
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();
        SearchNode pqBoard;
        SearchNode twinPQBoard;
        Iterable<Board> pqIterator;
        Iterable<Board> twinPQIterator;
        
        pq.insert(new SearchNode(initial, 0, null));
        twinPQ.insert(new SearchNode(twin,0,null));
            
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
                    pq.insert(new SearchNode(i,pqBoard.moves+1,pqBoard.currentBoard))
                        
                }
            }
        }
        
        
        SearchNode test = pq.min();
        System.out.println(test.moves);
        
    }
    
     public static void main(String[] args)
    {
        int[][] blocks = new int[3][3];
        
        Board test = new Board(blocks);
        Solver solve = new Solver(test);
    }
}