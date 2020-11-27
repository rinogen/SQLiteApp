package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageButton buttonAdd;
    ArrayList<Map<String,String>> arrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.ListView);
        buttonAdd=findViewById(R.id.button_add);
        databaseHelper= new DatabaseHelper(this);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,InputActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id=Integer.parseInt(arrayList.get(i).get("id"));
                showConfirm(id);
                return true;
            }
        });
    }

    private void showConfirm(final int id) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah anda yakin untuk menghapus data ini?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(id);
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    private void delete(int id) {
        databaseHelper.delete(id);
        arrayList.clear();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        arrayList=databaseHelper.getAllStudents();
        SimpleAdapter simpleAdapter=new SimpleAdapter(this, arrayList,
                android.R.layout.simple_list_item_2,new String[]{"nama","alamat"},
                new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
}