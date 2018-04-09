package com.example.android.lab2_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText URL1;
    public Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL1 = findViewById(R.id.url1);//xml에서 id url1을 URL1에 지정한다
        button1 = findViewById(R.id.button1);//xml에서 id button1을 button1에 지정한다

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = URL1.getText().toString();//URL1에서 String URL값에 넣어준다
                //(새로운)NewActivity클래스를 intent로 지목
                Intent intent1 = new Intent(getApplicationContext(),NewActivity.class);

                intent1.putExtra("url",URL);//url이라는 이름으로 URL을 넘겨준다

                startActivity(intent1);//activity 시작
            }
        });
    }
}
