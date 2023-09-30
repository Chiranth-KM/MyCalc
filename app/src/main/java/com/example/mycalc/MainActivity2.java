package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private Button close,clean;
    private ListView listView;
    calculator_hist hist=null;
    databaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        close=findViewById(R.id.button5);
        clean=findViewById(R.id.button2);
        listView=findViewById(R.id.lst);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        db = new databaseHelper(MainActivity2.this);
        ArrayList<String> displays = new ArrayList<>(); // Use an ArrayList instead of a String array
        ArrayList<calculator_hist> arr = db.getVals();
        for (int i = 0; i < arr.size(); i++) {
            String item = "Expression: " + arr.get(i).getExpression()+ '\n' + "Answer: "+arr.get(i).getAns();
            displays.add(item); // Use add() to add items to the ArrayList
        }
        ArrayAdapter<String> historyAdapter = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1, displays); // Specify the type of ArrayAdapter
        listView.setAdapter(historyAdapter);

        

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteData(hist);
                List<calculator_hist> arr=db.getVals();
                ArrayAdapter histroyAdapter=new ArrayAdapter<calculator_hist>(MainActivity2.this, android.R.layout.simple_list_item_1,arr);
                listView.setAdapter(histroyAdapter);
                Toast.makeText(MainActivity2.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}