package edu.hw1;

public final class Task8 {

    private static final int SIZE_OF_BORDER = 8;

    private Task8() {

    }

    @SuppressWarnings("checkstyle:MagicNumber") public static boolean knightBoardCapture(int[][] board) {
        if (board == null || board.length != SIZE_OF_BORDER) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (board[i].length != SIZE_OF_BORDER) {
                return false;
            }
        }
        int[][] delta = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    for (int k = 0; k < delta.length; k++) {
                        if (!(i + delta[k][0] < 0 || i + delta[k][0] > 7 || j + delta[k][1] < 0
                            || j + delta[k][1] > 7)) {
                            if (board[i + delta[k][0]][j + delta[k][1]] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
