package com.example.android.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int num = 0;  //Confirm Convert
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference XML(activity_main_xml)
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);

        Button button = (Button) findViewById(R.id.button);
        final ImageView imageView = findViewById(R.id.imageView);
        final ImageView imageView2 = findViewById(R.id.imageView2);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                num++; // When button click, num stores +1 on num
                changeImage(); //Function to confirm num's status
            }
            private void changeImage () {
                //If num is odd, First image is visible and Second image is invisible
                if(num%2 == 1) {
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);

                }
                //If num is even, First image is invisible and Second image is visible
                else if (num%2 == 0) {
                    imageView.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}
