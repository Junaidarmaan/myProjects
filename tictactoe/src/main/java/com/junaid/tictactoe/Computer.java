package com.junaid.tictactoe;

class Computer {
    char computerMove;
    char userMove;

    int analyze(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int position = 1; position <= 9; position++) {
            int row = (position - 1) / 3;
            int col = (position - 1) % 3;
            if (board[row][col] == ' ') {
                board[row][col] = computerMove;
                int score = minimax(board, false);
                board[row][col] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = position;
                }
            }
        }
        return bestMove;
    }

    int minimax(char[][] board, boolean isMaximizing) {
        if (checkWin(board, computerMove)) {
            return 1;
        }
        if (checkWin(board, userMove)) {
            return -1;
        }
        if (isBoardFull(board)) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int position = 1; position <= 9; position++) {
                int row = (position - 1) / 3;
                int col = (position - 1) % 3;
                if (board[row][col] == ' ') {
                    board[row][col] = computerMove;
                    int score = minimax(board, false);
                    board[row][col] = ' ';
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int position = 1; position <= 9; position++) {
                int row = (position - 1) / 3;
                int col = (position - 1) % 3;
                if (board[row][col] == ' ') {
                    board[row][col] = userMove;
                    int score = minimax(board, true);
                    board[row][col] = ' ';
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
