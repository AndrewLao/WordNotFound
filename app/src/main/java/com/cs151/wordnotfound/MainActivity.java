package com.cs151.wordnotfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageButton;
=======
>>>>>>> f40ea9b11c1b39089365fd4af36b12b902c6d903

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gravButton = (Button) findViewById(R.id.main_title_button1);

        gravButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hello world", "hello world");
            }
        });

<<<<<<< HEAD
        ImageButton settingButton = (ImageButton) findViewById(R.id.main_title_setting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });

=======
>>>>>>> f40ea9b11c1b39089365fd4af36b12b902c6d903
    }



}