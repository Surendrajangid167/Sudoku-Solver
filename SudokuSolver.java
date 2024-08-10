//It is solved by using backtracking method
//Time complexity => O(n^(rows*cols)) ,here n is number of choices to be put in each cell 1-9
//and rows and columns are 9 each
//so ,time complexity => O(9^81)
public class SudokuSolver{
    public boolean isSafe(char[][] board , int row, int col,int number){
        //column
        for(int i=0;i<board.length;i++){
//converting int to char, 0-9 are 48 to 57 respectively in decimal values 
//so for every character to be accessed , '0' means 48 plus that number=something ,48+1=49  ,48+2=50 ,and so on....
            if(board[i][col] == (char)(number+'0')){ 
                return false; 
            }
        }
        //row
        for(int j=0;j<board.length;j++){
            if(board[row][j] == (char)(number + '0')){
                return false;
            }
        }
        //grid
        int sr = 3 * (row/3);
        int sc = 3 * (col/3);
         
        for(int i=sr ;i<sr+3;i++){
            for(int j=sc;j<sc+3;j++){
                if(board[i][j] == (char)(number + '0')){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean helper(char[][] board,int row,int col){
        if(row == board.length){  //Base Case
            return true;
        }

        int nrow = 0;
        int ncol = 0;

        if(col == board.length-1){
            nrow = row+1;
            ncol = 0;
        }else{
            nrow = row;
            ncol = col+1;
        }
        if(board[row][col] != '.'){
            if(helper(board, nrow, ncol)){
                return true;
            }
        }else{
            //fill the place
            for(int i=1;i<=9;i++){
                if(isSafe(board,row,col,i)){
                    board[row][col] = (char)(i + '0');
                    if(helper(board,nrow,ncol)){
                        return true;
                    }else{
                        board[row][col] = '.';
                    }
                }
            }
        }
        return false;
        }

        public void solveSudoku(char[][] board){
            helper(board , 0 , 0);
        }
    
    public static void main(String args[]){
        //Initialize a sample Sudoku board
        char[][] board={
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        //Print the original board
        System.out.println("Original Sudoku Board: ");
        printBoard(board);

        //Create an instance of SudokuSolver class and solvethe sudoku
        SudokuSolver solver = new SudokuSolver();
        solver.solveSudoku(board);

        //Print the solved sudoku
        System.out.println("\nSolved SudokuBoard:");
        printBoard(board);
    }
    //Helper method to print the board
    public static void printBoard(char[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){ 
//the condn "j<board[i].length" is used to ensure that we are iterating over correct number of columns
//for each row in 2D array 'board'
//board[i].length gives number of columns in the i-th row of the 2D array
                System.out.print(board[i][j] + " ");
        }
        System.out.println(); //move to next line after printing all columns in current row
    }
}
}