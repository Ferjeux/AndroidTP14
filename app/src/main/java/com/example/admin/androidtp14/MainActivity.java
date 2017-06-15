package com.example.admin.androidtp14;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;


import static android.R.id.progress;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(100);

    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            int p = msg.getData().getInt("PROGRESS");
            progressBar.setProgress(p);
        }
    };

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try{
                Message message;
                Bundle messageBundle = new Bundle();
                for (int i = 0; i < 100; i++){
                    message = handler.obtainMessage();
                    messageBundle.putInt("PROGRESS", i);
                    message.setData(messageBundle);
                    handler.sendMessage(message);
                    Thread.sleep(300);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    });

    @Override
    protected void onStart(){
        super.onStart();
        thread.start();

    }





}
