package com.example.a2026_01_holamundoandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity_Demo20 extends AppCompatActivity {
    Button B11, B12, B13, B14, B21, B22, B23, B24, B31, B32, B33, B34, B41, B42, B43, B44, B51, B52, B53, B54;

    PongSurfaceView pongView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_main_demo20);
        InicializarVistas ();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    void InicializarVistas () {
        B11 = findViewById(R.id.B11);
        B12 = findViewById(R.id.B12);
        B13 = findViewById(R.id.B13);
        B14 = findViewById(R.id.B14);

        B21 = findViewById(R.id.B21);
        B22 = findViewById(R.id.B22);
        B23 = findViewById(R.id.B23);
        B24 = findViewById(R.id.B24);

        B31 = findViewById(R.id.B31);
        B32 = findViewById(R.id.B32);
        B33 = findViewById(R.id.B33);
        B34 = findViewById(R.id.B34);

        B41 = findViewById(R.id.B41);
        B42 = findViewById(R.id.B42);
        B43 = findViewById(R.id.B43);
        B44 = findViewById(R.id.B44);

        B51 = findViewById(R.id.B51);
        B52 = findViewById(R.id.B52);
        B53 = findViewById(R.id.B53);
        B54 = findViewById(R.id.B54);



    }
}
