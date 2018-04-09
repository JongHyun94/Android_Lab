package com.example.android.lab21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public EditText Name;
    public EditText Age;
    public Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.name);//activity_main.xml에서 id: name을 지정해준다.
        Age = findViewById(R.id.age);//activity_main.xml에서 id: age를 지정해준다.
        button1 = findViewById(R.id.button1);//activity_main.xml에서 id: button1(추가)을 지정해준다.

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();//EditText에 적힌 내용(이름)을 string name에 저장
                String age = Age.getText().toString();//EditText에 적힌 내용(나이)을 string age에 저장
                Intent intent = new Intent (getApplicationContext(),NewActivity.class);//(새로운)NewActivity클래스를 intent로 지목

                intent.putExtra("name",name);//name이라는 태그에 string name을 넘겨줌
                intent.putExtra("age",age);//age라는 태그에 string age를 넘겨줌

                startActivity(intent);//Activity 실행
            }
        });
    }

}
