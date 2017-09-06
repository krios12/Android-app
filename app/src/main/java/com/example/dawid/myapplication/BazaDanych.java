package com.example.dawid.myapplication;

import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BazaDanych extends AppCompatActivity {

    DatabaseHelper db;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    EditText editModel;
    EditText editMarka;
    EditText editId;
    boolean czyDziala;

    public void clean1(View v)
    {
        editModel.setText("");
    }
    public void clean2(View v)
    {
        editMarka.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baza);

        db= new DatabaseHelper(this);

        button= (Button)findViewById(R.id.button);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        button4= (Button)findViewById(R.id.button4);
        editModel=(EditText)findViewById(R.id.editText);
        editMarka=(EditText)findViewById(R.id.editText2);
        editId=(EditText)findViewById(R.id.editText3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                czyDziala=db.wstawDane(editModel.getText().toString(), editMarka.getText().toString());
                if(czyDziala)
                {
                    Toast.makeText(BazaDanych.this,"Udalo sie", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(BazaDanych.this,"Niestety nie udalo sie", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCursor kursor=db.pobierzdane();
                if(kursor.getCount()>0)
                {
                    StringBuffer sb= new StringBuffer();
                    while(kursor.moveToNext())
                    {
                        sb.append("ID: "+ kursor.getString(0)+ "\n");
                        sb.append("Model: "+ kursor.getString(1)+ "\n");
                        sb.append("Marka: "+ kursor.getString(2)+ "\n");
                    }
                    getMessage("rekord",sb.toString());
                }
                else
                {
                    getMessage("brak", "Niestety brak rekordu");
                }

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                czyDziala=db.aktualizuj(editId.getText().toString(),editModel.getText().toString(), editMarka.getText().toString());
                if(czyDziala)
                {
                    Toast.makeText(BazaDanych.this,"Udalo sie", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(BazaDanych.this,"Niestety nie udalo sie", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.usun(editId.getText().toString()))
                {
                    Toast.makeText(BazaDanych.this,"Usunieto wybrany rekord", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(BazaDanych.this,"Niestety nie udalo sie usunac wybranego rekordu", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
///////////////////////////// koniec onCreate


    public void getMessage(String tytul, String wiadomosc)
    {
        AlertDialog.Builder budowniczy = new AlertDialog.Builder(this);
        budowniczy.setCancelable(true);
        budowniczy.setMessage(wiadomosc);
        budowniczy.setTitle(tytul);
        budowniczy.show();
    }
}