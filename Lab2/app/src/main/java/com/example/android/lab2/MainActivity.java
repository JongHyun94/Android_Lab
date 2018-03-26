package com.example.android.lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
RelativeLayout container;
String str; //Store the string from edit text
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference from XML(activity_main.xml)
        setContentView(R.layout.activity_main);

        container = (RelativeLayout) findViewById(R.id.container);
        final EditText entry = findViewById(R.id.entry);
        final Button button1 = (Button) findViewById(R.id.clear);
        final Button button2 = (Button) findViewById(R.id.print);
        final TextView text1 = findViewById(R.id.text1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialization the text " "  nothing
                text1.setText("");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Store the string from edit text to str
                str = entry.getText().toString();
                //Show the String (str) on text1
                text1.setText(str);

            }
        });
    }
}
