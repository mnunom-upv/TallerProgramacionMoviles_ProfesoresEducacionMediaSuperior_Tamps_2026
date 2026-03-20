package com.example.a2026_01_holamundoandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Demo10 extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private LinearLayout mCLayout;
    private Button mButtonDo;
    private ProgressDialog mProgressDialog;
    private TextView TV2;

    private AsyncTask mMyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo10);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity_Demo10.this;

        // Get the widget reference from XML layout
        mCLayout = (LinearLayout) findViewById(R.id.main);
        mButtonDo = (Button) findViewById(R.id.BT1);
        TV2 = (TextView) findViewById(R.id.TV2);

        // Initialize the progress dialog
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setIndeterminate(false);
        // Progress dialog horizontal style
        //mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // Progress dialog title
        mProgressDialog.setTitle("AsyncTask");
        // Progress dialog message
        mProgressDialog.setMessage("Please wait, we are downloading your files...");

        // Initialize a new click listener for positive button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute the async task
                mMyTask = new DownloadTask()
                        .execute(
                                "Santos Peregrinos",
                                "La vuelta al mundo en 80 días",
                                "Pepito y Chabelo detectives",
                                "El Santos vs la Tetona Mendoza",
                                "Macario",
                                "Vacaiones de Terror",
                                "La risa en Vacaiones",
                                "Y tu mamá tambien",
                                "Amores Perros",
                                "Masacre en Texas",
                                "Un par a todo dar",
                                "El rey del Barrio",
                                "Los Japoneses no Esperan",
                                "Cilantro y Perejil",
                                "Como Agua para Chocolate",
                                "El crimen del Cáraro Gumaro",
                                "Cabeza de Buda",
                                "Con todo el Poder",
                                "La ley de Herodes"
                        );
            }
        });
    }

    private class DownloadTask extends AsyncTask<String,Integer,List<String>>{

        private int count;
        private String currentTask ;
        // Before the tasks execution
        protected void onPreExecute(){
            // Display the progress dialog on async task start
            mProgressDialog.show();
            count =0;
            currentTask="";
        }

        // Do the task in background/non UI thread
        protected List<String> doInBackground(String...tasks){
            // Get the number of task
            count = tasks.length;
            // Initialize a new list
            List<String> taskList= new ArrayList<>(count);

            // Loop through the task
            for(int i =0;i<count;i++){
                // Do the current task here
                currentTask = tasks[i];
                taskList.add(currentTask);



                // Publish the async task progress
                // Added 1, because index start from 0
                //publishProgress((int) (((i+1) / (float) count) * 100));
                publishProgress((i+1));

                // Sleep the thread for 1 second
                try {
                    //Thread.sleep(1000);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // If the AsyncTask cancelled
                if(isCancelled()){
                    break;
                }
            }
            // Return the task list for post execute
            return taskList;
        }

        // After each task done
        protected void onProgressUpdate(Integer... progress){
            // Update the progress bar on dialog
            mProgressDialog.setProgress((int) (progress[0]/  (float) count) * 100);
            mProgressDialog.setMessage("Procesando "+currentTask+" ("+progress[0]+" de "+count+")");
        }

        // When all async task done
        protected void onPostExecute(List<String> result){
            // Hide the progress dialog
            mProgressDialog.dismiss();

            TV2.setText("");

            for (int i=0;i<result.size();i++){
                // Do something here
                TV2.append(result.get(i).toString()+"\n");
            }


        }
    }
}
