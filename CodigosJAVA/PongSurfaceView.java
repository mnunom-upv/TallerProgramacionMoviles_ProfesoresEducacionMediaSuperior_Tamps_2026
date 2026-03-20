package com.example.a2026_01_holamundoandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private PongThread thread;

    // Screen
    private int screenWidth, screenHeight;

    // Ball
    private float ballX, ballY, ballRadius = 20;
    private float ballVX = 8, ballVY = 8;

    // Paddles
    private float paddleWidth = 30, paddleHeight = 200;
    private float leftPaddleY, rightPaddleY;
    private float paddleSpeed = 20;

    // Scores
    private int scoreLeft = 0;
    private int scoreRight = 0;

    // Paint
    private Paint paint;

    public PongSurfaceView(Context context) {
        super(context);
        init();
    }

    public PongSurfaceView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        getHolder().addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(60);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();

        resetGame();

        thread = new PongThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    private void resetGame() {
        ballX = screenWidth / 2;
        ballY = screenHeight / 2;

        leftPaddleY = screenHeight / 2 - paddleHeight / 2;
        rightPaddleY = screenHeight / 2 - paddleHeight / 2;

        ballVX = 8;
        ballVY = 8;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);

        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) { }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        // Draw paddles
        paint.setColor(Color.WHITE);
        canvas.drawRect(20, leftPaddleY, 20 + paddleWidth, leftPaddleY + paddleHeight, paint);
        canvas.drawRect(screenWidth - 50, rightPaddleY,
                screenWidth - 50 + paddleWidth, rightPaddleY + paddleHeight, paint);

        // Draw ball
        canvas.drawCircle(ballX, ballY, ballRadius, paint);

        // Draw scores
        canvas.drawText(scoreLeft + " : " + scoreRight,
                screenWidth / 2 - 60, 80, paint);
    }

    public void update() {
        // Move ball
        ballX += ballVX;
        ballY += ballVY;

        // Top/bottom collision
        if (ballY <= 0 || ballY >= screenHeight) {
            ballVY *= -1;
        }

        // Left paddle collision
        if (ballX <= 50 &&
                ballY >= leftPaddleY &&
                ballY <= leftPaddleY + paddleHeight) {
            ballVX *= -1;
        }

        // Right paddle collision
        if (ballX >= screenWidth - 50 &&
                ballY >= rightPaddleY &&
                ballY <= rightPaddleY + paddleHeight) {
            ballVX *= -1;
        }

        // Score conditions
        if (ballX < 0) {
            scoreRight++;
            resetGame();
        }

        if (ballX > screenWidth) {
            scoreLeft++;
            resetGame();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y = event.getY();
        float x = event.getX();

        // Left half controls left paddle
        if (x < screenWidth / 2) {
            leftPaddleY = y - paddleHeight / 2;
        } else {
            rightPaddleY = y - paddleHeight / 2;
        }

        return true;
    }

    // ================= THREAD =================
    private class PongThread extends Thread {

        private SurfaceHolder surfaceHolder;
        private PongSurfaceView view;
        private boolean running = false;

        public PongThread(SurfaceHolder holder, PongSurfaceView view) {
            this.surfaceHolder = holder;
            this.view = view;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;

            while (running) {
                canvas = null;

                try {
                    canvas = surfaceHolder.lockCanvas();

                    synchronized (surfaceHolder) {
                        view.update();
                        view.onDraw(canvas);
                    }

                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                try {
                    sleep(16); // ~60 FPS
                } catch (InterruptedException e) { }
            }
        }
    }
}
