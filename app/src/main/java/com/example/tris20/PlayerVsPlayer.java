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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerVsPlayer extends AppCompatActivity implements View.OnClickListener {
    Button uno, due, tre, quattro, cinque, sei, sette, otto, nove, reset;
    TextView giocatore1, giocatore2, punteggio1, punteggio2, vittoria,turno;
    ImageView p1shrek,p2donkey,draw;

    Integer punti1=0;
    Integer punti2=0;
    Random random = new Random();
    int turnoG;
    String nomeG1;
    String nomeG2;
    List<Button> bottoni;

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
        bottoni = new ArrayList<>(Arrays.asList(uno,due,tre,quattro,cinque,sei,sette,otto,nove));

        sceltaTurno(); // la cpu sceglie a random chi comincia tra i due player
    }

    @Override
    public void onClick(View v) {
        if (vittoria.getText().toString().equals("")) {
            switch (v.getId()) {
                case R.id.uno:
                    buttonPressed(uno);
                    break;
                case R.id.due:
                    buttonPressed(due);
                    break;
                case R.id.tre:
                    buttonPressed(tre);
                    break;
                case R.id.quattro:
                    buttonPressed(quattro);
                    break;
                case R.id.cinque:
                    buttonPressed(cinque);
                    break;
                case R.id.sei:
                    buttonPressed(sei);
                    break;
                case R.id.sette:
                    buttonPressed(sette);
                    break;
                case R.id.otto:
                    buttonPressed(otto);
                    break;
                case R.id.nove:
                    buttonPressed(nove);
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

    private void buttonPressed(Button button){
        if (button.getText().toString().equals("")) {
            if (turno.getText().toString().equals("Tocca a " + nomeG1)) {
                button.setText("O");
                /* metodo che setta le immagini di shrek,il counter delle vittorie
                 e le scritte di vittoria,pareggio o sconfitta se è avvenuta una di queste 3 condizioni
                 */
                setWinDrawLose();
                cambiaTurno();
            } else {
                button.setText("X");
                setWinDrawLose();
                cambiaTurno();
            }
        }
    }
    private void setWinDrawLose() {

        if (checkWinDrawLose(true,"O")) {
            vittoria.setText("Ha vinto "+nomeG1+"!");
            turno.setText("");
            punti1++;
            p1shrek.setImageResource(R.drawable.shreklovesyou);
            p2donkey.setImageResource(R.drawable.donkeysad);
            p1shrek.setVisibility(View.VISIBLE);
            p2donkey.setVisibility(View.VISIBLE);
        } else if (checkWinDrawLose(true,"X")) {
            vittoria.setText("Ha vinto "+nomeG2+"!");
            turno.setText("");
            punti2++;
            p1shrek.setImageResource(R.drawable.shrekhatesyou);
            p2donkey.setImageResource(R.drawable.donkeyhappy);
            p1shrek.setVisibility(View.VISIBLE);
            p2donkey.setVisibility(View.VISIBLE);
        } else if (checkWinDrawLose(false,"")) {
            vittoria.setText("Pareggio !");
            turno.setText("");
            draw.setImageResource(R.drawable.donkeyshrekboh);
            draw.setVisibility(View.VISIBLE);
        }
    }
    private boolean checkWinDrawLose(boolean checkWinOrLose,String segno) {
        if (checkWinOrLose) { // controlla se il segno preso come argomento ha vinto
            return (controllaVittoria(uno,due,tre,segno) || controllaVittoria(uno,cinque,nove,segno) ||
                    controllaVittoria(uno,quattro,sette,segno) || controllaVittoria(tre,sei,nove,segno) ||
                    controllaVittoria(tre,cinque,sette,segno) || controllaVittoria(sette,otto,nove,segno)
                    || controllaVittoria(quattro,cinque,sei,segno) || controllaVittoria(due,cinque,otto,segno));
        } else { // controlla se le caselle sono tutte piene e quindi se qualcuno ha pareggiato
            return (!(controlloCaselle(uno) || controlloCaselle(due) || controlloCaselle(tre) ||
                    controlloCaselle(quattro) || controlloCaselle(cinque) || controlloCaselle(sei)
                    || controlloCaselle(sette) || controlloCaselle(otto) || controlloCaselle(nove)));
        }
    }
    private boolean controllaVittoria(Button b1,Button b2,Button b3,String segno){
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        String s3=b3.getText().toString();
        return(s1.equals(s2)&&s2.equals(s3)&&s1.equals(segno));
    }
    private boolean controlloCaselle(Button b1){
        String s1=b1.getText().toString();
        return (s1.equals(""));
    }

    private void cambiaTurno(){
        if(turno.getText().equals("Tocca a "+nomeG1)){
            turno.setText("Tocca a "+nomeG2);
        }else if (turno.getText().equals("Tocca a "+nomeG2)){
            turno.setText("Tocca a "+nomeG1);
        }
    }

    private void faiIlReset(){
        bottoni = new ArrayList<>(Arrays.asList(uno,due,tre,quattro,cinque,sei,sette,otto,nove));
        for (int i=0;i<9;i++){
            bottoni.get(i).setText("");
        }
        punteggio1.setText(punti1.toString());
        punteggio2.setText(punti2.toString());
        vittoria.setText("");
        sceltaTurno();
        p1shrek.setVisibility(View.INVISIBLE);
        p2donkey.setVisibility(View.INVISIBLE);
        draw.setVisibility(View.INVISIBLE);
    }
}
