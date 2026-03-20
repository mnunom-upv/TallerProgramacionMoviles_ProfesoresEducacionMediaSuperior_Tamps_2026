package com.example.a2026_01_holamundoandroid;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
Demo de Sistesis de VOZ
http://www.tutorialspoint.com/android/android_text_to_speech.htm
 */

public class MainActivity_Demo14 extends AppCompatActivity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo14);

        ed1=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button);

        ed1.setText("Ezequiel 25:17. \"El camino del hombre justo está plagado por todos lados por las iniquidades de los egoístas y la tiranía de los hombres malvados. Bendito sea aquel que, en nombre de la caridad y la buena voluntad, pastorea a los débiles a través del valle de la oscuridad. Porque él es verdaderamente el guardián de su hermano y el que encuentra a los niños perdidos. Y descargaré sobre ti con gran venganza y furiosa ira a aquellos que intentan envenenar y destruir a mis hermanos. Y sabrás que yo soy el Señor cuando ponga mi venganza sobre ti\". ");

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    // En Inglés Británico
                    //t1.setLanguage(Locale.UK);

                    // En Español de Tepito
                    Locale locSpanish = new Locale("spa", "MEX");
                    t1.setLanguage(locSpanish);

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


    
}
