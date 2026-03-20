package com.example.a2026_01_holamundoandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    // Store all paths and their paints
    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> paints = new ArrayList<>();

    private Path currentPath;
    private Paint currentPaint;

    Context context;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    private Random random = new Random();

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initNewPath(); // initialize first path
    }

    private void initNewPath() {
        currentPath = new Path();

        currentPaint = new Paint();
        currentPaint.setAntiAlias(true);

        // Random color
        currentPaint.setColor(Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        ));

        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeWidth(4f);

        paths.add(currentPath);
        paints.add(currentPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw all stored paths
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
    }

    private void startTouch(float x, float y) {
        currentPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            currentPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void upTouch() {
        currentPath.lineTo(mX, mY);

        // Start a new path for next stroke
        initNewPath();
    }

    public void clearCanvas() {
        paths.clear();
        paints.clear();
        initNewPath();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }
}