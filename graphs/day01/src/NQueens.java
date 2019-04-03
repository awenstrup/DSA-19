import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkRow(char[][] board, int r, int c) {
        for (int i = 0; i < board[0].length; i++) {
            if (i != c && board[r][i] == 'Q')
                return true;
        }
        return false;
    }

    public static boolean checkColumn(char[][] board, int r, int c) {
        for (int i = 0; i < board.length; i++) {
            if (i != r && board[i][c] == 'Q')
                return true;
        }
        return false;
    }

    public static boolean validQueenPlacement(char[][] board, int r, int c) {
        if (board[r][c] == '.') {
            System.out.println("No queen here");
            return true;
        }
        else {
            return !(checkRow(board, r, c) || checkColumn(board, r, c) || checkDiagonal(board, r, c));
        }
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }


    public static List<char[][]> nQueensSolutions(int n) {
        List<char[][]> answers = new ArrayList<>();

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        nQueensHelper(0, board, answers);

        return answers;
    }

    public static void nQueensHelper(int r, char[][] current, List<char[][]> solutions) {
        //base case
        if (r == current.length) {
            solutions.add(copyOf(current));
            printBoard(current);
            System.out.println("Valid!");
        }

        //recursive case
        else {
            for (int c = 0; c < current[0].length; c++) {
                current[r][c] = 'Q';
                //printBoard(current);

                if (validQueenPlacement(current, r, c))
                    nQueensHelper(r + 1, current, solutions);
                else {
                    current[r][c] = '.';
                }

                current[r][c] = '.';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("---------------");
    }

    /*
    public static void permutationHelper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations) {
        //base case
        if (unused.size() == 0) {
            permutations.add(new LinkedList<>(current));
            System.out.println(permutations);
        }

        //recursive case
        for (int i = 0; i < unused.size(); i++) {
            //LinkedList<Integer> workingCopy = new LinkedList<>(unused);
            Integer item = unused.get(i);
            current.add(item);
            unused.remove(i);
            permutationHelper(current, unused, permutations);
            current.remove(item);
            unused.add(i, item);
        }
    }
    */

}
