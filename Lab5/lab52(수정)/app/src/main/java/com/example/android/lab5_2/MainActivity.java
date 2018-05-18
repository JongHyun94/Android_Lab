package com.example.android.lab5_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText num;
    Button btn;
    TextView operate;
    TextView result;

    String number; // 입력받은 숫자 스트링을 저장하기 위한 변수
    int factNum;   // 입력받은 숫자 스트링을 int형으로 고치기 위한 변수
    String temp;   // 단계별 "n * n-1 * n-2 ... * 1" 와 같이 나오게 하기 위한 스트링 변수

    String value;  // 따로 팩토리얼 함수 만드는 경우
    int fact;      // AsyncTask 내부에서 사용하는 경우

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 초기화 부분
        setContentView(R.layout.activity_main);
        num = findViewById(R.id.number);
        btn = findViewById(R.id.button);
        operate = findViewById(R.id.operate);
        result = findViewById(R.id.result);

        // 버튼 입력시
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Facto().execute(); // Facto (AsyncTask) 시작
            }
        });
    }

    private class Facto extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            number = num.getText().toString(); // 쓰여진 스트링을 가져온다
            factNum = Integer.valueOf(number); // 스트링을 int형으로 변환
            // value = String.valueOf(Factorial(factNum)); // 1. 팩토리얼 함수를 이용하는 경우
            fact = 1;  // 2.  AsyncTask 내부에서 계산하는 경우 초기화를 해준다
            temp = ""; // 처음 초기화
        }
        @Override
        protected Void doInBackground(Void... params) {
            for(int i=factNum; i > 0; i--){
                try{Thread.sleep(500); // 500ms 마다
                    fact = fact * i; // 2. AsyncTask 내부에서 계산하는 경우
                    publishProgress(i); // onProgressUpdate로 넘겨준다
                }catch (Exception e){}
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            // 처음 출력 "n"
            if(number.equals(Integer.toString(values[0].intValue()))) {
                operate.setText(number);
            }
            // 이후 출력 "n * n-1 * n-2 .... * 1"
            else {
                temp = temp + (" * " + Integer.toString(values[0].intValue()));
                operate.setText(number + temp);
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            // 결과값 표기
            operate.setText(number + temp);       // 전체 결과값의 과정 출력
            result.setText(String.valueOf(fact)); // 2. AsyncTask 내부 계산 해준경우
            // result.setText(value);             // 1. 팩토리얼 함수를 이용하는 경우
        }
    }
    /*
    // 팩토리얼 함수
    public int Factorial(int fact) {
        for(int i =fact-1; i >1 ; i--) {
            fact = fact * i;
        }
        return fact;
    }
    */
}
