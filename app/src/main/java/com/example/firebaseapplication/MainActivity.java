package com.example.firebaseapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    EditText engWord, vietWord;
    Button btnAdd, btnRecords;
    Spinner spnCategory;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        engWord = (EditText)findViewById(R.id.edtEng);
        vietWord = (EditText)findViewById(R.id.edtViet);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnRecords = (Button)findViewById(R.id.btnRecords);
        spnCategory = (Spinner)findViewById(R.id.spnCategory);
        List<String> list = new ArrayList<>();
        list.add("Nouns");
        list.add("Verb");
        list.add("Adjective");
        list.add("Advern");
        list.add("Prepositions");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spnCategory.setAdapter(adapter);
        myRef = FirebaseDatabase.getInstance().getReference().child("Account");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Words words = new Words();
                words.setEngWord(engWord.getText().toString());
                words.setType(spnCategory.getSelectedItem().toString());
                words.setVietWord(vietWord.getText().toString());
                myRef.push().child("Words").setValue(words);
                Toast.makeText(MainActivity.this, "Inserted success!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}