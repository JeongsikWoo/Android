package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JeongsikWoo on 2017. 11. 17..
 */

// 계산기 프로그램

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    static float num = 0;
    static String answer = "0";

    final char cal_array[] = {'/', '*', '-', '+', '='};

    public void setDecimal(){

    }

    public void setOperator(View view) {
        TextView textView = (TextView) findViewById(R.id.Cal_text);
        answer += "0";
        textView.setText(answer);
    }

    public void setNumber(View view){
        Button b = (Button)view;
        answer += b.getText().toString();

        TextView textView = (TextView) findViewById(R.id.Cal_text);
        //int number = view.getText();
        //answer += number;
        textView.setText(answer);
    }

    public void setInitialize(View view){
        TextView textView = (TextView) findViewById(R.id.Cal_text);
        answer = "0";
        textView.setText(answer);
    }

    public void getResult(View view){
        TextView textView = (TextView) findViewById(R.id.Cal_text);
        textView.setText(answer);
    }

    public void exit(View view){
        finish();
    }
}
