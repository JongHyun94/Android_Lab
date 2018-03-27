package com.example.android.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public EditText entry1;
public Button print1;
public Button clear1;
public TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Call init() Function
        init();
        //When clear button is clicked ,Operate clearClicked() Function
        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearClicked(view);
            }
        });
        //When print button is clicked,Operate printClicked() Function
        print1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printClicked(view);
            }
        });
    }
    //reference from XML(activity_main.xml)
    public void init(){
        entry1 = findViewById(R.id.entry);
        clear1 = findViewById(R.id.clear);
        print1 = findViewById(R.id.print);
        text2 = findViewById(R.id.text1);
        text2.setText("contents");//initial value
    }
    public void clearClicked(View view) {
        entry1.setText("");//set edit space to Empty
        text2.setText("contents");//default show "contents"
    }
    public void printClicked(View view) {
        String str = "";//Store the string from edit text
        str = entry1.getText().toString();
        text2.setText(str);//Show the String (str) on text1
    }
}
