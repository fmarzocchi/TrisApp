package com.example.tris20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageIniziale extends AppCompatActivity implements View.OnClickListener {
    Button pvscpu,pvsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_iniziale);

        pvscpu=findViewById(R.id.p_vs_cpu);
        pvsp=findViewById(R.id.p_vs_p);

        pvsp.setOnClickListener(this);
        pvscpu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(PageIniziale.this, Difficolta.class);
        Intent j=new Intent(PageIniziale.this, InserireNomi.class);
        switch (v.getId()){
            case R.id.p_vs_cpu:
                startActivity(i);
                break;
            case R.id.p_vs_p:
                startActivity(j);
                break;
        }
    }
}