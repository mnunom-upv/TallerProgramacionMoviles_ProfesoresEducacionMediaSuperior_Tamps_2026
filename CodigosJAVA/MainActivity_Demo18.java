package com.example.a2026_01_holamundoandroid;

/* Fuente:
http://examples.javacodegeeks.com/android/core/graphics/canvas-graphics/android-canvas-example/
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity_Demo18 extends AppCompatActivity {
    private TicTacToeView dragAndDropViewSurface;
    Button B1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo18);
        B1 = findViewById(R.id.button1);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dragAndDropViewSurface.resetGame();
            }
        });

        dragAndDropViewSurface = findViewById(R.id.TicTacToeView);
    }
}
