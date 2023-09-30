package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.*;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button one,two,three,four,five,six,seven,eight,nine,zero,zero2;
    private Button ac,brac1,brac2,div,multiply,add,sub,dot,equal,history;
    private TextView ques,res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextViews
        ques=findViewById(R.id.textView6);
        res=findViewById(R.id.textView5);

        // numbers
        one=findViewById(R.id.button63);
        two=findViewById(R.id.button42);
        three=findViewById(R.id.button43);
        four=findViewById(R.id.button37);
        five=findViewById(R.id.button38);
        six=findViewById(R.id.button39);
        seven=findViewById(R.id.button33);
        eight=findViewById(R.id.button34);
        nine=findViewById(R.id.button35);
        zero=findViewById(R.id.button);
        zero2=findViewById(R.id.button41);

        // others
        ac=findViewById(R.id.button29);
        brac1=findViewById(R.id.button30);
        brac2=findViewById(R.id.button31);
        div=findViewById(R.id.button32);
        multiply=findViewById(R.id.button36);
        sub=findViewById(R.id.button40);
        add=findViewById(R.id.button44);
        dot=findViewById(R.id.button65);
        equal=findViewById(R.id.button64);
        history=findViewById(R.id.button5);


        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    ques.setText("");
                    res.setText("0");
                }
            }
        });


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculator_hist history = null;

                try
                {
                    history=new calculator_hist(ques.getText().toString(),res.getText().toString());
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Some Error occoured", Toast.LENGTH_SHORT).show();
                }

                databaseHelper datahelp=new databaseHelper(MainActivity.this);
                boolean b = datahelp.addVal(history);

                ques.setText(res.getText());
                res.setText("");
            }
        });


        try {
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No rec", Toast.LENGTH_SHORT).show();
        }


    }
    public void onclick(View view)
    {
        Button button=(Button) view;
        String buttonText=button.getText().toString();
        String textBox=ques.getText().toString();

        if (buttonText.equals("C"))
        {
            if(textBox.length()>1)
            {
                textBox=textBox.substring(0,textBox.length()-1);
            }
            else
            {
                textBox="0";
                res.setText("");
            }
        }
        else {
            if (textBox.equals("0"))
            {
                textBox="";
            }
            textBox=textBox+buttonText;
        }
        ques.setText(textBox);
        String finalans=ans(textBox);
        if(!finalans.equals("0"))
        {
            res.setText(finalans);
        }
    }

    String ans(String finalques)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable script= context.initStandardObjects();
            String finalres= context.evaluateString(script,finalques,"Javascript",1,null).toString();
            if(finalres.endsWith(".0"))
            {
                finalres=finalres.replace(".0","");
            }
            return finalres;
        }
        catch (Exception e)
        {
            return "0";
        }
    }
}