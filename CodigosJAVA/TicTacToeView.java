package com.example.a2026_01_holamundoandroid;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TicTacToeView extends View {

    private Paint gridPaint, xPaint, oPaint;

    private int[][] board = new int[3][3]; // 0 = empty, 1 = X, 2 = O
    private boolean playerXTurn = true;
    private boolean gameOver = false;

    private float cellSize;

    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    DisplayMetrics metrics;
    int screenX;
    int screenY;


    public void init() {
        gridPaint = new Paint();
        gridPaint.setColor(Color.BLACK);
        gridPaint.setStrokeWidth(8);

        xPaint = new Paint();
        xPaint.setColor(Color.RED);
        xPaint.setStrokeWidth(12);

        oPaint = new Paint();
        oPaint.setColor(Color.BLUE);
        oPaint.setStrokeWidth(12);
        oPaint.setStyle(Paint.Style.STROKE);

        metrics = getResources().getDisplayMetrics();
        screenX = metrics.widthPixels;
        screenY = metrics.heightPixels;

        Toast.makeText(getContext(),
                ""+screenX+"x"+screenY,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //cellSize = getWidth() / 8f;
        if (screenX > screenY) {
            cellSize = screenY / 4f;
        } else {
            cellSize = screenX / 4f;
        }


        drawGrid(canvas);
        drawMarks(canvas);
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 1; i < 3; i++) {
            // Vertical lines
            //canvas.drawLine(i * cellSize, 0, i * cellSize, getHeight(), gridPaint);
            canvas.drawLine(i * cellSize, 0, i * cellSize, cellSize*3, gridPaint);

            // Horizontal lines
            canvas.drawLine(0, i * cellSize, cellSize*3, i * cellSize, gridPaint);
        }
    }

    private void drawMarks(Canvas canvas) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                float cx = col * cellSize + cellSize / 2;
                float cy = row * cellSize + cellSize / 2;

                if (board[row][col] == 1) {
                    drawX(canvas, cx, cy);
                } else if (board[row][col] == 2) {
                    drawO(canvas, cx, cy);
                }
            }
        }
    }

    private void drawX(Canvas canvas, float cx, float cy) {
        float offset = cellSize / 3;

        canvas.drawLine(cx - offset, cy - offset, cx + offset, cy + offset, xPaint);
        canvas.drawLine(cx + offset, cy - offset, cx - offset, cy + offset, xPaint);
    }

    private void drawO(Canvas canvas, float cx, float cy) {
        float radius = cellSize / 3;
        canvas.drawCircle(cx, cy, radius, oPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameOver) return true;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int col = (int)(event.getX() / cellSize);
            int row = (int)(event.getY() / cellSize);

            if (row < 3 && col < 3 && board[row][col] == 0) {

                board[row][col] = playerXTurn ? 1 : 2;

                if (checkWinner()) {
                    gameOver = true;
                    Toast.makeText(getContext(),
                            playerXTurn ? "Player X Wins!" : "Player O Wins!",
                            Toast.LENGTH_SHORT).show();
                } else if (isBoardFull()) {
                    gameOver = true;
                    Toast.makeText(getContext(),
                            "Draw!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    playerXTurn = !playerXTurn;
                }

                invalidate();
            }
        }
        return true;
    }

    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    private boolean checkWinner() {

        int player = playerXTurn ? 1 : 2;

        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) return true;
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player) return true;
        }

        // Diagonals
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) return true;

        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) return true;

        return false;
    }

    public void resetGame() {
        board = new int[3][3];
        playerXTurn = true;
        gameOver = false;
        invalidate();
    }
}