package com.example.android.lab6_1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final static String MYFILE = "mysdfile.txt";

    EditText edit;
    Button WriteBtn;
    Button ClearBtn;
    Button ReadBtn;
    Button ExitBtn;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 변수 초기화
        edit = findViewById(R.id.txtData);
        WriteBtn = findViewById(R.id.btn1);
        ClearBtn = findViewById(R.id.btn2);
        ReadBtn = findViewById(R.id.btn3);
        ExitBtn = findViewById(R.id.btn4);

        // write 버튼 입력시 sd카드의 저장
        WriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edit.getText().toString();

                save(str);

                Toast.makeText(getApplicationContext(), "Done writing SD 'mysdfile.txt'",
                        Toast.LENGTH_LONG).show();

            }
        });

        // Clear 버튼 입력시 초기화
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });

        // Read 버튼 입력시 파일 읽기
        ReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
                Toast.makeText(getApplicationContext(), "Done reading SD 'mysdfile.txt'",
                        Toast.LENGTH_LONG).show();
            }
        });

        // 나가기
        ExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // 데이터 저장 메소드
    public void save(String result) {

        try { // 경로 설정
            File root = Environment.getExternalStorageDirectory();
            if (root.canWrite()) {
                File mydir = new File("/sdcard/mydir");
                mydir.mkdirs();
                File file2 = new File(mydir, MYFILE);
                FileWriter mFW = new FileWriter(file2);
                BufferedWriter out2 = new BufferedWriter(mFW);

                out2.write(result);
                out2.close();
            }
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }

    }

    // 데이터 로드 메소드
    public void load() {

        try { // 경로 설정
            File file = new File("/sdcard/mydir/" + MYFILE);
            InputStream mIS3 = new FileInputStream(file);
            InputStreamReader tmp3 = new InputStreamReader(mIS3);
            BufferedReader reader3 = new BufferedReader(tmp3);
            String TempStr;

            // edit에 추가 해주는 부분
            while ((TempStr = reader3.readLine()) != null) {
                edit.append(TempStr);
            }
            mIS3.close();

        } catch (java.io.FileNotFoundException e) {

        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}