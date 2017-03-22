package com.aplicatie.cornel.randomcards;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button buton = null;
    boolean vizitat = false;
    boolean eMomentul = false;
    ImageView image1 = null;
    ImageView image2 = null;
    ImageView image3 = null;
    boolean repeta = false;
    List<Integer> intregi = new ArrayList<>();
    int nrAles;

    int generateRandomNumber() {
        int newV;
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        newV = randomNum;
        while (intregi.contains(newV)) {
            rand = new Random();
            randomNum = rand.nextInt(3);
            newV = randomNum;
        }
        intregi.add(newV);
        return newV;
    }

    protected void initCard(int nr, ImageView image) {
        if (nr == 0) {
            image.setImageResource(R.drawable.carda);
        } else if (nr == 1) {
            image.setImageResource(R.drawable.cardj);
        } else {
            image.setImageResource(R.drawable.cardq);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton = (Button) findViewById(R.id.btnStart);
        image1 = (ImageView) findViewById(R.id.back1);
        image2 = (ImageView) findViewById(R.id.back2);
        image3 = (ImageView) findViewById(R.id.back3);
        InitCards();
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!repeta) {
                if (vizitat) {
                    eMomentul = true;
                    image1.setImageResource(R.drawable.back);
                    image2.setImageResource(R.drawable.back);
                    image3.setImageResource(R.drawable.back);
                    Toast.makeText(MainActivity.this, "Let's see your luck!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Select a card!", Toast.LENGTH_SHORT).show();
                } } else {
                    Toast.makeText(MainActivity.this, "Press try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void InitCards() {
        image1.setImageResource(R.drawable.back);
        image2.setImageResource(R.drawable.back);
        image3.setImageResource(R.drawable.back);
    }

    public void Reinit(View view) {
        InitCards();
        intregi.clear();
        eMomentul = false;
        vizitat = false;
        repeta = false;
    }

    protected void carteOnClick(ImageView image) {
        if (!vizitat) {
            vizitat = true;
            nrAles = generateRandomNumber();
            initCard(nrAles, image);
            intregi.clear();
        } else if (eMomentul){
            int nr = generateRandomNumber();
            initCard(nr, image);
            eMomentul = false;
            repeta = true;
            if (nr == nrAles) {
                Toast.makeText(this, "Today is a lucky day", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "I am sorry, try again!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Press a button", Toast.LENGTH_SHORT).show();
        }
    }

    public void carte1OnClick(View view) {
        carteOnClick(image1);
    }

    public void carte2OnClick(View view) {
        carteOnClick(image2);
    }

    public void carte3OnClick(View view) {
        carteOnClick(image3);
    }
}
