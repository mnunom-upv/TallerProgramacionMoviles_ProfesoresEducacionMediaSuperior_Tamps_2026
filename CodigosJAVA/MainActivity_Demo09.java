package com.example.a2026_01_holamundoandroid;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
// Al agregar este archivo, la siguiente linea marca errro
// Solucion: 1) Poner el cursor sobre la palabra remarcada en rojo y teclar ALT+ENTER
// 2) Seleccionar la ocion Add Dependency on ...
import androidx.documentfile.provider.DocumentFile;

import android.os.Bundle;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class MainActivity_Demo09 extends AppCompatActivity {

    TextView TV1;
    EditText TV2;

    private ActivityResultLauncher<Intent> launcher;

    private static final int WRITE_REQUEST_CODE = 101;
    private static final int READ_REQUEST_CODE = 102;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo09);

        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TV1 = findViewById(R.id.tv_ultimo_archivo_abierto);
        TV2 = findViewById(R.id.tv_contenido_archivo);
        TV2.setMovementMethod(new ScrollingMovementMethod()); // Para hacer el TV2 "Scrollable"



    }

    public void SeleccionarFolder (View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, "Folder");
            values.put(MediaStore.MediaColumns.MIME_TYPE, "vnd.android.document/directory");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/TallerCBTIs_119");

            getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
        }
    }
    public void UsuarioSeleccionaArchivoLectura (View v) {
        readFile();
    }


    public void UsuarioSeleccionaArchivoEscritura (View v) {
        createFile();
    }

    private void readFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        // filter to only show openable items.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");

        startActivityForResult(intent, READ_REQUEST_CODE);

    }

    // create text file
    private void createFile() {
        // when you create document, you need to add Intent.ACTION_CREATE_DOCUMENT
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        // filter to only show openable items.
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Create a file with the requested Mime type
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "ProgramacionMovil2026.txt");

        startActivityForResult(intent, WRITE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WRITE_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (data != null
                            && data.getData() != null) {
                        writeInFile(data.getData(), TV2.getText().toString());
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    break;
            }
        }

        if (requestCode == READ_REQUEST_CODE) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (data != null
                            && data.getData() != null) {
                        readFromFile(data.getData(), "");
                        //DocumentFile.fromSingleUri

                        DocumentFile documentFile = DocumentFile.fromSingleUri(getApplicationContext(), data.getData());
                        TV1.setText(documentFile.getName());
                    }
                    break;
            }
        }
    }

    private void readFromFile(@NonNull Uri uri, @NonNull String text) {
        InputStreamReader input;
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String strCurrentLine;
            String FileContents ="";
            while ((strCurrentLine = bf.readLine()) != null) {
                //System.out.println(strCurrentLine);
                FileContents+=strCurrentLine+"\n";
            }
            //String Texto = String.valueOf(bf.read());
            //Toast.makeText(getApplicationContext(),Texto,Toast.LENGTH_SHORT).show();
            TextView tv1 = findViewById(R.id.tv_contenido_archivo);
            tv1.setText(FileContents);
        } catch (IOException e) {

        }
    }

    private void writeInFile(@NonNull Uri uri, @NonNull String text) {
        OutputStream outputStream;
        try {
            outputStream = getContentResolver().openOutputStream(uri);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}