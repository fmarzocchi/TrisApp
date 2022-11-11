package com.example.tris20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inserireNomi extends AppCompatActivity implements View.OnClickListener {
    EditText gio1,gio2;
    Button gioca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserire_nomi);

       gio1=findViewById(R.id.nomeg1);
       gio2=findViewById(R.id.nomeg2);
       gioca=findViewById(R.id.gioca);

       gioca.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(inserireNomi.this,pVsP.class);
        String g1=gio1.getText().toString();
        String g2=gio2.getText().toString();
        if(v.getId()==(R.id.gioca)){
            if(gio1.getText().toString().equals("")&&gio2.getText().toString().equals("") ){
                Toast.makeText(getApplicationContext(),"Inserire il nome del Giocatore 1 e del Giocatore 2",Toast.LENGTH_SHORT).show();
            }else if(gio1.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Inserire il nome del Giocatore 1 ",Toast.LENGTH_SHORT).show();
            }else if(gio2.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"Inserire il nome del Giocatore 2 ",Toast.LENGTH_SHORT).show();
            }else {
                i.putExtra("nomeG1", g1);
                i.putExtra("nomeG2", g2);
                startActivity(i);
            }
        }
    }
}