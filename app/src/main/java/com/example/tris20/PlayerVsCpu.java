package com.example.tris20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerVsCpu extends AppCompatActivity implements View.OnClickListener {
    Button uno, due, tre, quattro, cinque, sei, sette, otto, nove, reset;
    TextView punteggio1, punteggio2, vittoria;
    ImageView shrek;

    Integer punti1 = 0;
    Integer punti2 = 0;
    Random random = new Random();
    int chanceStupidChoice;
    List<Button> bottoni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_vs_cpu);

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
        vittoria = findViewById(R.id.vittoria);
        shrek = findViewById(R.id.shrek);

        Intent i = getIntent();
        chanceStupidChoice = i.getIntExtra(Difficolta.CHANCESTUPIDCHOICE, 0);

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

        bottoni = new ArrayList<>(Arrays.asList(uno,due,tre,quattro,cinque,sei,sette,otto,nove));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reset) {
            faiIlReset(); //metodo che pulisce i segni dai bottoni e l'immagine di shrek
        }
        if (vittoria.getText().toString().equals("")) {
            Button b=(Button)v;
            /* Se il button premuto non ha simboli allora l'app ce lo mette e
            controlla se qualcuno ha vinto
             */
            if (b.getText().toString().equals("")) {
                b.setText("O");
                /* metodo che setta le immagini di shrek,il counter delle vittorie
                 e le scritte di vittoria,pareggio o sconfitta se è avvenuta una di queste 3 condizioni
                 */
                setWinDrawLose(); 
                cpuChoice(); //metodo che fa fare la mossa alla cpu
            }
        }
    }

    private void faiIlReset() {
        bottoni = new ArrayList<>(Arrays.asList(uno,due,tre,quattro,cinque,sei,sette,otto,nove));
        for (int i=0;i<9;i++){
            bottoni.get(i).setText("");
        }
        punteggio1.setText(punti1.toString());
        punteggio2.setText(punti2.toString());
        vittoria.setText("");
        shrek.setVisibility(View.INVISIBLE);
    }

    private Button getRandomChoice() { //metodo che fa prendere alla cpu una scelta a caso
        //finche ci sono ancora bottoni nell'arraylist ne prendi uno a caso rimuovendolo dall'arraylist
        while(bottoni.size()>0) {
            int x = random.nextInt(bottoni.size());
            Button b = bottoni.remove(x);
            if (b.getText().toString().equals("")) return b;
        }
        return null;
    }

    //metodo che controlla se qualcuno ha vinto o pareggiato
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

    private void setWinDrawLose() {
        if (checkWinDrawLose(true,"O")) {
            vittoria.setText("Hai vinto!");
            punti1++;
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shreklovesyou);
        } else if (checkWinDrawLose(true,"X")) {
            vittoria.setText("Hai perso");
            punti2++;
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shrekhatesyou);
        } else if (checkWinDrawLose(false,"")) {
            vittoria.setText("Pareggio");
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shreksaynothing);
        }
    }

    private void drawCpuChoice(Button b) { 
            b.setText("X");
            setWinDrawLose();
    }
    
    private boolean controllaVittoria(Button b1,Button b2,Button b3,String segno){
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        String s3=b3.getText().toString();
        return(s1.equals(s2)&&s2.equals(s3)&&s1.equals(segno));
    }
    
    private void cpuChoice() {
        int sceltaIntelligente = random.nextInt(100) + 1;
        if (vittoria.getText().toString().equals("") && (sceltaIntelligente > chanceStupidChoice)) {
                    if (controlloCaselle(uno,due,tre)|| controlloCaselle(sei,nove,tre) || controlloCaselle(cinque,sette,tre)) {
                        drawCpuChoice(tre);
                    }
                    else if (controlloCaselle(uno,tre,due) || controlloCaselle(cinque,otto,due)) {
                        drawCpuChoice(due);
                    }
                    else if (controlloCaselle(due,tre,uno) || controlloCaselle(quattro,sette,uno)
                            || controlloCaselle(cinque,nove,uno)) {
                        drawCpuChoice(uno);
                    }
                    else if (controlloCaselle(quattro,sei,cinque) || controlloCaselle(uno,nove,cinque)
                            || controlloCaselle(due,otto,cinque) || controlloCaselle(tre,sette,cinque)) {
                        drawCpuChoice(cinque);
                    }
                    else if (controlloCaselle(sette,otto,nove) || controlloCaselle(tre,sei,nove) || controlloCaselle(uno,cinque,nove)) {
                        drawCpuChoice(nove);
                    }
                    else if (controlloCaselle(otto,nove,sette) || controlloCaselle(uno,quattro,sette) || controlloCaselle(tre,cinque,sette)) {
                        drawCpuChoice(sette);
                    }
                    else if (controlloCaselle(sette,nove,otto) || controlloCaselle(due,cinque,otto)) {
                        drawCpuChoice(otto);
                    }
                    else if (controlloCaselle(quattro,cinque,sei) || controlloCaselle(tre,nove,sei)) {
                        drawCpuChoice(sei);
                    }
                    else if (controlloCaselle(cinque,sei,quattro) || controlloCaselle(uno,sette,quattro)) {
                        drawCpuChoice(quattro);
                    }
                    else if (controlloCaselle(uno,due,tre) || controlloCaselle(sei,nove,tre) || controlloCaselle(cinque,sette,tre)) {
                        drawCpuChoice(tre);
                    }
                    else if (controlloCaselle(sei,sette,nove) || controlloCaselle(tre,otto,nove)) {
                        drawCpuChoice(nove);
                    }
                    else if (controlloCaselle(uno,otto,sette) || controlloCaselle(nove,quattro,sette)) {
                        drawCpuChoice(sette);
                    }
                    else if(controlloCaselle(tre,quattro,uno) || controlloCaselle(due,sette,uno)) {
                        drawCpuChoice(uno);
                    }
                    else if(controlloCaselle(uno,sei,tre) || controlloCaselle(due,nove,tre)) {
                        drawCpuChoice(tre);
                    }
                    else if(controlloCaselle(due,sei,tre)) {
                        drawCpuChoice(tre);
                    }
                    else if(controlloCaselle(sei,otto,nove)) {
                        drawCpuChoice(nove);
                    }
                    else if(controlloCaselle(otto,quattro,sette)) {
                        drawCpuChoice(sette);
                    }
                    else if(controlloCaselle(quattro,due,uno)) {
                        drawCpuChoice(uno);
                    }
                    else if(controlloCaselle(cinque)) {
                        drawCpuChoice(cinque);
                    }
                    else if(controlloCaselle(uno,nove) || controlloCaselle(tre,sette)) {
                        if(controlloCaselle(due)) {
                            drawCpuChoice(due);
                        }
                        else if(controlloCaselle(sei)) {
                            drawCpuChoice(sei);
                        }
                        else if(controlloCaselle(otto)) {
                            drawCpuChoice(otto);
                        }
                        else if(controlloCaselle(quattro)) {
                            drawCpuChoice(quattro);
                        }
                    }
                    else if(controlloCaselle(uno)) {
                        drawCpuChoice(uno);
                    }
                    else if(controlloCaselle(tre)) {
                        drawCpuChoice(tre);
                    }
                    else if(controlloCaselle(sette)) {
                        drawCpuChoice(sette);
                    }
                    else if(controlloCaselle(nove)) {
                        drawCpuChoice(nove);
                    }
                    else {
                        Button b=getRandomChoice();
                        drawCpuChoice(b);
                    }
        }else if (vittoria.getText().toString().equals("")){
            Button b=getRandomChoice();//prende un bottone a caso da questo metodo (tra quelli liberi),altrimenti ritorna null
            drawCpuChoice(b); //segna la sua "X" su quel bottone che ha scelto
        }
    }

    private boolean controlloCaselle(Button b1,Button b2,Button b3){ //metodi per semplificare gli equals da fare
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        String s3=b3.getText().toString();
        if(s1.equals("X")&&s1.equals(s2)) {
            return (s1.equals(s2)&& s1.equals("X") && s3.equals(""));
        }else {
            return (s1.equals(s2)&& s1.equals("O") && s3.equals(""));
        }
    }
    private boolean controlloCaselle(Button b1){
        String s1=b1.getText().toString();
        return (s1.equals(""));
    }
    private boolean controlloCaselle(Button b1,Button b2) {
        String s1 = b1.getText().toString();
        String s2 = b2.getText().toString();
        return (s1.equals(s2));
    }
}