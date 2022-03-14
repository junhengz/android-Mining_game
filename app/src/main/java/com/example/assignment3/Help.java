package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Help extends AppCompatActivity {

        public static Intent makeLaunchHelp (Context c)
        {
            Intent intent = new Intent(c, Help.class);
            return intent;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


    }
}
