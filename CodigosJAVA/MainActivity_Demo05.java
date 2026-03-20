package com.example.a2026_01_holamundoandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity_Demo05 extends AppCompatActivity {

    TextView TV1;
    ImageView IM1;
    Button B1;
    RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_demo05);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TV1 = (TextView) findViewById(R.id.textView1);
        IM1= (ImageView) findViewById(R.id.imageView);
        B1 = (Button) findViewById (R.id.button1);

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);

                switch (rb.getText().toString()) {
                    case "Opcion 1": IM1.setImageResource(R.drawable.upv); break;
                    case "Opcion 2": IM1.setImageResource(R.drawable.logo_upv_2019); break;
                    case "Opcion 3": IM1.setImageResource(R.drawable.cgup); break;
                    default: break;
                }

            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*RadioButton RB1;
                RB1 = (RadioButton) radioGroup.getCheckedRadioButtonId();*/
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = radioGroup.findViewById(id);

                switch (rb.getText().toString()) {
                    case "Opcion 1": Toast.makeText(getApplicationContext(), "Opcion 1:", Toast.LENGTH_LONG).show(); break;
                    case "Opcion 2": Toast.makeText(getApplicationContext(), "Opcion 2:", Toast.LENGTH_LONG).show(); break;
                    case "Opcion 3": Toast.makeText(getApplicationContext(), "Opcion 3:", Toast.LENGTH_LONG).show(); break;
                    default: break;
                }


            }
        });

    }

}
