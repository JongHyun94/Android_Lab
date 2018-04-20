package com.example.android.lab_32;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText text1;
    RadioGroup gender;
    RadioButton radiobtn1;
    RadioButton radiobtn2;
    CheckBox chkbtn1;
    CheckBox chkbtn2;
    Button enrollbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화(참조) 부분
        text1 = findViewById(R.id.entry1);
        gender = findViewById(R.id.gender);
        radiobtn1 = findViewById(R.id.man);
        radiobtn2 = findViewById(R.id.woman);
        chkbtn1 = findViewById(R.id.email);
        chkbtn2 = findViewById(R.id.sms);
        enrollbtn = findViewById(R.id.enroll);

        enrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = text1.getText().toString();
                String gen = "";
                String connect = "";

                //라디오그룹id를 얻어온다
                int radioId = gender.getCheckedRadioButtonId();

                if (radiobtn1.getId() == radioId){//radiobtn1(남)이 선택 유무확인
                    gen = radiobtn1.getText().toString();
                }
                if (radiobtn2.getId() == radioId){//radiobtn2(여)이 선택 유무확인
                    gen = radiobtn2.getText().toString();
                }
                if (chkbtn1.isChecked()) {//chkbtn1(SMS)가 체크됬는지 확인
                    connect += (" " + chkbtn1.getText().toString());
                }
                if (chkbtn2.isChecked()) {//chkbtn2(email)이 체크됬는지 확인
                    connect += (" " + chkbtn2.getText().toString());
                }
                //(새로운)NewActivity클래스를 intent로 지목
                Intent intent = new Intent (getApplicationContext(),NewActivity.class);

                intent.putExtra("name",name);//name이라는 태그에 string name을 넘겨줌
                intent.putExtra("gender",gen);//gender라는 태그에 string gen을 넘겨줌
                intent.putExtra("connect",connect);//connect라는 태그에 string connect를 넘겨줌

                startActivityForResult(intent,1);//Activity 실행
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //원래 선택된 값들 모두 초기화
        text1.setText("");
        gender.clearCheck();
        chkbtn1.setChecked(false);
        chkbtn2.setChecked(false);
    }
}
