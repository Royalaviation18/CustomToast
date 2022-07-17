package com.royalaviation.toastp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etuserName;
    private Button btnClick;
    private ProgressBar prgBar;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etuserName = findViewById(R.id.etuserName);
        btnClick   = findViewById(R.id.btnClick);
        prgBar     = findViewById(R.id.progressBar);
        tvName     = findViewById(R.id.textView);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void validateUser() {
        String Name = etuserName.getText().toString().trim();

        if(Name.isEmpty()){
            etuserName.setError("Please enter a name!");
            etuserName.requestFocus();
            return;
        }

        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        prgBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();


        //Starting a new Thread
        new Thread(new Runnable() {
            public void run() {
                try{
                    //running this thread for 3 seconds
                    Thread.sleep(3000);
                }
                catch (Exception e) { }
                handler.post(new Runnable() {
                    public void run() {

                        prgBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
        View toastRoot = inflater.inflate(R.layout.toast,null);
        Toast toast = new Toast(context);
        toast.setView(toastRoot);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.TOP,0,20);
        toast.show();
        tvName.setText("Hello " +Name+ ",  We really missed you! ");


        etuserName.setText("");

        
    }
}