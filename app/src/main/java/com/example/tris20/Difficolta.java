package com.example.tris20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficolta extends AppCompatActivity implements View.OnClickListener {
    Button facile,medio,difficile;
    public static final String CHANCESTUPIDCHOICE = "com.example.tris20.difficolta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficolta);

        facile=findViewById(R.id.facile);
        medio=findViewById(R.id.medio);
        difficile=findViewById(R.id.difficile);

        facile.setOnClickListener(this);
        medio.setOnClickListener(this);
        difficile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(Difficolta.this, PlayerVsCpu.class);
        switch (v.getId()){
            case R.id.facile:
                i.putExtra(CHANCESTUPIDCHOICE,70);
                startActivity(i);
                break;
            case R.id.medio:
                i.putExtra(CHANCESTUPIDCHOICE,35);
                startActivity(i);
                break;
            case R.id.difficile:
                i.putExtra(CHANCESTUPIDCHOICE,0);
                startActivity(i);
                break;
        }
    }
}