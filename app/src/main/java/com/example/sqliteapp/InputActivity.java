package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintAttribute;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText editNama;
    EditText editalamat;
    Button buttonSubmit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        editNama=findViewById(R.id.edit_nama);
        editalamat=findViewById(R.id.edit_alamat);
        databaseHelper=new DatabaseHelper(this);
        buttonSubmit=findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
    }

    private void submitData() {
        if(editNama.getText().toString().length()>0 && editalamat.getText().toString().length()>0 ){
            databaseHelper.addStudent(editNama.getText().toString(),editalamat.getText().toString());
            onBackPressed();
        }

    }
}