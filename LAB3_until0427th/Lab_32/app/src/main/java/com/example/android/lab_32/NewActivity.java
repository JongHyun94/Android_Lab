package com.example.android.lab_32;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    TextView text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);

        //초기화(참조) 부분
        text1 = findViewById(R.id.textview1);
        text2 = findViewById(R.id.textview2);
        text3 = findViewById(R.id.textview3);

        //지목된(intent)을 가져옴
        Intent passedIntent = getIntent();
        if(passedIntent!=null) {//intent 지목 받은게 있으면
            //"name"이라는 태그에서 값을 가져와 setName에 저장
            String setName = passedIntent.getStringExtra("name");
            //"gender"이라는 태그에서 값을 가져와 setGender에 저장
            String setGender = passedIntent.getStringExtra("gender");
            //"connect"이라는 태그에서 값을 가져와 setConnect에 저장
            String setConnect = passedIntent.getStringExtra("connect");

            //출력해준다
            text1.setText(setName);
            text2.setText(setGender);
            text3.setText(setConnect);
        }

        Button button0 = findViewById(R.id.button2);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"돌아가기버튼이 눌렸어요",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}