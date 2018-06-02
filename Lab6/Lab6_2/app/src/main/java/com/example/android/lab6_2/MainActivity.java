package com.example.android.lab6_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameIn;
    EditText numberIn;

    Button Save;
    Button Clear;
    Button Load;

    String name;
    String number;

    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        nameIn = findViewById(R.id.nameEdit);
        numberIn = findViewById(R.id.schEdit);

        Save = findViewById(R.id.saveBtn);
        Load = findViewById(R.id.callBtn);
        Clear = findViewById(R.id.initBtn);

        // 저장
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameIn.getText().toString();
                number= numberIn.getText().toString();
                sharedPrefernces();
            }
        });

        // 불러오기
        Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applySharedPreference();

            }
        });

        // 화면 초기화
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameIn.setText("");    // 초기화
                numberIn.setText("");  // 초기화
            }
        });

    }
    public void sharedPrefernces() {
        sh_Pref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        toEdit.putString("Username", name); // Username에 name 을 넣어준다.
        toEdit.putString("Number", number); // Number에 number를 넣어준다.
        toEdit.commit(); // 저장
    }

    public void applySharedPreference() {
        sh_Pref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
        if (sh_Pref != null && sh_Pref.contains("Username")) {
            String name = sh_Pref.getString("Username", "noname"); // Username을 가져온다.
            String pass = sh_Pref.getString("Number", "0000"); // Number을 가져온다.
            nameIn.setText(name);    // nameIn에 보여준다.
            numberIn.setText(pass);  // numberIn에 보여준다.
        }
    }
}
