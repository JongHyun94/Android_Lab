package com.example.android.lab21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    public Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newlayout);

        button2 = findViewById(R.id.button2);//newlayout.xml에 button2(닫기)를 지정해줌
        Intent passedIntent = getIntent();//MainActivity에서 지목한 intent를 가져온다.
        if(passedIntent!=null){//intent 지목 받은게 있으면
            String loginName = passedIntent.getStringExtra("name");//"name"이라는 태그에서 값을 가져와 loginName에 저장
            String loginAge = passedIntent.getStringExtra("age");//"age"라는 태그에서 값을 가져와 loginAge에 저장
            Toast.makeText(getApplicationContext(),"Student Info:"+ loginName
                    +", "+ loginAge,Toast.LENGTH_LONG).show();//토스트 메시지를 보여줌 ("Student Info: 이름, 나이")
        }

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//Activity 종료
            }
        });
    }
}