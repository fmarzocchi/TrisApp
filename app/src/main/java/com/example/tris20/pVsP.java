package com.example.tris20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class pVsP extends AppCompatActivity implements View.OnClickListener {
    Button uno, due, tre, quattro, cinque, sei, sette, otto, nove, reset;
    TextView giocatore1, giocatore2, punteggio1, punteggio2, vittoria,turno;
    ImageView p1shrek,p2donkey,draw;

    Integer punti1=0;
    Integer punti2=0;
    Random random = new Random();
    int turnoG;
    String nomeG1;
    String nomeG2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_vs_p);

        uno = findViewById(R.id.uno);
        due = findViewById(R.id.due);
        tre = findViewById(R.id.tre);
        quattro = findViewById(R.id.quattro);
        cinque = findViewById(R.id.cinque);
        sei = findViewById(R.id.sei);
        sette = findViewById(R.id.sette);
        otto = findViewById(R.id.otto);
        nove = findViewById(R.id.nove);
        reset = findViewById(R.id.reset);
        punteggio1 = findViewById(R.id.punteggio1);
        punteggio2 = findViewById(R.id.punteggio2);
        vittoria=findViewById(R.id.vittoria);
        giocatore1=findViewById(R.id.giocatore1);
        giocatore2=findViewById(R.id.giocatore2);
        turno=findViewById(R.id.turno);
        p1shrek=findViewById(R.id.p1shrek);
        p2donkey=findViewById(R.id.p2donkey);
        draw=findViewById(R.id.draw);

        uno.setOnClickListener(this);
        due.setOnClickListener(this);
        tre.setOnClickListener(this);
        quattro.setOnClickListener(this);
        cinque.setOnClickListener(this);
        sei.setOnClickListener(this);
        sette.setOnClickListener(this);
        otto.setOnClickListener(this);
        nove.setOnClickListener(this);
        reset.setOnClickListener(this);

        Intent i=getIntent();
        nomeG1=i.getStringExtra("nomeG1");
        nomeG2=i.getStringExtra("nomeG2");

        giocatore1.setText(nomeG1);
        giocatore2.setText(nomeG2);

        sceltaTurno();
    }

    @Override
    public void onClick(View v) {
        if (vittoria.getText().toString().equals("")) {
            switch (v.getId()) {
                case R.id.uno:
                    if (uno.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            uno.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            uno.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
                case R.id.due:
                    if (due.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            due.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            due.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }

                    }
                    break;
                case R.id.tre:
                    if (tre.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            tre.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            tre.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
                case R.id.quattro:
                    if (quattro.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            quattro.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            quattro.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }

                    }
                    break;
                case R.id.cinque:
                    if (cinque.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            cinque.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            cinque.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
                case R.id.sei:
                    if (sei.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            sei.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            sei.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }

                    }
                    break;
                case R.id.sette:
                    if (sette.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            sette.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            sette.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
                case R.id.otto:
                    if (otto.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            otto.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            otto.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
                case R.id.nove:
                    if (nove.getText().toString().equals("")) {
                        if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                            nove.setText("O");
                            chiHaVinto();
                            cambiaTurno();
                        } else {
                            nove.setText("X");
                            chiHaVinto();
                            cambiaTurno();
                        }
                    }
                    break;
            }
        }
        if(v.getId()==R.id.reset){
            faiIlReset();
        }
    }
    private void sceltaTurno(){
        turnoG = random.nextInt(2);
        switch (turnoG){
            case 0:
                turno.setText("Tocca a "+nomeG1);
                break;
            case 1:
                turno.setText("Tocca a "+nomeG2);
                break;
        }
    }
    private void cambiaTurno(){
        if(turno.getText().equals("Tocca a "+nomeG1)){
            turno.setText("Tocca a "+nomeG2);
        }else if (turno.getText().equals("Tocca a "+nomeG2)){
            turno.setText("Tocca a "+nomeG1);
        }
    }

    private void faiIlReset(){
        uno.setText("");
        due.setText("");
        tre.setText("");
        quattro.setText("");
        cinque.setText("");
        sei.setText("");
        sette.setText("");
        otto.setText("");
        nove.setText("");

        punteggio1.setText(punti1.toString());
        punteggio2.setText(punti2.toString());
        vittoria.setText("");
        sceltaTurno();
        p1shrek.setVisibility(View.INVISIBLE);
        p2donkey.setVisibility(View.INVISIBLE);
        draw.setVisibility(View.INVISIBLE);
    }
    private boolean win(){
        return false;
    }
    private boolean controlloSenzaSenso(String x, int c) {
        String one = uno.getText().toString();
        String two = due.getText().toString();
        String three = tre.getText().toString();
        String four = quattro.getText().toString();
        String five = cinque.getText().toString();
        String six = sei.getText().toString();
        String seven = sette.getText().toString();
        String eight = otto.getText().toString();
        String nine = nove.getText().toString();
        if (c != 3) {
            return ((one.equals(x) && two.equals(x) && three.equals(x)) || (one.equals(x) && five.equals(x) && nine.equals(x)) ||
                    (one.equals(x) && four.equals(x) && seven.equals(x)) || (three.equals(x) && six.equals(x) && nine.equals(x)) ||
                    (three.equals(x) && five.equals(x) && seven.equals(x)) || (seven.equals(x) && eight.equals(x) && nine.equals(x))
                    || (four.equals(x) && five.equals(x) && six.equals(x)) || (two.equals(x) && five.equals(x) && eight.equals(x)));
        } else {
            return (!(one.equals(x) || two.equals(x) || three.equals(x) || four.equals(x) || five.equals(x) || six.equals(x)
                    || seven.equals(x) || eight.equals(x) || nine.equals(x)));
        }
    }
    private void chiHaVinto() {

        if (controlloSenzaSenso("O", 1)) {
            vittoria.setText("Ha vinto "+nomeG1+"!");
            turno.setText("");
            punti1++;
            p1shrek.setImageResource(R.drawable.shreklovesyou);
            p2donkey.setImageResource(R.drawable.donkeysad);
            p1shrek.setVisibility(View.VISIBLE);
            p2donkey.setVisibility(View.VISIBLE);
        } else if (controlloSenzaSenso("X", 2)) {
            vittoria.setText("Ha vinto "+nomeG2+"!");
            turno.setText("");
            punti2++;
            p1shrek.setImageResource(R.drawable.shrekhatesyou);
            p2donkey.setImageResource(R.drawable.donkeyhappy);
            p1shrek.setVisibility(View.VISIBLE);
            p2donkey.setVisibility(View.VISIBLE);
        } else if (controlloSenzaSenso("", 3)) {
            vittoria.setText("Pareggio !");
            turno.setText("");
            draw.setImageResource(R.drawable.donkeyshrekboh);
            draw.setVisibility(View.VISIBLE);
        }
    }
}