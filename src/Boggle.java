import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {
        TST dict = new TST();
        ArrayList<String> goodWords = new ArrayList<String>();
        boolean[][] copy = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                copy[i][j] = false;
            }
        }

        int k = 0;
        while (k < dictionary.length) {
            dict.insert(dictionary[k], k);
        }

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                String temp = "";
                dfs(temp, board, copy, i, j, goodWords);
            }
        }

        for (int i = 0; i < goodWords.size(); i++) {
            if (dict.lookup(goodWords.get(i)) == -1) {
                goodWords.remove(i);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public static void dfs(String prefix, char[][] grid, boolean[][] copy, int i, int j, ArrayList<String> words) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            words.add(prefix);
            return;
        }
        if (copy[i][j] == true) {
            words.add(prefix);
            return;
        }
        copy[i][j] = true;
        prefix += grid[i][j];
        dfs(prefix, grid, copy, i - 1, j, words);
        dfs(prefix, grid, copy,i + 1, j, words);
        dfs(prefix, grid, copy, i, j - 1, words);
        dfs(prefix, grid, copy, i, j + 1, words);
        copy[i][j] = false;
    }
}
