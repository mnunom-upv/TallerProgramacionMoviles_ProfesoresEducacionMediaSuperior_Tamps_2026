package com.example.a2026_01_holamundoandroid;

/* Fuente:
http://examples.javacodegeeks.com/android/core/graphics/canvas-graphics/android-canvas-example/
 */

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity_Demo16 extends AppCompatActivity {

    private CanvasView customCanvas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo16);

        customCanvas = findViewById(R.id.signature_canvas);


    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

}
