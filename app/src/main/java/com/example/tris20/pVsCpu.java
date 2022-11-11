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

public class pVsCpu extends AppCompatActivity implements View.OnClickListener {
    Button uno, due, tre, quattro, cinque, sei, sette, otto, nove, reset;
    TextView punteggio1, punteggio2, vittoria;
    ImageView shrek;

    Integer punti1 = 0;
    Integer punti2 = 0;
    Random random = new Random();
    int mode;

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
        mode = i.getIntExtra("livello", 0);

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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reset) {
            faiIlReset(); //metodo che pulisce i segni dai bottoni e l'immagine di shrek
            return;
        }
        if (vittoria.getText().toString().equals("")) {
            Button b=(Button)v;
            if (b.getText().toString().equals("")) {
                b.setText("O");
                chiHaVinto(); //metodo che controlla se qualcuno ha vinto
                sonoIntelligente(); //metodo che randomicamente sceglie se la cpu farà una mossa intelligente o no
                return;
            }
        }
    }

    private void faiIlReset() {
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
        shrek.setVisibility(View.INVISIBLE);
    }

    private Button getSceltaOppo() { //metodo che fa prendere alla cpu una scelta a caso
        //finche ci sono ancora bottoni nell'arraylist ne prendi uno a caso rimuovendolo dall'arraylist
        List<Button> bottoni=new ArrayList<>(Arrays.asList(uno,due,tre,quattro,cinque,sei,sette,otto,nove));
        while(bottoni.size()>0) {
            int x = random.nextInt(bottoni.size());
            Button b = bottoni.remove(x);
            if (b.getText().toString().equals("")) return b;
        }
        return null;
    }

    private boolean controlloSenzaSenso(int c,String x) { //metodo che controlla se qualcuno ha vinto o pareggiato
        if (c != 3) { //controlla se qualcuno ha vinto
            return (controllaVittoria(uno,due,tre,x) || controllaVittoria(uno,cinque,nove,x) ||
                    controllaVittoria(uno,quattro,sette,x) || controllaVittoria(tre,sei,nove,x) ||
                    controllaVittoria(tre,cinque,sette,x) || controllaVittoria(sette,otto,nove,x)
                    || controllaVittoria(quattro,cinque,sei,x) || controllaVittoria(due,cinque,otto,x));
        } else { // controlla se le caselle sono tutte piene e quindi se qualcuno ha pareggiato
            return (!(controlla(uno) || controlla(due) || controlla(tre) || controlla(quattro) || controlla(cinque) || controlla(sei)
                    || controlla(sette) || controlla(otto) || controlla(nove)));
        }
    }

    private void chiHaVinto() {
        if (controlloSenzaSenso(1,"O")) {
            vittoria.setText("Hai vinto!");
            punti1++;
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shreklovesyou);
        } else if (controlloSenzaSenso(2,"X")) {
            vittoria.setText("Hai perso");
            punti2++;
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shrekhatesyou);
        } else if (controlloSenzaSenso(3,"Z")) {
            vittoria.setText("Pareggio");
            shrek.setVisibility(View.VISIBLE);
            shrek.setImageResource(R.drawable.shreksaynothing);
        }
    }

    private void turnoOppo() {
        turnoOppo(null);
    }

    private void turnoOppo(Button b) { //metodo che fa segnare la X alla cpu in base alla scelta/bottone preso dal metodo getSceltaOppo
        if (vittoria.getText().toString().equals("")) {
            if (b==null||!b.getText().toString().equals("")) {
                b=getSceltaOppo();
            }
            b.setText("X");
            chiHaVinto();
            return;
        }
    }

    public void sonoIntelligente() { //metodo per stabilire se la cpu farà una mossa intelligente o no
        int intelligenza = random.nextInt(100) + 1;
        if (intelligenza < mode) {//se va a finire qui è intelligente
            stoPensando();
        } else {//se va a finire qui è stupido e farà una mossa a caso
            getSceltaOppo(); //se i bottoni non sono tutte pieni prende un bottone a caso da questo metodo (tra quelli liberi),altrimenti ritorna null
            turnoOppo(); //segna la sua "X" su quel bottone che ha scelto
        }
    }
    private boolean controlla(Button b1,Button b2,Button b3){ //metodi per semplificare gli equals da fare
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        String s3=b3.getText().toString();
        if(s1.equals("X")&&s1.equals(s2)) {
            Log.i("Controllo","sto provando a fare tris");
            return (s1.equals(s2)&& s1.equals("X") && s3.equals(""));
        }else {
            Log.i("Controllo","sto provando a bloccare");
            return (s1.equals(s2)&& s1.equals("O") && s3.equals(""));
        }
    }
    private boolean controlla(Button b1){
        String s1=b1.getText().toString();
        return (s1.equals(""));
    }
    private boolean controlla(Button b1,Button b2){
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        return (s1.equals(s2));
    }
    private boolean controllaVittoria(Button b1,Button b2,Button b3,String x){
        String s1=b1.getText().toString();
        String s2=b2.getText().toString();
        String s3=b3.getText().toString();
        return(s1.equals(s2)&&s2.equals(s3)&&s1.equals(x));
    }

    private void stoPensando() {//metodo che è essenzialmente il cervello della cpu
        if (vittoria.getText().toString().equals("")) {
                    if (controlla(uno,due,tre)|| controlla(sei,nove,tre) || controlla(cinque,sette,tre)) {
                        turnoOppo(tre);
                        return;
                    }
                    else if (controlla(uno,tre,due) || controlla(cinque,otto,due)) {
                        turnoOppo(due);
                        return;
                    }
                    else if (controlla(due,tre,uno) || controlla(quattro,sette,uno)
                            || controlla(cinque,nove,uno)) {
                        turnoOppo(uno);
                        return;
                    }
                    else if (controlla(quattro,sei,cinque) || controlla(uno,nove,cinque)
                            || controlla(due,otto,cinque) || controlla(tre,sette,cinque)) {
                        turnoOppo(cinque);
                        return;
                    }
                    else if (controlla(sette,otto,nove) || controlla(tre,sei,nove) || controlla(uno,cinque,nove)) {
                        turnoOppo(nove);
                        return;
                    }
                    else if (controlla(otto,nove,sette) || controlla(uno,quattro,sette) || controlla(tre,cinque,sette)) {
                        turnoOppo(sette);
                        return;
                    }
                    else if (controlla(sette,nove,otto) || controlla(due,cinque,otto)) {
                        turnoOppo(otto);
                        return;
                    }
                    else if (controlla(quattro,cinque,sei) || controlla(tre,nove,sei)) {
                        turnoOppo(sei);
                        return;
                    }
                    else if (controlla(cinque,sei,quattro) || controlla(uno,sette,quattro)) {
                        turnoOppo(quattro);
                        return;
                    }
                    else if (controlla(uno,due,tre) || controlla(sei,nove,tre) || controlla(cinque,sette,tre)) {
                        turnoOppo(tre);
                        return;
                    }
                    else if (controlla(sei,sette,nove) || controlla(tre,otto,nove)) {
                        turnoOppo(nove);
                        return;
                    }
                    else if (controlla(uno,otto,sette) || controlla(nove,quattro,sette)) {
                        turnoOppo(sette);
                        return;
                    }
                    else if(controlla(tre,quattro,uno) || controlla(due,sette,uno)) {
                        turnoOppo(uno);
                        return;
                    }
                    else if(controlla(uno,sei,tre) || controlla(due,nove,tre)) {
                        turnoOppo(tre);
                        return;
                    }
                    else if(controlla(due,sei,tre)) {
                        turnoOppo(tre);
                        return;
                    }
                    else if(controlla(sei,otto,nove)) {
                        turnoOppo(nove);
                        return;
                    }
                    else if(controlla(otto,quattro,sette)) {
                        turnoOppo(sette);
                        return;
                    }
                    else if(controlla(quattro,due,uno)) {
                        turnoOppo(uno);
                        return;
                    }
                    else if(controlla(cinque)) {
                        turnoOppo(cinque);
                        return;
                    }
                    else if(controlla(uno,nove) || controlla(tre,sette)) {
                        if(controlla(due)) {
                            turnoOppo(due);
                            return;
                        }
                        else if(controlla(sei)) {
                            turnoOppo(sei);
                            return;
                        }
                        else if(controlla(otto)) {
                            turnoOppo(otto);
                            return;
                        }
                        else if(controlla(quattro)) {
                            turnoOppo(quattro);
                            return;
                        }
                    }
                    else if(controlla(uno)) {
                        turnoOppo(uno);
                        return;
                    }
                    else if(controlla(tre)) {
                        turnoOppo(tre);
                        return;
                    }
                    else if(controlla(sette)) {
                        turnoOppo(sette);
                        return;
                    }
                    else if(controlla(nove)) {
                        turnoOppo(nove);
                        return;
                    }
                    else {
                        Button b=getSceltaOppo();
                        turnoOppo(b);
                        return;
                    }
            }
        }
    }